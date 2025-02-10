package model.statements.heapstatements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.expressions.IExpression;
import model.statements.IStatement;
import model.states.ProgramState;
import model.types.IType;
import model.types.RefType;
import model.values.IValue;
import model.values.RefValue;

public class HeapWritingStatement implements IStatement {
    private String name;
    private IExpression expression;

    public HeapWritingStatement(String name, IExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        if (programState.getSymbolTable().contains(this.name) == false) {
            throw new StatementException("The variable was not declared before");
        }
        if (programState.getSymbolTable().get(this.name).getType() instanceof RefType == false) {
            throw new StatementException("The variable is not of type reference type");
        }
        if (programState.getHeap().contains(((RefValue) programState.getSymbolTable().get(this.name)).getAddress()) == false) {
            throw new StatementException("The address does not exist in the heap");
        }

        IValue value = this.expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (value.getType().equals(((RefValue) programState.getSymbolTable().get(this.name)).getLocationType()) == false) {
            throw new StatementException("The location type and expression type do not match");
        }

        programState.getHeap().set(((RefValue) programState.getSymbolTable().get(this.name)).getAddress(), value);

        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException, StatementException {
        IType varType = typeEnv.get(this.name);
        IType expType = this.expression.typeCheck(typeEnv);
        if (varType.equals(new RefType(expType)) == true) {
            return typeEnv;
        }
        else {
            throw new StatementException("NEW statement: right hand side and left hand side have different types");
        }
    }

    @Override
    public String toString() {
        return "write_heap(" + this.name + ", " + this.expression.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new HeapWritingStatement(this.name, this.expression.deepCopy());
    }
}
