package cpsc2150.listDec;

import java.util.List;
import java.util.Random;

public interface IShuffleList<T> extends List<T> {

    /**
     * Shuffles the list
     * @param swaps is the number of swaps to be performed in the shuffle
     *
     * @pre List size > 0
     * @pre swaps > 0
     * @post List has been shuffled swaps times
     */
    default void shuffle(int swaps){
        Random rand = new Random();

        for(int i = 0; i < swaps; i++){
            int randIndex1 = rand.nextInt(this.size());
            int randIndex2 = rand.nextInt(this.size());

            swap(randIndex1, randIndex2);
        }
    }

    /**
     * Swaps the values in the list at two positions
     * @param i is one index position with item to be swapped
     * @param j is other index position with item to be swapped with item at index i
     *
     * @pre List size > 0
     * @post List[i] = #List[j] AND List[j] = #List[i]
     */
    default void swap(int i, int j){
        T temp = this.get(i);

        this.set(i, this.get(j));

        this.set(j, temp);
    }
}
