package controller;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.RepoException;
import exceptions.StatementException;
import model.state.PrgState;

import java.io.IOException;

import model.statements.IStatement;
import repository.Repository;

public interface IController {
    PrgState oneStep(PrgState state) throws ADTException, StatementException, IOException, ExpressionException;
    void allStep() throws ADTException, StatementException, IOException, ExpressionException, RepoException;
    void add(IStatement statement);
}
