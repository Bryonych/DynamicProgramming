import org.junit.Test;

public class Tests {

    @Test
    public void test_01(){
        Precinct[] population = new Precinct[8];
        population[0] = new Precinct(1, 55, 45);
        population[1] = new Precinct(2, 43, 57);
        population[2] = new Precinct(3, 60, 40);
        population[3] = new Precinct(4, 47, 53);
        population[4] = new Precinct(5, 55, 45);
        population[5] = new Precinct(2, 43, 57);
        population[6] = new Precinct(3, 60, 40);
        population[7] = new Precinct(4, 47, 53);
        System.out.println("Gerrymandering possible for A and not B");
        Gerrymandering g = new Gerrymandering(8, 100);
        g.process(population);
    }

    @Test
    public void test_02(){
        Precinct[] population = new Precinct[8];
        population[0] = new Precinct(1, 99, 101);
        population[1] = new Precinct(2, 98, 102);
        population[2] = new Precinct(3, 60, 140);
        population[3] = new Precinct(4, 47, 153);
        population[4] = new Precinct(5, 90, 110);
        population[5] = new Precinct(2, 80, 120);
        population[6] = new Precinct(3, 160, 40);
        population[7] = new Precinct(4, 147, 53);
        System.out.println("Gerrymandering possible for B and not A");
        Gerrymandering g = new Gerrymandering(8, 200);
        g.process(population);
}
}
