package problem1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *  @author Jay
 * A utility class to process and load templates from a file.
 */
public class TemplateProcessor {

  /** Constant representing the initial size for the StringBuilder. */
  private static final int INITIAL_STRING_BUILDER_CAPACITY = 500;

  /** Holds the template content. */
  private String template;

  /**
   * Constructs a TemplateProcessor with the provided template file path.
   *
   * @param templatePath The path to the template file.
   * @throws IOException If there is an error reading the file.
   */
  public TemplateProcessor(String templatePath) throws IOException {
    this.template = loadTemplate(templatePath);
  }

  /**
   * Loads the template content from a file.
   *
   * @param templatePath The path to the template file.
   * @return The template content as a string.
   * @throws IOException If there is an error reading the file.
   */
  private String loadTemplate(String templatePath) throws IOException {
    StringBuilder content = new StringBuilder(INITIAL_STRING_BUILDER_CAPACITY);
    try (BufferedReader reader = new BufferedReader(new FileReader(templatePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append(System.lineSeparator()); // Add each line followed by a newline
      }
    }
    return content.toString().trim(); // Trim trailing newlines or spaces
  }

  /**
   * Returns the loaded template content.
   *
   * @return The template content.
   */
  public String getTemplate() {
    return template;
  }
}
