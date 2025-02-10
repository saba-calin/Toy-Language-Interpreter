package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.expressions.IExpression;
import model.states.ProgramState;
import model.types.IType;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;

public class CreateLatchStatement implements IStatement {
    private String var;
    private IExpression expression;

    public CreateLatchStatement(String var, IExpression expression) {
        this.var = var;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        synchronized (programState.getLatchTable()) {
            IValue value = this.expression.evaluate(programState.getSymbolTable(), programState.getHeap());
            if (value.getType().equals(new IntType()) == false) {
                throw new StatementException("Expression does not evaluate to an integer.");
            }

            int location = programState.getLatchTable().allocate(((IntValue) value).getValue());
            if (programState.getSymbolTable().contains(this.var) == true && programState.getSymbolTable().get(this.var).getType().equals(new IntType()) == true) {
                programState.getSymbolTable().put(this.var, new IntValue(location));
            }
            else {
                throw new StatementException("The variable was not declared before or it is not of type int");
            }
        }

        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException, StatementException {
        IType expType = this.expression.typeCheck(typeEnv);
        IType varType = typeEnv.get(this.var);
        if (expType.equals(new IntType()) == false || varType.equals(new IntType()) == false) {
            throw new StatementException("The expression is not of type int");
        }

        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new CreateLatchStatement(this.var, this.expression.deepCopy());
    }

    @Override
    public String toString() {
        return "CREATE_LATCH(" + this.var + ", " + this.expression.toString() + ")";
    }
}
