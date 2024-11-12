package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expresions.IExpression;
import model.state.PrgState;
import model.types.*;
import model.value.*;

import java.io.IOException;

public class CloseFileStatement implements IStatement {
    IExpression expression;
    public CloseFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, ADTException, ExpressionException, IOException {
        IValue value = this.expression.eval(state.getSymTable());
        if(!(value.getType().equals(new StringType()))) {
            throw new StatementException("The value is not a string");
        }
        if(!(state.getFileTable().contains((StringValue)value))) {
            throw new StatementException("The file is already closed");
        }

        state.getFileTable().getValue((StringValue)value).close();
        state.getFileTable().remove((StringValue)value);

        return state;
    }

    @Override
    public IStatement deepCopy() {
        return null;
    }

    @Override
    public String toString() {
        return "CloseFileStatement(" + expression + ")";
    }
}
