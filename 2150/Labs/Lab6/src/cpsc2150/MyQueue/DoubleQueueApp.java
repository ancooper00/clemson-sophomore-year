package cpsc2150.MyQueue;

import java.util.Scanner;

/**
 * Uses implementations of IQueue but with a queue of Doubles
 */
public class DoubleQueueApp {
    /**
     * Prints the menu on the screen
     *
     * @pre NONE
     * @post menu has been printed to the screen
     */
    public static void printMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Add to the queue");
        System.out.println("2. Get the next number from the queue");
        System.out.println("3. Peek at the front of the queue");
        System.out.println("4. Peek at the end of the queue");
        System.out.println("5. Insert  in the queue");
        System.out.println("6. Get a position in the queue");
        System.out.println("7. Remove from a position in the queue");
        System.out.println("8. Exit");
    }

    /**
     * Checks to see if the queue is empty
     * @param length is length of the queue to be checked
     * @return truth value associated with whether the queue is empty
     *
     * @pre NONE
     * @post checkEmpty = true iff length = 0 AND
     *       does not change the queue
     */
    public static boolean checkEmpty(int length) {
        if (length == 0) {
            System.out.println("Queue is empty!");
            return true;
        } else {
            return false;
        }
    }


    /**
     * Checks to see if the queue is full
     * @param length is length of the queue to be checked
     * @return truth value associated with whether the queue is full
     *
     * @pre NONE
     * @post checkFull = true iff length = MAX_LENGTH AND
     *       does not change the queue
     */
    public static boolean checkFull(int length){
        if (length == IQueue.MAX_LENGTH){
            System.out.println("Queue is Full! Please remove an item or clear the queue before adding.");
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Gets a valid position from the user when they want to insert
     * @param length is the length of queue where a number is being inserted
     * @return valid position chosen by the user
     *
     * @pre length < MAX_LENGTH
     * @post 0 < getPosInsert <= length + 1
     */
    public static int getPosInsert(int length) {
        int p;
        System.out.println("What position to insert in?");
        Scanner inp = new Scanner(System.in);
        p = inp.nextInt();
        while ((p <= 0) || (p > length + 1)) {
            System.out.println("Not a valid position in the Queue!");
            System.out.println("What position to insert in?");
            Scanner input = new Scanner(System.in);
            p = input.nextInt();
        }
        return p;
    }

    /**
     * Gets a valid position from the user when they want to get a member of the queue
     * @param length is the length of queue where a number is being retrieved
     * @return valid position chosen by the user
     *
     * @pre 0 < length <= MAX_LENGTH
     * @post 0 < getPosGet <= length
     */
    public static int getPosGet(int length) {
        int p;
        System.out.println("What position to get from the Queue?");
        Scanner inp = new Scanner(System.in);
        p = inp.nextInt();
        while ((p <= 0) || (p > length)) {
            System.out.println("Not a valid position in the Queue!");
            System.out.println("What position to get from the Queue?");
            Scanner in = new Scanner(System.in);
            p = in.nextInt();
        }
        return p;
    }

    /**
     * Gets a valid position from the user when they want to remove
     * @param length is the length of queue where a number is being removed
     * @return valid position chosen by the user
     *
     * @pre 0 < length <= MAX_LENGTH
     * @post 0 < getPosRemove <= length
     */
    public static int getPosRemove(int length) {
        int p;
        System.out.println("What position to remove from the Queue?");
        Scanner inp = new Scanner(System.in);
        p = inp.nextInt();
        while ((p <= 0) || (p > length)) {
            System.out.println("Not a valid position in the Queue!");
            System.out.println("What position to remove from the Queue?");
            Scanner in = new Scanner(System.in);
            p = in.nextInt();
        }
        return p;
    }

    /**
     * Prints the current state of the queue
     * @param q is the queue to be printed
     *
     * @pre NONE
     * @post queue has been printed to the screen
     */
    public static void printQueue(IQueue q) {
        System.out.println("Queue is:");
        System.out.println(q.toString());
    }

    public static void main(String args[]) {
        IQueue<Double> q;
        /*
        Asks user whether they want an array or list implementation
        */
        System.out.println("Enter 1 for array implementation or 2 for List implementation");
        Scanner sc = new Scanner(System.in);
        String imple = sc.nextLine();

        if (imple == "1") {
            q = new ArrayQueue<Double>();
        } else {
            q = new ListQueue<Double>();
        }


        //ask user for menu input and executes that choice
        printMenu();
        Scanner inp = new Scanner(System.in);
        int choice = inp.nextInt();

        while (choice != 8) {
            Double val;
            int pos;
            switch (choice) {
                case 1: //Add to the queue
                    if(checkFull(q.length())){
                        printQueue(q);
                        break;
                    }
                    else {
                        System.out.println("What number to add to the queue?");
                        Scanner userIn = new Scanner(System.in);
                        val = userIn.nextDouble();
                        q.enqueue(val);
                        printQueue(q);
                    }
                    break;
                case 2: //get next number from the queue
                    if (checkEmpty(q.length())) {
                        printQueue(q);
                        break;
                    } else {
                        System.out.println("Next number is " + q.dequeue());
                        printQueue(q);
                    }
                    break;
                case 3: //peek at the front of the queue
                    if (checkEmpty(q.length())) {
                        printQueue(q);
                        break;
                    } else {
                        System.out.println("Peek: " + q.peek());
                        printQueue(q);
                    }
                    break;
                case 4: //peek at the end of the queue
                    if (checkEmpty(q.length())) {
                        printQueue(q);
                        break;
                    } else {
                        System.out.println("Peek at end: " + q.endOfQueue());
                        printQueue(q);
                    }
                    break;
                case 5: //insert in the queue
                    if(checkFull(q.length())){
                        printQueue(q);
                        break;
                    }
                    else {
                        System.out.println("What number to add to the queue?");
                        Scanner inVal = new Scanner(System.in);
                        val = inVal.nextDouble();
                        pos = getPosInsert(q.length());
                        q.insert(val, pos);
                        printQueue(q);
                    }
                    break;
                case 6: // get a position in the queue
                    if (checkEmpty(q.length())) {
                        printQueue(q);
                        break;
                    } else {
                        pos = getPosGet(q.length());
                        System.out.println(q.get(pos) + " is at position " + pos + " in the queue");
                        printQueue(q);
                    }
                    break;
                case 7: //remove from a position in the queue
                    if (checkEmpty(q.length())) {
                        printQueue(q);
                        break;
                    } else {
                        pos = getPosRemove(q.length());
                        System.out.println(q.remove(pos) + " was at position " + pos + " in the queue");
                        printQueue(q);
                    }
                    break;
                default:
                    System.out.println("Not a valid option!");
                    printQueue(q);
                    break;
            }
            System.out.println("\n");
            printMenu();
            Scanner newInp = new Scanner(System.in);
            choice = newInp.nextInt();
        }

    }

}
