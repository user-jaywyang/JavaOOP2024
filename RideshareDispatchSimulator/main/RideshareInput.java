package option1;

import java.util.List;

/**
 * Wrapper class for JSON input data representing the drivers and ride requests.
 * This class is used to hold the list of drivers and ride requests read from the input JSON file.
 */
public class RideshareInput {

  /** List of drivers available for the rideshare simulation. */
  private List<Driver> drivers;

  /** List of ride requests to be processed in the simulation. */
  private List<RideshareRequest> rideRequests;

  /**
   * Gets the list of drivers.
   *
   * @return A list of drivers available in the simulation.
   */
  public List<Driver> getDrivers() {
    return drivers;
  }

  /**
   * Sets the list of drivers.
   *
   * @param drivers A list of drivers to set.
   */
  public void setDrivers(List<Driver> drivers) {
    this.drivers = drivers;
  }

  /**
   * Gets the list of ride requests.
   *
   * @return A list of ride requests to be processed.
   */
  public List<RideshareRequest> getRideRequests() {
    return rideRequests;
  }

  /**
   * Sets the list of ride requests.
   *
   * @param rideRequests A list of ride requests to set.
   */
  public void setRideRequests(List<RideshareRequest> rideRequests) {
    this.rideRequests = rideRequests;
  }
}
