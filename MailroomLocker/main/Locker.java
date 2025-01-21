package problem2;

import java.util.Objects;

/**
 * The Locker class represents a locker that can hold a mail item. The locker has dimensions (width, height, depth)
 * and can either be occupied (if it contains a mail item) or empty.
 */
public class Locker {

  private int width;
  private int height;
  private int depth;
  private MailItem mailItem;
  private boolean occupied;
  private static final int MAX_MEASUREMENT = 1;

  /**
   * Constructs a Locker that is already occupied with a mail item.
   *
   * @param width     the width of the locker (must be >= 1)
   * @param height    the height of the locker (must be >= 1)
   * @param depth     the depth of the locker (must be >= 1)
   * @param mailItem  the mail item currently in the locker
   */
  public Locker(int width, int height, int depth, MailItem mailItem) {
    this.width = width;
    this.height = height;
    this.depth = depth;
    this.mailItem = mailItem;
    this.occupied = true;
  }

  /**
   * Constructs an empty Locker with the given dimensions.
   *
   * @param width  the width of the locker (must be >= 1)
   * @param height the height of the locker (must be >= 1)
   * @param depth  the depth of the locker (must be >= 1)
   */
  public Locker(int width, int height, int depth) {
    this.width = width;
    this.height = height;
    this.depth = depth;
    this.mailItem = null;
    this.occupied = false;
  }

  /**
   * Returns the width of the locker.
   *
   * @return the width of the locker
   */
  public int getWidth() {
    return width;
  }

  /**
   * Sets a new width for the locker. The width must be greater than or equal to 1.
   *
   * @param width the new width of the locker
   * @throws InvalidMeasurementException if the width is less than 1
   */
  public void setWidth(int width) throws InvalidMeasurementException {
    if (width >= MAX_MEASUREMENT) {
      this.width = width;
    } else {
      throw new IllegalArgumentException("width must be greater than 1");
    }
  }

  /**
   * Returns the height of the locker.
   *
   * @return the height of the locker
   */
  public int getHeight() {
    return height;
  }

  /**
   * Sets a new height for the locker. The height must be greater than or equal to 1.
   *
   * @param height the new height of the locker
   * @throws InvalidMeasurementException if the height is less than 1
   */
  public void setHeight(int height) throws InvalidMeasurementException {
    if (height >= MAX_MEASUREMENT) {
      this.height = height;
    } else {
      throw new IllegalArgumentException("height must be greater than 1");
    }
  }

  /**
   * Returns the depth of the locker.
   *
   * @return the depth of the locker
   */
  public int getDepth() {
    return depth;
  }

  /**
   * Sets a new depth for the locker. The depth must be greater than or equal to 1.
   *
   * @param depth the new depth of the locker
   * @throws InvalidMeasurementException if the depth is less than 1
   */
  public void setDepth(int depth) throws InvalidMeasurementException {
    if (depth >= MAX_MEASUREMENT) {
      this.depth = depth;
    } else {
      throw new InvalidMeasurementException("depth must be greater than 1");
    }
  }

  /**
   * Returns whether the locker is occupied.
   *
   * @return true if the locker is occupied, false otherwise
   */
  public boolean isOccupied() {
    return this.occupied;
  }

  /**
   * Returns the mail item stored in the locker (only accessible within the class).
   *
   * @return the mail item in the locker
   */
  private MailItem getMailItem() {
    return this.mailItem;
  }

  /**
   * Adds a mail item to the locker. Throws exceptions if the locker is already occupied or if the
   * mail item exceeds the locker's dimensions.
   *
   * @param newMail the mail item to add
   * @throws LockerOccupiedException     if the locker is already occupied
   * @throws InvalidMeasurementException if the mail item exceeds the locker dimensions
   */
  public void addMail(MailItem newMail) throws LockerOccupiedException, InvalidMeasurementException {
    if (this.occupied) {
      throw new LockerOccupiedException("Cannot add mail since locker is occupied");
    } else {
      if (newMail.getWidth() > this.width || newMail.getHeight() > this.height || newMail.getDepth() > this.depth) {
        throw new InvalidMeasurementException("Cannot add mail that has bigger width, height, or depth");
      } else {
        this.mailItem = newMail;
        this.occupied = true;
      }
    }
  }

  /**
   * Removes and returns the mail item from the locker if the recipient matches.
   * Throws exceptions if the locker is empty or if the recipient does not match.
   *
   * @param guest the recipient trying to pick up the mail
   * @return the mail item in the locker
   * @throws LockerEmptyException      if the locker is empty
   * @throws RecipientMatchException   if the recipient does not match the mail item's recipient
   */
  public MailItem pickupMail(Recipient guest) throws LockerEmptyException, RecipientMatchException {
    if (!this.occupied) {
      throw new LockerEmptyException("No mail in locker");
    } else {
      if (this.mailItem.getRecipient().equals(guest)) {
        MailItem itemReturn = this.getMailItem();  // Store the mail item to return
        this.mailItem = null;  // Empty the locker
        this.occupied = false; // Mark the locker as unoccupied
        return itemReturn;   // Return the stored mail item
      } else {
        throw new RecipientMatchException("Recipient does not match");
      }
    }
  }

  /**
   * Compares this locker to another object. Returns true if the specified object is also
   * a Locker and has the same dimensions, mail item, and occupied status as this locker.
   *
   * @param o the object to compare with this locker
   * @return true if the specified object is equal to this locker, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Locker locker = (Locker) o;
    return width == locker.width &&
        height == locker.height &&
        depth == locker.depth &&
        occupied == locker.occupied &&
        Objects.equals(mailItem, locker.mailItem);
  }

  /**
   * Returns the hash code for this locker based on its width, height, depth, mail item, and occupied status.
   *
   * @return the hash code of this locker
   */
  @Override
  public int hashCode() {
    return Objects.hash(width, height, depth, mailItem, occupied);
  }
}
