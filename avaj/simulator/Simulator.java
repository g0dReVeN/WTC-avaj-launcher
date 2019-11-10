package avaj.simulator;

import avaj.exceptions.InvalidSimCount;
import avaj.simulator.aircraft.AircraftFactory;
import avaj.simulator.aircraft.Flyable;

import java.io.*;
import java.util.*;

public class Simulator {
    public static PrintWriter stxt;
    private static WeatherTower weatherTower;
    private static List<Flyable> flyables = new ArrayList<Flyable>();


    public static void main(String[] args) throws InterruptedException {
        try {
            try {
                stxt = new PrintWriter(new File("simulations.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("File not found: simulations.txt");
                System.exit(1);
            }
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
            if(line != null) {
                weatherTower = new WeatherTower();
                int simulations = Integer.parseInt(line.split(" ")[0]);
                if (simulations < 0)
                    throw new InvalidSimCount(simulations);

                while ((line = reader.readLine()) != null) {
                    String[] tmp = line.split(" ");
                    Flyable flyable = AircraftFactory.newAircraft(tmp[0], tmp[1], Integer.parseInt(tmp[2]),
                            Integer.parseInt(tmp[3]), Integer.parseInt(tmp[4]));
                    flyables.add(flyable);
                }

                for (Flyable flyable : flyables)
                    flyable.registerTower(weatherTower);

                for (int i = 0; i < simulations; i++)
                    weatherTower.changeWeather();

                stxt.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + args[0]);
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error reading file: " + args[0]);
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array out of bounds for file: " + args[0]);
            System.exit(1);
        } catch (InvalidSimCount e) {
            System.out.println(e);
            System.exit(1);
        }

    }

}
