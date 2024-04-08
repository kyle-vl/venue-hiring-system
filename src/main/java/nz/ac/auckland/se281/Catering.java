package nz.ac.auckland.se281;

public class Catering extends Service {

  public Catering(String bookingReference, String type, int price) {
    super(bookingReference, type, price);
  }

  public void displayMessage(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Catering (" + type + ")", bookingReference);
  }

  public int viewInvoice(String bookingReference, String attendees) {
    int attendeesInt = Integer.parseInt(attendees);
    int totalPrice = price * attendeesInt;
    MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(type, String.valueOf(totalPrice));
    return totalPrice;
  }

  public String getReference() {
    return bookingReference;
  }
}
