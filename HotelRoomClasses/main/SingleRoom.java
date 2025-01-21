package problem1;

/**
 * The SingleRoom class represents a single room, which can accommodate
 * a maximum of one guest. It extends the Room class and uses a
 *  maximum occupancy of 1.
 */
public class SingleRoom extends Room {

  /** Constant representing the maximum occupancy of a single room (1 guest) */
  protected static final int SINGLE_MAX_OCCUPANCY = 1;

  /**
   * Constructs a new SingleRoom with the specified price.
   *
   * @param price the price of the single room, must be greater than zero
   * @throws InvalidPriceException if the price is zero or negative
   */
  public SingleRoom(float price) throws InvalidPriceException {
    super(SINGLE_MAX_OCCUPANCY, price);
  }
}
