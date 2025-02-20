package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.expressions.IExpression;
import model.expressions.RelationalExpression;
import model.expressions.RelationalOperation;
import model.expressions.VariableExpression;
import model.states.ProgramState;
import model.types.IType;
import model.types.IntType;

public class ForStatement implements IStatement {
    IExpression expression1;
    IExpression expression2;
    IExpression expression3;
    IStatement statement;

    public ForStatement(IExpression expression1, IExpression expression2, IExpression expression3, IStatement statement) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        IStatement temp = new CompositeStatement(
            new VarDeclStatement("v", new IntType()),
            new CompositeStatement(
                new AssignStatement("v", this.expression1),
                new WhileStatement(new RelationalExpression(new VariableExpression("v"), this.expression2, RelationalOperation.LESS), new CompositeStatement(this.statement, new AssignStatement("v", this.expression3)))
            )
        );
        programState.getExecStack().push(temp);

        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException, StatementException {
        IType type1 = this.expression1.typeCheck(typeEnv);
        IType type2 = this.expression2.typeCheck(typeEnv);
        IType type3 = this.expression3.typeCheck(typeEnv);
        if (type1.equals(new IntType()) == true) {
            if (type2.equals(new IntType()) == true) {
                if (type3.equals(new IntType()) == true) {
                    this.statement.typeCheck(typeEnv.copy());
                    return typeEnv;
                }
                else {
                    throw new StatementException("The third expression is not of type int");
                }
            }
            else {
                throw new StatementException("The second expression is not of type int");
            }
        }
        else {
            throw new StatementException("The first expression is not of type int");
        }
    }

    @Override
    public IStatement deepCopy() {
        return new ForStatement(this.expression1.deepCopy(), this.expression2.deepCopy(), this.expression3.deepCopy(), this.statement.deepCopy());
    }

    @Override
    public String toString() {
        return "FOR(v = " + this.expression1.toString() + "; v < " + this.expression2.toString() + "; v = " + this.expression3.toString() + ") { " + this.statement.toString() + " }";
    }
}
