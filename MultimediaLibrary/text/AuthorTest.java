package problem2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

  @Test
  void testEquals_sameObject() {
    Author author = new Author("John", "Doe");
    assertTrue(author.equals(author)); // Test self-comparison
  }

  @Test
  void testEquals_differentObjectSameValues() {
    Author author1 = new Author("John", "Doe");
    Author author2 = new Author("John", "Doe");
    assertTrue(author1.equals(author2)); // Test equal values
  }

  @Test
  void testEquals_nullObject() {
    Author author = new Author("John", "Doe");
    assertFalse(author.equals(null)); // Test comparison with null
  }

  @Test
  void testEquals_differentType() {
    Author author = new Author("John", "Doe");
    assertFalse(author.equals("lolololololo")); // Test comparison with different type
  }

  @Test
  void testEquals_differentValues() {
    Author author1 = new Author("John", "Doe");
    Author author2 = new Author("Jane", "Doe");
    assertFalse(author1.equals(author2)); // Test different first name
  }

  @Test
  void testHashCode_sameValues() {
    Author author1 = new Author("John", "Doe");
    Author author2 = new Author("John", "Doe");
    assertEquals(author1.hashCode(), author2.hashCode()); // Test equal hashCodes
  }

  @Test
  void testHashCode_differentValues() {
    Author author1 = new Author("John", "Doe");
    Author author2 = new Author("Jane", "Doe");
    assertNotEquals(author1.hashCode(), author2.hashCode()); // Test different hashCodes
  }

  @Test
  void testToString() {
    Author author = new Author("John", "Doe");
    String expected = "Author{firstName='John', lastName='Doe'}";
    assertEquals(expected, author.toString()); // Test toString format
  }

  @Test
  void testGetFirstName() {
    Author author = new Author("John", "Doe");
    assertEquals("John", author.getFirstName()); // Test firstName getter
  }

  @Test
  void testGetLastName() {
    Author author = new Author("John", "Doe");
    assertEquals("Doe", author.getLastName()); // Test lastName getter
  }

  @Test
  void testConstructor_invalidFirstName() {
    assertThrows(IllegalArgumentException.class, () -> new Author(null, "Doe")); // Test invalid firstName
  }

  @Test
  void testConstructor_invalidLastName() {
    assertThrows(IllegalArgumentException.class, () -> new Author("John", null)); // Test invalid lastName
  }

  @Test
  void testConstructor_emptyFirstName() {
    assertThrows(IllegalArgumentException.class, () -> new Author("", "Doe")); // Test empty firstName
  }

  @Test
  void testConstructor_emptyLastName() {
    assertThrows(IllegalArgumentException.class, () -> new Author("John", "")); // Test empty lastName
  }
}
