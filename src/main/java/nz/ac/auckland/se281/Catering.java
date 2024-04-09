package nz.ac.auckland.se281;

public class Catering extends Service {

  public Catering(String bookingReference, String type, int cost) {
    super(bookingReference, type, cost);
  }

  public void displayMessage(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Catering (" + type + ")", bookingReference);
  }

  public void viewInvoice() {
    MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(type, String.valueOf(cost));
  }
}
