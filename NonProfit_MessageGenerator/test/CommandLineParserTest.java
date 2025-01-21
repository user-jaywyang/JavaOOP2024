package problem1;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandLineParserTest {
  private CommandLineParser parser;

  @BeforeEach
  void setUp() {
    parser = new CommandLineParser();
  }
  @Test
  void parseValidArguments() {
    String[] args = {
        "--email",
        "--email-template", "email-template.txt",
        "--csv-file", "supporters.csv",
        "--output-dir", "output"
    };

    Map<String, String> result = parser.parse(args);

    assertEquals("true", result.get("email"));
    assertEquals("email-template.txt", result.get("email-template"));  // Correct key here
    assertEquals("supporters.csv", result.get("csv-file"));            // Correct key here
    assertEquals("output", result.get("output-dir"));                   // Correct key here
  }

  @Test
  void parseMissingValueForTemplate() {
    String[] args = {
        "--email",
        "--email-template"
    };
    Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse(args));
    assertEquals("Missing required option: --email-template", exception.getMessage());
  }

  @Test
  void parseUnknownArgument() {
    String[] args = {
        "--email",
        "--unknown-arg", "some-value"
    };
    Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse(args));
    assertEquals("Unknown option: --unknown-arg", exception.getMessage());
  }

  @Test
  void parseValidArgumentsWithBothFlags() {
    String[] args = {
        "--email",
        "--email-template", "email-template.txt",
        "--letter",
        "--letter-template", "letter-template.txt",
        "--csv-file", "supporters.csv",
        "--output-dir", "output"
    };
    Map<String, String> result = parser.parse(args);
    assertEquals("true", result.get("email"));
    assertEquals("email-template.txt", result.get("email-template"));  // Correct key here
    assertEquals("true", result.get("letter"));
    assertEquals("letter-template.txt", result.get("letter-template"));  // Correct key here
    assertEquals("supporters.csv", result.get("csv-file"));            // Correct key here
    assertEquals("output", result.get("output-dir"));                   // Correct key here
  }

  @Test
  void parseMissingValueForCsvFile() {
    String[] args = {
        "--csv-file"
    };
    Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse(args));
    assertEquals("Missing required option: --csv-file", exception.getMessage());
  }
}
