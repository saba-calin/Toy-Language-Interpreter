package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.adts.stack.MyStack;
import model.states.ProgramState;
import model.types.IType;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        return new ProgramState(this.statement, new MyStack<>(), programState.getSymbolTable().copy(), programState.getOutputList(), programState.getFileTable(), programState.getHeap(), programState.getSemaphoreTable());
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws StatementException, KeyNotFoundException, ExpressionException {
        return this.statement.typeCheck(typeEnv);
    }

    @Override
    public String toString() {
        return "FORK(" + this.statement.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(this.statement.deepCopy());
    }
}
