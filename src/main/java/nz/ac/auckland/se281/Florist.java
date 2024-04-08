package nz.ac.auckland.se281;

public class Florist extends Service {

  public Florist(String bookingReference, String type, int price) {
    super(bookingReference, type, price);
  }

  public void displayMessage(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Floral (" + type + ")", bookingReference);
  }

  public int viewInvoice(String bookingReference, String attendees) {
    MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(type, String.valueOf(price));
    return price;
  }
}
