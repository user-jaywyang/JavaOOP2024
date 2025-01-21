package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

  private Course course1;
  private Course course2;
  private Course course3;

  @BeforeEach
  public void setUp() {
    course1 = new Course("Introduction to Programming", "CS", 100);
    course2 = new Course("Data Structures", "CS", 200);
    course3 = new Course("Introduction to Programming", "CS", 100); // Same as course1
  }

  @Test
  public void testConstructorAndGetters() {
    // Constructor should correctly initialize fields
    assertEquals("Introduction to Programming", course1.toString().split(": ")[1]);
    assertEquals("CS", course1.toString().split(": ")[0].substring(0, 2));
    assertEquals("CS100: Introduction to Programming", course1.toString());
  }

  @Test
  public void testToString() {
    assertEquals("CS100: Introduction to Programming", course1.toString());
    assertEquals("CS200: Data Structures", course2.toString());
  }

  @Test
  public void testEqualsSameObject() {
    // Same reference
    assertTrue(course1.equals(course1));
  }

  @Test
  public void testEqualsDifferentObjectSameValues() {
    // Different objects, same values
    assertTrue(course1.equals(course3));
  }

  @Test
  public void testNotEqualsDifferentCourse() {
    // Different courses
    assertFalse(course1.equals(course2));
  }

  @Test
  public void testNotEqualsDifferentType() {
    // Not equal to null or an object of a different type
    assertFalse(course1.equals(null));
    assertFalse(course1.equals("Some String"));
  }

  @Test
  public void testHashCodeSameObjects() {
    // Equal objects must have the same hash code
    assertEquals(course1.hashCode(), course3.hashCode());
  }

  @Test
  public void testHashCodeDifferentObjects() {
    // Different objects should have different hash codes
    assertNotEquals(course1.hashCode(), course2.hashCode());
  }
}
