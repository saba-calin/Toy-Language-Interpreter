package model.statements.filestatements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.expressions.IExpression;
import model.statements.IStatement;
import model.states.ProgramState;
import model.types.IType;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStatement implements IStatement {
    IExpression expression;

    public CloseFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        IValue value = expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (value.getType().equals(new StringType()) == false) {
            throw new StatementException("The expression does not evaluate to a string type");
        }
        if (programState.getFileTable().contains((StringValue) value) == false) {
            throw new StatementException("The file does not exist in the file table");
        }

        try {
            BufferedReader reader = programState.getFileTable().get((StringValue) value);
            reader.close();
            programState.getFileTable().remove((StringValue) value);
        }
        catch (IOException e) {
            throw new StatementException(e.getMessage());
        }

        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException, StatementException {
        if ((new StringType()).equals(this.expression.typeCheck(typeEnv)) == true) {
            return typeEnv;
        }
        else {
            throw new StatementException("The close_file statement does not evaluate to a StringType");
        }
    }

    @Override
    public IStatement deepCopy() {
        return new CloseFileStatement(expression.deepCopy());
    }

    @Override
    public String toString() {
        return "closeFile(" + expression.toString() + ")";
    }
}
