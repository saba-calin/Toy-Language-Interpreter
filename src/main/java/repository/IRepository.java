package repository;

import exceptions.RepositoryException;
import model.states.ProgramState;

import java.util.List;

public interface IRepository {
    void addProgramState(ProgramState programState);
    void logProgramState(ProgramState programState) throws RepositoryException;
    List<ProgramState> getProgramsList();
    void setProgramsList(List<ProgramState> list);
}
