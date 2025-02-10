package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.expressions.IExpression;
import model.states.ProgramState;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;

public class IfStatement implements IStatement {
    private IExpression condition;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public IfStatement(IExpression condition, IStatement thenStatement, IStatement elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        IValue value = this.condition.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (value.getType().equals(new BoolType()) == false) {
            throw new StatementException("The condition is not boolean");
        }

        if (((BoolValue) value).getValue() == true) {
            programState.getExecStack().push(this.thenStatement);
        }
        else {
            programState.getExecStack().push(this.elseStatement);
        }

        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException, StatementException {
        IType expType = this.condition.typeCheck(typeEnv);
        if (expType.equals(new BoolType()) == true) {
            this.thenStatement.typeCheck(typeEnv.copy());
            this.elseStatement.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else {
            throw new StatementException("The condition of the IF statement is not of type bool");
        }
    }

    @Override
    public String toString() {
        return "IF(" + this.condition.toString() + ") THEN {" + this.thenStatement.toString() + "} ELSE {" + this.elseStatement.toString() + "}";
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(this.condition.deepCopy(), this.thenStatement.deepCopy(), this.elseStatement.deepCopy());
    }
}
