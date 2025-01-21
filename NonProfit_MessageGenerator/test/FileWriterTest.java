package problem1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the FileWriter class.
 */
class FileWriterTest {

  private static final String TEST_DIRECTORY = "test-output"; // Directory where test files will be created
  private static final String TEST_FILE_NAME = "test-file.txt"; // Test file name
  private static final String TEST_CONTENT = "This is a test content."; // Sample content for testing

  private final FileWriter fileWriter = new FileWriter();

  /**
   * Cleans up test files and directories after each test.
   */
  @AfterEach
  void cleanUp() {
    Path testDir = Paths.get(TEST_DIRECTORY);
    if (Files.exists(testDir)) {
      try {
        Files.walk(testDir)
            .map(Path::toFile)
            .forEach(file -> {
              if (!file.delete()) {
                System.err.println("Failed to delete: " + file.getAbsolutePath());
              }
            });
        Files.deleteIfExists(testDir); // Finally, delete the directory itself
      } catch (IOException e) {
        fail("Failed to clean up test directory: " + e.getMessage());
      }
    }
  }
  /**
   * Deletes a directory and its contents.
   * Handles cases where files or directories are locked temporarily.
   *
   * @param dir The directory to delete.
   */
  private void deleteTestDir(File dir) {
    // First, delete all files within the directory
    if (dir.isDirectory()) {
      File[] files = dir.listFiles();
      if (files != null) {
        for (File file : files) {
          if (file.isDirectory()) {
            deleteTestDir(file); // Recursive call for subdirectories
          } else {
            // Try deleting the file
            if (!file.delete()) {
              System.err.println("Failed to delete file: " + file.getAbsolutePath());
            }
          }
        }
      }
    }

    // Retry deleting the directory itself
    for (int i = 0; i < 3; i++) { // Retry up to 3 times
      if (dir.delete()) {
        return; // Success
      }
      try {
        Thread.sleep(100); // Wait for 100ms before retrying
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    // Final assertion if deletion failed after retries
    assertTrue(dir.delete(), "Failed to delete: " + dir.getAbsolutePath());
  }

  @Test
  void testWriteToFileCreatesFileWithCorrectContent() throws IOException {
    fileWriter.writeToFile(TEST_DIRECTORY, TEST_FILE_NAME, TEST_CONTENT);

    File testFile = new File(TEST_DIRECTORY, TEST_FILE_NAME);
    assertTrue(testFile.exists(), "Test file should exist.");

    try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
      String fileContent = reader.readLine();
      assertEquals(TEST_CONTENT, fileContent, "File content does not match the expected content.");
    }
  }

  @Test
  void testWriteToFileCreatesDirectoryIfNotExists() throws IOException {
    File testDir = new File(TEST_DIRECTORY);
    if (testDir.exists()) {
      deleteTestDir(testDir); // Ensure the test directory does not exist before running the test
    }

    fileWriter.writeToFile(TEST_DIRECTORY, TEST_FILE_NAME, TEST_CONTENT);

    assertTrue(testDir.exists(), "Test directory should be created.");
    assertTrue(testDir.isDirectory(), "Test directory should be valid.");
  }

  @Test
  void testWriteToFileThrowsIOExceptionForInvalidDirectory() {
    String invalidDirectory = "invalid:/directory";

    IOException exception = assertThrows(IOException.class, () -> {
      fileWriter.writeToFile(invalidDirectory, TEST_FILE_NAME, TEST_CONTENT);
    });

    assertTrue(exception.getMessage().contains("Failed to create directory"),
        "Exception message should indicate directory creation failure.");
  }

  @Test
  void testWriteToFileHandlesSpecialCharactersInContent() throws IOException {
    String specialContent = "Content with special characters: &<>\"'";

    fileWriter.writeToFile(TEST_DIRECTORY, "special-char-file.txt", specialContent);

    File testFile = new File(TEST_DIRECTORY, "special-char-file.txt");
    assertTrue(testFile.exists(), "Test file with special characters should exist.");

    try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
      String fileContent = reader.readLine();
      assertEquals(specialContent, fileContent, "File content with special characters does not match the expected content.");
    }
  }

}
