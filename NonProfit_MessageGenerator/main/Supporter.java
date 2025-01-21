package problem1;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jackie
 * Represents a supporter with dynamic attributes based on the CSV file headers.
 * Attributes are stored in a sorted map to ensure consistent ordering when
 * retrieving or displaying the data.
 */
public class Supporter {

  /** Constant representing a multiplier for hash codes. */
  private static final int HASH_MULTIPLIER = 31;

  private final Map<String, String> attributes;

  /**
   * Constructs a Supporter object with the specified attributes.
   * The attributes map is sorted using a TreeMap to maintain a consistent order.
   *
   * @param attributes Map of attributes where the keys are the CSV headers, and the values are the row data.
   */
  public Supporter(Map<String, String> attributes) {
    this.attributes = new TreeMap<>(attributes); // Ensures consistent order by sorting keys
  }

  /**
   * Retrieves the value of a specific attribute.
   *
   * @param key The attribute key (CSV header).
   * @return The attribute value or {@code null} if the key is not found.
   */
  public String getAttribute(String key) {
    return attributes.get(key);
  }

  /**
   * Retrieves all attributes of the supporter as a sorted map.
   *
   * @return A new TreeMap containing all attributes of the supporter.
   */
  public Map<String, String> getAttributes() {
    return new TreeMap<>(attributes); // Return sorted map to maintain consistent ordering
  }

  /**
   * Provides a string representation of the Supporter object.
   * Includes all attributes for debugging or display purposes.
   *
   * @return A string representation of the Supporter object.
   */
  @Override
  public String toString() {
    return "Supporter{attributes=" + attributes + '}';
  }

  /**
   * Checks equality between this Supporter and another object.
   * Two supporters are considered equal if their attributes are identical.
   *
   * @param obj The object to compare with.
   * @return {@code true} if the objects are equal, otherwise {@code false}.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Supporter)) {
      return false;
    }
    Supporter other = (Supporter) obj;
    return attributes.equals(other.attributes);
  }

  /**
   * Generates a hash code for the Supporter object based on its attributes.
   *
   * @return The hash code of the Supporter object.
   */
  @Override
  public int hashCode() {
    return HASH_MULTIPLIER * attributes.hashCode();
  }
}
