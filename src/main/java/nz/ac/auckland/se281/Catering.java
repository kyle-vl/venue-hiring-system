package nz.ac.auckland.se281;

public abstract class Catering {
    private String bookingReference;
    private String type;
    private int price;

    public Catering (String bookingReference, String type, int price) {
        this.bookingReference = bookingReference;
        this.type = type;
        this.price = price;
    }

    public abstract void displayMessage(String bookingReference);

}