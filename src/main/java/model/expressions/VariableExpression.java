package model.expressions;

import exceptions.KeyNotFoundException;
import model.adts.dictionary.MyDictionary;
import model.adts.heap.IHeap;
import model.types.IType;
import model.values.IValue;

public class VariableExpression implements IExpression {
    private String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public IValue evaluate(MyDictionary<String, IValue> symbolTable, IHeap heap) throws KeyNotFoundException {
        return symbolTable.get(this.name);
    }

    @Override
    public IType typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException {
        return typeEnv.get(this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public IExpression deepCopy() {
        return new VariableExpression(this.name);
    }
}
