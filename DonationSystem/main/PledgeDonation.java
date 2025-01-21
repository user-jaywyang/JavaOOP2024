package problem1;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a pledge donation made to a non-profit organization.
 * A pledge donation is a promise to donate a specified amount in the future.
 * The donation is processed once the pledge is fulfilled, and it may or may not
 * have a processing date at the time of creation.
 */
public class PledgeDonation implements Donation {

  /**
   * The amount pledged to the non-profit organization.
   */
  private final double amount;

  /**
   * The date and time when the pledge was made.
   */
  private final LocalDateTime creationDate;

  /**
   * The date and time when the pledge will be processed (optional).
   * If the pledge has not yet been processed, this field remains null.
   */
  private LocalDateTime processingDate;

  /**
   * A constant representing the value zero, used for comparisons and calculations.
   */
  private static final int ZERO = 0;

  /**
   * Constructs a PledgeDonation with the specified amount and creation date.
   * The donation amount must be greater than zero, and the pledge will not have
   * a processing date until it is set later.
   *
   * @param amount       The pledged donation amount.
   * @param creationDate The date and time when the pledge was made.
   * @throws IllegalArgumentException if the amount is zero or negative.
   */
  public PledgeDonation(double amount, LocalDateTime creationDate) {
    if (amount <= ZERO) {
      throw new IllegalArgumentException("Amount must be above 0");
    }
    this.amount = amount;
    this.creationDate = creationDate;
    this.processingDate = null; // No processing date by default.
  }

  /**
   * Constructs a PledgeDonation with an optional processing date.
   * The donation amount must be greater than zero. If the processing date is provided,
   * it must not be earlier than the creation date.
   *
   * @param amount         The pledged donation amount.
   * @param creationDate   The date and time when the pledge was made.
   * @param processingDate The date and time when the pledge will be processed.
   * @throws IllegalArgumentException if the amount is zero or negative,
   *                                  or if the processing date is before the creation date.
   */
  public PledgeDonation(double amount, LocalDateTime creationDate, LocalDateTime processingDate) {
    if (amount <= ZERO) {
      throw new IllegalArgumentException("Amount must be above 0");
    }
    if (processingDate != null && processingDate.isBefore(creationDate)) {
      throw new IllegalArgumentException("Processing date cannot be before the creation date");
    }
    this.amount = amount;
    this.creationDate = creationDate;
    this.processingDate = processingDate;
  }

  /**
   * Returns the pledge donation amount.
   *
   * @return The donation amount.
   */
  @Override
  public double getAmount() {
    return this.amount;
  }

  /**
   * Returns the creation date and time for this pledge.
   *
   * @return The creation date of the donation.
   */
  @Override
  public LocalDateTime getCreationDate() {
    return this.creationDate;
  }

  /**
   * Calculates the donation amount for a specified year, but only if the pledge is processed
   * in that year. If the pledge is not processed in the specified year, zero is returned.
   *
   * @param year The year to check if the pledge was processed.
   * @return The donation amount if the pledge is processed in the specified year, otherwise zero.
   */
  @Override
  public double getDonationAmountForYear(int year) {
    if (processingDate != null && processingDate.getYear() == year) {
      return amount;
    }
    return ZERO;
  }

  /**
   * Sets or updates the processing date for the pledge.
   * The processing date must not be earlier than the creation date.
   *
   * @param newDate The new processing date for the pledge.
   * @throws IllegalArgumentException if the processing date is earlier than the creation date.
   */
  public void setProcessingDate(LocalDateTime newDate) {
    if (newDate.isBefore(creationDate)) {
      throw new IllegalArgumentException("Processing date cannot be before the creation date");
    }
    this.processingDate = newDate;
  }

  /**
   * Removes the processing date, effectively leaving the pledge unprocessed.
   */
  public void removeProcessingDate() {
    this.processingDate = null;
  }

  /**
   * Compares this PledgeDonation to another object. They are considered equal if
   * they have the same amount, creation date, and processing date.
   *
   * @param o The other object to compare.
   * @return True if the donations are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PledgeDonation)) return false;
    PledgeDonation that = (PledgeDonation) o;
    return Double.compare(that.amount, amount) == ZERO &&
        creationDate.equals(that.creationDate) &&
        Objects.equals(processingDate, that.processingDate);
  }

  /**
   * Returns the hash code of this PledgeDonation, based on its amount, creation date, and processing date.
   *
   * @return The hash code of this donation.
   */
  @Override
  public int hashCode() {
    return Objects.hash(amount, creationDate, processingDate);
  }

  /**
   * Returns a string representation of this PledgeDonation, including the amount,
   * creation date, and processing date (if not null).
   *
   * @return A string representation of the donation.
   */
  @Override
  public String toString() {
    return "PledgeDonation{" +
        "amount=" + amount +
        ", creationDate=" + creationDate +
        ", processingDate=" + (processingDate != null ? processingDate : "None") +
        '}';
  }
}
