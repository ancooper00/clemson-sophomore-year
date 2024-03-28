package cpsc2150.MyQueue;

public class ArrayQueue extends AbsQueue implements IQueue {
    /**
     * @invariant  (0 < myLength <= MAX_LENGTH)
     *
     * Correspondence queue_length = myLength
     * Correspondence self = myQ[MAX_LENGTH]
     */

    // where the data is stored. myQ[0] is the front of the queue
    private Integer[] myQ;

    // tracks how many items in the queue
    // also used to find the end of the queue
    private int myLength;

    /**
     * Initializes an empty array of Integers of size 100
     *
     * @post myQ is empty AND of size 100
     */
    public ArrayQueue(){
        myQ = new Integer[MAX_LENGTH];
    }

    /**
     * Adds an integer to the end of the queue
     * @param x is the number to add to the queue
     *
     * @pre myLength <= MAX_LENGTH
     * @post x is at the end of the queue
     */
    public void enqueue(Integer x){
        for(int i = 0; i < MAX_LENGTH; i++){
            if(myQ[i] == null){
                myQ[i] = x;
                break;
            }
        }
    }

    /**
     * Removes and returns the Integer at the front of the queue
     * @return Integer at the front of the queue
     *
     * @pre myLength > 0;
     * @post dequeue = myQ[0] AND integer at the front of queue is removed AND
     *       myLength = #myLength - 1
     */
    public Integer dequeue(){
        int frontItem;
        frontItem = myQ[0];
        for(int i = 0; i < myQ.length; i++){
            for(int j = i; j < myQ.length - 1; j++){
                myQ[j] = myQ[j+1];
            }
        }
        myLength = myLength - 1;
        return frontItem;
    }


    /**
     * returns the number of elements in the Queue
     * @return the number of Integers in the Queue (myLength)
     *
     * @pre NONE
     * @post length = myLength;
     */
    public int length(){
        myLength = 0;
        for(int i = 0; i < MAX_LENGTH; i++){
            if(myQ[i] == null){
                break;
            }
            myLength++;
        }
        return myLength;
    }

    /**
     * clears the entire queue
     *
     * @pre NONE
     * @post queue is cleared/integers in the queue have been removed
     */
    public void clear(){
        myQ = null;
    }


}
