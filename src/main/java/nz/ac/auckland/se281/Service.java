package nz.ac.auckland.se281;

public abstract class Service {
  protected String bookingReference;
  protected String type;
  protected int price;

  public Service(String bookingReference, String type, int price) {
    this.bookingReference = bookingReference;
    this.type = type;
    this.price = price;
  }

  public abstract void displayMessage(String bookingReference);
  public abstract void viewInvoice(String bookingReference, String attendees);
}
