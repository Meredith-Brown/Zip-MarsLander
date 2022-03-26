public class Simulation {
    private final Vehicle vehicle;

    public Simulation(Vehicle v) {
        this.vehicle = v;
    }
    // Mars Simulation Source Code.
    static String version = "2.0"; /* The Version of the program */

    public static int randomaltitude() {
        int max = 20000;
        int min = 10000;
        int r = (int)(Math.random() * (max - min)) + min;
        return (r % 15000 + 4000);
    }


    public String gameHeader() {
        String s = "";
        s = s + "\nMars Simulation - Version " + version + "\n";
        s = s + "Elon Musk has sent a really expensive Starship to land on Mars.\n";
        s = s + "The on-board computer has failed! You have to land the spacecraft manually.\n";
        s = s + "Set burn rate of retro rockets to any value between 0 (free fall) and 200\n";
        s = s + "(maximum burn) kilo per second. Set burn rate every 10 seconds.\n"; /* That's why we
        have to go with 10 second-steps. */
        s = s + "You must land at a speed of 2 or 1. Good Luck!\n\n";
        return s;
    }

    public String getHeader() {
        String s = "";
        s = s + "\nTime\t";
        s = s + "Velocity\t\t"; s = s + "Fuel\t\t";
        s = s + "Altitude\t\t"; s = s + "Burn\n";
        s = s + "----\t";
        s = s + "-----\t\t";
        s = s + "----\t\t";
        s = s + "------\t\t"; s = s + "----\n";
        return s;
    }


    public void printString(String string) {
// print long strings with new lines the them.
    String[] a = string.split("\r?\n");
        for (String s : a) {
            System.out.println(s);
        }
    }

    // main game loop
    public int runSimulation(BurnStream burnSource) {
        DescentEvent status = null;
        int burnInterval = 0;
        printString(gameHeader());
        printString(getHeader());
        while (vehicle.stillFlying()) {
            status = vehicle.getStatus(burnInterval);
            System.out.print(status.toString()+"\t\t");
            vehicle.adjustForBurn(burnSource.getNextBurn(status));
            if (vehicle.outOfFuel()) {
                break;
            }
            burnInterval++;
            if (burnInterval % 9 == 0) {
                printString(getHeader()); // prints header every 9 lines
            }
        }
        printString(vehicle.checkFinalStatus()); // I think it's correct up through here... EXCEPT last 0 line from array isn't printing
        if (vehicle.checkFinalStatus().equals(vehicle.dead)) { // I added...
            return -3;
        } else if (vehicle.checkFinalStatus().equals(vehicle.crashed)) { // I added...
            return -2;
        } else if (vehicle.checkFinalStatus().equals(vehicle.emptyfuel)) { // I added...
            return -1;
        } else if (vehicle.checkFinalStatus().equals(vehicle.success)) { // I added...
            return -0;
        }
//        if (status != null) {
//            return status.getStatus(); // status not set on DescentEvent page
//        }
        return -1;
    }

    public static void main(String[] args) {
        // create a new Simulation object with a random starting altitude
        Simulation simulation = new Simulation(new Vehicle(randomaltitude()));
        // create a new BurnInputStream
        BurnInputStream inputStream = new BurnInputStream();
        // pass the new BurnInputStream to the runSimulation method
        simulation.runSimulation(inputStream);
    }

}
