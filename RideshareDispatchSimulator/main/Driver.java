package option1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a driver in the rideshare system.
 * A driver has a unique ID, a list of completed rides, and a flag indicating availability.
 * The driver can be assigned rides and can complete them, marking themselves as available again.
 */
public class Driver implements Comparable<Driver> {

  /** Unique ID for the driver */
  private final String driverId;

  /** Whether the driver is available or busy (true if available) */
  private boolean available;

  /** List of rides completed by the driver */
  private final List<RideshareRequest> completedRides;

  /**
   * Constructs a Driver object.
   * This constructor initializes the driver with the provided driver ID and sets the driver as available.
   * The completed rides list is also initialized as an empty list.
   *
   * @param driverId The unique ID of the driver.
   */
  @JsonCreator
  public Driver(@JsonProperty("driverId") String driverId) {
    this.driverId = driverId;
    this.available = true; // Drivers are initially available
    this.completedRides = new ArrayList<>();
  }

  // Getters

  /**
   * Gets the unique driver ID.
   *
   * @return The unique driver ID.
   */
  public String getDriverId() {
    return driverId;
  }

  /**
   * Checks whether the driver is available or not.
   *
   * @return true if the driver is available, false if the driver is busy.
   */
  public boolean isAvailable() {
    return available;
  }

  /**
   * Returns a copy of the list of completed rides.
   * This is done to maintain immutability and prevent modification of the original list.
   *
   * @return A copy of the list of completed rides.
   */
  public List<RideshareRequest> getCompletedRides() {
    return new ArrayList<>(completedRides); // Return a copy for immutability
  }

  // Setters

  /**
   * Sets the availability status of the driver.
   *
   * @param available The new availability status. true if the driver is available, false if the driver is busy.
   */
  public void setAvailable(boolean available) {
    this.available = available;
  }

  /**
   * Assigns a ride to the driver, marking them as busy.
   * If the driver is already busy, an exception is thrown.
   *
   * @param ride The ride request assigned to the driver.
   * @throws IllegalStateException if the driver is already busy.
   */
  public void assignRide(RideshareRequest ride) {
    if (!available) {
      throw new IllegalStateException("Driver is already busy.");
    }
    this.available = false; // Mark the driver as busy
  }

  /**
   * Marks a ride as completed and frees up the driver.
   * If the driver is not assigned to any ride, an exception is thrown.
   *
   * @param ride The completed ride request.
   * @throws IllegalStateException if the driver is not currently assigned to a ride.
   */
  public void completeRide(RideshareRequest ride) {
    if (available) {
      throw new IllegalStateException("Driver is not currently assigned to a ride.");
    }
    this.completedRides.add(ride);
    this.available = true; // Mark the driver as available again
  }

  /**
   * Compares two drivers based on their driver IDs in lexicographical order.
   * This is used to sort drivers when necessary, for example in a list or queue.
   *
   * @param other The other driver to compare to.
   * @return A negative integer, zero, or a positive integer as this driver's ID
   *         is lexicographically less than, equal to, or greater than the other driver's ID.
   */
  @Override
  public int compareTo(Driver other) {
    return this.driverId.compareTo(other.driverId);
  }

  /**
   * Checks if this driver is equal to another object.
   * Two drivers are considered equal if they have the same driver ID.
   *
   * @param o The object to compare this driver with.
   * @return true if the given object is a driver and has the same driver ID, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Driver)) return false;
    Driver driver = (Driver) o;
    return driverId.equals(driver.driverId);
  }

  /**
   * Returns the hash code for this driver.
   * The hash code is computed based on the driver's unique ID.
   *
   * @return The hash code of this driver.
   */
  @Override
  public int hashCode() {
    return Objects.hash(driverId);
  }

  /**
   * Returns a string representation of the driver object.
   * This includes the driver ID, availability, and the number of completed rides.
   *
   * @return A string representation of the driver.
   */
  @Override
  public String toString() {
    return "Driver{" +
        "driverId='" + driverId + '\'' +
        ", available=" + available +
        ", completedRides=" + completedRides.size() +
        '}';
  }
}
