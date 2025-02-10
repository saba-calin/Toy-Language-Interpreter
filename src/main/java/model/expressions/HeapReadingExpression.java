package model.expressions;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adts.dictionary.MyDictionary;
import model.adts.heap.IHeap;
import model.types.IType;
import model.types.RefType;
import model.values.IValue;
import model.values.RefValue;

public class HeapReadingExpression implements IExpression {
    IExpression expression;

    public HeapReadingExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(MyDictionary<String, IValue> symbolTable, IHeap heap) throws KeyNotFoundException, ExpressionException {
        IValue value = this.expression.evaluate(symbolTable, heap);
        if (value instanceof RefValue == false) {
            throw new ExpressionException("The value is not of type RefValue");
        }

        if (heap.contains(((RefValue) value).getAddress()) == false) {
            throw new ExpressionException("The value does not exist in the heap");
        }

        return heap.get(((RefValue) value).getAddress());
    }

    @Override
    public IType typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException {
        IType type = this.expression.typeCheck(typeEnv);
        if (type instanceof RefType == true) {
            RefType refType = (RefType) type;
            return refType.getInner();
        }
        else {
            throw new ExpressionException("The read_heap argument is not of type RefType");
        }
    }

    @Override
    public String toString() {
        return "read_heap(" + this.expression.toString() + ")";
    }

    @Override
    public IExpression deepCopy() {
        return new HeapReadingExpression(this.expression.deepCopy());
    }
}
