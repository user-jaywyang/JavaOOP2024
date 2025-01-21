package option1;

/**
 * Represents a ride request event in the rideshare system.
 * This event occurs when a ride request is created in the system.
 * The event contains information about the ride request.
 */
public class RideRequestEvent extends Event {

  /** The ride request associated with this event */
  private final RideshareRequest rideRequest;

  /**
   * Constructs a RideRequestEvent.
   * This event is triggered when a new ride request is made in the system.
   * The event stores information about the time the event occurs and the ride request itself.
   *
   * @param eventTime   The time the event occurs, in milliseconds.
   * @param rideRequest The ride request associated with this event.
   */
  public RideRequestEvent(long eventTime, RideshareRequest rideRequest) {
    super(eventTime);  // Call the parent class (Event) constructor
    this.rideRequest = rideRequest;
  }

  /**
   * Gets the ride request associated with this event.
   *
   * @return The ride request associated with the event.
   */
  public RideshareRequest getRideRequest() {
    return rideRequest;
  }

  /**
   * Processes the event.
   * This method contains the logic for handling the ride request event.
   * For example, it might enqueue the ride request or initiate a driver assignment.
   * This method can be expanded as needed.
   */
  @Override
  public void process() {
    // Placeholder for processing logic, e.g., enqueue the ride request
    System.out.println("Processing ride request: " + rideRequest);
  }

  /**
   * Returns a string representation of this RideRequestEvent.
   * This includes the event time and the associated ride request information.
   *
   * @return A string representing the RideRequestEvent.
   */
  @Override
  public String toString() {
    return "RideRequestEvent{" +
        "eventTime=" + eventTime +
        ", rideRequest=" + rideRequest +
        '}';
  }
}
