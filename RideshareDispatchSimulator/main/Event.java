package option1;

import java.util.Objects;

/**
 * Represents an event in the rideshare system.
 * Events have a timestamp to indicate when they occur, and each event needs to be processed.
 * This is an abstract class, and concrete subclasses will define specific types of events.
 */
public abstract class Event implements Comparable<Event> {

  /** The time at which the event occurs (in milliseconds) */
  protected final long eventTime;

  /**
   * Constructs an Event with the given event time.
   * The event time represents when the event is supposed to occur in the simulation.
   *
   * @param eventTime The time the event occurs, in milliseconds.
   */
  public Event(long eventTime) {
    this.eventTime = eventTime;
  }

  /**
   * Gets the time of the event.
   * This method returns the time at which the event was scheduled to occur.
   *
   * @return The event time in milliseconds.
   */
  public long getEventTime() {
    return eventTime;
  }

  /**
   * Processes the event.
   * This is an abstract method, meaning it will be implemented by subclasses to define
   * how each specific event is processed in the rideshare system.
   */
  public abstract void process();

  /**
   * Compares two events based on their event times.
   * Events are compared chronologically, meaning that events occurring earlier will be considered "less" than
   * those occurring later. This is useful for sorting events in order of execution.
   *
   * @param other The other event to compare to.
   * @return A negative integer, zero, or a positive integer as this event's time is earlier than, equal to,
   *         or later than the other event's time.
   */
  @Override
  public int compareTo(Event other) {
    return Long.compare(this.eventTime, other.eventTime); // Events occur in chronological order
  }

  /**
   * Checks if this event is equal to another object.
   * Two events are considered equal if they occur at the same time (eventTime).
   *
   * @param o The object to compare this event to.
   * @return true if the other object is an instance of Event and the event times are the same, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Event)) return false;
    Event event = (Event) o;
    return eventTime == event.eventTime;
  }

  /**
   * Returns the hash code for this event.
   * The hash code is computed based on the event time, ensuring that two events with the same event time
   * have the same hash code.
   *
   * @return The hash code of this event.
   */
  @Override
  public int hashCode() {
    return Objects.hash(eventTime);
  }

  /**
   * Returns a string representation of this event.
   * This includes the event time to identify when the event occurred.
   *
   * @return A string representation of the event.
   */
  @Override
  public String toString() {
    return "Event{" +
        "eventTime=" + eventTime +
        '}';
  }
}
