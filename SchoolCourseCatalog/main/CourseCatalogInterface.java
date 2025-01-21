package problem1;

/**
 * An interface that defines the operations for a Course Catalog, a collection of courses.
 */
public interface CourseCatalogInterface {

  /**
   * Adds a Course to the end of the CourseCatalog.
   *
   * @param course The course to be added.
   */
  void append(Course course);

  /**
   * Removes the specified Course from the CourseCatalog.
   *
   * @param course The course to be removed.
   * @throws CourseNotFoundException If the course is not found in the catalog.
   */
  void remove(Course course) throws CourseNotFoundException;

  /**
   * Checks if the specified course exists in the CourseCatalog.
   *
   * @param course The course to check.
   * @return true if the course is in the catalog, false otherwise.
   */
  boolean contains(Course course);

  /**
   * Returns the index of the specified Course in the CourseCatalog.
   *
   * @param course The course whose index needs to be found.
   * @return The index of the course if it exists, -1 otherwise.
   */
  int indexOf(Course course);

  /**
   * Gets the number of Courses in the CourseCatalog.
   *
   * @return The total number of courses in the catalog.
   */
  int count();

  /**
   * Returns the Course at the given index in the CourseCatalog.
   *
   * @param index The index of the course to return.
   * @return The course at the specified index.
   * @throws InvalidIndexException If the index does not exist.
   */
  Course get(int index) throws InvalidIndexException;

  /**
   * Checks if the CourseCatalog is empty.
   *
   * @return true if the catalog is empty, false otherwise.
   */
  boolean isEmpty();
}
