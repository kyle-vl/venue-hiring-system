package nz.ac.auckland.se281;

public class Catering extends Service {

  public Catering(String bookingReference, String type, int price) {
    super(bookingReference, type, price);
  }

  public void displayMessage(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Catering (" + type + ")", bookingReference);
  }
}
