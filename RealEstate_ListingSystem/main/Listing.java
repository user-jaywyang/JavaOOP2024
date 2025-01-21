package problem1;

/**
 * The class Listing represents a listing on a property company website.
 * Each listing includes a property and a contract, that have generic parameters.
 *
 * @param <P> The type of property in this listing, must extend property.
 * @param <C> The type of contract in this listing, must extend contract.
 */
public class Listing<P extends Property<P>, C extends Contract<C>> {
  private P property;
  private C contract;

  /**
   * Constructs a new Listing with the specified property and contract.
   *
   * @param property The property associated with the listing.
   * @param contract The contract associated with the listing.
   */
  public Listing(P property, C contract) {
    this.property = property;
    this.contract = contract;
  }

  /**
   * Returns the property associated with the listing.
   * @return The property in this listing.
   */
  public P getProperty() {
    return property;
  }

  /**
   * Returns the contract associated with this listing.
   * @return The contract in this listing.
   */
  public C getContract() {
    return contract;
  }
}
