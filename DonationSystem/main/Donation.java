package problem1;

import java.time.LocalDateTime;

/**
 * Represents a donation made to a non-profit organization.
 * A donation can be of different types: one-time, monthly, or pledge.
 * This interface defines the functionality that all donations must implement.
 */
public interface Donation {

  /**
   * Calculates the donation amount that applies to a specified year.
   * Each type of donation implements this method differently, depending on the
   * rules for that donation type.
   *
   * @param year The year for which the donation amount is being calculated.
   * @return The total donation amount that applies to the specified year.
   */
  double getDonationAmountForYear(int year);

  /**
   * Returns the amount of this donation.
   * The amount is the total sum for the donation, which may vary based
   * on whether the donation is one-time, recurring, or a pledge.
   *
   * @return The donation amount.
   */
  double getAmount();

  /**
   * Returns the creation date and time of the donation.
   * The creation date represents the date when the donation was made,
   * pledged, or set to begin in the case of recurring donations.
   *
   * @return The creation date and time of the donation.
   */
  LocalDateTime getCreationDate();

  /**
   * Determines whether two donations are considered equal.
   * Two donations are considered equal if they have the same creation
   * date and time and the same donation amount.
   *
   * @param o The other object to compare with this donation.
   * @return True if the donations are equal, false otherwise.
   */
  @Override
  boolean equals(Object o);

  /**
   * Returns the hash code value for this donation.
   * The hash code is based on the creation date and the donation amount.
   *
   * @return The hash code of this donation.
   */
  @Override
  int hashCode();

  /**
   * Returns a string representation of this donation.
   * The string includes the donation amount and the creation date.
   *
   * @return A string representation of the donation.
   */
  @Override
  String toString();
}
