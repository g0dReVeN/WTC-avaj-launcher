package avaj.simulator.aircraft;

import avaj.simulator.Simulator;
import avaj.simulator.WeatherTower;
import avaj.weather.Coordinates;

import java.util.*;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon (String name, Coordinates coordinates) {
        super(name, coordinates);
    }


    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        Map<String, String> message = new HashMap<String, String>();

        message.put("SUN", "I'm glad it's finally hot enough to complain about how hot it is.");
        message.put("RAIN", "Thank GOD I brought my umbrella.");
        message.put("FOG", "I tried to grab the fog but I mist.");
        message.put("SNOW", "Everyday I'm shovelling.");

        switch (weather) {
            case "SUN": this.coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
                        break;
            case "RAIN": this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
                break;
            case "FOG": this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
                break;
            case "SNOW": this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
                break;
        }


        Simulator.stxt.println("Baloon#" + this.name + "(" + this.id + "): " + message.get(weather));

        if (this.coordinates.getHeight() <= 0)
        {
            Simulator.stxt.println("Baloon#" + this.name + "(" + this.id + "): Landing Coordinates: " + coordinates.getLongitude() + " " + coordinates.getLatitude() + " 0");
            Simulator.stxt.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.stxt.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}
