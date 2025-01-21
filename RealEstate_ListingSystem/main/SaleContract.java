package problem1;

/**
 * Represents a sale contract for a property listing. This type of contract
 * includes an asking price and a negotiability flag.
 */
public class SaleContract extends Contract<SaleContract> {

  /** A constant representing the value zero, used for comparisons and calculations.*/
  private static final int ZERO = 0;

  /** A constant representing the value one, used for comparisons and calculations.*/
  private static final int ONE = 1;

  /**
   * Constructs a new SaleContract with the specified asking price and negotiability flag.
   *
   * @param askingPrice   The asking price for the sale, must be non-negative.
   * @param isNegotiable  A boolean flag indicating if the asking price is negotiable.
   * @throws IllegalArgumentException if askingPrice is negative.
   */
  public SaleContract(double askingPrice, boolean isNegotiable) throws InvalidAskingPriceException{
    super(askingPrice, isNegotiable);
  }

  /**
   * Calculates the potential earnings from this sale contract based on the agent's commission rate.
   *
   * @param commissionRate The agent's commission rate, a value between 0 and 1 inclusive.
   * @return The earnings from this sale as a double, calculated as askingPrice * commissionRate.
   * @throws IllegalArgumentException if commissionRate is out of the range [0, 1].
   */
  @Override
  public double calculateEarnings(double commissionRate) throws InvalidCommissionRateException{
    if (commissionRate < ZERO || commissionRate > ONE) {
      throw new InvalidCommissionRateException("Commission rate must be between 0 and 1 inclusive.");
    }
    return askingPrice * commissionRate;
  }
}
