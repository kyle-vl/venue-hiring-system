package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  
  String systemDate;
  private ArrayList<Venue> venues = new ArrayList<>();

  public VenueHireSystem() {}

  public void printVenues() {
    // Counting venues
    int venueCount = venues.size();
    if (venueCount == 0) {
      MessageCli.NO_VENUES.printMessage();
      return;
    } countVenues(venueCount);

    // Listing venues
    for (Venue venue : venues) {
      String name = venue.getName();
      String code = venue.getCode();
      String capacity = venue.getCapacity();
      String hireFee = venue.getHireFee();
      MessageCli.VENUE_ENTRY.printMessage(name, code, capacity, hireFee);
    }
  }

  public void createVenue(
    String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // Checking for invalid venueName
    if (venueName == null || venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // Checking for invalid capacityInput
    int capacity = 0;
    try {
      capacity = Integer.parseInt(capacityInput);
      if (capacity < 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        return;
      }
    } catch (NumberFormatException nfe) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }

    // Checking for invalid hireFeeInput
    int hireFee = 0;
    try {
      hireFee = Integer.parseInt(hireFeeInput);
      if (hireFee < 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return;
      }
    } catch (NumberFormatException nfe) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }

    // Checking for invalid venueCode
    for (Venue venue : venues) {
      if (venue.getCode().equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venueName);
        return;
      }
    }

    Venue newVenue = new Venue(venueName, venueCode, capacityInput, hireFeeInput);
    venues.add(newVenue);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    }

  public void countVenues(int venueCount) {

    switch (venueCount) {
      case 1: 
        MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
        break;
      case 2:
        MessageCli.NUMBER_VENUES.printMessage("are", "two", "s");
        break;
      case 3:
        MessageCli.NUMBER_VENUES.printMessage("are", "three", "s");
        break;
      case 4:
        MessageCli.NUMBER_VENUES.printMessage("are", "four", "s");
        break;
      case 5:
        MessageCli.NUMBER_VENUES.printMessage("are", "five", "s");
        break;
      case 6:
        MessageCli.NUMBER_VENUES.printMessage("are", "six", "s");
        break;
      case 7:
        MessageCli.NUMBER_VENUES.printMessage("are", "seven", "s");
        break;
      case 8:
        MessageCli.NUMBER_VENUES.printMessage("are", "eight", "s");
        break;
      case 9:
        MessageCli.NUMBER_VENUES.printMessage("are", "nine", "s");
        break;
      default:
        MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(venueCount), "s");
        }
  }
    
  public void setSystemDate(String dateInput) {
    systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  public void printSystemDate() {
    if (systemDate == null || systemDate.isEmpty()) {
      MessageCli.CURRENT_DATE.printMessage("not set");
      return;
    }
    MessageCli.CURRENT_DATE.printMessage(systemDate);
  }

  public void makeBooking(String[] options) {
    // Get individual strings from options 
    String code = options[0];
    String date = options[1];
    String email = options[2];
    String attendees = options[3];
    String name = null;

    // Check system date and venues list
    if (systemDate == null || systemDate.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }

    if (venues.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }

    // Use venue code to find venue name
    for (Venue venue : venues) {
      if (venue.getCode().equals(code)) {
        name = venue.getName();
        break;
      }
    }

    // Create booking
    if (name != null) {
      String reference = BookingReferenceGenerator.generateBookingReference();
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(reference, name, date, attendees);
    }
  }


  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
