package model.statements;

import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.states.ProgramState;
import model.types.IType;

public class VarDeclStatement implements IStatement {
    private String name;
    private IType type;

    public VarDeclStatement(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {
        if (programState.getSymbolTable().contains(this.name)) {
            throw new StatementException("A variable with the same name already exists\n");
        }
        programState.getSymbolTable().put(this.name, this.type.getDefaultValue());
        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) {
        typeEnv.put(this.name, this.type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }

    @Override
    public IStatement deepCopy() {
        return new VarDeclStatement(this.name, this.type);
    }
}
