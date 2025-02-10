package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.states.ProgramState;
import model.types.IType;
import model.types.IntType;
import model.values.IntValue;

public class CountdownStatement implements IStatement {
    private String var;

    public CountdownStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        synchronized (programState.getLatchTable()) {
            if (programState.getSymbolTable().contains(this.var) == false) {
                throw new StatementException("The variable was not declared before");
            }
            if (programState.getSymbolTable().get(this.var).getType().equals(new IntType()) == false) {
                throw new StatementException("The variable is not of type int");
            }

            int index = ((IntValue) programState.getSymbolTable().get(this.var)).getValue();
            if (programState.getLatchTable().contains(index) == false) {
                throw new StatementException("The index is not in the latch table");
            }

            if (programState.getLatchTable().get(index) > 0) {
                programState.getLatchTable().set(index, programState.getLatchTable().get(index) - 1);
                programState.getOutputList().add(Integer.toString(programState.getId()));
            }
            else {
                programState.getOutputList().add(Integer.toString(programState.getId()));
            }
        }

        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException, StatementException {
        IType type = typeEnv.get(this.var);
        if (type.equals(new IntType()) == false) {
            throw new StatementException("The variable is not of type int");
        }

        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new CountdownStatement(this.var);
    }

    @Override
    public String toString() {
        return "COUNTDOWN(" + this.var + ")";
    }
}
