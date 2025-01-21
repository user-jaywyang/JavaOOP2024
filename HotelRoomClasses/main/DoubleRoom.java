package problem1;

/**
 * The DoubleRoom class represents a double room, which can accommodate
 * a maximum of two guests. It extends the Room class and uses a
 *  maximum occupancy of 2.
 */
public class DoubleRoom extends Room {

  /** Constant representing the maximum occupancy of a double room (2 guests) */
  protected static final int DOUBLE_MAX_OCCUPANCY = 2;

  /**
   * Constructs a new DoubleRoom with the specified price.
   *
   * @param price the price of the double room, must be greater than zero
   * @throws InvalidPriceException if the price is zero or negative
   */
  public DoubleRoom(float price) throws InvalidPriceException {
    super(DOUBLE_MAX_OCCUPANCY, price);
  }
}
