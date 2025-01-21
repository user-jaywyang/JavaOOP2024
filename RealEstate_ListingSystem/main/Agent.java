package problem1;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents an agent responsible for managing property listings. An agent can specialize
 * in certain types of properties and contracts using generics.
 *
 * @param <P> The type of property the agent can handle (e.g., ResidentialProperty, CommercialProperty).
 * @param <C> The type of contract the agent can handle (e.g., SaleContract, RentalContract).
 */
public class Agent<P extends Property<P>, C extends Contract<C>> {

  /** The name of the agent. */
  private String name;

  /** A collection of the agent's current listings. */
  private Collection<Listing<P, C>> listings;

  /** The commission rate of the agent, represented as a percentage between 0 and 1 inclusive. */
  private double commissionRate;

  /** The total earnings the agent has earned from completed listings. */
  private double totalEarnings;

  /** A constant representing the value zero, used for comparisons and calculations. */
  private static final int ZERO = 0;

  /** A constant representing the value zero as a double, used for comparisons and calculations. */
  private static final double ZEROD = 0.0;

  /** A constant representing the value one, used for comparisons and calculations. */
  private static final int ONE = 1;

  /**  A constant representing the value 31, used for hashCode calculations. */
  private static final int HASH = 31;

  /**
   * Constructs a new Agent with the specified name and commission rate.
   *
   * @param name           The name of the agent.
   * @param commissionRate The commission rate, a value between 0 and 1 inclusive.
   * @throws IllegalArgumentException if the commission rate is not between 0 and 1 inclusive.
   */
  public Agent(String name, double commissionRate) throws InvalidCommissionRateException {
    if (commissionRate < ZERO || commissionRate > ONE) {
      throw new InvalidCommissionRateException("Commission rate must be between 0 and 1 inclusive.");
    }
    this.name = name;
    this.commissionRate = commissionRate;
    this.totalEarnings = ZEROD;
    this.listings = new ArrayList<>();
  }

  /**
   * Adds a listing to the agent's current listings if it matches the agent's property and contract type.
   *
   * @param listing The listing to add.
   */
  public void addListing(Listing<P, C> listing) {
    listings.add(listing);
  }

  /**
   * Completes a listing if it is part of the agent's collection, removing it and adding earnings to total earnings.
   * Earnings are calculated based on the listing's contract type.
   *
   * @param listing The listing to complete.
   * @throws IllegalArgumentException if the listing is not part of the agent's collection.
   */
  public void completeListing(Listing<P, C> listing) throws NoListingException, InvalidCommissionRateException {
    if (!listings.contains(listing)) {
      throw new NoListingException("Listing not found in agent's collection.");
    }
    double earnings = listing.getContract().calculateEarnings(commissionRate);
    totalEarnings += earnings;
    listings.remove(listing);
  }

  /**
   * Removes a listing from the agent's collection without adjusting total earnings.
   *
   * @param listing The listing to drop.
   * @throws IllegalArgumentException if the listing is not part of the agent's collection.
   */
  public void dropListing(Listing<P, C> listing) throws NoListingException{
    if (!listings.contains(listing)) {
      throw new NoListingException("Listing not found in agent's collection.");
    }
    listings.remove(listing);
  }

  /**
   * Calculates the total potential earnings from all listings currently in the agent's collection,
   * based on the commission rate and contract type of each listing.
   *
   * @return The total potential portfolio value.
   */
  public double getTotalPortfolioValue() throws InvalidCommissionRateException {
    double totalValue = ZEROD;
    for (Listing<P, C> listing : listings) {
      totalValue += listing.getContract().calculateEarnings(commissionRate);
    }
    return totalValue;
  }

  /**
   * Gets the agent's name.
   *
   * @return The agent's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the agent's total earnings from completed listings.
   *
   * @return The agent's total earnings.
   */
  public double getTotalEarnings() {
    return totalEarnings;
  }

  /**
   * Gets the agent's current commission rate.
   *
   * @return The agent's commission rate.
   */
  public double getCommissionRate() {
    return commissionRate;
  }

  @Override
  public String toString() {
    return "Agent{" +
        "name='" + name + '\'' +
        ", commissionRate=" + commissionRate +
        ", totalEarnings=" + totalEarnings +
        ", listings=" + listings +
        '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Agent<?, ?> agent = (Agent<?, ?>) obj;
    return Double.compare(agent.commissionRate, commissionRate) == ZERO &&
        Double.compare(agent.totalEarnings, totalEarnings) == ZERO &&
        name.equals(agent.name) &&
        listings.equals(agent.listings);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = HASH * result + Double.hashCode(commissionRate);
    result = HASH * result + Double.hashCode(totalEarnings);
    result = HASH * result + listings.hashCode();
    return result;
  }
}
