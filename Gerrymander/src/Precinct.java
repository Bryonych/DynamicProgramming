/**
 * Represents a precinct in the state.
 * @author Bryony Church
 */
public class Precinct {

    int number;
    int lVotes;
    int nVotes;
    int difference;

    /**
     * Generates a precinct object.
     * @param number    Number of people.
     * @param lVotes    Number of l voters.
     * @param nVotes    Number of n voters.
     */
    public Precinct(int number, int lVotes, int nVotes){
        this.number = number;
        this.lVotes = lVotes;
        this.nVotes = nVotes;

    }

    public int getNumber() {
        return number;
    }

    public int getlVotes() {
        return lVotes;
    }

    public int getnVotes() {
        return nVotes;
    }

}
