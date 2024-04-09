package nz.ac.auckland.se281;

public class Florist extends Service {

  public Florist(String bookingReference, String type, int cost) {
    super(bookingReference, type, cost);
  }

  public void displayMessage(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Floral (" + type + ")", bookingReference);
  }

  public void viewInvoice() {
    MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(type, String.valueOf(cost));
  }
}
