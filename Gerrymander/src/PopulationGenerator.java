import java.util.Random;

/**
 * Creates random populations made up of precincts of voters.
 * @author Bryony Church
 */
public class PopulationGenerator {
    private int numPrecincts;
    private int numPeople;

    /**
     * Constructs a population generator.
     * @param n Number of precincts.
     * @param m Number of people in each precinct.
     */
    public PopulationGenerator(int n, int m){
        this.numPrecincts = n;
        this.numPeople = m;
    }

    /**
     * Generates random set of precincts.
     * @return  The set of precincts.
     */
    public Precinct[] getRandomPrecinct(){
        Precinct[] population = new Precinct[numPrecincts];
        int variance;
        if (numPeople < 100) variance = 10;
        else if (numPeople < 200) variance = 30;
        else if (numPeople < 300) variance = 50;
        else if (numPeople < 700) variance = 100;
        else if (numPeople < 2000) variance = 250;
        else variance = 700;
        int half = numPeople/2;
        int upperBound = variance*2;
        Random rand = new Random();
        for (int i = 0; i < numPrecincts; i++){
            int difference = rand.nextInt(upperBound);
            int votes = half-variance+difference;
            population[i] = new Precinct(i, votes, numPeople-votes);
        }
        return population;
    }

    /**
     * Generates the precincts from the assignment handout for testing.
     * @return  The precincts.
     */
    public Precinct[] getTestPopulation(){
        if (numPrecincts != 4 || numPeople != 100){
            System.out.println("Wrong input numbers for test set");
            return null;
        }
        Precinct[] population = new Precinct[4];
        population[0] = new Precinct(1, 55, 45);
        population[1] = new Precinct(2, 43, 57);
        population[2] = new Precinct(3, 60, 40);
        population[3] = new Precinct(4, 47, 53);
        return population;
    }
}