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
        myQ[myLength] = x;
        myLength++;
    }

    public T dequeue(){
        T frontItem = myQ[0];

        if(length() == MAX_LENGTH){
            int i;
            for(i = 0; i < length() - 1; i++){
                myQ[i] = myQ[i + 1];
            }
            myQ[i++] = null;
        }
        else{
            for(int i = 0; i < length(); i++){
                myQ[i] = myQ[i + 1];
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
        myLength = 0;
    }


}
