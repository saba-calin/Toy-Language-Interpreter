package model.expressions;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adts.dictionary.MyDictionary;
import model.adts.heap.IHeap;
import model.types.BoolType;
import model.types.IType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

public class RelationalExpression implements IExpression {
    private IExpression left;
    private IExpression right;
    private RelationalOperation operation;

    public RelationalExpression(IExpression left, IExpression right, RelationalOperation operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    @Override
    public IValue evaluate(MyDictionary<String, IValue> symbolTable, IHeap heap) throws KeyNotFoundException, ExpressionException {
        IValue leftValue = this.left.evaluate(symbolTable, heap);
        IValue rightValue = this.right.evaluate(symbolTable, heap);
        if (leftValue.getType().equals(new IntType()) == false || rightValue.getType().equals(new IntType()) == false) {
            throw new ExpressionException("The values are not of type int");
        }

        int intLeftValue = ((IntValue) leftValue).getValue();
        int intRightValue = ((IntValue) rightValue).getValue();
        switch (this.operation) {
            case LESS -> { return new BoolValue(intLeftValue < intRightValue); }
            case LESS_EQUAL -> { return new BoolValue(intLeftValue <= intRightValue); }
            case EQUAL -> { return new BoolValue(intLeftValue == intRightValue); }
            case NOT_EQUAL -> { return new BoolValue(intLeftValue != intRightValue); }
            case GREATER -> { return new BoolValue(intLeftValue > intRightValue); }
            case GREATER_EQUAL -> { return new BoolValue(intLeftValue >= intRightValue); }
            default -> { throw new ExpressionException("Invalid operation"); }
        }
    }

    @Override
    public IType typeCheck(MyDictionary<String, IType> typeEnv) throws ExpressionException, KeyNotFoundException {
        IType type1 = this.left.typeCheck(typeEnv);
        IType type2 = this.right.typeCheck(typeEnv);
        if (type1.equals(new IntType()) == true) {
            if (type2.equals(new IntType()) == true) {
                return new BoolType();
            }
            else {
                throw new ExpressionException("The second operator is not an integer");
            }
        }
        else {
            throw new ExpressionException("The first operator is not an integer");
        }
    }

    @Override
    public IExpression deepCopy() {
        return new RelationalExpression(this.left.deepCopy(), this.right.deepCopy(), this.operation);
    }

    private String enumToString() {
        switch (this.operation) {
            case LESS -> { return "<"; }
            case LESS_EQUAL -> { return "<="; }
            case EQUAL -> { return "=="; }
            case NOT_EQUAL -> { return "!="; }
            case GREATER -> { return ">"; }
            case GREATER_EQUAL -> { return ">="; }
            default -> { return ""; }
        }
    }

    @Override
    public String toString() {
        return this.left.toString() + " " + enumToString() + " " + this.right.toString();
    }
}
