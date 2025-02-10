package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.states.ProgramState;
import model.types.IType;

public class CompositeStatement implements IStatement {
    private IStatement first;
    private IStatement second;

    public CompositeStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        programState.getExecStack().push(this.second);
        programState.getExecStack().push(this.first);
        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws StatementException, KeyNotFoundException, ExpressionException {
        return this.second.typeCheck(this.first.typeCheck(typeEnv));
    }

    @Override
    public String toString() {
        return this.first.toString() + "; " + this.second.toString();
    }

    @Override
    public IStatement deepCopy() {
        return new CompositeStatement(this.first.deepCopy(), this.second.deepCopy());
    }
}
