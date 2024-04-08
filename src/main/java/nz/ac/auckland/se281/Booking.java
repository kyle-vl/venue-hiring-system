package nz.ac.auckland.se281;

public class Booking {
  private String name;
  private String date;
  private String code;
  private String reference;
  private String attendees;

  public Booking(String name, String date, String code, String reference, String attendees) {
    this.name = name;
    this.date = date;
    this.code = code;
    this.reference = reference;
    this.attendees = attendees;
  }

  public String getName() {
    return name;
  }

  public String getDate() {
    return date;
  }

  public String getCode() {
    return code;
  }

  public String getReference() {
    return reference;
  }

  public String getAttendees() {
    return attendees;
  }
}
