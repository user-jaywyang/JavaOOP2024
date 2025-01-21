package problem2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringLinkedListTest {

  private StringLinkedList list;

  @BeforeEach
  void setUp() {
    list = new StringLinkedList();
  }

  // Test if the list is empty
  @Test
  void testIsEmpty() {
    assertTrue(list.isEmpty());
    list.add("apple");
    assertFalse(list.isEmpty());
  }

  // Test size of the list
  @Test
  void testSize() {
    assertEquals(0, list.size());
    list.add("apple");
    assertEquals(1, list.size());
    list.add("banana");
    assertEquals(2, list.size());
  }

  // Test contains method
  @Test
  void testContains() {
    list.add("apple");
    list.add("banana");
    assertTrue(list.contains("apple"));
    assertFalse(list.contains("cherry"));
  }

  // Test containsAll method
  @Test
  void testContainsAll() {
    StringLinkedList otherList = new StringLinkedList();
    otherList.add("apple");
    otherList.add("banana");

    list.add("apple");
    list.add("banana");
    list.add("cherry");

    assertTrue(list.containsAll(otherList));

    otherList.add("grape");
    assertFalse(list.containsAll(otherList));
  }

  // Test filterLargerThan method
  @Test
  void testFilterLargerThan() {
    list.add("apple");
    list.add("banana");
    list.add("kiwi");

    StringLinkedList filteredList = list.filterLargerThan(5);

    assertEquals(1, filteredList.size());
    assertTrue(filteredList.contains("kiwi"));
    assertFalse(filteredList.contains("apple"));
  }

  // Test hasDuplicates method
  @Test
  void testHasDuplicates() {
    list.add("apple");
    list.add("banana");
    list.add("apple");  // duplicate

    assertTrue(list.hasDuplicates());

    list = new StringLinkedList(); // reset the list
    list.add("apple");
    list.add("banana");

    assertFalse(list.hasDuplicates());
  }

  // Test removeDuplicates method
  @Test
  void testRemoveDuplicates() {
    list.add("apple");
    list.add("banana");
    list.add("apple");  // duplicate

    StringLinkedList noDuplicatesList = list.removeDuplicates();

    assertEquals(2, noDuplicatesList.size());
    assertTrue(noDuplicatesList.contains("apple"));
    assertTrue(noDuplicatesList.contains("banana"));
  }

  // Test if adding elements works and displaying the list (indirect test of add method)
  @Test
  void testAddAndDisplay() {
    list.add("apple");
    list.add("banana");

    // Since there's no return value for display, we indirectly test the functionality
    // by ensuring size and contains methods are working correctly.
    assertEquals(2, list.size());
    assertTrue(list.contains("apple"));
    assertTrue(list.contains("banana"));
  }
}
