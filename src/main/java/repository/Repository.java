package repository;

import exceptions.RepositoryException;
import model.adts.dictionary.MyDictionary;
import model.statements.IStatement;
import model.states.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<ProgramState> programStates;
    private String logFilePath;

    public Repository() {
        this.programStates = new ArrayList<>();
        this.logFilePath = "";
    }

    public Repository(String logFilePath) {
        this.programStates = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    public Repository(ProgramState programState, String logFilePath) {
        this.programStates = new ArrayList<>();
        this.programStates.add(programState);
        this.logFilePath = logFilePath;
    }

    @Override
    public void addProgramState(ProgramState programState) {
        typeCheck(programState.getInitStatement());
        
        this.programStates.add(programState);
    }

    @Override
    public List<ProgramState> getProgramsList() {
        return this.programStates;
    }

    @Override
    public void setProgramsList(List<ProgramState> list) {
        this.programStates = list;
    }

    @Override
    public void logProgramState(ProgramState programState) throws RepositoryException{
        if (this.logFilePath != "") {
            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
                printWriter.println(programState);
                printWriter.close();
            }
            catch (IOException e) {
                throw new RepositoryException(e.getMessage());
            }
        }
    }

    private void typeCheck(IStatement statement) {
        try {
            statement.typeCheck(new MyDictionary<>());
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
