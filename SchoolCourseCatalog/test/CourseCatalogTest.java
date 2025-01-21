package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseCatalogTest {

  private CourseCatalog catalog;
  private Course course1;
  private Course course2;
  private Course course3;

  @BeforeEach
  public void setUp() {
    catalog = new CourseCatalog();
    course1 = new Course("Introduction to Programming", "CS", 100);
    course2 = new Course("Data Structures", "CS", 200);
    course3 = new Course("Algorithms", "CS", 300);
  }

  @Test
  public void testAppendAndCount() {
    assertTrue(catalog.isEmpty());
    assertEquals(0, catalog.count());

    catalog.append(course1);
    assertFalse(catalog.isEmpty());
    assertEquals(1, catalog.count());

    catalog.append(course2);
    assertEquals(2, catalog.count());
  }

  @Test
  public void testRemove() throws CourseNotFoundException {
    catalog.append(course1);
    catalog.append(course2);
    catalog.append(course3);
    assertEquals(3, catalog.count());

    catalog.remove(course2);
    assertEquals(2, catalog.count());
    assertFalse(catalog.contains(course2));

    // Remove course from the beginning of the list
    catalog.remove(course1);
    assertEquals(1, catalog.count());
    assertFalse(catalog.contains(course1));
  }

  @Test
  public void testRemoveNonExistentCourse() {
    catalog.append(course1);
    catalog.append(course2);

    assertThrows(CourseNotFoundException.class, () -> {
      catalog.remove(course3); // course3 is not in the catalog
    });
  }

  @Test
  public void testContains() {
    catalog.append(course1);
    assertTrue(catalog.contains(course1));
    assertFalse(catalog.contains(course2));

    catalog.append(course2);
    assertTrue(catalog.contains(course2));
  }

  @Test
  public void testIndexOf() {
    catalog.append(course1);
    catalog.append(course2);
    catalog.append(course3);

    assertEquals(0, catalog.indexOf(course1));
    assertEquals(1, catalog.indexOf(course2));
    assertEquals(2, catalog.indexOf(course3));

    // Test non-existing course
    Course course4 = new Course("Software Engineering", "CS", 400);
    assertEquals(-1, catalog.indexOf(course4));
  }

  @Test
  public void testGet() throws InvalidIndexException {
    catalog.append(course1);
    catalog.append(course2);

    assertEquals(course1, catalog.get(0));
    assertEquals(course2, catalog.get(1));
  }

  @Test
  public void testGetInvalidIndex() {
    catalog.append(course1);
    assertThrows(InvalidIndexException.class, () -> {
      catalog.get(-1); // invalid index
    });

    assertThrows(InvalidIndexException.class, () -> {
      catalog.get(2); // out of bounds index
    });
  }

  @Test
  public void testIsEmpty() {
    assertTrue(catalog.isEmpty());
    catalog.append(course1);
    assertFalse(catalog.isEmpty());
  }

  @Test
  public void testToString() {
    catalog.append(course1);
    catalog.append(course2);
    assertEquals("CourseCatalog: CS100: Introduction to Programming, CS200: Data Structures", catalog.toString());
  }

  @Test
  public void testDynamicResizing() {
    // Initially empty, with a capacity of 10
    for (int i = 0; i < 15; i++) {
      catalog.append(new Course("Course" + i, "CS", 100 + i));
    }
    assertEquals(15, catalog.count());

    // Now, remove some courses and check if it shrinks
    for (int i = 0; i < 10; i++) {
      try {
        catalog.remove(catalog.get(0));
      } catch (CourseNotFoundException | InvalidIndexException e) {
        fail("Exception should not occur while removing courses.");
      }
    }

    // After removing, the array should resize down as well
    assertEquals(5, catalog.count());
  }

  @Test
  public void testEqualsSameObject() {
    // Same reference
    assertTrue(catalog.equals(catalog));
  }

  @Test
  public void testEqualsDifferentObjectSameValues() {
    CourseCatalog catalog2 = new CourseCatalog();
    catalog.append(course1);
    catalog.append(course2);

    catalog2.append(course1);
    catalog2.append(course2);

    // Different objects, same values
    assertTrue(catalog.equals(catalog2));
  }

  @Test
  public void testNotEqualsDifferentCatalog() {
    CourseCatalog catalog2 = new CourseCatalog();
    catalog.append(course1);
    catalog2.append(course2);

    // Different catalogs (one contains course1, other contains course2)
    assertFalse(catalog.equals(catalog2));
  }

  @Test
  public void testNotEqualsDifferentType() {
    // Not equal to null or an object of a different type
    assertFalse(catalog.equals(null));
    assertFalse(catalog.equals("Some String"));
  }

  @Test
  public void testHashCodeSameObjects() {
    CourseCatalog catalog2 = new CourseCatalog();
    catalog.append(course1);
    catalog.append(course2);

    catalog2.append(course1);
    catalog2.append(course2);

    // Equal catalogs must have the same hash code
    assertEquals(catalog.hashCode(), catalog2.hashCode());
  }

  @Test
  public void testHashCodeDifferentObjects() {
    CourseCatalog catalog2 = new CourseCatalog();
    catalog.append(course1);
    catalog2.append(course2);

    // Different catalogs should have different hash codes
    assertNotEquals(catalog.hashCode(), catalog2.hashCode());
  }

}
