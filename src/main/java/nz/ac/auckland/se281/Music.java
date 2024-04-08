package nz.ac.auckland.se281;

public class Music extends Service {

  public Music(String bookingReference) {
    super(bookingReference, "hasMusic", 500);
  }

  public void displayMessage(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
  }

  public int viewInvoice(String bookingReference, String attendees) {
    final int musicCost = 500;
    MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(String.valueOf(musicCost));
    return musicCost;
  }
}
