package nz.ac.auckland.se281;

public abstract class Service {
  protected String bookingReference;
  protected String type;
  protected int cost;

  public Service(String bookingReference, String type, int cost) {
    this.bookingReference = bookingReference;
    this.type = type;
    this.cost = cost;
  }

  public abstract void displayMessage(String bookingReference);

  public abstract void viewInvoice();

  public String getReference() {
    return bookingReference;
  }

  public int getCost() {
    return cost;
  }
}
