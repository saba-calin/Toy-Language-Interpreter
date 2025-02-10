package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.expressions.IExpression;
import model.statements.AssignStatement;
import model.states.ProgramState;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;

public class CondAssignmentStatement implements IStatement {
    private String v;
    private IExpression exp1;
    private IExpression exp2;
    private IExpression exp3;

    public CondAssignmentStatement(String v, IExpression exp1, IExpression exp2, IExpression exp3) {
        this.v = v;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
//        IValue value = this.exp1.evaluate(programState.getSymbolTable(), programState.getHeap());
//        if (value.getType().equals(new BoolType()) == false) {
//            throw new StatementException("The condition is not boolean");
//        }
//        if (programState.getSymbolTable().contains(this.v) == false) {
//            throw new StatementException("The variable was not declared before");
//        }
//
//        IValue val1 = this.exp2.evaluate(programState.getSymbolTable(), programState.getHeap());
//        IValue val2 = this.exp3.evaluate(programState.getSymbolTable(), programState.getHeap());
//        if (val1.getType().equals(programState.getSymbolTable().get(this.v).getType()) == false || val2.getType().equals(programState.getSymbolTable().get(this.v).getType()) == false) {
//            throw new StatementException("The types do not match");
//        }

        IStatement ifStatement = new IfStatement(this.exp1, new AssignStatement(this.v, this.exp2), new AssignStatement(this.v, this.exp3));
        programState.getExecStack().push(ifStatement);

        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException, StatementException {
        IType vType = typeEnv.get(this.v);
        IType exp1Type = this.exp1.typeCheck(typeEnv);
        IType exp2Type = this.exp2.typeCheck(typeEnv);
        IType exp3Type = this.exp3.typeCheck(typeEnv);

        if (exp1Type.equals(new BoolType()) == false) {
            throw new StatementException("Exp1 is not of type bool");
        }
        if (vType.equals(exp2Type) == false || vType.equals(exp3Type) == false) {
            throw new StatementException("Expressions are not of the same type as the variable");
        }

        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new CondAssignmentStatement(this.v, this.exp1.deepCopy(), this.exp2.deepCopy(), this.exp3.deepCopy());
    }

    @Override
    public String toString() {
        return this.v + " = (" + this.exp1.toString() + ") ? " + this.exp2.toString() + " : " + this.exp3.toString();
    }
}
