package problem1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TemplateProcessorTest {

  private static final String TEST_TEMPLATE_PATH = "test-template.txt";
  private static final String TEST_TEMPLATE_CONTENT = "Dear [name],\nThank you for your support.";

  @AfterEach
  void cleanUp() {
    // Delete the test template file after each test
    File testFile = new File(TEST_TEMPLATE_PATH);
    if (testFile.exists()) {
      assertTrue(testFile.delete(), "Failed to delete test template file.");
    }
  }

  @Test
  void testTemplateProcessorLoadsTemplateCorrectly() throws IOException {
    // Create a test template file
    createTestTemplateFile(TEST_TEMPLATE_PATH, TEST_TEMPLATE_CONTENT);

    // Initialize TemplateProcessor with the test template file
    TemplateProcessor processor = new TemplateProcessor(TEST_TEMPLATE_PATH);

    // Normalize line separators for comparison
    String expectedContent = normalizeLineSeparators(TEST_TEMPLATE_CONTENT);
    String actualContent = normalizeLineSeparators(processor.getTemplate());

    // Verify the template content
    assertEquals(expectedContent, actualContent, "Loaded template content does not match expected content.");
  }

  @Test
  void testTemplateProcessorThrowsIOExceptionForNonexistentFile() {
    String nonexistentPath = "nonexistent-template.txt";

    IOException exception = assertThrows(IOException.class, () -> {
      new TemplateProcessor(nonexistentPath);
    });

    assertTrue(exception.getMessage().contains("nonexistent-template.txt"), "Exception message should mention the missing file.");
  }

  @Test
  void testTemplateProcessorHandlesEmptyFile() throws IOException {
    // Create an empty test template file
    createTestTemplateFile(TEST_TEMPLATE_PATH, "");

    // Initialize TemplateProcessor with the empty file
    TemplateProcessor processor = new TemplateProcessor(TEST_TEMPLATE_PATH);

    // Verify the loaded template content is empty
    assertEquals("", processor.getTemplate(), "Loaded template content should be empty.");
  }

  /**
   * Helper method to create a test template file with the specified content.
   *
   * @param filePath The path to the test template file.
   * @param content  The content to write to the file.
   * @throws IOException If there is an error writing to the file.
   */
  private void createTestTemplateFile(String filePath, String content) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write(content);
    }
  }

  /**
   * Normalizes line separators in a string to ensure consistency across different operating systems.
   *
   * @param content The content to normalize.
   * @return The content with normalized line separators.
   */
  private String normalizeLineSeparators(String content) {
    return content.replace("\r\n", "\n").replace("\r", "\n");
  }
}
