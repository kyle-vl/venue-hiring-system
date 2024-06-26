package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private String systemDate;
  private ArrayList<Venue> venues = new ArrayList<>();
  private ArrayList<Booking> bookings = new ArrayList<>();
  private ArrayList<Service> services = new ArrayList<>();

  public VenueHireSystem() {}

  public void printVenues() {
    // Counting venues
    int venueCount = venues.size();
    if (venueCount == 0) {
      MessageCli.NO_VENUES.printMessage();
      return;
    }
    displayVenueCount(venueCount);

    // Adjust method must be run multiple times to correctly set next available dates.
    // It needs to be run at least once, hence the +1 to ensure the system is up to date.
    for (int i = 0; i < bookings.size() + 1; i++) {
      adjustNextAvailable();
    }

    // Listing venues
    for (Venue venue : venues) {
      String name = venue.getName();
      String code = venue.getCode();
      String capacity = venue.getCapacity();
      String hireFee = venue.getHireFee();
      String nextAvailable = venue.getNextAvailable();
      MessageCli.VENUE_ENTRY.printMessage(name, code, capacity, hireFee, nextAvailable);
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
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.getName());
        return;
      }
    }

    Venue newVenue =
        new Venue(venueName, venueCode, capacityInput, hireFeeInput, "SYSTEM DATE NOT SET");
    venues.add(newVenue);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public void displayVenueCount(int venueCount) {

    // Should print the number as a word if less than 10 venues
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
        // If more than 10 venues, print digits
      default:
        MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(venueCount), "s");
    }
  }

  public void setSystemDate(String dateInput) {
    systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(systemDate);
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

    // Check system date, venues list, and attendees
    if (systemDate == null || systemDate.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
    }

    if (venues.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }

    boolean venueFound = false;
    int capacityInt = 0;
    int attendeesAdjusted = 0;

    int attendeesInt = Integer.parseInt(attendees);

    // Use venue code to find venue name
    for (Venue venue : venues) {
      if (venue.getCode().equals(code)) {
        name = venue.getName();
        venueFound = true;
        String capacity = venue.getCapacity();

        // Adjust attendees count if not valid
        capacityInt = Integer.parseInt(venue.getCapacity());
        if (capacityInt / 4 > attendeesInt) {
          attendeesAdjusted = capacityInt / 4;
          String attendeesOld = attendees;
          attendees = String.valueOf(attendeesAdjusted);
          MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(attendeesOld, attendees, capacity);
        }
        if (attendeesInt > capacityInt) {
          attendeesAdjusted = capacityInt;
          String attendeesOld = attendees;
          attendees = String.valueOf(attendeesAdjusted);
          MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(attendeesOld, attendees, capacity);
        }
        break;
      }
    }

    // Venue not found
    if (venueFound = false) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(code);
      return;
    }

    // Checks if venue is already booked
    for (Booking booking : bookings) {
      if (booking.getName().equals(name) && booking.getDate().equals(date)) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(name, date);
        return;
      }
    }

    // Create booking
    if (name != null) {
      String reference = BookingReferenceGenerator.generateBookingReference();
      Booking newBooking = new Booking(name, date, code, reference, attendees, email);
      bookings.add(newBooking);
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(reference, name, date, attendees);
    }
  }

  public void printBookings(String venueCode) {

    // Find venue
    boolean venueFound = false;
    boolean booked = false;
    String venueSelected = null;
    for (Venue venue : venues) {
      if (venue.getCode().equals(venueCode)) {
        venueFound = true;
        venueSelected = venue.getName();
        break;
      }
    }

    if (venueFound == false && venueSelected == null) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }

    MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueSelected);

    for (Booking booking : bookings) {
      if (booking.getCode().equals(venueCode)) {
        booked = true;
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(booking.getReference(), booking.getDate());
      }
    }

    if (booked == false) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueSelected);
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    int attendees = 0;

    // Find booking using reference
    for (Booking booking : bookings) {
      if (booking.getReference().equals(bookingReference)) {
        // If booking found, add catering chosen by user
        attendees = Integer.parseInt(booking.getAttendees());

        String cateringTypeName = cateringType.getName();
        int cateringTypeCost = cateringType.getCostPerPerson();

        int cateringCost = attendees * cateringTypeCost;

        Catering newCatering = new Catering(bookingReference, cateringTypeName, cateringCost);
        newCatering.displayMessage(bookingReference);
        services.add(newCatering);
        return;
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
    return;
  }

  public void addServiceMusic(String bookingReference) {
    // Find booking using reference
    for (Booking booking : bookings) {
      if (booking.getReference().equals(bookingReference)) {
        // If booking found, add music
        Music newMusic = new Music(bookingReference);
        newMusic.displayMessage(bookingReference);
        services.add(newMusic);
        return;
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
    return;
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // Find booking using reference
    for (Booking booking : bookings) {
      if (booking.getReference().equals(bookingReference)) {
        // If booking found, add floral chosen by user
        String floristTypeName = floralType.getName();
        int floristTypeCost = floralType.getCost();

        Florist newFlorist = new Florist(bookingReference, floristTypeName, floristTypeCost);
        newFlorist.displayMessage(bookingReference);
        services.add(newFlorist);
        return;
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
    return;
  }

  public void viewInvoice(String bookingReference) {
    String attendees = null;
    String bookingCode = null;
    String email = null;
    String partyDate = null;
    int totalCost = 0;

    // Find booking using reference
    for (Booking booking : bookings) {
      if (booking.getReference().equals(bookingReference)) {
        attendees = booking.getAttendees();
        bookingCode = booking.getCode();
        email = booking.getEmail();
        partyDate = booking.getDate();
        break;
      }
    }

    // If booking not found, return
    if (attendees == null || bookingCode == null || email == null || partyDate == null) {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      return;
    }

    // Find hire fee for venue
    for (Venue venue : venues) {
      if (venue.getCode().equals(bookingCode)) {
        int hireFeeInt = Integer.parseInt(venue.getHireFee());
        totalCost += hireFeeInt;

        // Print top half of invoice, with booking details
        MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
            bookingReference, email, systemDate, partyDate, attendees, venue.getName());

        // Print venue fee
        MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(venue.getHireFee());
        break;
      }
    }

    // Print and add to total cost all services attached to reference
    for (Service service : services) {
      if (service.getReference().equals(bookingReference)) {
        service.viewInvoice();
        totalCost += service.getCost();
      }
    }

    // Print bottom half of invoice, with total cost
    MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(String.valueOf(totalCost));
  }

  public void adjustNextAvailable() {
    // System date must be set to adjust next available dates
    if (systemDate == null || systemDate.isEmpty()) {
      return;
    }

    for (Venue venue : venues) {
      // If system date has been set for first time, iniitalise next available
      if (venue.getNextAvailable().equals("SYSTEM DATE NOT SET")) {
        venue.setNextAvailable(systemDate);
      } else {
        String[] nextAvailableDate = venue.getNextAvailable().split("/");
        String[] systemDateArray = systemDate.split("/");

        int nextAvailableYear = Integer.parseInt(nextAvailableDate[2]);
        int nextAvailableMonth = Integer.parseInt(nextAvailableDate[1]);
        int nextAvailableDay = Integer.parseInt(nextAvailableDate[0]);

        int systemYear = Integer.parseInt(systemDateArray[2]);
        int systemMonth = Integer.parseInt(systemDateArray[1]);
        int systemDay = Integer.parseInt(systemDateArray[0]);

        // If next available is behind system date, set to system date
        if (nextAvailableYear < systemYear) {
          venue.setNextAvailable(systemDate);
        } else if (nextAvailableYear == systemYear) {
          if (nextAvailableMonth < systemMonth) {
            venue.setNextAvailable(systemDate);
          } else if (nextAvailableMonth == systemMonth) {
            if (nextAvailableDay < systemDay) {
              venue.setNextAvailable(systemDate);
            }
          }
        }
      }
    }

    // Check bookings and adjust next available date if needed
    for (Venue venue : venues) {
      for (Booking booking : bookings) {
        if (booking.getCode().equals(venue.getCode())) {
          String[] bookingDate = booking.getDate().split("/");
          String[] nextAvailableDate = venue.getNextAvailable().split("/");

          int bookingYear = Integer.parseInt(bookingDate[2]);
          int bookingMonth = Integer.parseInt(bookingDate[1]);
          int bookingDay = Integer.parseInt(bookingDate[0]);

          int nextAvailableYear = Integer.parseInt(nextAvailableDate[2]);
          int nextAvailableMonth = Integer.parseInt(nextAvailableDate[1]);
          int nextAvailableDay = Integer.parseInt(nextAvailableDate[0]);

          // If booking falls on same day, increment next available day
          if (bookingYear == nextAvailableYear
              && bookingMonth == nextAvailableMonth
              && bookingDay == nextAvailableDay) {
            String nextAvailableDayString = String.valueOf(nextAvailableDay + 1);

            // Add leading zeros if needed for day and month
            if (nextAvailableDayString.length() == 1) {
              nextAvailableDayString = "0" + nextAvailableDayString;
            }
            String nextAvailableMonthString = String.valueOf(nextAvailableMonth);
            if (nextAvailableMonthString.length() == 1) {
              nextAvailableMonthString = "0" + nextAvailableMonthString;
            }

            // Concatenate strings and set next available
            venue.setNextAvailable(
                nextAvailableDayString + "/" + nextAvailableMonthString + "/" + nextAvailableYear);
          }
        }
      }
    }
  }
}
