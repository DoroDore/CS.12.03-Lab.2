package impl;

import common.QueueEmptyException;
import common.QueueFullException;
import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IQueue;

public class Queue implements IQueue {
    private int capacity, size;
    private DoubleStack stack;

    public Queue (int capacity) {
        this.capacity = capacity;
        this.size = 0;
        // Has to be double size, because one half will be the list going forward and the other one will be the list
        // going backward
        stack = new DoubleStack(capacity*2);
    }

    @Override
    public void enqueue(Object element) throws QueueFullException, StackOverflowException {
        // Throws exception if queue is full and cannot receive more elements.
        if (size == capacity) {
            throw new QueueFullException();
        }
        stack.getFirstStack().push(element);
        size++;
    }

    @Override
    public Object dequeue() throws QueueEmptyException, StackEmptyException, StackOverflowException {
        //Checks if the stack is empty.
        if (isEmpty()) {
            throw new QueueEmptyException();
        }

        // Moves all values of the first stack to the second stack in reverse order due to FILO Law of Stacks
        // This essentially reverses the stack, leaving the first value at the top
        while (!stack.getFirstStack().isEmpty()) {
            stack.getSecondStack().push(stack.getFirstStack().pop());
        }

        // The actual first value will now be stored
        Object element = stack.getSecondStack().pop();

        // Restores the values back to the first stack minus the first in queue.
        while (!stack.getSecondStack().isEmpty()) {
            stack.getFirstStack().push(stack.getSecondStack().pop());
        }

        //Reset second stack to be used again for de-queuing
        stack.getSecondStack().clear();
        //The size of the queue has shrunk by one.
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        stack = new DoubleStack(capacity);
    }
}
