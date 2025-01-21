package problem2;

import java.util.Objects;

/**
 * Represents an abstract class, item in a collection, such as a book or a music piece.
 * An item has a title, a creator, and a release year.
 */
public abstract class Item {

  /**
   * The title of the item.
   */
  private final String title;

  /**
   * The creator of the item.
   */
  private final Creator creator;

  /**
   * The year the item was released.
   */
  private final int yearReleased;

  /**
   * A constant representing the value zero, used for validation.
   */
  private static final int ZERO = 0;

  /**
   * Constructs a new Item with the specified title, creator, and release year.
   *
   * @param title        the title of the item.
   * @param creator      the creator of the item.
   * @param yearReleased the year the item was released.
   * @throws IllegalArgumentException if the title is null or empty, or if the year released is less than or equal to zero.
   */
  public Item(String title, Creator creator, int yearReleased) {
    if (title == null || title.isEmpty()) {
      throw new IllegalArgumentException("Title cannot be null or empty.");
    }
    if (yearReleased <= ZERO) {
      throw new IllegalArgumentException("Year released must be greater than 0.");
    }
    this.title = title;
    this.creator = creator;
    this.yearReleased = yearReleased;
  }

  /**
   * Returns the title of the item.
   *
   * @return the title of the item.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Returns the creator of the item.
   *
   * @return the creator of the item.
   */
  public Creator getCreator() {
    return this.creator;
  }

  /**
   * Returns the year the item was released.
   *
   * @return the year the item was released.
   */
  public int getYearReleased() {
    return this.yearReleased;
  }

  /**
   * Checks if the given title matches this item's title.
   * The comparison is case-insensitive.
   *
   * @param title the title to check.
   * @return true if the title matches, false otherwise.
   * @throws IllegalArgumentException if the title is null or empty.
   */
  public boolean matchesTitle(String title) {
    if (title == null || title.isEmpty()) {
      throw new IllegalArgumentException("Title cannot be null or empty.");
    }
    return this.title.equalsIgnoreCase(title);
  }

  /**
   * Compares this Item to the specified object. The result is true if and only if the
   * argument is not null and is an Item object that has the same title, creator, and year released as this item.
   *
   * @param o the object to compare this Item against.
   * @return true if the given object represents an Item equivalent to this item, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return this.yearReleased == item.yearReleased &&
        Objects.equals(this.title, item.title) &&
        Objects.equals(this.creator, item.creator);
  }

  /**
   * Returns a hash code value for the Item.
   * This method is supported for the benefit of hash tables.
   *
   * @return the hash code value for this Item.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.title, this.creator, this.yearReleased);
  }

  /**
   * Returns a string representation of the Item.
   * The string includes the title, the creator's name, and the year the item was released.
   *
   * @return a string representation of the Item.
   */
  @Override
  public String toString() {
    return "Item{title='" + title + "', creator=" + creator.getName() +
        ", yearReleased=" + yearReleased + "}";
  }
}
