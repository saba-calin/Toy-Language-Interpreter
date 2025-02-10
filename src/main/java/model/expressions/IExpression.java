package model.expressions;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adts.dictionary.MyDictionary;
import model.adts.heap.IHeap;
import model.types.IType;
import model.values.IValue;

public interface IExpression {
    IValue evaluate(MyDictionary<String, IValue> symbolTable, IHeap heap) throws KeyNotFoundException, ExpressionException;
    IType typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException;
    IExpression deepCopy();
}
