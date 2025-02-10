package controller;

import exceptions.KeyNotFoundException;
import exceptions.RepositoryException;
import model.adts.dictionary.MyDictionary;
import model.adts.heap.Heap;
import model.adts.latch.LatchTable;
import model.adts.list.MyList;
import model.adts.stack.MyStack;
import model.statements.IStatement;
import model.states.ProgramState;
import model.types.RefType;
import model.values.IValue;
import model.values.RefValue;
import model.values.StringValue;
import repository.IRepository;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;
    private boolean displayFlag;
    private ExecutorService executorService;

    public Controller(IRepository repository) {
        this.repository = repository;
        this.displayFlag = false;
    }

    public Controller(IRepository repository, boolean displayFlag) {
        this.repository = repository;
        this.displayFlag = displayFlag;
    }

    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public void addStatement(IStatement statement) {
        MyStack<IStatement> stack = new MyStack<>();
        MyDictionary<String, IValue> symbolTable = new MyDictionary<>();
        MyList<String> outputList = new MyList<>();
        MyDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<>();
        Heap heap = new Heap();
        LatchTable latchTable = new LatchTable();
        this.repository.addProgramState(new ProgramState(statement, stack, symbolTable, outputList, fileTable, heap, latchTable));
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> list) {
        return list.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }

    private void logProgramStates(List<ProgramState> list) {
        list.forEach(p -> {
            try {
                this.repository.logProgramState(p);
            }
            catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void oneStepForAllPrograms(List<ProgramState> list) throws InterruptedException {
//        logProgramStates(list);

        // preparing the list of callables
        List<Callable<ProgramState>> callList = list.stream().map((ProgramState p) -> (Callable<ProgramState>)(() -> {return p.oneStep();})).collect(Collectors.toList());

        // start the execution of the callables
        // the list of new created ProgramStates(threads) is returned
        List<ProgramState> newList = this.executorService.invokeAll(callList).stream().map(future -> {
            try {
                return future.get();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).filter(p -> p != null).collect(Collectors.toList());

        list.addAll(newList);
        logProgramStates(list);
        this.repository.setProgramsList(list);
    }

    public void UpdatedOneStep() throws InterruptedException, KeyNotFoundException {
        this.executorService = Executors.newFixedThreadPool(2);
        List<ProgramState> list = removeCompletedPrograms(this.repository.getProgramsList());
        if (list.size() > 0)
        {
            for (ProgramState programState : list) {
                programState.getHeap().setContent(unsafeGarbageCollector(
                        getActiveAddressesForState(programState),
                        programState.getHeap().getContent()
                ));
            }

            oneStepForAllPrograms(list);
            list = removeCompletedPrograms(this.repository.getProgramsList());
        }

        this.executorService.shutdownNow();
        this.repository.setProgramsList(list);
    }

    public void allSteps() throws InterruptedException, KeyNotFoundException {
        this.executorService = Executors.newFixedThreadPool(2);
        List<ProgramState> list = removeCompletedPrograms(this.repository.getProgramsList());
        while (list.size() > 0)
        {
            for (ProgramState programState : list) {
                programState.getHeap().setContent(unsafeGarbageCollector(
                        getActiveAddressesForState(programState),
                        programState.getHeap().getContent()
                ));
            }

            oneStepForAllPrograms(list);
            list = removeCompletedPrograms(this.repository.getProgramsList());
        }

        this.executorService.shutdownNow();
        this.repository.setProgramsList(list);
    }

//    DEPRECIATED
//    public ProgramState oneStep(ProgramState programState) throws EmptyStackException, StatementException, KeyNotFoundException, ExpressionException {
//        IStatement currentStatement = programState.getExecStack().pop();
//        return currentStatement.execute(programState);
//    }

//    DEPRECIATED
//    public void allSteps() throws EmptyStackException, StatementException, KeyNotFoundException, ExpressionException, RepositoryException {
//        ProgramState programState = this.repository.getCurrentProgramState();
//        this.repository.logProgramState();
//
////        if (displayFlag == true) {
////            System.out.println(programState);
////        }
//
//        while (programState.getExecStack().isEmpty() == false) {
//            oneStep(programState);
//            this.repository.logProgramState();
//
//            programState.getHeap().setContent(unsafeGarbageCollector(
//                getActiveAddressesForState(programState),
//                programState.getHeap().getContent()
//            ));
//
////            if (displayFlag == true) {
////                System.out.println(programState);
////            }
//        }
//
////        programState.getSymbolTable().clear();
////        programState.getHeap().setContent(unsafeGarbageCollector(
////                getActiveAddressesForState(programState),
////                programState.getHeap().getContent()
////        ));
////        this.repository.logProgramState();
//    }

    private Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symbolTableAddresses, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
            .filter(e -> symbolTableAddresses.contains(e.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getActiveAddressesForState(ProgramState state) {
        return state.getSymbolTable().getValues().stream().filter(e -> e.getType() instanceof RefType).map(e -> (RefValue) e).flatMap(value -> {
            List<Integer> addresses = new ArrayList<>();
            while (true) {
                if (value.getAddress() == 0) {
                    break;
                }
                addresses.add(value.getAddress());
                IValue next_value;
                try {
                    next_value = state.getHeap().get(value.getAddress());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (!(next_value.getType() instanceof RefType)) {
                    break;
                }
                value = (RefValue) next_value;
            }
            return addresses.stream();
        }).collect(Collectors.toList());
    }

    public List<ProgramState> getProgramsList() {
        return this.repository.getProgramsList();
    }

    public List<Integer> getIds() {
        List<Integer> ans = new ArrayList<>();
        for (ProgramState programState : this.repository.getProgramsList()) {
            ans.add(programState.getId());
        }
        return ans;
    }

    public ProgramState getProgramStateById(int id) {
        for (ProgramState programState : this.repository.getProgramsList()) {
            if (programState.getId() == id) {
                return programState;
            }
        }
        throw new RuntimeException("Program state with the given id was not found");
    }

    public boolean containsProgramStateWithId(int id) {
        for (ProgramState programState : this.repository.getProgramsList()) {
            if (programState.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
