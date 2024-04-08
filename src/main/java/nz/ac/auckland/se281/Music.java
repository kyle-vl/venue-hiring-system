package nz.ac.auckland.se281;

public class Music extends Service {

  public Music(String bookingReference) {
    super(bookingReference, "hasMusic", 500);
  }

  public void displayMessage(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
  }

  public int viewInvoice(String bookingReference, String attendees) {
    final int MUSIC_COST = 500;
    MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(String.valueOf(MUSIC_COST));
    return MUSIC_COST;
  }
}
