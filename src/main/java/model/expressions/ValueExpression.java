package model.expressions;

import model.adts.dictionary.MyDictionary;
import model.adts.heap.IHeap;
import model.types.IType;
import model.values.IValue;

public class ValueExpression implements IExpression {
    private IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(MyDictionary<String, IValue> symbolTable, IHeap heap) {
        return this.value;
    }

    @Override
    public IType typeCheck(MyDictionary<String, IType> typeEnv) {
        return this.value.getType();
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public IExpression deepCopy() {
        return new ValueExpression(this.value);
    }
}
