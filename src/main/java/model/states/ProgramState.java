package model.states;

import exceptions.EmptyStackException;
import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adts.dictionary.MyDictionary;
import model.adts.heap.IHeap;
import model.adts.latch.ILatchTable;
import model.adts.list.MyList;
import model.adts.stack.MyStack;
import model.statements.IStatement;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;

public class ProgramState {
    private IStatement initStatement;
    private MyStack<IStatement> execStack;
    private MyDictionary<String, IValue> symbolTable;
    private MyList<String> outputList;
    private MyDictionary<StringValue, BufferedReader> fileTable;
    private IHeap heap;
    private ILatchTable latchTable;
    private int id;
    private static int nextId = 0;

    public ProgramState(IStatement initStatement, MyStack<IStatement> execStack, MyDictionary<String, IValue> symbolTable, MyList<String> outputList, MyDictionary<StringValue, BufferedReader> fileTable, IHeap heap, ILatchTable latchTable) {
        // typeCheck(initStatement);

        this.id = requestId();
        this.execStack = execStack;
        this.symbolTable = symbolTable;
        this.outputList = outputList;
        this.fileTable = fileTable;
        this.heap = heap;
        this.latchTable = latchTable;
        this.initStatement = initStatement.deepCopy();
        this.execStack.push(initStatement);
    }

    private void typeCheck(IStatement statement) {
        try {
            statement.typeCheck(new MyDictionary<>());
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private synchronized int requestId() {
        nextId++;
        return nextId;
    }

    public MyStack<IStatement> getExecStack() {
        return this.execStack;
    }

    public MyDictionary<String, IValue> getSymbolTable() {
        return this.symbolTable;
    }

    public MyList<String> getOutputList() {
        return this.outputList;
    }

    public MyDictionary<StringValue, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public IHeap getHeap() {
        return this.heap;
    }

    public ILatchTable getLatchTable() {
        return this.latchTable;
    }

    public boolean isNotCompleted() {
        return this.execStack.isEmpty() == false;
    }

    public ProgramState oneStep() throws EmptyStackException, StatementException, KeyNotFoundException, ExpressionException {
        IStatement currentStatement = this.getExecStack().pop();
        return currentStatement.execute(this);
    }

    @Override
    public String toString() {
        return "ID:" + this.id + "\n" + "EXECUTION_STACK: " + this.execStack.toString()+ "\n" + "SYMBOL_TABLE: " + this.symbolTable.toString() + "\n" + "OUTPUT_LIST: " + this.outputList.toString() + "\n" + "FILE_TABLE: " + this.fileTableToString() + "\n" + "HEAP: " + this.heap.toString() + "\n" + "LATCH_TABLE: " + this.latchTable.toString() + "\n";
    }

    private String fileTableToString() {
        StringBuilder string = new StringBuilder();

        for (StringValue str : this.fileTable.getKeys()) {
            string.append(str.getValue());
            string.append("\n");
        }
        if (string.length() > 0) {
            string.deleteCharAt(string.length() - 1);
        }

        return string.toString();
    }

    public MyList<String> getFileList() {
        MyList<String> ans = new MyList<>();
        for (StringValue str : this.fileTable.getKeys()) {
            ans.add(str.getValue());
        }
        return ans;
    }

    public int getId() {
        return this.id;
    }

    public IStatement getInitStatement() {
        return this.initStatement;
    }
}
