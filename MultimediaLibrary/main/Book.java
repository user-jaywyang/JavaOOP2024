package problem2;

import java.util.Objects;

/**
 * Represents a book, which includes a title, an author, and the year it was published.
 * Extends abstract class Item
 */
public class Book extends Item {

  /**
   * The author of the book.
   */
  private final Author author;

  /**
   * Constructs a new Book with the specified title, author, and year of release.
   *
   * @param title        the title of the book.
   * @param author       the author of the book.
   * @param yearReleased the year the book was published.
   */
  public Book(String title, Author author, int yearReleased) {
    super(title, author, yearReleased);
    this.author = author;
  }

  /**
   * Returns the author of the book.
   *
   * @return the author of the book.
   */
  public Author getAuthor() {
    return this.author;
  }

  /**
   * Compares this Book to the specified object. The result is true if and only if the
   * argument is not null and is a Book object that has the same title, author, and year released as this Book.
   *
   * @param o the object to compare this Book against.
   * @return true if the given object represents a Book equivalent to this Book, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return Objects.equals(this.getAuthor(), book.getAuthor()) &&
        Objects.equals(this.getTitle(), book.getTitle()) &&
        this.getYearReleased() == book.getYearReleased();
  }

  /**
   * Returns a hash code value for the Book. This method is supported for the benefit of hash tables.
   *
   * @return the hash code value for this Book.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getAuthor(), this.getTitle(), this.getYearReleased());
  }

  /**
   * Returns a string representation of the Book.
   * The string includes the title, the author's full name, and the year the book was released.
   *
   * @return a string representation of the Book.
   */
  @Override
  public String toString() {
    return "Book{title='" + getTitle() + "', author=" + author.getFirstName() + " " + author.getLastName() +
        ", yearReleased=" + getYearReleased() + "}";
  }
}
