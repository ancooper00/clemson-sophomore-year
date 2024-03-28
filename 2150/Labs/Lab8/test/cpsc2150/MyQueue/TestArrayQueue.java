package cpsc2150.MyQueue;

import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

public class TestArrayQueue {
    //for when queue is full
    private final int MAX_LENGTH = 100;

    private IQueue<Double> MakeAQueue(){
        IQueue<Double> q = new ArrayQueue<Double>();
        return q;
    }

    //queue is empty and element being added
    @Test
    public void testEnqueue_queueEmpty(){
        IQueue q = MakeAQueue();

        Double val = 1.0;
        q.enqueue(val);

        assertTrue(q.get(1) == val);
        assertTrue(q.length() == 1);
        assertTrue(q.toString().equals("1.0 "));
    }

    //queue is having last element added to make it full
    @Test
    public void testEnqueue_queueOneFromFull(){
        IQueue q = MakeAQueue();

        for(int i = 1; i < MAX_LENGTH; i++){
            Double input = (double) i;
            q.enqueue(input);
        }

        Double val = 100.0;
        q.enqueue(val);

        assertEquals(val, q.get(MAX_LENGTH));
        assertEquals(MAX_LENGTH, q.length());

        for(int i = 1; i <= MAX_LENGTH; i++){
            Double check = (double) i;
            assertEquals(q.get(i), check);
        }

    }



    //common case, queue is neither empty nor one from full
    @Test
    public void testEnqueue_common_QueueAdd(){
        IQueue q = MakeAQueue();

        Double val1 = 1.0;
        q.enqueue(val1);
        Double val2 = 2.0;
        q.enqueue(val2);
        Double val3 = 3.0;
        q.enqueue(val3);

        assertTrue(q.get(3) == val3);
        assertTrue(q.length() == 3);
        assertTrue(q.toString().equals("1.0 2.0 3.0 "));

    }

    //has only one element
    @Test
    public void testDequeue_Has_One(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);
        q.dequeue();

