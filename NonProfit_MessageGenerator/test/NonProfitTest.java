package problem1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class NonProfitTest {

  private static final String TEST_OUTPUT_DIRECTORY = "test-output";
  private NonProfit nonProfit;

  @BeforeEach
  public void setUp() {
    nonProfit = new NonProfit();
  }

  @AfterEach
  public void cleanUp() {
    // Clean up the test-output directory
    Path testOutputPath = Path.of(TEST_OUTPUT_DIRECTORY);
    if (Files.exists(testOutputPath)) {
      try {
        Files.walk(testOutputPath)
            .map(Path::toFile)
            .forEach(file -> {
              if (!file.delete()) {
                System.err.println("Failed to delete file: " + file.getAbsolutePath());
              }
            });
        Files.deleteIfExists(testOutputPath); // Delete the directory itself
      } catch (IOException e) {
        fail("Failed to clean up test-output directory: " + e.getMessage());
      }
    }
  }

  @Test
  void testProcessArgumentsThrowsExceptionIfEmailTemplateIsMissing() {
    String[] args = {
        "--csv-file", "src/test/resources/nonprofit-supporters.csv",
        "--output-dir", "./output",
        "--email" // No email template, this should cause an exception
    };

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      nonProfit.processArguments(args);
    });

    assertEquals("--email requires --email-template to be specified.", exception.getMessage());
  }

  @Test
  void testProcessArgumentsThrowsExceptionIfLetterTemplateIsMissing() {
    String[] args = {
        "--csv-file", "src/test/resources/nonprofit-supporters.csv",
        "--output-dir", "./output",
        "--letter" // No letter template, this should cause an exception
    };

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      nonProfit.processArguments(args);
    });

    assertEquals("--letter requires --letter-template to be specified.", exception.getMessage());
  }

  @Test
  void testProcessArguments() {
    String[] args = {
        "--csv-file", "src/test/resources/nonprofit-supporters.csv",
        "--output-dir", "./output",
        "--email",
        "--email-template", "src/test/resources/email-template.txt"
    };

    nonProfit.processArguments(args);

    assertEquals("src/test/resources/nonprofit-supporters.csv", nonProfit.getCsvFilePath());
    assertEquals("./output", nonProfit.getOutputDirectory());
    assertTrue(nonProfit.isGenerateEmails());
    assertFalse(nonProfit.isGenerateLetters());
    assertEquals("src/test/resources/email-template.txt", nonProfit.getEmailTemplatePath());
    assertNull(nonProfit.getLetterTemplatePath());
  }

  @Test
  void testGenerateCommunications() throws IOException {
    NonProfit app = new NonProfit();

    // Mock inputs
    app.setCsvFilePath("src/test/resources/nonprofit-supporters.csv");
    app.setOutputDirectory(TEST_OUTPUT_DIRECTORY);
    app.setGenerateEmails(true);
    app.setEmailTemplatePath("src/test/resources/email-template.txt");

    // Load supporters
    CSVParser csvParser = new CSVParser();
    List<Map<String, String>> supporters = csvParser.parse(app.getCsvFilePath());

    // Generate communications
    app.generateCommunications(supporters);

    // Assert output files
    File outputDir = new File(TEST_OUTPUT_DIRECTORY);
    assertTrue(outputDir.exists(), "Output directory should exist");

    File[] files = outputDir.listFiles();
    assertNotNull(files, "Output files should be generated");
    assertTrue(files.length > 0, "At least one output file should be created");

    // Validate a specific file's name and contents
    File expectedFile = new File(outputDir, "James_Butt_email.txt");
    assertTrue(expectedFile.exists(), "File for James Butt's email should be created");

    String fileContent = Files.readString(expectedFile.toPath());
    assertTrue(fileContent.contains("James"), "File content should include first name");
    assertTrue(fileContent.contains("Butt"), "File content should include last name");
  }
}
