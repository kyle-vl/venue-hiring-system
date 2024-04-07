package nz.ac.auckland.se281;

public class Booking {
    private String name;
    private String date;
    private String code;

    public Booking(String name, String date, String code) {
        this.name = name;
        this.date = date;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }
}