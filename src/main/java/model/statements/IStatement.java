package model.statements;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.states.ProgramState;
import model.types.IType;

public interface IStatement {
    ProgramState execute(ProgramState programState) throws StatementException, KeyNotFoundException, ExpressionException;
    MyDictionary<String, IType> typeCheck(MyDictionary<String, IType> typeEnv) throws KeyNotFoundException, ExpressionException, StatementException;
    IStatement deepCopy();
}
