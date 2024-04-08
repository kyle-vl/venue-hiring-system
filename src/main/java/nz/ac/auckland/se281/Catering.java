package nz.ac.auckland.se281;

public abstract class Catering {
  protected String bookingReference;
  protected String type;
  protected int price;

  public Catering(String bookingReference, String type, int price) {
    this.bookingReference = bookingReference;
    this.type = type;
    this.price = price;
  }

  public abstract void displayMessage(String bookingReference);
}
