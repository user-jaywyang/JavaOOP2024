package problem1;

import java.util.Arrays;
import java.util.Objects;

/**
 * A class that implements the CourseCatalogInterface, representing a dynamic, mutable collection
 * of Course objects. The catalog dynamically doubles or halves its size as needed when courses are added or removed.
 */
public class CourseCatalog implements CourseCatalogInterface {

  /**
   * The initial capacity of the CourseCatalog.
   */
  private static final int INITIAL_CAPACITY = 10;

  /**
   * Represents an empty state or index (value of 0).
   */
  private static final int EMPTY = 0;

  /**
   * Represents the value 1, used for shifting or incrementing indices.
   */
  private static final int ONE = 1;

  /**
   * Represents an error state, typically used as a return value when a search fails.
   */
  private static final int ERROR = -1;

  /**
   * Represents the value 2, used for doubling or halving array sizes.
   */
  private static final int TWO = 2;


  private Course[] catalog;
  private int size;

  /**
   * Constructs an empty CourseCatalog with an initial capacity of 10 for storing courses.
   * The catalog is initialized with zero courses.
   */
  public CourseCatalog() {
    this.catalog = new Course[INITIAL_CAPACITY]; // initial capacity
    this.size = EMPTY;
  }


  /**
   * Adds a Course to the end of the CourseCatalog. If the internal array is full,
   * the array size is doubled.
   *
   * @param course The course to be added.
   */
  @Override
  public void append(Course course) {
    doubleIfNecessary();
    catalog[size] = course;
    size++;
  }

  /**
   * Removes the specified Course from the CourseCatalog. If the Course is not found,
   * a CourseNotFoundException is thrown. After removal, all courses are shifted
   * to the left to fill the gap.
   *
   * @param course The course to be removed.
   * @throws CourseNotFoundException If the course is not found in the catalog.
   */
  @Override
  public void remove(Course course) throws CourseNotFoundException {
    int index = indexOf(course);
    if (index == ERROR) {
      throw new CourseNotFoundException("Course not found in the catalog.");
    }
    // Shift elements to the left
    for (int i = index; i < size - ONE; i++) {
      catalog[i] = catalog[i + ONE];
    }
    catalog[size - ONE] = null;
    size--;
    shrinkIfNecessary(); // Check if the array needs to shrink
  }

  /**
   * Ensures the internal array has enough capacity to hold more courses. If the array
   * is full, the size is doubled.
   */
  private void doubleIfNecessary() {
    if (size == catalog.length) {
      catalog = Arrays.copyOf(catalog, catalog.length * TWO); // Double the array size
    }
  }

  /**
   * Shrinks the internal array if it is too empty, reducing the size by half
   * if the number of courses is less than half the array's current capacity.
   */
  private void shrinkIfNecessary() {
    if (size < catalog.length / TWO && catalog.length > INITIAL_CAPACITY) {
      catalog = Arrays.copyOf(catalog, catalog.length / TWO); // Halve the array size
    }
  }

  /**
   * Checks if the specified course exists in the CourseCatalog.
   *
   * @param course The course to check for.
   * @return true if the course exists, false otherwise.
   */
  @Override
  public boolean contains(Course course) {
    return indexOf(course) != ERROR;
  }

  /**
   * Returns the index of the specified Course in the CourseCatalog, or -1 if the Course
   * is not found.
   *
   * @param course The course to find.
   * @return The index of the course if found, -1 otherwise.
   */
  @Override
  public int indexOf(Course course) {
    for (int i = EMPTY; i < size; i++) {
      if (catalog[i].equals(course)) {
        return i;
      }
    }
    return ERROR;
  }

  /**
   * Returns the number of courses currently in the CourseCatalog.
   *
   * @return The number of courses in the catalog.
   */
  @Override
  public int count() {
    return size;
  }

  /**
   * Returns the Course at the specified index in the CourseCatalog.
   *
   * @param index The index of the course to return.
   * @return The course at the given index.
   * @throws InvalidIndexException If the index is out of bounds.
   */
  @Override
  public Course get(int index) throws InvalidIndexException {
    if (index < EMPTY || index >= size) {
      throw new InvalidIndexException("Invalid index: " + index);
    }
    return catalog[index];
  }

  /**
   * Checks if the CourseCatalog is empty.
   *
   * @return true if the catalog is empty, false otherwise.
   */
  @Override
  public boolean isEmpty() {
    return size == EMPTY;
  }

  /**
   * Returns a string representation of the CourseCatalog in the format:
   * "CourseCatalog: [course1, course2, ...]".
   *
   * @return A string representing the CourseCatalog.
   */
  @Override
  public String toString() {
    String result = "CourseCatalog: ";
    for (int i = EMPTY; i < size; i++) {
      result += catalog[i].toString();
      if (i < size - ONE) {
        result += ", ";
      }
    }
    return result;
  }

  /**
   * Compares this CourseCatalog to another object for equality. Two CourseCatalogs are
   * considered equal if they have the same number of courses in the same order.
   *
   * @param o The object to compare to.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseCatalog that = (CourseCatalog) o;
    return size == that.size && Arrays.equals(catalog, that.catalog);
  }

  /**
   * Returns the hash code for this CourseCatalog, based on the courses it contains.
   *
   * @return The hash code for this CourseCatalog.
   */
  @Override
  public int hashCode() {
    return Objects.hash(size, Arrays.hashCode(catalog));
  }

}
