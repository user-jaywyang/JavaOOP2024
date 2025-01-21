package problem1;

import java.util.Objects;

/**
 * Represents a rental contract for a property listing. This type of contract includes
 * an asking price, negotiability flag, and a term in months.
 */
public class RentalContract extends Contract<RentalContract> {

  /** The term length of the rental contract in months, must be greater than 0. */
  private int termInMonths;

  /** A constant representing the value zero, used for comparisons and calculations. */
  private static final int ZERO = 0;

  /** A constant representing the value one, used for comparisons and calculations. */
  private static final int ONE = 1;

  /**
   * Constructs a new RentalContract with the specified asking price, negotiability flag, and term length.
   *
   * @param askingPrice   The asking price for the rental, must be non-negative.
   * @param isNegotiable  A boolean flag indicating if the asking price is negotiable.
   * @param termInMonths  The term length in months, must be greater than 0.
   * @throws IllegalArgumentException if askingPrice is negative or termInMonths is not greater than 0.
   */
  public RentalContract(double askingPrice, boolean isNegotiable, int termInMonths) throws InvalidAskingPriceException, InvalidTermInMonthsException{
    super(askingPrice, isNegotiable);
    if (termInMonths <= ZERO) {
      throw new InvalidTermInMonthsException("Term in months must be greater than 0.");
    }
    this.termInMonths = termInMonths;
  }

  /**
   * Gets the term length of the rental contract in months.
   *
   * @return The term length in months.
   */
  public int getTermInMonths() {
    return termInMonths;
  }

  /**
   * Calculates the potential earnings from this rental contract based on the agent's commission rate.
   *
   * @param commissionRate The agent's commission rate, a value between 0 and 1 inclusive.
   * @return The earnings from this rental as a double, calculated as askingPrice * commissionRate * termInMonths.
   * @throws IllegalArgumentException if commissionRate is out of the range [0, 1].
   */
  @Override
  public double calculateEarnings(double commissionRate) throws InvalidCommissionRateException {
    if (commissionRate < ZERO || commissionRate > ONE) {
      throw new InvalidCommissionRateException("Commission rate must be between 0 and 1 inclusive.");
    }
    return askingPrice * commissionRate * termInMonths;
  }

  @Override
  public String toString() {
    return "RentalContract{" +
        "askingPrice=" + askingPrice +
        ", isNegotiable=" + isNegotiable +
        ", termInMonths=" + termInMonths +
        '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    RentalContract that = (RentalContract) obj;
    return Double.compare(that.askingPrice, askingPrice) == 0 &&
        isNegotiable == that.isNegotiable &&
        termInMonths == that.termInMonths;
  }

  @Override
  public int hashCode() {
    return Objects.hash(askingPrice, isNegotiable, termInMonths);
  }

}
