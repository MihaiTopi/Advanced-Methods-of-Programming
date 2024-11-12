package model.state;
import java.io.BufferedReader;

import model.adt.*;
import model.statements.IStatement;
import model.value.IValue;
import model.value.StringValue;

public class PrgState {
    private MyIDictionary<String, IValue> symTable;

    public MyIDictionary<String, IValue> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, IValue> symTable) {
        this.symTable = symTable;
    }

    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    private MyIStack<IStatement> exeStack;
    private MyIList<String> output;
    private IStatement initialState;

    private MyIDictionary<StringValue, BufferedReader> fileTable;

    //OpenFile and CloseFile here

    public PrgState(MyIDictionary<String, IValue> symTable ,
                    MyIStack<IStatement> exeStack,
                    MyIList<String> output ,
                    IStatement initialState,
                    MyIDictionary<StringValue,
                            BufferedReader> fileTable) {
        this.symTable = symTable;
        this.exeStack = exeStack;
        this.output = output;
        this.initialState = initialState.deepCopy();
        this.exeStack.push(this.initialState);
        this.fileTable = fileTable;
    }

    public PrgState(IStatement initialState) {
        this.symTable = new MyDictionary<>();
        this.exeStack = new MyStack<>();
        this.output = new MyList<>();
        this.exeStack.push(initialState);
        this.fileTable = new MyDictionary<>();
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable(){
        return this.fileTable;
    }

    public String fileTableToString() {
        StringBuilder text = new StringBuilder();
        text.append("FileTable: \n");
        if (fileTable != null) {
            for(StringValue key : this.fileTable.getKeys()) {
                text.append(key).append("\n");
            }
        }
        return text.toString();
    }

    public MyIList<String> getOutput() {
        return output;
    }

    public void setOutput(MyIList<String> output) {
        this.output = output;
    }
    public String toString(){
        return symTable.toString() + exeStack.toString() + output.toString() + fileTableToString() + "\n";
        //return symTable.toString() + " " + exeStack.toString() + " " + output.toString() + " " + fileTableToString() + " ";
    }
}
