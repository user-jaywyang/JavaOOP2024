package problem1;
/**
 * Represents a contract for a property listing. This class is generic, allowing
 * it to represent different types of contracts (e.g., Sale, Rental).
 * Each contract has an asking price and a negotiability flag.
 *
 * @param <T> The specific type of the contract (e.g., SaleContract, RentalContract).
 */
public abstract class Contract<T> {

  /** The asking price for the contract. Must be a non-negative value. */
  protected double askingPrice;

  /** Indicates if the asking price is negotiable. */
  protected boolean isNegotiable;

  /** A constant representing the value zero, used for comparisons and calculations. */
  private static final int ZERO = 0;

  /**  A constant representing the value 31, used for hashCode calculations. */
  private static final int HASH = 31;


  /**
   * Constructs a new Contract with the specified asking price and negotiability flag.
   *
   * @param askingPrice   The asking price of the contract, which must be non-negative.
   * @param isNegotiable  A boolean flag indicating if the asking price is negotiable.
   * @throws IllegalArgumentException if askingPrice is negative.
   */
  public Contract(double askingPrice, boolean isNegotiable) throws InvalidAskingPriceException{
    if (askingPrice < ZERO) {
      throw new InvalidAskingPriceException("Asking price must be non-negative.");
    }
    this.askingPrice = askingPrice;
    this.isNegotiable = isNegotiable;
  }

  /**
   * Gets the asking price of the contract.
   *
   * @return The asking price as a double.
   */
  public double getAskingPrice() {
    return askingPrice;
  }

  /**
   * Checks if the asking price is negotiable.
   *
   * @return true if the price is negotiable, false otherwise.
   */
  public boolean isNegotiable() {
    return isNegotiable;
  }

  /**
   * Calculates the potential earnings from this contract. The implementation of this
   * method depends on the type of contract and should be provided by subclasses.
   *
   * @param commissionRate The agent's commission rate, a value between 0 and 1 inclusive.
   * @return The calculated earnings based on the contract type.
   */
  public abstract double calculateEarnings(double commissionRate)
      throws InvalidCommissionRateException;

  @Override
  public String toString() {
    return "Contract{" +
        "askingPrice=" + askingPrice +
        ", isNegotiable=" + isNegotiable +
        '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Contract<?> contract = (Contract<?>) obj;
    return Double.compare(contract.askingPrice, askingPrice) == ZERO &&
        isNegotiable == contract.isNegotiable;
  }

  @Override
  public int hashCode() {
    int result = Double.hashCode(askingPrice);
    result = HASH * result + Boolean.hashCode(isNegotiable);
    return result;
  }
}
