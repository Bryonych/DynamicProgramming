
import java.util.Random;

/**
 * Random generator for supply sets.
 */
public class RandomGenerator {

    private int size;
    private int[] data;

    /**
     * Represents a random generator object.
     * @param n The number of weeks.
     */
    public RandomGenerator(int n){
        this.size = n;
        data = new int[size];
        generateRandomValues();
    }

    /**
     * Generates the random supply set.
     */
    public void generateRandomValues(){
        Random rand = new Random();
        for (int i = 0; i < size; i++){
            data[i] = 5 + rand.nextInt(15);
        }
    }

    public int[] getData(){
        return data;
    }
}
