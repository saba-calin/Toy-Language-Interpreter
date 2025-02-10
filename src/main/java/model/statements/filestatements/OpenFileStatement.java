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
import java.io.FileReader;
import java.io.IOException;

public class OpenFileStatement implements IStatement {
    private IExpression expression;

    public OpenFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        IValue value = this.expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (value.getType().equals(new StringType()) == false) {
            throw new StatementException("The expression does not evaluate to a string type");
        }
        if (programState.getFileTable().contains((StringValue) value) == true) {
            throw new StatementException("The file already exists in the file table");
        }

        try {
            String path = ((StringValue) value).getValue();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            programState.getFileTable().put((StringValue) value, reader);
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
            throw new StatementException("The open_file statement does not evaluate to a string type");
        }
    }

    @Override
    public IStatement deepCopy() {
        return new OpenFileStatement(expression.deepCopy());
    }

    @Override
    public String toString() {
        return "openFile(" + this.expression.toString() + ")";
    }
}
