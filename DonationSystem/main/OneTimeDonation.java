package problem1;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a one-time donation made to a non-profit organization.
 * A one-time donation is a single contribution made on a specific date
 * and is not repeated.
 */
public class OneTimeDonation implements Donation {

  /**
   * The amount of the one-time donation.
   */
  private final double amount;

  /**
   * The date and time when the one-time donation was made.
   */
  private final LocalDateTime creationDate;

  /**
   * A constant representing the value zero, used for comparison and return values.
   */
  private static final int ZERO = 0;

  /**
   * Constructs a OneTimeDonation with the specified amount and creation date.
   * The donation amount must be greater than zero, and the creation date represents
   * the date and time the donation was made.
   *
   * @param amount The amount of the one-time donation.
   * @param creationDate The date and time the donation was made.
   * @throws IllegalArgumentException if the amount is zero or negative.
   */
  public OneTimeDonation(double amount, LocalDateTime creationDate) {
    if (amount <= ZERO) {
      throw new IllegalArgumentException("Amount must be above 0");
    }
    this.amount = amount;
    this.creationDate = creationDate;
  }

  /**
   * Returns the donation amount for this one-time donation.
   *
   * @return The donation amount.
   */
  @Override
  public double getAmount() {
    return this.amount;
  }

  /**
   * Returns the creation date and time for this one-time donation.
   * The creation date represents when the donation was made.
   *
   * @return The creation date of the donation.
   */
  @Override
  public LocalDateTime getCreationDate() {
    return this.creationDate;
  }

  /**
   * Returns the donation amount if the donation was made in the specified year,
   * otherwise returns zero.
   *
   * The donation amount is only included in the total for the year that matches
   * the creation date's year.
   *
   * @param year The year to check if the donation was made.
   * @return The donation amount if it was made in the specified year, otherwise zero.
   */
  @Override
  public double getDonationAmountForYear(int year) {
    if (creationDate.getYear() == year) {
      return amount;
    }
    return ZERO;
  }

  /**
   * Compares this OneTimeDonation to another object. Two one-time donations are
   * considered equal if they have the same amount and creation date.
   *
   * @param o The other object to compare.
   * @return True if the donations are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OneTimeDonation)) return false;
    OneTimeDonation that = (OneTimeDonation) o;
    return Double.compare(that.amount, amount) == ZERO &&
        creationDate.equals(that.creationDate);
  }

  /**
   * Returns the hash code of this OneTimeDonation based on its amount and creation date.
   *
   * @return The hash code of this donation.
   */
  @Override
  public int hashCode() {
    return Objects.hash(amount, creationDate);
  }

  /**
   * Returns a string representation of this OneTimeDonation, including the donation
   * amount and creation date.
   *
   * @return A string representation of the donation.
   */
  @Override
  public String toString() {
    return "OneTimeDonation{" +
        "amount=" + amount +
        ", creationDate=" + creationDate +
        '}';
  }
}
