package problem2;

import java.util.Arrays;

/**
 * Represents an immutable mathematical Set of integers.
 * This class provides methods to create an empty Set, add or remove elements,
 * check for contents, and get the size of the Set.
 */
public class Set {

  /**
   * Represents the size of an empty Set.
   */
  private static final int EMPTY = 0;

  /**
   * Represents the value to increment the size of the Set when adding an element.
   */
  private static final int ONE = 1;

  /**
   * The elements of the set, stored as an array of Integers.
   * This array is final to ensure immutability.
   */
  private final Integer[] elements;

  /**
   * Private constructor to initialize the Set with a specific array of elements.
   * The constructor is private to enforce immutability by controlling the way sets are created.
   *
   * @param input the array of integers to initialize the Set.
   */
  private Set(Integer[] input) {
    this.elements = input;
  }

  /**
   * Creates and returns an empty Set.
   *
   * @return a new empty Set.
   */
  public static Set emptySet() {
    return new Set(new Integer[EMPTY]);
  }

  /**
   * Checks if the Set is empty.
   *
   * @return true if the Set contains no elements, false otherwise.
   */
  public Boolean isEmpty() {
    return elements.length == EMPTY;
  }

  /**
   * Checks if the Set contains a given Integer.
   *
   * @param n the Integer to check for membership in the Set.
   * @return true if the given Integer is in the Set, false otherwise.
   */
  public Boolean contains(Integer n) {
    for (Integer current : elements) {
      if (current.equals(n)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Adds an Integer to the Set IF it is not already present.
   * Returns a new Set with the element added, leaving the original Set unchanged.
   *
   * @param n the Integer to add to the Set.
   * @return a new Set with the added Integer.
   */
  public Set add(Integer n) {
    if (contains(n)) {
      return this; // If already contains the element, return current set
    }

    Integer[] newElements = Arrays.copyOf(elements, elements.length + ONE);
    newElements[elements.length] = n;
    return new Set(newElements);
  }

  /**
   * Removes an Integer from the Set IF it is present.
   * Returns a new Set with the element removed, leaving the original Set unchanged.
   *
   * @param n the Integer to remove from the Set.
   * @return a new Set with the Integer removed, or the same Set if the Integer was not found.
   */
  public Set remove(Integer n) {
    if (!contains(n)) {
      return this; // If the element doesn't exist, return the same Set
    }

    Integer[] newElements = new Integer[elements.length - ONE];
    int index = EMPTY;
    for (Integer current : elements) {
      if (!current.equals(n)) {
        newElements[index++] = current;
      }
    }
    return new Set(newElements);
  }

  /**
   * Returns the number of elements in the Set.
   *
   * @return the size of the Set as an Integer.
   */
  public Integer size() {
    return elements.length;
  }

  /**
   * Compares the Set with another object for equality.
   * Two sets are considered equal if they contain the same elements.
   *
   * @param o the object to compare this Set with.
   * @return true if the sets contain the same elements, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Set set = (Set) o;
    if (this.size() != set.size()) return false;
    for (Integer element : elements) {
      if (!set.contains(element)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the hash code for the Set, computed based on its elements.
   *
   * @return the hash code of the Set.
   */
  @Override
  public int hashCode() {
    return Arrays.hashCode(elements);
  }

}
