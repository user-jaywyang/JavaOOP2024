package problem1;

/**
 * The Name class represents a person's name, consisting of a first name and a last name.
 * It provides methods to retrieve the first and last names as well as a string representation of the full name.
 */
public class Name {

  /**
   * The first name of the person.
   */
  private String firstName;

  /**
   * The last name of the person.
   */
  private String lastName;

  /**
   * Constructs a Name object with the specified first name and last name.
   *
   * @param firstName the first name of the person
   * @param lastName the last name of the person
   */
  public Name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Returns the first name of the person.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the last name of the person.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Returns a string representation of the full name, which is the first name followed by the last name.
   *
   * @return a string representation of the full name
   */
  @Override
  public String toString() {
    return firstName + " " + lastName;
  }
}
