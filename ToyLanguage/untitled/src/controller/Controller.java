package controller;

import exceptions.*;
import model.state.PrgState;
import model.statements.IStatement;
import model.adt.MyIStack;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import repository.*;

public class Controller implements IController {

    IRepository repository;

    public Controller(IRepository repo) {
        this.repository = repo;
    }

    @Override
    public PrgState oneStep(PrgState state) throws ADTException, StatementException, IOException, ExpressionException {
        MyIStack<IStatement> stk = state.getExeStack();
        if (stk.isEmpty()) {
            throw new EmptyStackException("Empty stack");
        }
        IStatement currentStatement = stk.pop();
        return currentStatement.execute(state);
    }

    @Override
    public void allStep() throws ADTException, StatementException, IOException, ExpressionException, RepoException {
        PrgState program = this.repository.getCurrentPrg();
        System.out.println(program);
        while(!program.getExeStack().isEmpty()) {
            this.oneStep(program);
            repository.logPrgStateExec();
            System.out.println(program);
        }
        this.repository.deletePrgState(program);
    }

    @Override
    public void add(IStatement statement) {
        this.repository.addPrgState(new PrgState(statement));
    }
}
