package avaj.simulator.aircraft;

import avaj.simulator.Simulator;
import avaj.simulator.WeatherTower;
import avaj.weather.Coordinates;

import java.util.*;

public class Helicopter extends Aircraft implements Flyable{
    WeatherTower weatherTower;

    Helicopter (String name, Coordinates coordinates) {
        super(name, coordinates);
    }


    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        Map<String, String> message = new HashMap<String, String>();

        message.put("SUN", "I'm flying on sunshine. (Wow!)");
        message.put("RAIN", "Some people feel the rain, others just get wet.");
        message.put("FOG", "Fog you!");
        message.put("SNOW", "I prefer cocaine.");

        switch (weather) {
            case "SUN": this.coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
                break;
            case "RAIN": this.coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
                break;
            case "FOG": this.coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
                break;
            case "SNOW": this.coordinates = new Coordinates(coordinates.getLongitude() - 12, coordinates.getLatitude(), coordinates.getHeight());
                break;
        }


        Simulator.stxt.println("Helicopter#" + this.name + "(" + this.id + "): " + message.get(weather));

        if (this.coordinates.getHeight() <= 0)
        {
            Simulator.stxt.println("Helicopter#" + this.name + "(" + this.id + "): Landing Coordinates: " + coordinates.getLongitude() + " " + coordinates.getLatitude() + " 0");
            Simulator.stxt.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.stxt.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}
