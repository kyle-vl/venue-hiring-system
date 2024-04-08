package nz.ac.auckland.se281;

public class Booking {
  private String name;
  private String date;
  private String code;
  private String reference;
  private String attendees;
  private String email;

  public Booking(
      String name, String date, String code, String reference, String attendees, String email) {
    this.name = name;
    this.date = date;
    this.code = code;
    this.reference = reference;
    this.attendees = attendees;
    this.email = email;
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

  public String getEmail() {
    return email;
  }
}
