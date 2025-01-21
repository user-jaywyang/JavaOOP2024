package option1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Represents a ride request in the rideshare system.
 * This class includes details about the ride such as the customer, starting location, destination,
 * distance, ride type, request time, and priority.
 */
public class RideshareRequest implements Comparable<RideshareRequest> {

  // Fields
  /** The ID of the customer making the ride request. */
  private final String customerId;

  /** The starting location of the ride. */
  private final String startingLocation;

  /** The destination location of the ride. */
  private final String destination;

  /** The anticipated distance for the ride (in miles or kilometers). */
  private final double distance;

  /** The type of ride (e.g., "Express", "Standard", etc.). */
  private final String rideType;

  /** The time the ride request was made (in milliseconds since epoch). */
  private final long requestTime;

  /** The priority level of the ride request. */
  private final int priority;

  // Constants for ride type priorities
  /** Priority level for express rides. */
  private static final int EXPRESS_PRIORITY = 4;

  /** Priority level for standard rides. */
  private static final int STANDARD_PRIORITY = 3;

  /** Priority level for "wait-and-save" rides. */
  private static final int WAIT_AND_SAVE_PRIORITY = 2;

  /** Priority level for environmentally conscious rides. */
  private static final int ENVIRONMENTAL_PRIORITY = 1;

  /** Constant for int value zero. Used for comparisons. */
  private static final int ZERO = 0;

  /**
   * Constructs a RideshareRequest object.
   *
   * @param customerId      The ID of the customer making the ride request.
   * @param startingLocation The starting location of the ride.
   * @param destination      The destination of the ride.
   * @param distance         The anticipated distance of the ride.
   * @param rideType         The type of the ride (e.g., "Express", "Standard").
   * @param requestTime      The time the ride was requested (in milliseconds since epoch).
   */
  @JsonCreator
  public RideshareRequest(
      @JsonProperty("customerId") String customerId,
      @JsonProperty("startLocation") String startingLocation,
      @JsonProperty("endLocation") String destination,
      @JsonProperty("distance") double distance,
      @JsonProperty("type") String rideType,
      @JsonProperty("requestTime") long requestTime) {
    this.customerId = customerId;
    this.startingLocation = startingLocation;
    this.destination = destination;
    this.distance = distance;
    this.rideType = rideType;
    this.requestTime = requestTime;
    this.priority = calculatePriority();
  }

  // Getters
  /**
   * Gets the customer ID associated with the ride request.
   *
   * @return The customer ID.
   */
  public String getCustomerId() {
    return customerId;
  }

  /**
   * Gets the starting location of the ride.
   *
   * @return The starting location.
   */
  public String getStartingLocation() {
    return startingLocation;
  }

  /**
   * Gets the destination location of the ride.
   *
   * @return The destination location.
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Gets the anticipated distance for the ride.
   *
   * @return The distance in miles or kilometers.
   */
  public double getDistance() {
    return distance;
  }

  /**
   * Gets the type of the ride (e.g., "Express", "Standard").
   *
   * @return The ride type.
   */
  public String getRideType() {
    return rideType;
  }

  /**
   * Gets the time the ride was requested.
   *
   * @return The request time in milliseconds since epoch.
   */
  public long getRequestTime() {
    return requestTime;
  }

  /**
   * Gets the priority of the ride request.
   *
   * @return The priority level.
   */
  public int getPriority() {
    return priority;
  }

  /**
   * Calculates the priority of the ride request based on the ride type.
   *
   * @return The calculated priority level.
   */
  private int calculatePriority() {
    switch (rideType.toLowerCase()) {
      case "express pick-up":
        return EXPRESS_PRIORITY;
      case "standard pick-up":
        return STANDARD_PRIORITY;
      case "wait-and-save pick-up":
        return WAIT_AND_SAVE_PRIORITY;
      case "environmentally conscious pick-up":
        return ENVIRONMENTAL_PRIORITY;
      default:
        throw new IllegalArgumentException("Invalid ride type: " + rideType);
    }
  }

  /**
   * Compares two ride requests based on priority and request time.
   *
   * The comparison is done first by priority (higher priority first), and
   * then by request time (earlier requests first).
   *
   * @param other The other ride request to compare to.
   * @return A negative integer, zero, or a positive integer as this ride's
   *         priority is higher, equal to, or lower than the other ride's priority,
   *         or if priorities are equal, based on the request time.
   */
  @Override
  public int compareTo(RideshareRequest other) {
    if (this.priority != other.priority) {
      return Integer.compare(other.priority, this.priority); // Higher priority first
    }
    return Long.compare(this.requestTime, other.requestTime); // Earlier requests first
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RideshareRequest)) return false;
    RideshareRequest that = (RideshareRequest) o;
    return Double.compare(that.distance, distance) == ZERO &&
        requestTime == that.requestTime &&
        priority == that.priority &&
        Objects.equals(customerId, that.customerId) &&
        Objects.equals(startingLocation, that.startingLocation) &&
        Objects.equals(destination, that.destination) &&
        Objects.equals(rideType, that.rideType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, startingLocation, destination, distance, rideType, requestTime, priority);
  }

  @Override
  public String toString() {
    return "RideshareRequest{" +
        "customerId='" + customerId + '\'' +
        ", startingLocation='" + startingLocation + '\'' +
        ", destination='" + destination + '\'' +
        ", distance=" + distance +
        ", rideType='" + rideType + '\'' +
        ", requestTime=" + requestTime +
        ", priority=" + priority +
        '}';
  }
}
