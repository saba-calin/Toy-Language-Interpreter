package model.expressions;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adts.dictionary.MyDictionary;
import model.adts.heap.IHeap;
import model.types.IType;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;

public class ArithmeticExpression implements IExpression {
    private IExpression left;
    private IExpression right;
    private ArithmeticOperation operation;

    public ArithmeticExpression(IExpression left, IExpression right, ArithmeticOperation operation) {
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
            case ADD -> { return new IntValue(intLeftValue + intRightValue); }
            case SUBTRACT -> { return new IntValue(intLeftValue - intRightValue); }
            case MULTIPLY -> { return new IntValue(intLeftValue * intRightValue); }
            case DIVIDE -> {
                if (intRightValue == 0) {
                    throw new ExpressionException("Division by zero");
                }
                return new IntValue(intLeftValue / intRightValue);
            }
            default -> { throw new ExpressionException("Invalid operation"); }
        }
    }

    private String enumToString() {
        switch (this.operation) {
            case ADD -> { return "+"; }
            case SUBTRACT -> { return "-"; }
            case MULTIPLY -> { return "*"; }
            case DIVIDE -> { return "/"; }
            default -> { return ""; }
        }
    }

    @Override
    public IType typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException {
        IType type1 = this.left.typeCheck(typeEnv);
        IType type2 = this.right.typeCheck(typeEnv);
        if (type1.equals(new IntType()) == true) {
            if (type2.equals(new IntType()) == true) {
                return new IntType();
            }
            else {
                throw new ExpressionException("The second operand is not an integer");
            }
        }
        else {
            throw new ExpressionException("The first operand is not an integer");
        }
    }

    @Override
    public String toString() {
        return this.left.toString() + " " + enumToString() + " " + this.right.toString();
    }

    @Override
    public IExpression deepCopy() {
        return new ArithmeticExpression(this.left.deepCopy(), this.right.deepCopy(), this.operation);
    }
}
