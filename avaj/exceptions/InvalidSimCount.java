package avaj.exceptions;

public class InvalidSimCount extends Exception {
    int simCount;

    public InvalidSimCount(int simCount) {
        this.simCount = simCount;
    }

    public String toString() {
        return "Invalid simulations count: " + simCount;
    }
}
