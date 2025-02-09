package impl;

import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IStack;

public class Stack implements IStack {
    private int capacity;
    private int focus;
    private Object[] elements;
    private boolean firstStack;
    public Stack (int capacity, boolean firstStack) {
        this.firstStack = firstStack;
        this.capacity = capacity;
        this.focus = 0;
        this.elements = new Object[capacity];
    }

    @Override
    public void push(Object element) throws StackOverflowException {
        // Throws StackOverflowException because the focus isn't looking at anything within the scope of the stack
        if (focus == capacity) {
            throw new StackOverflowException();
        }
        elements[focus] = element;
        focus++;
    }

    @Override
    public Object pop() throws StackEmptyException {
        // StackEmptyException because focus zero means there isn't anything added yet.
        if (focus == 0) {
            throw new StackEmptyException();
        }
        focus--;
        Object element = elements[focus];
        elements[focus] = null;
        return element;
    }

    @Override
    public Object top() throws StackEmptyException {
        if (focus == 0) {
            throw new StackEmptyException();
        }
        return elements[focus - 1]; // Access the top element without modifying focus
    }

    @Override
    public int size() {
        return focus;
    }

    @Override
    public boolean isEmpty() {
        return focus == 0;
    }

    @Override
    public void clear() {
        focus = 0;
        elements = new Object[capacity];
    }
}
