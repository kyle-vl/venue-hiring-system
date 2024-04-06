package nz.ac.auckland.se281;

public class Venue {
    private String name;
    private String code;
    private String capacity;
    private String hireFee;

    public Venue(String name, String code, String capacity, String hireFee) {
        this.name = name;
        this.code = code;
        this.capacity = capacity;
        this.hireFee = hireFee;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getHireFee() {
        return hireFee;
    }
}