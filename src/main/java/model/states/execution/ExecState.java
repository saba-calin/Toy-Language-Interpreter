package model.states.execution;

import exceptions.EmptyStackException;
import model.adts.stack.MyStack;
import model.statements.IStatement;

public class ExecState implements IExecStack {
    private MyStack<IStatement> stack;

    public ExecState() {
        this.stack = new MyStack<>();
    }

    public MyStack<IStatement> getStack() {
        return this.stack;
    }

    @Override
    public void push(IStatement statement) {
        this.stack.push(statement);
    }

    @Override
    public IStatement pop() throws EmptyStackException {
        return this.stack.pop();
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public String toString() {
        return this.stack.toString();
    }
}
