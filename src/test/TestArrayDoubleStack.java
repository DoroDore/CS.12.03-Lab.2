package test;

import static org.junit.jupiter.api.Assertions.*;

import common.StackEmptyException;
import common.StackOverflowException;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import interfaces.IDoubleStack;

/**
 * Tests array collection implementation.
 */
public class TestArrayDoubleStack extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;

    /**
     * Tests that the factory constructs a non-null double stack.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackObject() {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertNotNull(doubleStack1, "Failure: IFactory.makeDoubleStack returns null, expected non-null object");
    }

    /**
     * Tests that both stacks are able to push values while empty.
     * @throws StackOverflowException
     * @throws StackEmptyException
     */
    @Test
    public void properPush() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(1);
        doubleStack1.getSecondStack().push(1);
        assertEquals(1, doubleStack1.getFirstStack().top());
        assertEquals(1, doubleStack1.getSecondStack().top());
    }

    /**
     * Tests that both stacks are able to pop values, and that they don't affect values prior.
     * @throws StackOverflowException
     * @throws StackEmptyException
     */
    @Test
    public void properPop() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().push(2);
        doubleStack.getSecondStack().push(1);
        doubleStack.getSecondStack().push(2);
        assertEquals(2, doubleStack.getFirstStack().pop());
        assertEquals(1, doubleStack.getFirstStack().top());
        assertEquals(2, doubleStack.getSecondStack().pop());
        assertEquals(1, doubleStack.getSecondStack().top());
    }

    /**
     * Tests that both stacks throw a StackEmptyException if trying to pop nothing.
     * @throws StackOverflowException
     * @throws StackEmptyException
     */
    @Test
    public void properPopEmptyStack() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertThrows(StackEmptyException.class, () -> doubleStack.getFirstStack().pop());
        assertThrows(StackEmptyException.class, () -> doubleStack.getSecondStack().pop());
    }

    /**
     * Tests that a stack once filled, will throw a StackOverflowException if attempting to push another value
     * @throws StackOverflowException
     */
    @Test
    public void pushFullStack() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        for (int i = 1; i <= DEFAULT_MAX_SIZE; i++) {
            doubleStack.getFirstStack().push(i);
        }
        assertThrows(StackOverflowException.class, () -> doubleStack.getFirstStack().push(11));
    }

    /**
     * Checks whether both stacks will accurately return the value of the top object.
     * @throws StackOverflowException
     * @throws StackEmptyException
     */
    @Test
    public void checkTop() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getFirstStack().push(1);
        doubleStack.getSecondStack().push(2);
        assertEquals(1,doubleStack.getFirstStack().top());
        assertEquals(2,doubleStack.getSecondStack().top());
    }

    @Test
    public void checkTopEmpty() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertThrows(StackEmptyException.class, () -> doubleStack.getFirstStack().pop());
    }

    /**
     * Tests whether the method "isEmpty" accurately returns states of stacks.
     * @throws StackOverflowException
     */
    @Test
    public void testIsEmpty() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getFirstStack().push(1);
        assertFalse(doubleStack.getFirstStack().isEmpty());
        assertTrue(doubleStack.getSecondStack().isEmpty());
    }

    /**
     * Tests whether clear actually clears the stack, removing traces of previous values.
     * @throws StackOverflowException
     */
    @Test
    public void testClear() throws StackOverflowException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack.getFirstStack().push(1);
        assertFalse(doubleStack.getFirstStack().isEmpty());
        doubleStack.getFirstStack().clear();
        assertTrue(doubleStack.getFirstStack().isEmpty());
    }
}
