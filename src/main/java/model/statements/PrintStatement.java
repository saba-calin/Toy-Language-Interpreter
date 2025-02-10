package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adts.dictionary.MyDictionary;
import model.expressions.IExpression;
import model.states.ProgramState;
import model.types.IType;
import model.values.IValue;

public class PrintStatement implements IStatement {
    private IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws KeyNotFoundException, ExpressionException {
        IValue val = this.expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        programState.getOutputList().add(val.toString());
        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException {
        this.expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print(" + this.expression.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(this.expression.deepCopy());
    }
}
