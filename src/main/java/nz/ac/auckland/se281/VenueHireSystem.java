package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  
  private String venueName;
  private String venueCode;
  private String capacityInput;
  private String hireFeeInput;
  private ArrayList<VenueHireSystem> venues = new ArrayList<>();

  public VenueHireSystem() {}

  public void printVenues() {
    int venueCount = venues.size();
    if (venueCount == 0) {
      System.out.println(MessageCli.NO_VENUES);
    } countVenues(venueCount);

    for (VenueHireSystem venue : venues) {
      System.out.println(venue.venueName + " (" + venue.venueCode + ") - " + 
      venue.capacityInput + " people - $" + venue.hireFeeInput + " base hire fee");
    }
    }

  public void createVenue(
    String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    if (venueName == null || venueName.isEmpty()) {
      System.out.println(MessageCli.VENUE_NOT_CREATED_EMPTY_NAME);
      return;
    }

    int capacity = 0;
    try {
      capacity = Integer.parseInt(capacityInput);
      if (capacity < 0) {
        System.out.println("Venue not created: capacity must be a positive number.");
        return;
      }
    } catch (NumberFormatException e) {
      System.out.println("Venue not created: capacity must be a number.");
      return;
    }

    int hireFee = 0;
    try {
      hireFee = Integer.parseInt(hireFeeInput);
      if (hireFee < 0) {
        System.out.println("Venue not created: hire fee must be a positive number.");
        return;
      }
    } catch (NumberFormatException e) {
      System.out.println("Venue not created: hire fee must be a number.");
      return;
    }

    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacityInput = capacityInput;
    this.hireFeeInput = hireFeeInput;
    venues.add(this);

    System.out.println("Successfully created venue '" + venueName + "' (" + venueCode + ").");
    }

  public void countVenues(int venueCount) {
    switch (venueCount) {
      case 1: 
        System.out.println("There is one venue in the system:");
        break;
      case 2:
        System.out.println("There are two venues in the system:");
        break;
      case 3:
        System.out.println("There are three venues in the system:");
        break;
      case 4:
        System.out.println("There are four venues in the system:");
        break;
      case 5:
        System.out.println("There are five venues in the system:");
        break;
      case 6:
        System.out.println("There are six venues in the system:");
        break;
      case 7:
        System.out.println("There are seven venues in the system:");
        break;
      case 8:
        System.out.println("There are eight venues in the system:");
        break;
      case 9:
        System.out.println("There are nine venues in the system:");
        break;
      default:
        System.out.println("There are " + venueCount + " venues in the system:");
        }
  }
    
  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
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
