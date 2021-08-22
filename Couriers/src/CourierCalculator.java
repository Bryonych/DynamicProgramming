/**
 * Represents a calculator that generates the minimum schedule of couriers
 */
public class CourierCalculator {

    private int rate;
    private int cost;
    private int[] supply;
    private int count = 0;

    /**
     * Constructs a CourierCalculator Object.
     * @param r     The rate for NZ Couriers.
     * @param c     The cost for DHL.
     * @param values    The supply set.
     */
    public CourierCalculator(int r, int c, int[] values){
        this.rate = r;
        this.cost = c;
        this.supply = values;
    }

    /**
     * Generates a running cost of the minimum courier schedule.
     * @return  The schedule of couriers.
     */
    public String[] generateCost(){
        int[] costCalc = new int[supply.length];
        String[] schedule;

        if (supply.length == 0) return null;
        //Iterate through the supply set and calculate the minimum cost.
        for (int i = 0; i < supply.length; i++){
            count+=3;
            int NZCCost = 0;
            int DHLCost = 0;

            if (i < 1)  NZCCost = supply[i]*rate;
            else    NZCCost = (supply[i]*rate) + costCalc[i-1];

            if (i < 4)  DHLCost = 4*cost;
            else    DHLCost = (4*cost) + costCalc[i-4];
            if (NZCCost < DHLCost){
                costCalc[i] = NZCCost;
            }
            else {
                costCalc[i] = DHLCost;
            }
        }
        System.out.println("Total cost = $" + costCalc[costCalc.length-1]);
        schedule = generateSchedule(costCalc);
        return schedule;
    }

    /**
     * Generates the couriers schedule from the running cost.
     * @param costs The running cost.
     * @return      The courier schedule.
     */
    public String[] generateSchedule(int[] costs){
        String[] schedule = new String[costs.length];
        //Go backwards through the running cost and assign the courier associated to that cost.
        for (int i = costs.length-1; i >= 1; i--){
            count++;
            if (costs[i] - costs[i-1] == supply[i]){
                schedule[i] = "NZ Couriers";
            }
            else {
                schedule[i] = "DHL";
                schedule[i-1] = "DHL";
                if (i > 1) schedule[i-2] = "DHL";
                if (i > 2) schedule[i-3] = "DHL";
                i-=3;
            }
        }
        if (schedule[0] == null) schedule[0] = "NZ Couriers";
        return schedule;
    }

    /**
     * Prints the schedule to the screen.
     * @param schedule  The schedule.
     */
    public void printSchedule(String[] schedule){
        System.out.println("Order of Courier Schedule: ");
        for (String s : schedule){
            System.out.println(s);
        }
    }

    public static void main(String[] args){
        int[] testData = {11, 9, 9, 12, 12, 12, 12, 9, 9, 11};
        //int[] testData = {30, 30, 30, 30, 12, 12, 12, 9, 9, 11};
        CourierCalculator c = new CourierCalculator(1, 10, testData);
        String[] schedule = c.generateCost();
        c.printSchedule(schedule);
        System.out.println("Number of steps = " + c.count);
        System.out.println("_________________________________");

        RandomGenerator r = new RandomGenerator(20);
        int[] data = r.getData();
        c = new CourierCalculator(2, 15, data);
        System.out.println("Size = 20, rate = 2, cost = 12");
        schedule = c.generateCost();
        c.printSchedule(schedule);
        System.out.println("Number of steps = " + c.count);
        System.out.println("_________________________________");

        r = new RandomGenerator(30);
        data = r.getData();
        c = new CourierCalculator(1, 8, data);
        System.out.println("Size = 30, rate = 1, cost = 15");
        schedule = c.generateCost();
        c.printSchedule(schedule);
        System.out.println("Number of steps = " + c.count);
        System.out.println("_________________________________");

        r = new RandomGenerator(50);
        data = r.getData();
        c = new CourierCalculator(1, 12, data);
        System.out.println("Size = 50, rate = 1, cost = 12");
        schedule = c.generateCost();
        c.printSchedule(schedule);
        System.out.println("Number of steps = " + c.count);
        System.out.println("_________________________________");

        r = new RandomGenerator(100);
        data = r.getData();
        c = new CourierCalculator(2, 18, data);
        System.out.println("Size = 100, rate = 2, cost = 18");
        schedule = c.generateCost();
        c.printSchedule(schedule);
        System.out.println("Number of steps = " + c.count);
        System.out.println("_________________________________");

        r = new RandomGenerator(1000);
        data = r.getData();
        c = new CourierCalculator(1, 10, data);
        System.out.println("Size = 1000, rate = 1, cost = 10");
        schedule = c.generateCost();
        c.printSchedule(schedule);
        System.out.println("Number of steps = " + c.count);
        System.out.println("_________________________________");
    }
}
