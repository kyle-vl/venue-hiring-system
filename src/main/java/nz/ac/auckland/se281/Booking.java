package nz.ac.auckland.se281;

public class Booking {
    private String name;
    private String date;

    public Booking(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}