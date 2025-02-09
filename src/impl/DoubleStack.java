package impl;

import interfaces.IDoubleStack;
import interfaces.IStack;

public class DoubleStack implements IDoubleStack {
    private Stack firstStack;
    private Stack secondStack;
    private Object[] array;

    public DoubleStack(int size) {
        array = new Object[size];
        firstStack = new Stack(size, true);
        secondStack = new Stack(size, false);
    }
    @Override
    public IStack getFirstStack() {
        return firstStack;
    }

    @Override
    public IStack getSecondStack() {
        return secondStack;
    }
}
