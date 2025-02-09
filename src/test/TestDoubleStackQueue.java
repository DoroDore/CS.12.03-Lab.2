package test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import common.*;
import org.junit.jupiter.api.Test;
import interfaces.IQueue;

/**
 * Tests double stack queue implementation.
 */
public class TestDoubleStackQueue extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;

    /**
     * Tests that the factory constructs a non-null object.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackQueue() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        assertNotNull(queue, "Failure: IFactory.makeDoubleStackQueue returns null, expected non-null object");
    }

    /**
     * Tests for the accurate storing of a value into a queue and correct sizing of a queue.
     * @throws QueueFullException
     * @throws StackOverflowException
     * @throws QueueEmptyException
     * @throws StackEmptyException
     */
    @Test
    public void testQueues() throws QueueFullException, StackOverflowException, QueueEmptyException, StackEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.dequeue());
    }

    /**
     * Tests whether the queue will reject additional elements above the queue size.
     * @throws QueueFullException
     * @throws StackOverflowException
     */
    @Test
    public void testFullQueue() throws QueueFullException, StackOverflowException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
            queue.enqueue(i);
        }
        assertThrows(QueueFullException.class, () -> queue.enqueue(11));
    }

    /**
     * Tests whether the queue throws the correct exception for de-queuing in an empty queue
     */
    @Test
    public void testEmptyQueueDequeue() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        assertThrows(QueueEmptyException.class, queue::dequeue);
    }

    /**
     * Tests whether the queue size accurately increments along with the enqueuing of new elements
     * @throws QueueFullException
     * @throws StackOverflowException
     */
    @Test
    public void testQueueSize() throws QueueFullException, StackOverflowException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
            queue.enqueue(i);
            assertEquals(i + 1, queue.size());
        }
    }

    /**
     * Tests whether the "isEmpty" method accurately checks the status of the queue.
     * @throws StackOverflowException
     * @throws QueueFullException
     */
    @Test
    public void testIsEmpty() throws StackOverflowException, QueueFullException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
    }

    /**
     * Tests whether a queue can be successfully cleared after elements have been added.
     * @throws QueueFullException
     * @throws StackOverflowException
     */
    @Test
    public void clear() throws QueueFullException, StackOverflowException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        queue.clear();
        assertTrue(queue.isEmpty());
    }
}
