package problem1;

import java.util.Objects;

/**
 * The Room class represents a room with a maximum occupancy, a price,
 * and the current number of guests. It includes functionality to book
 * the room, check availability, and manage pricing and occupancy constraints.
 */
public class Room {

  /** The maximum number of guests the room can accommodate */
  protected int maxOccupancy;

  /** The price of the room in dollars */
  protected float price;

  /** The current number of guests in the room */
  protected int guestsIN;

  /** Constant representing the initial number of guests in the room (starting occupancy) */
  protected final int STARTINGOCCUPANCY = 0;

  /** Constant representing the invalid starting price. zero or below */
  private final int INVALID_PRICE_STARTING = 0;

  /**
   * Constructs a new Room with a specified maximum occupancy and price.
   *
   * @param maxOccupancy the maximum number of guests the room can accommodate
   * @param price the price of the room, must be greater than zero
   * @throws InvalidPriceException if the price is zero or negative
   */
  public Room(int maxOccupancy, float price) throws InvalidPriceException {
    this.maxOccupancy = maxOccupancy;
    this.guestsIN = STARTINGOCCUPANCY;
    if (price <= INVALID_PRICE_STARTING) {
      throw new InvalidPriceException("Price must be above zero");
    } else {
      this.price = price;
    }
  }

  /**
   * Returns the maximum occupancy of the room.
   *
   * @return the maximum number of guests the room can accommodate
   */
  public int getMaxOccupancy() {
    return maxOccupancy;
  }

  /**
   * Returns the price of the room.
   *
   * @return the price of the room in dollars
   */
  public float getPrice() {
    return price;
  }

  /**
   * Returns the current number of guests in the room.
   *
   * @return the current number of guests
   */
  public int getGuestsIN() {
    return guestsIN;
  }

  /**
   * Checks if the room is available .
   *
   * @return true if the room is available, false otherwise
   */
  public boolean isAvailable() {
    return guestsIN == STARTINGOCCUPANCY;
  }

  /**
   * Books the room for the specified number of guests.
   * If the room is available and the number of guests does not exceed
   * the maximum occupancy, the room is booked.
   *
   * @param num the number of guests to book the room for
   * @throws InvalidOccupancyException if the number of guests exceeds the maximum occupancy
   * @throws InvalidAvailabilityException if the room is already booked
   */
  public void bookRoom(int num) throws InvalidOccupancyException, InvalidAvailabilityException {
    if (num > this.maxOccupancy) {
      throw new InvalidOccupancyException("Your guest count is above the maximum occupancy.");
    } else {
      if (this.isAvailable()) {
        this.guestsIN = num;
      } else {
        throw new InvalidAvailabilityException("The room is currently booked and unavailable.");
      }
    }
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * Two rooms are considered equal if they have the same maximum occupancy,
   * price, and current number of guests.
   *
   * @param o the object to compare to
   * @return true if this object is the same as the object o
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Room room = (Room) o;
    return maxOccupancy == room.maxOccupancy &&
        Float.compare(room.price, price) == 0 &&
        guestsIN == room.guestsIN;
  }

  /**
   * Returns a hash code value for the object..
   *
   * @return a hash code value for this object, based on the max occupancy, price, and current number of guests
   */
  @Override
  public int hashCode() {
    return Objects.hash(maxOccupancy, price, guestsIN);
  }
}
