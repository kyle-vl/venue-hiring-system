package nz.ac.auckland.se281;

public class Breakfast extends Catering {

  public Breakfast(String bookingReference) {
    super(bookingReference, "Breakfast", 15);
  }

  public void displayMessage(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Catering (Breakfast)", bookingReference);
  }
}
