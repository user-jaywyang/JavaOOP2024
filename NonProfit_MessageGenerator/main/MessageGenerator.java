package problem1;

import java.util.Map;

/**
 * @author Jackie
 * A utility class for generating personalized messages based on a template and dynamic data.
 * Replaces placeholders in the template with corresponding values from the data map.
 */
public class MessageGenerator {

  /** Constant representing the start of a placeholder format. */
  private static final String PLACEHOLDER_START = "[[";

  /** Constant representing the end of a placeholder format. */
  private static final String PLACEHOLDER_END = "]]";

  /**
   * Generates a personalized message by replacing placeholders in the template with actual values
   * from the provided data map. Placeholders in the template are formatted as [[key]].
   *
   * @param template The template string containing placeholders in [[key]] format.
   * @param data     A map of placeholders (keys) and their corresponding values.
   * @return A personalized message with all placeholders replaced with actual values.
   */
  public String generate(String template, Map<String, String> data) {
    // Start with the template message
    String personalizedMessage = template;

    // Iterate over all key-value pairs in the data map
    for (Map.Entry<String, String> entry : data.entrySet()) {
      // Format the placeholder as [[key]]
      String placeholder = PLACEHOLDER_START + entry.getKey() + PLACEHOLDER_END;

      // Replace null values with an empty string
      String replacement = entry.getValue() == null ? "" : entry.getValue();

      // Replace the placeholder in the template with the actual value
      personalizedMessage = personalizedMessage.replace(placeholder, replacement);
    }
    return personalizedMessage;
  }
}
