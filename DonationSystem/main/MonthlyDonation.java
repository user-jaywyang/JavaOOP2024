package problem1;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

/**
 * Represents a monthly donation made to a non-profit organization.
 * A monthly donation repeats once a month until it is cancelled or continues indefinitely.
 */
public class MonthlyDonation implements Donation {

  /**
   * The monthly donation amount.
   */
  private final double amount;

  /**
   * The date and time when the monthly donation starts.
   */
  private final LocalDateTime creationDate;

  /**
   * The date and time when the monthly donation was cancelled (optional).
   * If the donation has not been cancelled, this field remains null.
   */
  private LocalDateTime cancellationDate;

  /**
   * A constant representing the value zero, used for comparisons and calculations.
   */
  private static final int ZERO = 0;

  /**
   * A constant representing the value one, used for adjusting calculations.
   */
  private static final int ONE = 1;

  /**
   * Constructs a MonthlyDonation with the specified amount and creation date.
   * The donation amount must be greater than zero, and the donation is considered active
   * from the creation date until it is cancelled.
   *
   * @param amount       The monthly donation amount.
   * @param creationDate The date and time when the donation starts.
   * @throws IllegalArgumentException if the amount is zero or negative.
   */
  public MonthlyDonation(double amount, LocalDateTime creationDate) {
    if (amount <= ZERO) {
      throw new IllegalArgumentException("Amount must be positive");
    }
    this.amount = amount;
    this.creationDate = creationDate;
    this.cancellationDate = null; // No cancellation by default.
  }

  /**
   * Constructs a MonthlyDonation with an optional cancellation date.
   * The donation amount must be greater than zero. If the cancellation date
   * is provided, it must not be earlier than the creation date.
   *
   * @param amount         The monthly donation amount.
   * @param creationDate   The date and time when the donation starts.
   * @param cancellationDate The optional cancellation date.
   * @throws IllegalArgumentException if the amount is zero or negative,
   *                                  or if the cancellation date is before the creation date.
   */
  public MonthlyDonation(double amount, LocalDateTime creationDate, LocalDateTime cancellationDate) {
    if (amount <= ZERO) {
      throw new IllegalArgumentException("Amount must be above 0");
    }
    this.amount = amount;
    this.creationDate = creationDate;
    if (cancellationDate != null && cancellationDate.isBefore(creationDate)) {
      throw new IllegalArgumentException("Cancellation date cannot be before the creation date");
    }
    this.cancellationDate = cancellationDate;
  }

  /**
   * Returns the donation amount for this monthly donation.
   *
   * @return The monthly donation amount.
   */
  @Override
  public double getAmount() {
    return this.amount;
  }

  /**
   * Returns the creation date and time for this monthly donation.
   *
   * @return The creation date of the donation.
   */
  @Override
  public LocalDateTime getCreationDate() {
    return this.creationDate;
  }

  /**
   * Calculates the total donation amount for a specified year by considering
   * the months the donation was active.
   * If the donation was created in the specified year, the calculation starts
   * from the creation month. If the donation was cancelled in the specified year,
   * the calculation stops at the cancellation month.
   *
   * @param year The year for which to calculate the total donation.
   * @return The total donation amount for the specified year.
   */
  @Override
  public double getDonationAmountForYear(int year) {
    // Get the start and end month of the donation in the given year
    int startMonth = (creationDate.getYear() == year) ? creationDate.getMonthValue() : Month.JANUARY.getValue();
    int endMonth = (cancellationDate != null && cancellationDate.getYear() == year) ? cancellationDate.getMonthValue() : Month.DECEMBER.getValue();

    // If the creation year is after the specified year or the cancellation year is before the specified year, return zero
    if (creationDate.getYear() > year || (cancellationDate != null && cancellationDate.getYear() < year)) {
      return ZERO;
    }

    // Count the months the donation was active in the given year
    int activeMonths = endMonth - startMonth + ONE;
    return activeMonths * amount;
  }

  /**
   * Cancels the donation by setting the cancellation date.
   * The cancellation date cannot be before the creation date.
   *
   * @param cancellationDate The date when the donation is cancelled.
   * @throws IllegalArgumentException if the cancellation date is before the creation date.
   */
  public void cancelDonation(LocalDateTime cancellationDate) {
    if (cancellationDate.isBefore(creationDate)) {
      throw new IllegalArgumentException("Cancellation date cannot be before the creation date");
    }
    this.cancellationDate = cancellationDate;
  }

  /**
   * Compares this MonthlyDonation to another object. They are considered equal if
   * they have the same amount, creation date, and cancellation date.
   *
   * @param o The other object to compare.
   * @return True if the donations are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MonthlyDonation)) return false;
    MonthlyDonation that = (MonthlyDonation) o;
    return Double.compare(that.amount, amount) == ZERO &&
        creationDate.equals(that.creationDate) &&
        Objects.equals(cancellationDate, that.cancellationDate);
  }

  /**
   * Returns the hash code of this MonthlyDonation, based on its amount, creation date, and cancellation date.
   *
   * @return The hash code of this donation.
   */
  @Override
  public int hashCode() {
    return Objects.hash(amount, creationDate, cancellationDate);
  }

  /**
   * Returns a string representation of this MonthlyDonation, including the amount,
   * creation date, and cancellation date (if not null).
   *
   * @return A string representation of the donation.
   */
  @Override
  public String toString() {
    return "MonthlyDonation{" +
        "amount=" + amount +
        ", creationDate=" + creationDate +
        ", cancellationDate=" + (cancellationDate != null ? cancellationDate : "None") +
        '}';
  }
}
