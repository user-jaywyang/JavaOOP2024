package problem2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmptyBagOfWordsTest {
  private final BagOfWords emptyBag = new EmptyBagOfWords();

  @Test
  void testEmptyBagOfWords_emptyBagOfWords() {
    BagOfWords newEmptyBag = emptyBag.emptyBagOfWords();
    assertTrue(newEmptyBag.isEmpty(), "Empty bag should be empty");
  }

  @Test
  void testIsEmpty() {
    assertTrue(emptyBag.isEmpty(), "An empty bag should return true for isEmpty");
  }

  @Test
  void testSize() {
    assertEquals(0, emptyBag.size(), "The size of an empty bag should be 0");
  }

  @Test
  void testAdd() {
    BagOfWords newBag = emptyBag.add("word");
    assertFalse(newBag.isEmpty(), "Adding an element should return a non-empty bag");
    assertEquals(1, newBag.size(), "Adding an element should increase size to 1");
  }

  @Test
  void testContains() {
    assertFalse(emptyBag.contains("word"), "Empty bag should not contain any elements");
  }

  @Test
  void testEquals() {
    BagOfWords anotherEmptyBag = new EmptyBagOfWords();
    assertEquals(emptyBag, anotherEmptyBag, "Two empty bags should be equal");
  }

  @Test
  void testHashCode() {
    BagOfWords anotherEmptyBag = new EmptyBagOfWords();
    assertEquals(emptyBag.hashCode(), anotherEmptyBag.hashCode(), "Hash code of two empty bags should be equal");
  }

  @Test
  void testToString() {
    assertEquals("EmptyBagOfWords", emptyBag.toString(), "toString should return 'EmptyBagOfWords'");
  }

}
