package option1;

/**
 * Represents a ride finished event in the rideshare system.
 * This event occurs when a ride is completed by a driver.
 * The event contains information about the driver and the completed ride.
 */
public class RideFinishedEvent extends Event {

  /** The driver associated with the completed ride */
  private final Driver driver;

  /** The ride request associated with the completed ride */
  private final RideshareRequest completedRide;

  /**
   * Constructs a RideFinishedEvent.
   * This event is triggered when a driver completes a ride. The event stores information about the time
   * the event occurs, the driver who completed the ride, and the ride request that was completed.
   *
   * @param eventTime      The time the event occurs, in milliseconds.
   * @param driver         The driver who completed the ride.
   * @param completedRide  The ride request associated with the completed ride.
   */
  public RideFinishedEvent(long eventTime, Driver driver, RideshareRequest completedRide) {
    super(eventTime);  // Call the parent class (Event) constructor
    this.driver = driver;
    this.completedRide = completedRide;
  }

  /**
   * Gets the driver associated with the completed ride.
   *
   * @return The driver who completed the ride.
   */
  public Driver getDriver() {
    return driver;
  }

  /**
   * Gets the ride request associated with the completed ride.
   *
   * @return The completed ride request.
   */
  public RideshareRequest getCompletedRide() {
    return completedRide;
  }

  /**
   * Processes the event.
   * This method contains the logic for handling the ride completion event.
   * For example, it might free up the driver or update the driver's statistics.
   * This method can be expanded as needed.
   */
  @Override
  public void process() {
    // Placeholder for processing logic, e.g., free up the driver and update stats
    System.out.println("Processing ride completion for driver: " + driver);
  }

  /**
   * Returns a string representation of this RideFinishedEvent.
   * This includes the event time, driver details, and completed ride information.
   *
   * @return A string representing the RideFinishedEvent.
   */
  @Override
  public String toString() {
    return "RideFinishedEvent{" +
        "eventTime=" + eventTime +
        ", driver=" + driver +
        ", completedRide=" + completedRide +
        '}';
  }
}
