package nz.ac.auckland.se281;

public abstract class CateringType {
    protected String bookingReference;
    protected String type;
    protected int price;

    public CateringType (String bookingReference, String type, int price) {
        this.bookingReference = bookingReference;
        this.type = type;
        this.price = price;
    }

    public abstract void displayMessage(String bookingReference);

}