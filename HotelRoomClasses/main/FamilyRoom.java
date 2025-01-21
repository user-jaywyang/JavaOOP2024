package problem1;

/**
 * The FamilyRoom class represents a family room, which can accommodate
 * a maximum of four guests. It extends the Room class and uses a
 * predefined maximum occupancy of 4.
 */
public class FamilyRoom extends Room {

  /** Constant representing the maximum occupancy of a family room (4 guests) */
  protected static final int FAMILY_MAX_OCCUPANCY = 4;

  /**
   * Constructs a new FamilyRoom with the specified price.
   *
   * @param price the price of the family room, must be greater than zero
   * @throws InvalidPriceException if the price is zero or negative
   */
  public FamilyRoom(float price) throws InvalidPriceException {
    super(FAMILY_MAX_OCCUPANCY, price);
  }
}
