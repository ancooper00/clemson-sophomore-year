package cpsc2150.MyQueue;

public abstract class AbsQueue implements IQueue {

    /**
     * Converts the queue into a string in the format "x x x x x"
     * @return a string representing the queue
     *
     * @pre None
     * @post toString returns a string of the queue
     */
    @Override
    public String toString(){
        String queue = "";
        for (int i = 0; i < this.length(); i++) {
            Integer temp = this.dequeue();
            queue += temp + " ";
            this.enqueue(temp);
        }
        return queue;
    }
}