        assertTrue(q.length() == 0);
        assertTrue(q.toString().equals(""));
    }

    //queue is full
    @Test
    public void testDequeue_QueueFull(){
        IQueue q = MakeAQueue();

        for(int i = 1; i <= MAX_LENGTH; i++){
            Double input = (double) i;
            q.enqueue(input);
        }

        q.dequeue();

        assertEquals(q.get(1), 2.0);
        assertTrue(q.length() == 99);

        for(int i = 1; i <= q.length(); i++){
            Double check = (double) i + 1;
            assertEquals(q.get(i), check);
        }

    }



    //queue is not full or empty
    @Test
    public void testDequeue_common_Dequeue(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);
        q.enqueue(2.0);
        q.enqueue(3.0);

        q.dequeue();

        assertEquals(q.get(1), 2.0);
        assertTrue(q.length() == 2);
        assertTrue(q.toString().equals("2.0 3.0 "));
    }

    //queue has one element
    @Test
    public void testClear_HasOne(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);

        q.clear();

        assertTrue(q.length() == 0);
        assertTrue(q.toString().equals(""));
    }

    //queue is full
    @Test
    public void testClear_QueueFull(){
        IQueue q = MakeAQueue();

        for(int i = 1; i <= MAX_LENGTH; i++){
            Double input = (double) i;
            q.enqueue(input);
        }

        q.clear();

        assertTrue(q.length() == 0);
        assertTrue(q.toString().equals(""));
    }

    //queue is not empty or full
    @Test
    public void testClear_routine(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);
        q.enqueue(2.0);
        q.enqueue(3.0);

        q.clear();

        assertTrue(q.length() == 0);
        assertTrue(q.toString().equals(""));

    }

    //queue only has one element (boundary case)
    @Test
    public void testPeek_HasOne(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);

        assertEquals(q.peek(), 1.0);
        assertEquals(q.get(1), 1.0);
        assertTrue(q.length() == 1);
        assertTrue(q.toString().equals("1.0 "));
    }

    //queue is full (boundary case)
    @Test
    public void testPeek_Full(){
        IQueue q = MakeAQueue();

        for(int i = 1; i <= MAX_LENGTH; i++){
            Double input = (double) i;
            q.enqueue(input);
        }

        assertEquals(q.peek(), 1.0);
        assertEquals(q.get(1), 1.0);
        assertTrue(q.length() == MAX_LENGTH);

        for(int i = 1; i <= MAX_LENGTH; i++){
            Double check = (double) i;
            assertEquals(q.get(i), check);
        }
    }

    //queue is not empty or full
    @Test
    public void testPeek_Routine(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);
        q.enqueue(2.0);
        q.enqueue(3.0);

        assertEquals(q.peek(), 1.0);
        assertEquals(q.get(1), 1.0);
        assertTrue(q.length() == 3);
        assertTrue(q.toString().equals("1.0 2.0 3.0 "));
    }

    //queue has one element, end of queue is same as begining
    @Test
    public void testEndOfQueue_HasOne(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);

        assertEquals(q.endOfQueue(), 1.0);
        assertEquals(q.get(1), 1.0);
        assertTrue(q.length() == 1);
        assertTrue(q.toString().equals("1.0 "));
    }

    //queue is full (boundary case)
    @Test
    public void testEndOfQueue_Full(){
        IQueue q = MakeAQueue();

        for(int i = 1; i <= MAX_LENGTH; i++){
            Double input = (double) i;
            q.enqueue(input);
        }

        assertEquals(q.endOfQueue(), 100.0);
        assertEquals(q.get(100), 100.0);
        assertTrue(q.length() == MAX_LENGTH);

        for(int i = 1; i <= MAX_LENGTH; i++){
            Double check = (double) i;
            assertEquals(q.get(i), check);
        }
    }

    //queue is not empty or full, routine test
    @Test
    public void testEndOfQueue_Routine(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);
        q.enqueue(2.0);
        q.enqueue(3.0);

        assertEquals(q.endOfQueue(), 3.0);
        assertEquals(q.get(3), 3.0);
        assertTrue(q.length() == 3);
        assertTrue(q.toString().equals("1.0 2.0 3.0 "));
    }

    //insert at front boundary
    @Test
    public void testInsert_InsertAtFront(){
        IQueue q = MakeAQueue();

        q.enqueue(2.0);
        q.enqueue(3.0);

        q.insert(1.0, 1);

        assertEquals(q.get(1), 1.0);
        assertTrue(q.length() == 3);
        assertTrue(q.toString().equals("1.0 2.0 3.0 "));

    }

    //insert when empty
    @Test
    public void testInsert_InsertAtEnd(){
        IQueue q = MakeAQueue();

        q.insert(1.0, 1);

        assertEquals(q.get(1), 1.0);
        assertTrue(q.length() == 1);
        assertTrue(q.toString().equals("1.0 "));
    }

    //insert routine
    @Test
    public void testInsert_InsertRoutine(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);
        q.enqueue(3.0);

        q.insert(2.0, 2);

        assertEquals(q.get(2), 2.0);
        assertTrue(q.length() == 3);
        assertTrue(q.toString().equals("1.0 2.0 3.0 "));
    }

    //queue has one element(min size), will empty the queue
    @Test
    public void testRemove_HasOne_Empties(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);

        assertEquals(q.remove(1), 1.0);
        assertTrue(q.length() == 0);
        assertTrue(q.toString().equals(""));
    }

    //removing first element, removing a boundary element
    @Test
    public void testRemove_RemoveFirstItem(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);
        q.enqueue(2.0);
        q.enqueue(3.0);

        assertEquals(q.remove(1), 1.0);
        assertTrue(q.length() == 2);
        assertTrue(q.toString().equals("2.0 3.0 "));
    }

    //routine remove
    @Test
    public void testRemove_Routine(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);
        q.enqueue(2.0);
        q.enqueue(3.0);

        assertEquals(q.remove(2), 2.0);
        assertTrue(q.length() == 2);
        assertTrue(q.toString().equals("1.0 3.0 "));

    }

    //get from the front boundary
    @Test
    public void testGet_getFront(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);
        q.enqueue(2.0);
        q.enqueue(3.0);

        assertEquals(q.get(1), 1.0);
        assertTrue(q.length() == 3);
        assertTrue(q.toString().equals("1.0 2.0 3.0 "));
    }

    //get from the end boundary
    @Test
    public void testGet_getEnd(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);
        q.enqueue(2.0);
        q.enqueue(3.0);

        assertEquals(q.get(3), 3.0);
        assertTrue(q.length() == 3);
        assertTrue(q.toString().equals("1.0 2.0 3.0 "));
    }

    //get routine
    @Test
    public void testGet_getRoutine(){
        IQueue q = MakeAQueue();

        q.enqueue(1.0);
        q.enqueue(2.0);
        q.enqueue(3.0);

        assertEquals(q.get(2), 2.0);
        assertTrue(q.length() == 3);
        assertTrue(q.toString().equals("1.0 2.0 3.0 "));

    }



}
