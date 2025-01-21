package problem2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetTest {

  private Set emptySet;
  private Set nonEmptySet;

  @BeforeEach
  void setUp() {
    emptySet = Set.emptySet();
    nonEmptySet = emptySet.add(1).add(2).add(3);
  }

  @Test
  void testEmptySet() {
    // Testing emptySet method
    assertNotNull(emptySet);
    assertTrue(emptySet.isEmpty());
  }

  @Test
  void testIsEmpty() {
    // Testing isEmpty method
    assertTrue(emptySet.isEmpty());
    assertFalse(nonEmptySet.isEmpty());
  }

  @Test
  void testAdd() {
    // Adding elements to an empty set
    Set setWithOneElement = emptySet.add(1);
    assertFalse(setWithOneElement.isEmpty());
    assertTrue(setWithOneElement.contains(1));
    assertEquals(1, setWithOneElement.size());

    // Adding duplicate elements should not change the set
    Set setWithDuplicate = setWithOneElement.add(1);
    assertEquals(1, setWithDuplicate.size());
  }

  @Test
  void testContains() {
    // Testing contains method
    assertFalse(emptySet.contains(1));
    assertTrue(nonEmptySet.contains(1));
    assertTrue(nonEmptySet.contains(2));
    assertTrue(nonEmptySet.contains(3));
    assertFalse(nonEmptySet.contains(4));
  }

  @Test
  void testRemove() {
    // Removing elements from a set
    Set setAfterRemoval = nonEmptySet.remove(2);
    assertFalse(setAfterRemoval.contains(2));
    assertTrue(setAfterRemoval.contains(1));
    assertTrue(setAfterRemoval.contains(3));
    assertEquals(2, setAfterRemoval.size());

    // Removing a non-existent element should not change the set
    Set unchangedSet = nonEmptySet.remove(4);
    assertEquals(nonEmptySet, unchangedSet);
  }

  @Test
  void testSize() {
    // Testing size method
    assertEquals(0, emptySet.size());
    assertEquals(3, nonEmptySet.size());

    Set setWithTwoElements = nonEmptySet.remove(1);
    assertEquals(2, setWithTwoElements.size());
  }

  @Test
  void testEquals() {
    // Testing equality of sets
    Set set1 = Set.emptySet().add(1).add(2).add(3);
    Set set2 = Set.emptySet().add(1).add(2).add(3);

    assertEquals(set1, set2);
    assertEquals(nonEmptySet, set1);
    assertNotEquals(nonEmptySet, emptySet);

    // Test equality with different order of insertion
    Set setWithDifferentOrder = Set.emptySet().add(3).add(1).add(2);
    assertEquals(setWithDifferentOrder, nonEmptySet);
  }

  @Test
  void testHashCode() {
    // Testing hashCode method
    Set set1 = Set.emptySet().add(1).add(2).add(3);
    Set set2 = Set.emptySet().add(1).add(2).add(3);

    assertEquals(set1.hashCode(), set2.hashCode());

    // Test with a different set
    Set differentSet = Set.emptySet().add(1).add(2);
    assertNotEquals(nonEmptySet.hashCode(), differentSet.hashCode());
  }

  @Test
  void testImmutability() {
    // Ensure immutability: original sets should not be modified
    Set set1 = emptySet.add(1);
    Set set2 = set1.add(2);

    assertTrue(emptySet.isEmpty());
    assertEquals(1, set1.size());
    assertEquals(2, set2.size());

    // Ensure removing from a set doesn't modify the original set
    Set setAfterRemove = nonEmptySet.remove(2);
    assertEquals(3, nonEmptySet.size());
    assertEquals(2, setAfterRemove.size());
  }
}
