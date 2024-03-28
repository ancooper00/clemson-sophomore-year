package cpsc2150.MyQueue;

/**
 * Abstractly, IQueue is a queue containing integers.
 * A queue is a data structure where the first item added to the structure is the first item removed from the structure
 * This queue is bounded by MAX_LENGTH. Indexing starts at 0.
 *
 * Initialization ensures:
 *      The queue is empty and is of size MAX_LENGTH or smaller
 *
 * Defines:
 *      queue_length: Z
 *
 * Constraints:
 *      0 < queue_length <= MAX_LENGTH
 */
public interface IQueue {
    public static final int MAX_LENGTH = 100;


    /**
     * Adds an integer to the end of the queue
     * @param x is what is added to the queue
     *
     * @pre Queue Length <= MAX_LENGTH
     * @post x is at the end of the queue
     */
    public void enqueue(Integer x);

    /**
     * Removes and returns the Integer at the front of the queue
     * @return Integer at the front of the queue
     *
     * @pre Queue length > 0
     * @post integer at the front of queue is removed AND
     *       dequeue = removed Integer
     */
    public Integer dequeue();

    /**
     * returns the number of elements in the Queue
     * @return number of Integers in the Queue
     *
     * @pre NONE
     * @post length = number of Integers in Queue
     */
    public int length();

    /**
     * clears the entire queue
     *
     * @pre NONE
     * @post queue is cleared/integers in the queue have been removed
     */
    public void clear();
}
