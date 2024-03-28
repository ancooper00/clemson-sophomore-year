package cpsc2150.MyQueue;

/**
 * Abstractly, IQueue is a queue containing integers.
 * A queue is a data structure where the first item added to the structure is the first item removed from the structure
 * This queue is bounded by MAX_LENGTH. Indexing starts at 1.
 *
 * Initialization ensures:
 *      The queue is empty and is of size MAX_LENGTH or smaller
 *
 * Defines:
 *      queue_length: Z
 *
 * Constraints:
 *      0 <= queue_length <= MAX_LENGTH
 */
public interface IQueue<T> {
    public static final int MAX_LENGTH = 100;

    /**
     * Adds an integer to the end of the queue
     * @param x is what is added to the queue
     *
     * @pre Queue Length <= MAX_LENGTH
     * @post x is at the end of the queue AND
     *       queue_length = #queue_length + 1
     */
    public void enqueue(T x);

    /**
     * Removes and returns the Integer at the front of the queue
     * @return Integer at the front of the queue
     *
     * @pre Queue length > 0
     * @post integer at the front of queue is removed AND
     *       dequeue = removed Integer
     *       queue_length = #queue_length - 1;
     */
    public T dequeue();

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

    /**
     * returns the integer at the front of the queue but does not remove it from the queue
     * @return integer at the front of the queue
     *
     * @pre queue is not empty
     * @post peek = integer at position 1
     */
    default public T peek() {
        T value = this.dequeue();
        this.enqueue(value);
        T temp;
        for(int i = 1; i < this.length(); i++){
            temp = this.dequeue();
            this.enqueue(temp);
        }
        return value;
    }

    /**
     * returns the integer at the end of the queue, but does not remove it from the queue
     * @return the integer at the end of the queue
     *
     * @pre queue is not empty
     * @post endOfQueue = Integer at the end of the queue
     */
    default public T endOfQueue() {
        T value, temp;
        for(int i = 1; i < this.length(); i++){
            temp = this.dequeue();
            this.enqueue(temp);
        }
        value = this.dequeue();
        this.enqueue(value);

        return value;
    }

    /**
     * inserts x at position pos in the queue.
     * @param x   Integer to be inserted
     * @param pos position in queue where x is to be inserted (pos index starts at 1)
     *
     * @pre 0 < pos <= MAX_LENGTH
     *      queue_length < MAX_LENGTH
     * @post x is in index pos in queue AND
     *       all values at and after pos are pushed back
     */
    default public void insert(T x, int pos) {
        T value, temp;
        for(int i = 1; i < pos; i++){
            temp = this.dequeue();
            this.enqueue(temp);
        }
        value = this.dequeue();
        this.enqueue(x);
        this.enqueue(value);
        for(int i = (pos + 1); i < this.length(); i++){
            temp = this.dequeue();
            this.enqueue(temp);
        }
    }

    /**
     * removes whatever integer was in position pos in the queue and returns it.
     * @param pos position where integer to be removed is located
     * @return integer that was removed
     *
     * @pre queue is not empty AND
     *      0 < pos <= MAX_LENGTH
     * @post remove = integer at index pos
     *       integer at index pos is removed
     *       all integers indexed after pos pushed forward
     */
    default public T remove(int pos) {
        T value, temp;
        for(int i = 1; i < pos; i++){
            temp = this.dequeue();
            this.enqueue(temp);
        }
        value = this.dequeue();
        for(int i = pos; i <= this.length(); i++){
            temp = this.dequeue();
            this.enqueue(temp);
        }

        return value;
    }

    /**
     * returns whatever integer was in position pos in the queue and without removing it
     * @param pos index where integer to be returned is located
     * @return integer at index pos
     *
     * @pre 0 < pos <= MAX_LENGTH AND
     *      queue is not empty
     * @post get = Integer at index pos
     */
    default public T get(int pos) {
        T value, temp;
        for(int i = 1; i < pos; i++){
            temp = this.dequeue();
            this.enqueue(temp);
        }
        value = this.dequeue();
        this.enqueue(value);
        for(int i = pos; i < this.length(); i++){
            temp = this.dequeue();
            this.enqueue(temp);
        }
        return value;
    }


}
