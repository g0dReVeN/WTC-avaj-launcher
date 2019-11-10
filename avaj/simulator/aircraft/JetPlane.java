package avaj.simulator.aircraft;

import avaj.simulator.Simulator;
import avaj.simulator.WeatherTower;
import avaj.weather.Coordinates;

import java.util.*;

public class JetPlane extends Aircraft implements Flyable{
    WeatherTower weatherTower;

    JetPlane (String name, Coordinates coordinates) {
        super(name, coordinates);
    }


    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        Map<String, String> message = new HashMap<String, String>();

        message.put("SUN", "I'm sorry for the things I said when it was winter.");
        message.put("RAIN", "Rain is just confetti from the sky.");
        message.put("FOG", "The longer you stare, the more zombies that will appear.");
        message.put("SNOW", "I'm freezing my turbines over here!");

        switch (weather) {
            case "SUN": this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 4);
                break;
            case "RAIN": this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
                break;
            case "FOG": this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
                break;
            case "SNOW": this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() - 7, coordinates.getHeight());
                break;
        }


        Simulator.stxt.println("JetPlane#" + this.name + "(" + this.id + "): " + message.get(weather));

        if (this.coordinates.getHeight() <= 0)
        {
            Simulator.stxt.println("JetPlane#" + this.name + "(" + this.id + "): Landing Coordinates: " + coordinates.getLongitude() + " " + coordinates.getLatitude() + " 0");
            Simulator.stxt.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.stxt.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}
