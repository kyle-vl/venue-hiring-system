package nz.ac.auckland.se281;

public class Booking {
    private String name;
    private String date;
    private String code;
    private String reference;

    public Booking(String name, String date, String code, String reference) {
        this.name = name;
        this.date = date;
        this.code = code;
        this.reference = reference;
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

    public String getReference() {
        return reference;
    }
}