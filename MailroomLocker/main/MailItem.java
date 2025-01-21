package problem2;

import java.util.Objects;

/**
 * The MailItem class represents a mail item with dimensions (width, height, depth)
 * and a recipient. It provides methods to access and modify these fields, along
 * with proper validation for the dimensions.
 */
public class MailItem {

  // Width, height, and depth are expected to be greater than or equal to 1
  private int width;
  private int height;
  private int depth;
  private Recipient recipient;
  private static final int MAX_MEASUREMENT = 1;

  /**
   * Constructs a new MailItem with the specified width, height, depth, and recipient.
   *
   * @param width     the width of the mail item (must be >= 1)
   * @param height    the height of the mail item (must be >= 1)
   * @param depth     the depth of the mail item (must be >= 1)
   * @param recipient the recipient of the mail item
   */
  public MailItem(int width, int height, int depth, Recipient recipient) {
    this.width = width;
    this.height = height;
    this.depth = depth;
    this.recipient = recipient;
  }

  /**
   * Returns the width of the mail item.
   *
   * @return the width of the mail item
   */
  public int getWidth() {
    return width;
  }

  /**
   * Sets a new width for the mail item. The width must be greater than or equal to 1.
   *
   * @param width the new width of the mail item
   * @throws InvalidMeasurementException if the width is less than 1
   */
  public void setWidth(int width) throws InvalidMeasurementException {
    if (width >= MAX_MEASUREMENT) {
      this.width = width;
    } else {
      throw new InvalidMeasurementException("width must be greater than 1");
    }
  }

  /**
   * Returns the height of the mail item.
   *
   * @return the height of the mail item
   */
  public int getHeight() {
    return height;
  }

  /**
   * Sets a new height for the mail item. The height must be greater than or equal to 1.
   *
   * @param height the new height of the mail item
   * @throws InvalidMeasurementException if the height is less than 1
   */
  public void setHeight(int height) throws InvalidMeasurementException {
    if (height >= MAX_MEASUREMENT) {
      this.height = height;
    } else {
      throw new InvalidMeasurementException("height must be greater than 1");
    }
  }

  /**
   * Returns the depth of the mail item.
   *
   * @return the depth of the mail item
   */
  public int getDepth() {
    return depth;
  }

  /**
   * Sets a new depth for the mail item. The depth must be greater than or equal to 1.
   *
   * @param depth the new depth of the mail item
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
   * Returns the recipient of the mail item.
   *
   * @return the recipient of the mail item
   */
  public Recipient getRecipient() {
    return recipient;
  }

  /**
   * Sets a new recipient for the mail item.
   *
   * @param recipient the new recipient of the mail item
   */
  public void setRecipient(Recipient recipient) {
    this.recipient = recipient;
  }

  /**
   * Compares this mail item to another object. Returns true if the specified object is also
   * a MailItem and has the same width, height, depth, and recipient as this mail item.
   *
   * @param o the object to compare with this mail item
   * @return true if the specified object is equal to this mail item, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MailItem mailItem = (MailItem) o;
    return width == mailItem.width &&
        height == mailItem.height &&
        depth == mailItem.depth &&
        recipient.equals(mailItem.recipient);
  }

  /**
   * Returns the hash code for this mail item based on its width, height, depth, and recipient.
   *
   * @return the hash code of this mail item
   */
  @Override
  public int hashCode() {
    return Objects.hash(width, height, depth, recipient);
  }
}
