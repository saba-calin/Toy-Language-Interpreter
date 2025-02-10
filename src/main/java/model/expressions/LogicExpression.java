package model.expressions;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adts.dictionary.MyDictionary;
import model.adts.heap.IHeap;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;

public class LogicExpression implements IExpression {
    private IExpression left;
    private IExpression right;
    private LogicOperation operation;

    public LogicExpression(IExpression left, IExpression right, LogicOperation operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    @Override
    public IValue evaluate(MyDictionary<String, IValue> symbolTable, IHeap heap) throws KeyNotFoundException, ExpressionException {
        IValue leftValue = this.left.evaluate(symbolTable, heap);
        IValue rightValue = this.right.evaluate(symbolTable, heap);
        if (leftValue.getType().equals(new BoolType()) == false || rightValue.getType().equals(new BoolType()) == false) {
            throw new ExpressionException("The values are not of type boolean");
        }

        boolean boolLeftValue = ((BoolValue) leftValue).getValue();
        boolean boolRightValue = ((BoolValue) rightValue).getValue();
        if (this.operation == LogicOperation.AND) {
            return new BoolValue(boolLeftValue && boolRightValue);
        } else {
            return new BoolValue(boolLeftValue || boolRightValue);
        }
    }

    @Override
    public IType typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException {
        IType type1 = this.left.typeCheck(typeEnv);
        IType type2 = this.right.typeCheck(typeEnv);
        if (type1.equals(new BoolType()) == true) {
            if (type2.equals(new BoolType()) == true) {
                return new BoolType();
            }
            else {
                throw new ExpressionException("The second operand is not a boolean");
            }
        }
        else {
            throw new ExpressionException("The first operand is not a boolean");
        }
    }

    @Override
    public String toString() {
        return this.left.toString() + " " + this.operation.toString().toLowerCase() + " " + this.right.toString();
    }

    @Override
    public IExpression deepCopy() {
        return new LogicExpression(left.deepCopy(), right.deepCopy(), this.operation);
    }
}
