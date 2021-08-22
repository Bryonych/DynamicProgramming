

import java.util.*;

/**
 * Represents a round of calls.
 * @author Bryony Church
 */
public class Round {

    int number;
    List<Call> calls = new ArrayList<Call>();

    public Round(){
    }

    public void addCall(Call call){
        calls.add(call);
    }

    public String toString(){
        String answer = "";
        for (Call c : calls){
            answer += c;
            answer += " ";
        }
        return answer;
    }

}
