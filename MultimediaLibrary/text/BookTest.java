package problem2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

  @Test
  void testEquals_sameObject() {
    Author author = new Author("John", "Doe");
    Book book = new Book("Test Book", author, 2020);
    assertTrue(book.equals(book)); // Test self-comparison
  }

  @Test
  void testEquals_differentObjectSameValues() {
    Author author1 = new Author("John", "Doe");
    Book book1 = new Book("Test Book", author1, 2020);

    Author author2 = new Author("John", "Doe");
    Book book2 = new Book("Test Book", author2, 2020);

    assertTrue(book1.equals(book2)); // Test equal values
  }

  @Test
  void testEquals_nullObject() {
    Author author = new Author("John", "Doe");
    Book book = new Book("Test Book", author, 2020);
    assertFalse(book.equals(null)); // Test comparison with null
  }

  @Test
  void testEquals_differentType() {
    Author author = new Author("John", "Doe");
    Book book = new Book("Test Book", author, 2020);
    assertFalse(book.equals("some string")); // Test comparison with different type
  }

  @Test
  void testEquals_differentValues() {
    Author author1 = new Author("John", "Doe");
    Book book1 = new Book("Test Book", author1, 2020);

    Author author2 = new Author("Jane", "Doe");
    Book book2 = new Book("Different Book", author2, 2021);

    assertFalse(book1.equals(book2)); // Test different title and author
  }

  @Test
  void testHashCode_sameValues() {
    Author author1 = new Author("John", "Doe");
    Book book1 = new Book("Test Book", author1, 2020);

    Author author2 = new Author("John", "Doe");
    Book book2 = new Book("Test Book", author2, 2020);

    assertEquals(book1.hashCode(), book2.hashCode()); // Test equal hashCodes
  }

  @Test
  void testHashCode_differentValues() {
    Author author1 = new Author("John", "Doe");
    Book book1 = new Book("Test Book", author1, 2020);

    Author author2 = new Author("Jane", "Doe");
    Book book2 = new Book("Different Book", author2, 2021);

    assertNotEquals(book1.hashCode(), book2.hashCode()); // Test different hashCodes
  }

  @Test
  void testToString() {
    Author author = new Author("John", "Doe");
    Book book = new Book("Test Book", author, 2020);
    String expected = "Book{title='Test Book', author=John Doe, yearReleased=2020}";
    assertEquals(expected, book.toString()); // Test toString format
  }

  @Test
  void testGetAuthor() {
    Author author = new Author("John", "Doe");
    Book book = new Book("Test Book", author, 2020);
    assertEquals(author, book.getAuthor()); // Test getAuthor
  }

  @Test
  void testConstructor_invalidTitle() {
    Author author = new Author("John", "Doe");
    assertThrows(IllegalArgumentException.class, () -> new Book(null, author, 2020)); // Test invalid title
  }

  @Test
  void testConstructor_invalidYearReleased() {
    Author author = new Author("John", "Doe");
    assertThrows(IllegalArgumentException.class, () -> new Book("Test Book", author, -1)); // Test invalid year
  }
}
