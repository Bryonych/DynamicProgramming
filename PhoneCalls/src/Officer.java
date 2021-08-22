/**
 * Represents and officer in the heirarchy.
 * @author Bryony Church
 */
public class Officer implements Comparable<Officer>{

    private Officer superior;
    private Officer[] subordinates;
    private int weight = 0;
    private int depth = 0;
    private char name;

    /**
     * Constructs and officer object.
     * @param boss  The officer's superior.
     * @param staff The officer's staff.
     * @param name  The character representing the officer.
     */
    public Officer(Officer boss, Officer[] staff, char name){
        this.superior = boss;
        this.subordinates = staff;
        this.name = name;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setWeight(int d){
        this.weight = d;
    }

    public int getWeight(){
        return weight;
    }

    public Officer getSuperior() {
        return superior;
    }

    public Officer[] getSubordinates() {
        return subordinates;
    }

    public void setSuperior(Officer superior) {
        this.superior = superior;
    }

    public void setSubordinates(Officer[] subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public int compareTo(Officer other){
        if (this.weight > other.weight)   { return -1; }
        else if (this.weight < other.weight)  { return 1; }
        else    { return 0; }
    }


}
