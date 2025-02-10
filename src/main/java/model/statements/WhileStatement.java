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

public class WhileStatement implements IStatement {
    private IExpression expression;
    private IStatement statement;

    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        IValue value = this.expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (value.getType().equals(new BoolType()) == false) {
            throw new StatementException("The expression is not a bool type");
        }

        if (((BoolValue) value).getValue() == true) {
            programState.getExecStack().push(this);
            programState.getExecStack().push(this.statement);
        }

        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException, StatementException {
        if ((new BoolType()).equals(this.expression.typeCheck(typeEnv)) == true) {
            return this.statement.typeCheck(typeEnv);
        }
        else {
            throw new StatementException("The While statement condition does not evaluate to a boolean");
        }
    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(expression.deepCopy(), this.statement.deepCopy());
    }

    @Override
    public String toString() {
        return "WHILE(" + this.expression.toString() + ") {" + this.statement.toString() + "}";
    }
}
