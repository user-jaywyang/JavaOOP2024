package problem2;

import java.util.Objects;

/**
 * The Recipient class represents an individual with a first name, last name, and email.
 * It provides methods to access and modify these fields, as well as methods to compare two Recipient objects.
 */
public class Recipient {

  private String firstName;
  private String lastName;
  private String email;

  /**
   * Constructs a new Recipient with the specified first name, last name, and email.
   *
   * @param firstName the first name of the recipient
   * @param lastName  the last name of the recipient
   * @param email     the email of the recipient
   */
  public Recipient(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  /**
   * Returns the first name of the recipient.
   *
   * @return the first name of the recipient
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets a new first name for the recipient.
   *
   * @param newName the new first name of the recipient
   */
  public void setFirstName(String newName) {
    this.firstName = newName;
  }

  /**
   * Returns the last name of the recipient.
   *
   * @return the last name of the recipient
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets a new last name for the recipient.
   *
   * @param newName the new last name of the recipient
   */
  public void setLastName(String newName) {
    this.lastName = newName;
  }

  /**
   * Returns the email of the recipient.
   *
   * @return the email of the recipient
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets a new email for the recipient.
   *
   * @param newEmail the new email of the recipient
   */
  public void setEmail(String newEmail) {
    this.email = newEmail;
  }

  /**
   * Compares this recipient to another object. Returns true if the specified object is also
   * a Recipient and has the same first name, last name, and email as this recipient.
   *
   * @param o the object to compare with this recipient
   * @return true if the specified object is equal to this recipient, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Recipient recipient = (Recipient) o;
    return Objects.equals(firstName, recipient.firstName) &&
        Objects.equals(lastName, recipient.lastName) &&
        Objects.equals(email, recipient.email);
  }

  /**
   * Returns the hash code for this recipient based on its first name, last name, and email.
   *
   * @return the hash code of this recipient
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, email);
  }
}
