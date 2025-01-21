package problem1;

import java.util.List;
import java.util.Objects;

/**
 * Represents a non-profit organization that tracks donations made to it.
 * A non-profit organization has a name and a list of donations. This class
 * provides functionality for adding donations and calculating total donations
 * for a specific year.
 */
public class NonProfit {

  /**
   * The name of the non-profit organization.
   */
  private final String name;

  /**
   * The list of donations made to the non-profit organization.
   */
  private final List<Donation> donations;

  /**
   * A constant representing the value zero, used in donation calculations.
   */
  private static final int ZERO = 0;

  /**
   * Constructs a NonProfit instance with the given name and list of donations.
   *
   * @param name The name of the non-profit organization.
   * @param donations The list of donations made to this non-profit.
   */
  public NonProfit(String name, List<Donation> donations) {
    this.name = name;
    this.donations = donations;
  }

  /**
   * Adds a new donation to the non-profit's list of donations.
   *
   * @param donation The donation to be added to the non-profit.
   */
  public void addDonation(Donation donation) {
    this.donations.add(donation);
  }

  /**
   * Calculates the total donation amount for a specified year by summing up
   * all applicable donations from the list of donations. Each donation type
   * calculates its contribution to the total in a different way, depending on
   * the donation type's rules.
   *
   * @param year The year for which the total donation amount is being calculated.
   * @return The total amount of donations processed in the specified year.
   */
  public double getTotalDonationsForYear(int year) {
    double totalAmount = ZERO;
    for (Donation donation : donations) {
      totalAmount += donation.getDonationAmountForYear(year);
    }
    return totalAmount;
  }

  /**
   * Returns the name of the non-profit organization.
   *
   * @return The name of the non-profit organization.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Determines whether two NonProfit objects are considered equal.
   * Two NonProfit objects are considered equal if they have the same name
   * and the same list of donations.
   *
   * @param o The other object to compare with this non-profit.
   * @return True if the non-profits are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof NonProfit)) return false;
    NonProfit nonProfit = (NonProfit) o;
    return name.equals(nonProfit.name) && donations.equals(nonProfit.donations);
  }

  /**
   * Returns the hash code of this NonProfit based on its name and donations.
   * The hash code is computed using the name and the list of donations.
   *
   * @return The hash code of this NonProfit.
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, donations);
  }

  /**
   * Returns a string representation of this NonProfit.
   * The string includes the non-profit's name and its list of donations.
   *
   * @return A string representation of this non-profit organization.
   */
  @Override
  public String toString() {
    return "NonProfit{" +
        "name='" + name + '\'' +
        ", donations=" + donations +
        '}';
  }
}
