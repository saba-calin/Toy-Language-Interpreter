package model.adts.stack;

import exceptions.EmptyStackException;

import java.util.Stack;

public class MyStack<T> implements IStack<T> {
    Stack<T> s;

    public MyStack() {
        this.s = new Stack<>();
    }

    @Override
    public T pop() throws EmptyStackException {
        if (this.s.empty()) {
            throw new EmptyStackException("The stack is empty");
        }
        return this.s.pop();
    }

    @Override
    public void push(T elem) {
        this.s.push(elem);
    }

    @Override
    public boolean isEmpty() {
        return this.s.isEmpty();
    }

    @Override
    public int size() {
        return this.s.size();
    }

    @Override
    public Stack<T> getAll() {
        return this.s;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (T elem : this.s) {
            string.append(elem);
            string.append("\n");
        }
        if (string.length() > 0) {  // remove the last new line
            string.deleteCharAt(string.length() - 1);
        }

        return string.toString();
    }
}
