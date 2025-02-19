package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.expressions.IExpression;
import model.expressions.ValueExpression;
import model.states.ProgramState;
import model.types.IType;
import model.types.IntType;
import model.values.IntValue;

public class WaitStatement implements IStatement {
    private IExpression number;

    public WaitStatement(IExpression number) {
        this.number = number;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        int val = ((IntValue) this.number.evaluate(programState.getSymbolTable(), programState.getHeap())).getValue();
        if (val > 0) {
            programState.getOutputList().add(this.number.evaluate(programState.getSymbolTable(), programState.getHeap()).toString());
            programState.getExecStack().push(new WaitStatement(new ValueExpression(new IntValue(val - 1))));
        }

        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException, StatementException {
        if (this.number.typeCheck(typeEnv).equals(new IntType()) == true) {
            return typeEnv;
        }
        else {
            throw new StatementException("The expression in the WAIT statement is not of type int");
        }
    }

    @Override
    public IStatement deepCopy() {
        return new WaitStatement(this.number.deepCopy());
    }

    @Override
    public String toString() {
        return "WAIT(" + this.number.toString() + ")";
    }
}
