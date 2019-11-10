package avaj.simulator.aircraft;

import avaj.weather.Coordinates;

public class Aircraft {
    protected long          id;
    protected String        name;
    protected Coordinates   coordinates;
    private static long     idCounter = 0;

    protected Aircraft(String name, Coordinates coordinates){
        this.name = name;
        this.coordinates = coordinates;
        this.id = this.idCounter++;
    }

    private long nextId() {
        return idCounter;
    }
}
