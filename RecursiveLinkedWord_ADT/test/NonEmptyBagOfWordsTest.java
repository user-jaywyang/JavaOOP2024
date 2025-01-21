package problem2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonEmptyBagOfWordsTest {
  private BagOfWords emptyBag;
  private BagOfWords nonEmptyBag;

  @BeforeEach
  void setUp() {
    emptyBag = new EmptyBagOfWords();
    nonEmptyBag = new NonEmptyBagOfWords("test", emptyBag);
  }

  @Test
  void testEmptyBagOfWords_emptyBagOfWords() {
    BagOfWords newEmptyBag = nonEmptyBag.emptyBagOfWords();
    assertTrue(newEmptyBag.isEmpty(), "emptyBagOfWords() should return an empty bag");
  }

  @Test
  void testIsEmpty() {
    assertFalse(nonEmptyBag.isEmpty(), "A non-empty bag should return false for isEmpty");
  }

  @Test
  void testSize() {
    assertEquals(1, nonEmptyBag.size(), "Non-empty bag with one element should have size 1");
    BagOfWords newBag = nonEmptyBag.add("new");
    assertEquals(2, newBag.size(), "Adding an element should increase the size by 1");
  }

  @Test
  void testAdd() {
    BagOfWords newBag = nonEmptyBag.add("word");
    assertEquals(2, newBag.size(), "Adding a new element should increase the size to 2");
    assertTrue(newBag.contains("word"), "The new bag should contain the added element");
  }

  @Test
  void testContains() {
    assertTrue(nonEmptyBag.contains("test"), "Non-empty bag should contain added word");
    assertFalse(nonEmptyBag.contains("notpresent"), "Non-empty bag should not contain absent word");
  }

  @Test
  void testEquals() {
    BagOfWords anotherNonEmptyBag = new NonEmptyBagOfWords("test", emptyBag);
    assertEquals(nonEmptyBag, anotherNonEmptyBag, "Two bags with the same elements should be equal");

    BagOfWords differentBag = new NonEmptyBagOfWords("different", emptyBag);
    assertNotEquals(nonEmptyBag, differentBag, "Bags with different elements should not be equal");
  }

  @Test
  void testHashCode() {
    BagOfWords anotherNonEmptyBag = new NonEmptyBagOfWords("test", emptyBag);
    assertEquals(nonEmptyBag.hashCode(), anotherNonEmptyBag.hashCode(), "Hash code should match for equal bags");

    BagOfWords differentBag = new NonEmptyBagOfWords("different", emptyBag);
    assertNotEquals(nonEmptyBag.hashCode(), differentBag.hashCode(), "Hash codes should differ for non-equal bags");
  }

  @Test
  void testToString() {
    assertEquals("NonEmptyBagOfWords{word='test', next=EmptyBagOfWords}", nonEmptyBag.toString(),
        "toString should correctly represent the non-empty bag with nested structure");

    BagOfWords twoElementBag = nonEmptyBag.add("another");
    assertEquals("NonEmptyBagOfWords{word='another', next=NonEmptyBagOfWords{word='test', next=EmptyBagOfWords}}",
        twoElementBag.toString(), "toString should represent multiple nested elements");
  }

}
