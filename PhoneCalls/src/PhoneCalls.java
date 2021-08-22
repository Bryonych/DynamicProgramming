

import java.util.*;
import java.util.Collections;

/**
 * Calculator for working out the minimum rounds of calls.
 * @author Bryony Church
 */
public class PhoneCalls {

    private List<Round> sequence = new ArrayList<Round>();
    private int count = 0;
    private int sort = 0;

    /**
     * Sets the depth and weight of each officer.
     * @param current   The current officer.
     * @param rank      Their depth in the tree.
     * @return          The calculated depth.
     */
    public int outputSequence(Officer current, int rank){
        int depth = 0;
        Officer[] subordinates = current.getSubordinates();
        if (subordinates != null) {
            for (int i = 0; i < subordinates.length; i++) {
                subordinates[i].setDepth(rank + 1);
                subordinates[i].setWeight(outputSequence(subordinates[i], rank + 1));
                count++;
            }
            for (int i = 0; i < subordinates.length; i++) {
                depth = Math.max(depth, subordinates[i].getWeight() + subordinates[i].getDepth());
                count++;
            }
        }
        return depth;
    }

    /**
     * Takes the root of the tree and recursively creates the rounds of phone calls.
     * @param tree  The current officer.
     */
    public void getRounds(List<Officer> tree){
        Round r = new Round();
        List<Officer> newTree = new ArrayList<Officer>();
        for (Officer o : tree){
            if (o.getSubordinates() != null) {
                count++;
                sort++;
                List<Officer> staff = Arrays.asList(o.getSubordinates());
                Collections.sort(staff);
                Officer first = null;
                for (Officer of : staff) {
                    if (!tree.contains(of)) {
                        first = of;
                        break;
                    }
                }
                if (first != null) {
                    Call c = new Call(o, first);
                    r.addCall(c);
                    newTree.add(first);
                }
            }
        }
        sequence.add(r);
        if (newTree.size() > 0) {
            tree.addAll(newTree);
            getRounds(tree);
        }
    }

    /**
     * Creates a tree for testing.
     * @return  The chief officer.
     */
    public Officer createTree(){
        Officer j = new Officer(null, null, 'j');
        Officer k = new Officer(null, null, 'k');
        Officer g = new Officer(null, new Officer[] {j, k}, 'g');
        j.setSuperior(g);
        k.setSuperior(g);
        Officer c = new Officer(null, new Officer[] {g}, 'c');
        g.setSuperior(c);
        Officer a = new Officer(null, null, 'a');
        c.setSuperior(a);
        Officer f = new Officer(null, null, 'f');
        Officer e = new Officer(null, null, 'e');
        Officer b = new Officer(a, new Officer[] {e, f}, 'b');
        e.setSuperior(b);
        f.setSuperior(b);
        Officer h = new Officer(null, null, 'h');
        Officer i = new Officer(null, null, 'i');
        Officer d = new Officer(a, new Officer[] {h, i}, 'd');
        h.setSuperior(d);
        i.setSuperior(d);
        a.setSubordinates(new Officer[] {b, c, d});
        a.setDepth(0);
        return a;
    }

    public static void main (String args[]){
        PhoneCalls p = new PhoneCalls();
        Officer a = p.createTree();
        p.outputSequence(a, 0);
        List<Officer> tree = new ArrayList<Officer>();
        tree.add(a);
        p.getRounds(tree);
        for (Round r : p.sequence){
            System.out.println(r);
        }
        System.out.println(p.count);
        System.out.println(p.sort);
    }
}


