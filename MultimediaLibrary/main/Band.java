package problem2;

import java.util.List;
import java.util.Objects;

/**
 * Represents a Band, which is a group of RecordingArtists. Extends abstract class Creator.
 * A Band has a name and a list of RecordingArtists who are members.
 */
public class Band extends Creator {

  /**
   * The name of the band.
   */
  private final String name;

  /**
   * The list of recording artists who are members of the band.
   */
  private final List<RecordingArtist> members;

  /**
   * Constructs a new Band with the specified name and members.
   *
   * @param name    the name of the band
   * @param members the list of recording artists who are members of the band
   * @throws IllegalArgumentException if the name is null, empty, or if the list of members is null or empty
   */
  public Band(String name, List<RecordingArtist> members) {
    super(name);
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Band name cannot be null or empty.");
    }
    if (members == null || members.isEmpty()) {
      throw new IllegalArgumentException("Band must have at least one member.");
    }
    this.name = name;
    this.members = members;
  }

  /**
   * Returns the name of the band.
   *
   * @return the band's name
   */
  public String getBandName() {
    return this.name;
  }

  /**
   * Returns the list of members of the band.
   *
   * @return the list of recording artists who are members of the band
   */
  public List<RecordingArtist> getMembers() {
    return this.members;
  }

  /**
   * Checks if a given recording artist is a member of the band.
   *
   * @param member the recording artist to check
   * @return true if the artist is a member of the band, false otherwise
   */
  public boolean hasMember(RecordingArtist member) {
    return this.members.contains(member);
  }

  /**
   * Compares this Band to the specified object. The result is true if and only if the
   * argument is not null and is a Band object that has the same name and members as this Band.
   *
   * @param o the object to compare this Band against
   * @return true if the given object represents a Band equivalent to this Band, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Band band = (Band) o;
    return Objects.equals(this.name, band.name) &&
        Objects.equals(this.members, band.members);
  }

  /**
   * Returns a hash code value for the Band. This method is supported for the benefit of hash tables.
   *
   * @return the hash code value for this Band
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.members);
  }

  /**
   * Returns a string representation of the Band.
   *
   * @return a string representation of the Band, including the name and members
   */
  @Override
  public String toString() {
    return "Band{name='" + name + "', members=" + members + "}";
  }
}
