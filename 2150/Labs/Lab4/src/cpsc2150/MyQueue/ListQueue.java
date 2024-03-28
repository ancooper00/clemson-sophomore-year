package cpsc2150.MyQueue;

import java.util.*;

public class ListQueue implements IQueue {
    /**
     * @invariant (0 < myLength < = MAX_LENGTH)
     *
     * Correspondence self = myQ
     * Correspondence queue_length = myQ.length()
     */
    // this time store the queue in a list
    // myQ.get(0) is the front of the queue
    private List<Integer> myQ;

    /**
     * Constructor that creates an empty queue
     *
     * @post list myQ is empty
     */
    public ListQueue(){
        myQ = new ArrayList<Integer>();
    }

    /**
     * Adds an integer to the end of the queue
     * @param x is what is added to the queue
     *
     * @pre myQ.size() <= MAX_LENGTH
     * @post x is at the end of the queue
     */
    public void enqueue(Integer x){
        myQ.add(x);
    }

    /**
     * Removes and returns the Integer at the front of the queue
     * @return Integer at the front of the queue
     *
     * @pre myQ.size() > 0
     * @post dequeue = myQ.get(0) AND integer at the front of queue is removed
     */
    public Integer dequeue(){
        Integer front = myQ.get(0);

        myQ.remove(0);

        return front;
    }


    /**
     * Returns the number of elements in the queue
     * @return the number of integers in the queue
     *
     * @pre NONE
     * @post length = myQ.size()
     */
    public int length(){
        return myQ.size();
    }

    /**
     * clears the entire queue
     *
     * @pre NONE
     * @post queue is cleared/integers in the queue have been removed
     */
    public void clear(){
        myQ.clear();
    }
}
