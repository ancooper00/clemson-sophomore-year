package cpsc2150.MyQueue;

import java.util.*;

public class QueueApp {
    public static void main(String args[]){
        IQueue q;
        /*
        You will add in code here to ask the user whether they want an
        array implementation or a list implementation. Then use their
        answer to initialize q appropriately
        */
        System.out.println("Array or list implementation? Please type A or L.");
        Scanner sc = new Scanner(System.in);
        String imple = sc.nextLine();

        if(imple == "A"){
            q = new ArrayQueue();
        }
        else{
            q = new ListQueue();
        }


        Integer x = 42;
        q.enqueue(x);
        x = 17;
        q.enqueue(x);
        x = 37;
        q.enqueue(x);
        x = 36;
        q.enqueue(x);
        x = 12;
        q.enqueue(x);

        //Add the code to print the queue. After the code is finished,
        // the queue should still contain all its values in order
        for(int i = 0; i < q.length(); i ++){
            Integer temp = q.dequeue();
            System.out.println(temp);
            q.enqueue(temp);
        }


    }
}
