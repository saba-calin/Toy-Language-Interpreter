package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.adts.list.MyList;
import model.adts.semaphore.Tuple;
import model.expressions.IExpression;
import model.states.ProgramState;
import model.types.IType;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;

public class CreateSemStatement implements IStatement {
    private String var;
    private IExpression exp1;
    private IExpression exp2;

    public CreateSemStatement(String var, IExpression exp1, IExpression exp2) {
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        synchronized (programState.getSemaphoreTable()) {
            IValue number1 = this.exp1.evaluate(programState.getSymbolTable(), programState.getHeap());
            IValue number2 = this.exp2.evaluate(programState.getSymbolTable(), programState.getHeap());
            if (number1.getType().equals(new IntType()) == false) {
                throw new StatementException("Expression1 is not of IntType");
            }
            if (number2.getType().equals(new IntType()) == false) {
                throw new StatementException("Expression2 is not of IntType");
            }

            int location = programState.getSemaphoreTable().allocate(new Tuple<>(((IntValue) number1).getValue(), new MyList<>(), ((IntValue) number2).getValue()));
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
        IType exp1Type = this.exp1.typeCheck(typeEnv);
        IType exp2Type = this.exp2.typeCheck(typeEnv);
        IType varType = typeEnv.get(this.var);
        if (exp1Type.equals(varType) == false) {
            throw new StatementException("Expression1 is not of type int");
        }
        if (exp2Type.equals(varType) == false) {
            throw new StatementException("Expression2 is not of type int");
        }

        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new CreateSemStatement(this.var, this.exp1.deepCopy(), this.exp2.deepCopy());
    }

    @Override
    public String toString() {
        return "CREATE_SEM(" + this.var + ", " + this.exp1.toString() + ", " + this.exp2.toString() + ")";
    }
}
