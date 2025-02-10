package model.statements.filestatements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.expressions.IExpression;
import model.statements.IStatement;
import model.states.ProgramState;
import model.types.IType;
import model.types.IntType;
import model.types.StringType;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement {
    private IExpression expression;
    private String variableName;

    public ReadFileStatement(IExpression expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException {
        if (programState.getSymbolTable().contains(variableName) == false) {
            throw new StatementException("The variable " + this.variableName + " was not defined");
        }
        if (programState.getSymbolTable().get(this.variableName).getType().equals(new IntType()) == false) {
            throw new StatementException("The variable is not an integer");
        }

        IValue value = this.expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (value.getType().equals(new StringType()) == false) {
            throw new StatementException("The expression does not evaluate to an string type");
        }
        if (programState.getFileTable().contains((StringValue) value) == false) {
            throw new StatementException("The files does not exist in the file table");
        }

        BufferedReader reader = programState.getFileTable().get((StringValue) value);
        try {
            String line = reader.readLine();
            if (line == null) {
                line = "0";
            }
            int num = Integer.parseInt(line);
            programState.getSymbolTable().put(this.variableName, new IntValue(num));
        }
        catch (IOException e) {
            throw new StatementException(e.getMessage());
        }

        return null;
    }

    @Override
    public MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException, StatementException {
        if ((new StringType()).equals(this.expression.typeCheck(typeEnv)) == false) {
            throw new StatementException("The read_file expression does not evaluate to a StringType");
        }

        if (typeEnv.get(this.variableName).equals(new IntType()) == false) {
            throw  new StatementException("The read_file value is not of type StringType");
        }

        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFileStatement(expression.deepCopy(), variableName);
    }

    @Override
    public String toString() {
        return "readFile(" + this.expression.toString() + ", " + this.variableName + ")";
    }
}
