package option1;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Simulates a rideshare dispatch system.
 * The simulator manages a set of drivers and ride requests, assigns rides to available drivers,
 * and calculates key statistics such as average wait time and rides per driver.
 */
public class RideshareDispatchSimulator {

  private final int driverCount; // Total number of drivers available in the simulation
  private final Queue<Driver> availableDrivers; // Queue of available drivers
  private final PriorityQueue<RideshareRequest> pendingRequests; // Priority queue of pending requests
  private final PriorityQueue<Event> eventQueue; // Priority queue of events to be processed

  private int totalWaitTime = 0; // Total accumulated wait time across all rides
  private int totalRidesCompleted = 0; // Total number of rides completed

  /** Constant for int value zero. */
  private static final int ZERO = 0;

  /** Constant for int value one. */
  private static final int ONE = 1;

  /** Constant for double value 60.0 (used for time calculations). */
  private static final double SIXTYD = 60.0;

  /** Constant for int value 3600 (used for time calculations). */
  private static final int THIRTYSIXHUNDRED = 3600;

  /** Constant for int value one thousand (used for time calculations). */
  private static final int THOUSAND = 1000;


  /**
   * Constructs the simulator with the specified number of drivers.
   *
   * @param driverCount The number of drivers available in the simulation.
   */
  public RideshareDispatchSimulator(int driverCount) {
    this.driverCount = driverCount;
    this.availableDrivers = new PriorityQueue<>();
    this.pendingRequests = new PriorityQueue<>();
    this.eventQueue = new PriorityQueue<>();

    // Initialize drivers and add them to the available drivers queue
    for (int i = ONE; i <= driverCount; i++) {
      availableDrivers.add(new Driver("Driver-" + i)); // Assign unique driver IDs like Driver-1, Driver-2, etc.
    }
  }

  /**
   * Adds a new ride request to the simulation event queue.
   * A new event is created for the ride request, which will be processed in the simulation.
   *
   * @param rideRequest The ride request to add to the simulation.
   */
  public void addRideRequest(RideshareRequest rideRequest) {
    eventQueue.add(new RideRequestEvent(rideRequest.getRequestTime(), rideRequest));
  }

  /**
   * Processes the next event in the simulation event queue.
   * Depending on the event type, it either processes a ride request or a completed ride event.
   */
  public void processNextEvent() {
    if (eventQueue.isEmpty()) return;

    Event event = eventQueue.poll(); // Retrieve and remove the next event from the queue
    if (event instanceof RideRequestEvent) {
      processRideRequest((RideRequestEvent) event);
    } else if (event instanceof RideFinishedEvent) {
      processRideFinished((RideFinishedEvent) event);
    }
  }

  /**
   * Processes a ride request event, either assigning an available driver to the ride
   * or adding the request to the pending queue if no drivers are available.
   *
   * @param event The ride request event to process.
   */
  private void processRideRequest(RideRequestEvent event) {
    RideshareRequest request = event.getRideRequest();
    if (!availableDrivers.isEmpty()) {
      Driver driver = availableDrivers.poll(); // Get an available driver
      assignRide(driver, request); // Assign the ride to the driver
    } else {
      pendingRequests.add(request); // No available drivers, so add the request to the pending queue
    }
  }

  /**
   * Processes a ride finished event. This updates the driver's status and checks if there are any pending requests
   * to assign to the now-available driver.
   *
   * @param event The ride finished event to process.
   */
  private void processRideFinished(RideFinishedEvent event) {
    Driver driver = event.getDriver();
    RideshareRequest completedRide = event.getCompletedRide();
    driver.completeRide(completedRide); // Mark the ride as completed for the driver
    totalRidesCompleted++;
    availableDrivers.add(driver); // Add the driver back to the available drivers queue

    // Check if there are any pending requests and assign the ride to the driver
    if (!pendingRequests.isEmpty()) {
      RideshareRequest nextRequest = pendingRequests.poll();
      totalWaitTime += event.getEventTime() - nextRequest.getRequestTime(); // Calculate wait time
      assignRide(driver, nextRequest);
    }
  }

  /**
   * Assigns a ride to a driver. The finish time for the ride is calculated based on the ride's distance,
   * and a RideFinishedEvent is added to the event queue to mark the ride completion.
   *
   * @param driver The driver to assign the ride to.
   * @param request The ride request to assign to the driver.
   */
  private void assignRide(Driver driver, RideshareRequest request) {
    driver.assignRide(request); // Mark the driver as busy
    long finishTime = System.currentTimeMillis() + (long) (request.getDistance() / SIXTYD * THIRTYSIXHUNDRED * THOUSAND); // Calculate finish time
    eventQueue.add(new RideFinishedEvent(finishTime, driver, request)); // Add the finish event to the queue
  }

  /**
   * Runs the simulation, processing all events until the event queue is empty.
   */
  public void runSimulation() {
    while (!eventQueue.isEmpty()) {
      processNextEvent();
    }
  }

  /**
   * Gets the average wait time for the simulation. This is the total wait time divided by the total number of rides completed.
   *
   * @return The average wait time in milliseconds.
   */
  public double getAverageWaitTime() {
    return totalRidesCompleted == ZERO ? ZERO : (double) totalWaitTime / totalRidesCompleted;
  }

  /**
   * Gets the average number of rides completed per driver. This is the total number of rides completed
   * divided by the total number of drivers in the simulation.
   *
   * @return The average number of rides completed per driver.
   */
  public double getAverageRidesPerDriver() {
    return (double) totalRidesCompleted / driverCount;
  }
}
