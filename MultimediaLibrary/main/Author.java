package problem2;

import java.util.Objects;

/**
 * Represents an author, who has a first name and a last name.
 * An author typically writes books or other literary works.
 */
public class Author extends Creator {

  /**
   * The first name of the author.
   */
  private final String firstName;

  /**
   * The last name of the author.
   */
  private final String lastName;

  /**
   * Constructs a new Author with the specified first name and last name.
   *
   * @param firstName the first name of the author.
   * @param lastName  the last name of the author.
   * @throws IllegalArgumentException if the first name or last name is null or empty.
   */
  public Author(String firstName, String lastName) {
    super(firstName + " " + lastName);
    if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
      throw new IllegalArgumentException("First name and last name cannot be null or empty.");
    }
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Returns the first name of the author.
   *
   * @return the first name of the author.
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Returns the last name of the author.
   *
   * @return the last name of the author.
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * Compares this Author to the specified object. The result is true if and only if the
   * argument is not null and is an Author object that has the same first name and last name as this author.
   *
   * @param o the object to compare this Author against.
   * @return true if the given object represents an Author equivalent to this author, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Author author = (Author) o;
    return Objects.equals(this.firstName, author.firstName) &&
        Objects.equals(this.lastName, author.lastName);
  }

  /**
   * Returns a hash code value for the Author.
   * This method is supported for the benefit of hash tables.
   *
   * @return the hash code value for this Author.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.firstName, this.lastName);
  }

  /**
   * Returns a string representation of the Author.
   * The string includes the first name and last name of the author.
   *
   * @return a string representation of the Author.
   */
  @Override
  public String toString() {
    return "Author{firstName='" + firstName + "', lastName='" + lastName + "'}";
  }
}
