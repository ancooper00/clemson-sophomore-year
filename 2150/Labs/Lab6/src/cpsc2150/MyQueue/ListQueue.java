package cpsc2150.MyQueue;

import java.util.*;

public class ListQueue<T> extends AbsQueue<T> implements IQueue<T> {
    /**
     * @invariant (0 <= myLength <= MAX_LENGTH)
     *
     * Correspondence self = myQ
     * Correspondence queue_length = myQ.length()
     */
    // this time store the queue in a list
    // myQ.get(0) is the front of the queue
    private List<T> myQ;

    public ListQueue(){
        myQ = new ArrayList<T>();
    }

    public void enqueue(T x){
        myQ.add(x);
    }

    public T dequeue(){
        T front = myQ.get(0);

        myQ.remove(0);

        return front;
    }

    public int length(){
        return myQ.size();
    }

    public void clear(){
        myQ.clear();
    }
}
