package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.adts.list.MyList;
import model.adts.semaphore.Tuple;
import model.states.ProgramState;
import model.types.IType;
import model.types.IntType;
import model.values.IntValue;

public class AcquireStatement implements IStatement {
    private String var;

    public AcquireStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        synchronized (programState.getSemaphoreTable()) {
            if (programState.getSymbolTable().contains(this.var) == false) {
                throw new StatementException("The variable was not declared before");
            }
            if (programState.getSymbolTable().get(this.var).getType().equals(new IntType()) == false) {
                throw new StatementException("The variable is not of type int");
            }

            int index = ((IntValue) programState.getSymbolTable().get(this.var)).getValue();
            if (programState.getSemaphoreTable().contains(index) == false) {
                throw new StatementException("The index is not in the semaphore table");
            }

            Tuple<Integer, MyList<Integer>, Integer> tuple = programState.getSemaphoreTable().get(index);
            int nl = tuple.getSecond().getAll().size();
            int n1 = tuple.getFirst();
            int n2 = tuple.getThird();
            if ((n1 - n2) > nl) {
                if (tuple.getSecond().contains(programState.getId()) == false) {
                    tuple.getSecond().add(programState.getId());
                }
            }
            else {
                programState.getExecStack().push(this);
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
        return new AcquireStatement(this.var);
    }

    @Override
    public String toString() {
        return "ACQUIRE(" + this.var + ")";
    }
}
