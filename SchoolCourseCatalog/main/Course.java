package problem1;

import java.util.Objects;

/**
 * A class that represents a course with a name, prefix, and number.
 */
public class Course {
    private final String name;
    private final String prefix;
    private final int number;

    /**
     * Constructs a Course with the specified name, prefix, and number.
     *
     * @param name   The name of the course.
     * @param prefix The prefix of the course (e.g., "CS").
     * @param number The course number (e.g., 101).
     */
    public Course(String name, String prefix, int number) {
        this.name = name;
        this.prefix = prefix;
        this.number = number;
    }

    /**
     * Returns a string representation of the course in the format "prefix + number: name".
     *
     * @return A string representing the course.
     */
    @Override
    public String toString() {
        return prefix + number + ": " + name;
    }

    /**
     * Compares this course to the specified object for equality. Two courses are equal if they have the
     * same name, prefix, and number.
     *
     * @param o The object to compare with this course.
     * @return true if the specified object is equal to this course, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return number == course.number &&
            name.equals(course.name) &&
            prefix.equals(course.prefix);
    }

    /**
     * Returns the hash code for this course. The hash code is computed based on the course's name, prefix, and number.
     *
     * @return The hash code for this course.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, prefix, number);
    }
}
