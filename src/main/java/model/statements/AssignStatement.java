package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.expressions.IExpression;
import model.states.ProgramState;
import model.types.IType;
import model.values.IValue;

public class AssignStatement implements IStatement {
    private String name;
    private IExpression expression;

    public AssignStatement(String name, IExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        if (programState.getSymbolTable().contains(this.name) == false) {
            throw new StatementException("The variable was not declared before");
        }
        IValue value = this.expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (value.getType().equals(programState.getSymbolTable().get(this.name).getType()) == false) {
            throw new StatementException("The types do not match");
        }
        programState.getSymbolTable().put(this.name, value);
        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException, StatementException {
        IType varType = typeEnv.get(this.name);
        IType expType = this.expression.typeCheck(typeEnv);
        if (varType.equals(expType) == true) {
            return typeEnv;
        }
        else {
            throw new StatementException("Assignment: right hand side and left hand side have different types");
        }
    }

    @Override
    public String toString() {
        return this.name + " = " + this.expression.toString();
    }

    @Override
    public IStatement deepCopy() {
        return new AssignStatement(this.name, this.expression.deepCopy());
    }
}
