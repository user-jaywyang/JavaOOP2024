package problem2;

import java.util.Objects;

/**
 * Represents a recording artist, who has a first name and a last name.
 * A recording artist can create music either as an individual or as part of a band.
 */
public class RecordingArtist extends Creator {

  /**
   * The first name of the recording artist.
   */
  private final String firstName;

  /**
   * The last name of the recording artist.
   */
  private final String lastName;

  /**
   * Constructs a new RecordingArtist with the specified first name and last name.
   *
   * @param firstName the first name of the recording artist.
   * @param lastName  the last name of the recording artist.
   * @throws IllegalArgumentException if the first name or last name is null or empty.
   */
  public RecordingArtist(String firstName, String lastName) {
    super(firstName + " " + lastName);
    if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
      throw new IllegalArgumentException("First name and last name cannot be null or empty.");
    }
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Returns the first name of the recording artist.
   *
   * @return the first name of the recording artist.
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Returns the last name of the recording artist.
   *
   * @return the last name of the recording artist.
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * Compares this RecordingArtist to the specified object. The result is true if and only if the
   * argument is not null and is a RecordingArtist object that has the same first name and last name as this artist.
   *
   * @param o the object to compare this RecordingArtist against.
   * @return true if the given object represents a RecordingArtist equivalent to this artist, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecordingArtist artist = (RecordingArtist) o;
    return Objects.equals(this.firstName, artist.firstName) &&
        Objects.equals(this.lastName, artist.lastName);
  }

  /**
   * Returns a hash code value for the RecordingArtist.
   * This method is supported for the benefit of hash tables.
   *
   * @return the hash code value for this RecordingArtist.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.firstName, this.lastName);
  }

  /**
   * Returns a string representation of the RecordingArtist.
   * The string includes the first name and last name of the artist.
   *
   * @return a string representation of the RecordingArtist.
   */
  @Override
  public String toString() {
    return "RecordingArtist{firstName='" + firstName + "', lastName='" + lastName + "'}";
  }
}
