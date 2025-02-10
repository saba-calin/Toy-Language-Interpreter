package model.statements;

import model.adts.dictionary.MyDictionary;
import model.states.ProgramState;
import model.types.IType;

public class NopStatement implements IStatement {

    @Override
    public ProgramState execute(ProgramState programState) {
        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "Nop Statement";
    }

    @Override
    public IStatement deepCopy() {
        return new NopStatement();
    }
}
