/**
 * Represents a calculator that detects if gerrymandering is possible in a given population.
 * @author Bryony Church
 */
public class Gerrymandering {

    int numPrecincts;
    int numPeople;
    int count = 0;

    /**
     * Constructs a gerrymandering object
     * @param n Number of precincts
     * @param m Population size of each precinct
     */
    public Gerrymandering(int n, int m){
        this.numPrecincts = n;
        this.numPeople = m;
    }

    /**
     * Gives the number of voters for a given party in each precinct of a given population.
     * @param party The party whose votes are being counted.
     * @param n     The number of precincts.
     * @param pop   The precincts in the state.
     * @return      An array of the number of voters in each precinct for this party.
     */
    public int[] getNumbers(char party, int n, Precinct[] pop){
        int[] answer = new int[n];
        for (int i = 0; i < n; i++){
            if (party == 'A'){
                answer[i] = pop[i].getlVotes();
            }
            else if (party == 'B'){
                answer[i] = pop[i].getnVotes();
            }
        }
        return answer;
    }

    /**
     * Calculates whether gerrymandering is possible for the given party and displays the result on the screen.
     * @param pop   The precincts that make up the state.
     * @param party The party being checked.
     */
    public void checkGerry(Precinct[] pop, char party){
        int[] voteNumbers = getNumbers(party, pop.length, pop);
        int voters = 0;
        //Count up the total number of voters for this party.
        for (int i = 0; i < voteNumbers.length; i++){
            voters += voteNumbers[i];
        }
        System.out.println("Party " + party + " has " + voters + " number of voters");
        //The maximum number of voters a precinct can have in order to have enough voters for the other precincts.
        int max = voters - ((numPrecincts*numPeople)/4) - 1;
        boolean[][][] matrix = new boolean[numPrecincts][numPrecincts/2][max];

        for (int k = 0; k < numPrecincts; k++){
            for (int l = 0; l < numPrecincts/2; l++){
                for (int w = 0; w < max; w++){
                    count++;
                    if ((l == 0 && w == 0) ||
                            (k == 0 && l == 0 && w == voteNumbers[0]) ||
                            (w > voteNumbers[k] && k > 0 && l > 0 && matrix[k-1][l-1][w-voteNumbers[k]]) ||
                            (k > 0 && matrix[k-1][l][w])){
                        matrix[k][l][w] = true;
                    }
                    else {
                        matrix[k][l][w] = false;
                    }
                }
            }
        }
        //The minimum number of voters a precinct needs to win.
        int majority = ((numPrecincts*numPeople)/4) + 1;
        System.out.println(max + " " + majority );
        for (int s = majority; s < max; s++){
            count++;
            if (matrix[numPrecincts-1][(numPrecincts/2)-1][s]){
                System.out.println("Gerrymandering is possible for party " + party);
                System.out.println("Number of steps: " + count);
                return;
            }
        }
        System.out.println("Gerrymandering is not possible for party " + party);
        System.out.println("Number of steps: " + count);
    }

    /**
     * Checks if gerrymandering is possible for each party in turn.
     * @param population    The precincts that make up the state.
     */
    public void process(Precinct[] population){
        checkGerry(population, 'A');
        checkGerry(population, 'B');
    }


    public static void main(String[] args) {
        PopulationGenerator p = new PopulationGenerator(4, 100);
        System.out.println("n = 4, m = 100");
        Precinct[] population = p.getTestPopulation();
        Gerrymandering g = new Gerrymandering(4, 100);
        g.process(population);
        System.out.println("________________________________________________________");
        p = new PopulationGenerator(10, 100);
        System.out.println("n = 10, m = 100");
        population = p.getRandomPrecinct();
        g.process(population);
        System.out.println("________________________________________________________");
        p = new PopulationGenerator(50, 300);
        System.out.println("n = 50, m = 300");
        population = p.getRandomPrecinct();
        g.process(population);
        System.out.println("________________________________________________________");
        p = new PopulationGenerator(60, 500);
        System.out.println("n = 60, m = 500");
        population = p.getRandomPrecinct();
        g.process(population);
        System.out.println("________________________________________________________");
        p = new PopulationGenerator(100, 1000);
        System.out.println("n = 100, m = 1000");
        population = p.getRandomPrecinct();
        g.process(population);
        System.out.println("________________________________________________________");
        p = new PopulationGenerator(300, 10000);
        System.out.println("n = 300, m = 10000");
        population = p.getRandomPrecinct();
        g.process(population);
        System.out.println("________________________________________________________");
        p = new PopulationGenerator(10000, 50);
        System.out.println("n = 10000, m = 5");
        population = p.getRandomPrecinct();
        g.process(population);
    }

}
