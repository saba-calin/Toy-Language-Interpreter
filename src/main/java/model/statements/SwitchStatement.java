package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.expressions.IExpression;
import model.states.ProgramState;
import model.types.IType;
import model.values.IValue;

public class SwitchStatement implements IStatement {
    private IExpression expression;
    private IExpression case1;
    private IStatement statement1;
    private IExpression case2;
    private IStatement statement2;
    private IStatement defaultStatement;

    public SwitchStatement(IExpression expression, IExpression case1, IStatement statement1, IExpression case2, IStatement statement2, IStatement defaultStatement) {
        this.expression = expression;
        this.case1 = case1;
        this.statement1 = statement1;
        this.case2 = case2;
        this.statement2 = statement2;
        this.defaultStatement = defaultStatement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        IValue value = this.expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        IValue case1Value = this.case1.evaluate(programState.getSymbolTable(), programState.getHeap());
        IValue case2Value = this.case2.evaluate(programState.getSymbolTable(), programState.getHeap());

        if (value.equals(case1Value)) {
            programState.getExecStack().push(this.statement1);
        }
        else if (value.equals(case2Value)) {
            programState.getExecStack().push(this.statement2);
        }
        else {
            programState.getExecStack().push(this.defaultStatement);
        }

        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException, StatementException {
        IType expressionType = this.expression.typeCheck(typeEnv);
        IType case1Type = this.case1.typeCheck(typeEnv);
        IType case2Type = this.case2.typeCheck(typeEnv);

        if (expressionType.equals(case1Type) && expressionType.equals(case2Type)) {
            return this.statement1.typeCheck(this.statement2.typeCheck(this.defaultStatement.typeCheck(typeEnv)));
        }
        else {
            throw new StatementException("The Switch statement cases do not evaluate to the same type as the expression");
        }

    }

    @Override
    public IStatement deepCopy() {
        return new SwitchStatement(this.expression.deepCopy(), this.case1.deepCopy(), this.statement1.deepCopy(), this.case2.deepCopy(), this.statement2.deepCopy(), this.defaultStatement.deepCopy());
    }

    @Override
    public String toString() {
        return "SWITCH(" + this.expression.toString() + ") {CASE(" + this.case1.toString() + ") {" + this.statement1.toString() + "} CASE(" + this.case2.toString() + ") {" + this.statement2 + "} DEFAULT {" + this.defaultStatement.toString() + "}}";
    }
}
