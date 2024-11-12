package model.statements;

import exceptions.ADTException;
import exceptions.StatementException;
import model.state.PrgState;
import model.types.BoolType;
import model.types.IType;
import model.types.IntType;
import model.value.BoolValue;
import model.value.IValue;
import model.value.IntValue;
import model.value.StringValue;

public class VarDeclStatement implements IStatement{
    private String varName;
    private IType type;

    public VarDeclStatement(String v, IType t)
    {
        this.varName = v;
        this.type = t;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, ADTException
    {
        if(state.getSymTable().contains(varName))
            throw new StatementException("Variable" + this.varName + " already exists.");
        IValue defaultValue;
        if (type.equals(new IntType()))
            defaultValue = new IntValue(0);
        else if (type.equals(new BoolType()))
            defaultValue = new BoolValue(false); // default value here
        else
            defaultValue = new StringValue("");
        state.getSymTable().insert(varName, defaultValue);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new VarDeclStatement(new String(this.varName), this.type);
    }

    public String toString()
    {
        return this.varName + " = " + this.type.toString();
    }

    //public IStatement defaultValue
}