package option1;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for RideshareDispatchMain.
 */
class RideshareDispatchMainTest {

  @Test
  void testMainWithValidJsonInput() {
    // Redirect standard output to capture the main method's output
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Path to the test JSON file
    Path jsonPath = Paths.get("src", "test", "resources", "rideshare_input.json");
    File jsonFile = jsonPath.toFile();

    // Ensure the file exists
    assertTrue(jsonFile.exists(), "Test JSON file does not exist!");

    // Invoke the main method with the file path as an argument
    RideshareDispatchMain.main(new String[]{jsonFile.getAbsolutePath()});

    // Verify the output contains expected results
    String output = outContent.toString();
    assertTrue(output.contains("Simulation Results:"), "Output should contain 'Simulation Results:'.");
    assertTrue(output.contains("Average Wait Time"), "Output should contain 'Average Wait Time'.");
    assertTrue(output.contains("Average Rides Per Driver"), "Output should contain 'Average Rides Per Driver'.");
  }
}
