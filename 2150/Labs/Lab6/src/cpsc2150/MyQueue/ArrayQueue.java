package cpsc2150.MyQueue;

public class ArrayQueue<T> extends AbsQueue<T> implements IQueue<T> {
    /**
     * @invariant  (0 <= myLength <= MAX_LENGTH)
     *
     * Correspondence queue_length = myLength
     * Correspondence self = myQ[MAX_LENGTH]
     */

    // where the data is stored. myQ[0] is the front of the queue
    private T[] myQ;

    // tracks how many items in the queue
    // also used to find the end of the queue
    private int myLength;

    public ArrayQueue(){
        myQ = (T[]) new Object[MAX_LENGTH];
        myLength = 0;
    }

    public void enqueue(T x){
        for(int i = 0; i < MAX_LENGTH; i++){
            if(myQ[i] == null){
                myQ[i] = x;
                myLength++;
                break;
            }
        }
    }

    public T dequeue(){
        T frontItem;
        frontItem = myQ[0];
        for(int i = 0; i < myQ.length; i++){
            for(int j = i; j < myQ.length - 1; j++){
                myQ[j] = myQ[j+1];
            }
        }
        myLength = myLength - 1;
        return frontItem;
    }

    public int length(){
        return myLength;
    }

    public void clear(){
        myQ = null;
    }


}
