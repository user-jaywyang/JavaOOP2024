package problem2;

import java.util.Objects;

/**
 * Represents an abstract class of a creator, which can be an individual or a band. The creator has
 * a name, which could be a full name or a band name.
 */
public abstract class Creator {

  /**
   * The full name of the creator or the name of the band.
   */
  private final String name;

  /**
   * Constructs a new Creator with the specified name.
   *
   * @param name the full name of the creator or band name.
   * @throws IllegalArgumentException if the name is null or empty.
   */
  public Creator(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty.");
    }
    this.name = name;
  }

  /**
   * Returns the name of the creator.
   *
   * @return the name of the creator.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Compares this Creator to the specified object. The result is true if and only if the
   * argument is not null and is a Creator object that has the same name as this creator.
   *
   * @param o the object to compare this Creator against.
   * @return true if the given object represents a Creator equivalent to this creator, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Creator creator = (Creator) o;
    return Objects.equals(this.name, creator.name);
  }

  /**
   * Returns a hash code value for the Creator.
   * This method is supported for the benefit of hash tables.
   *
   * @return the hash code value for this Creator.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.name);
  }

  /**
   * Returns a string representation of the Creator.
   * The string includes the name of the creator.
   *
   * @return a string representation of the Creator.
   */
  @Override
  public String toString() {
    return "Creator{name='" + name + "'}";
  }
}
