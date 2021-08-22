/**
 * Represents a phone call.
 * @author Bryony Church
 */
public class Call {

    Officer one;
    Officer two;

    public Call(Officer one, Officer two){
        this.one = one;
        this.two = two;
    }

    public String toString(){
        return "(" + one.getName() + ", " + two.getName() + ")";
    }
}
