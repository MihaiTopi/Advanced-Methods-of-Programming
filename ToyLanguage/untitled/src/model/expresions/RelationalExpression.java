package model.expresions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.adt.MyIDictionary;
import model.types.IntType;
import model.value.BoolValue;
import model.value.IValue;
import model.value.IntValue;
import model.expresions.RelationalExpression;

import java.beans.Expression;

public class RelationalExpression implements IExpression {
    private IExpression left;
    private IExpression right;
    private RelationalOperator operator;

    public RelationalExpression(IExpression left, RelationalOperator operator, IExpression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public String toString () {
        if (operator == RelationalOperator.LESS_THAN) {
            return left.toString() + " < " + right.toString();
        }
        if (operator == RelationalOperator.LESS_OR_EQUAL_THAN) {
            return left.toString() + " <= " + right.toString();
        }
        if (operator == RelationalOperator.EQUAL) {
            return left.toString() + " == " + right.toString();
        }
        if (operator == RelationalOperator.NOT_EQUAL) {
            return left.toString() + " != " + right.toString();
        }
        if (operator == RelationalOperator.GREATER_THAN) {
            return left.toString() + " > " + right.toString();
        }
        if (operator == RelationalOperator.GREATER_OR_EQUAL_THAN) {
            return left.toString() + " >= " + right.toString();
        }
        else return null;
    }

    @Override
    public model.value.IValue eval(MyIDictionary<String, model.value.IValue> symTbl) throws ADTException, ExpressionException {
        IValue value1 = left.eval(symTbl);
        IValue value2 = right.eval(symTbl);
        if (!value1.getType().equals(new IntType())) {
            throw new ExpressionException("First value is not int");
        }
        if (!value2.getType().equals(new IntType())) {
            throw new ExpressionException("Second value is not int");
        }

        IntValue int1 = (IntValue) value1;
        IntValue int2 = (IntValue) value2;

        switch (operator) {
            case LESS_THAN:
                return new BoolValue(int1.getValue() < int2.getValue());
            case LESS_OR_EQUAL_THAN:
                return new BoolValue(int1.getValue() <= int2.getValue());
            case EQUAL:
                return new BoolValue(int1.getValue() == int2.getValue());
            case NOT_EQUAL:
                return new BoolValue(int1.getValue() != int2.getValue());
            case GREATER_THAN:
                return new BoolValue(int1.getValue() > int2.getValue());
            case GREATER_OR_EQUAL_THAN:
                return new BoolValue(int1.getValue() >= int2.getValue());
            default:
                throw new ExpressionException("Unknown operator");
        }
    }

    @Override
    public IExpression deepCopy() {
        return new RelationalExpression(this.left.deepCopy(), this.operator, this.right).deepCopy();
    }
}
