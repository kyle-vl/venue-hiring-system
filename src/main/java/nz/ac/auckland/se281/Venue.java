package nz.ac.auckland.se281;

public class Venue {
  private String name;
  private String code;
  private String capacity;
  private String hireFee;
  private String nextAvailable;

  public Venue(String name, String code, String capacity, String hireFee, String nextAvailable) {
    this.name = name;
    this.code = code;
    this.capacity = capacity;
    this.hireFee = hireFee;
    this.nextAvailable = nextAvailable;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public String getCapacity() {
    return capacity;
  }

  public String getHireFee() {
    return hireFee;
  }

  public String getNextAvailable() {
    return nextAvailable;
  }

  public void setNextAvailable(String nextAvailable) {
    this.nextAvailable = nextAvailable;
  }

  // This method is called whenever the systemDate is changed
  public void adjustNextAvailable(String systemDate) {
    // If the system date has been set for the first time, set all venues to available today
    if (getNextAvailable().equals("SYSTEM DATE NOT SET")) {
      setNextAvailable(systemDate);
      return;
    }

    String[] systemDateParts = systemDate.split("/");
    int systemDay = Integer.parseInt(systemDateParts[0]);
    int systemMonth = Integer.parseInt(systemDateParts[1]);
    int systemYear = Integer.parseInt(systemDateParts[2]);

    String[] nextAvailableParts = nextAvailable.split("/");
    int nextAvailableDayInt = Integer.parseInt(nextAvailableParts[0]);
    int nextAvailableMonthInt = Integer.parseInt(nextAvailableParts[1]);
    int nextAvailableYearInt = Integer.parseInt(nextAvailableParts[2]);

    boolean adjusted = false;
    if (systemYear > nextAvailableYearInt) {
      adjusted = true;
      nextAvailableYearInt = systemYear;
    }
    if (systemMonth > nextAvailableMonthInt && adjusted == false) {
      adjusted = true;
      nextAvailableMonthInt = systemMonth;
    }
    if (systemDay > nextAvailableDayInt && adjusted == false) {
      nextAvailableDayInt = systemDay;
    }

    // Add leading zero if month or day is one digit
    String nextAvailableDay = String.valueOf(nextAvailableDayInt);
    if (nextAvailableDay.length() == 1) {
      nextAvailableDay = "0" + nextAvailableDay;
    }

    String nextAvailableMonth = String.valueOf(nextAvailableMonthInt);
    if (nextAvailableMonth.length() == 1) {
      nextAvailableMonth = "0" + nextAvailableMonth;
    }

    // Assume year will always be two digits
    String nextAvailableYear = String.valueOf(nextAvailableYearInt);

    String nextAvailable = nextAvailableDay + "/" + nextAvailableMonth + "/" + nextAvailableYear;
    setNextAvailable(nextAvailable);
  }

  public void changeNextAvailable(String partyDate) {
    String[] partyDateParts = partyDate.split("/");
    int partyDay = Integer.parseInt(partyDateParts[0]);
    int partyMonth = Integer.parseInt(partyDateParts[1]);
    int partyYear = Integer.parseInt(partyDateParts[2]);

    String[] nextAvailableParts = nextAvailable.split("/");
    int nextAvailableDayInt = Integer.parseInt(nextAvailableParts[0]);
    int nextAvailableMonthInt = Integer.parseInt(nextAvailableParts[1]);
    int nextAvailableYearInt = Integer.parseInt(nextAvailableParts[2]);

    boolean adjusted = false;
    
    if (partyDay == nextAvailableDayInt) {
      adjusted = true;
      nextAvailableDayInt = partyDay + 1;
    }
    if (partyMonth == nextAvailableMonthInt && adjusted == false) {
      adjusted = true;
      nextAvailableMonthInt = partyMonth + 1;
    }
    if (partyYear == nextAvailableYearInt && adjusted == false) {
      nextAvailableYearInt = partyYear + 1;
    }

    // Add leading zero if month or day is one digit
    String nextAvailableDay = String.valueOf(nextAvailableDayInt);
    if (nextAvailableDay.length() == 1) {
      nextAvailableDay = "0" + nextAvailableDay;
    }

    String nextAvailableMonth = String.valueOf(nextAvailableMonthInt);
    if (nextAvailableMonth.length() == 1) {
      nextAvailableMonth = "0" + nextAvailableMonth;
    }

    // Assume year will always be two digits
    String nextAvailableYear = String.valueOf(nextAvailableYearInt);

    String nextAvailable = nextAvailableDay + "/" + nextAvailableMonth + "/" + nextAvailableYear;
    setNextAvailable(nextAvailable);
  }
}
