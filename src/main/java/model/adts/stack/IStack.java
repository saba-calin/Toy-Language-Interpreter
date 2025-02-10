package model.adts.stack;

import exceptions.EmptyStackException;

import java.util.Stack;

public interface IStack<T> {
    T pop() throws EmptyStackException;
    void push(T elem);
    boolean isEmpty();
    int size();
    Stack<T> getAll();
}
