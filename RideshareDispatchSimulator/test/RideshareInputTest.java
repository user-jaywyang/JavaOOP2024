package option1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for RideshareInput.
 */
class RideshareInputTest {

  @Test
  void testJsonDeserialization() throws IOException {
    // Load the test JSON file
    Path jsonPath = Paths.get("src", "test", "resources", "rideshare_input.json");
    File jsonFile = jsonPath.toFile();

    // Ensure the file exists
    assertTrue(jsonFile.exists(), "Test JSON file does not exist!");

    // Deserialize the JSON file
    ObjectMapper objectMapper = new ObjectMapper();
    RideshareInput input = objectMapper.readValue(jsonFile, RideshareInput.class);

    // Validate drivers
    assertNotNull(input.getDrivers(), "Drivers list should not be null.");
    assertEquals(10, input.getDrivers().size(), "Expected 10 drivers in the input.");

    // Validate ride requests
    assertNotNull(input.getRideRequests(), "Ride requests list should not be null.");
    assertEquals(12, input.getRideRequests().size(), "Expected 12 ride requests in the input.");

    // Check specific driver
    assertEquals("Driver-1", input.getDrivers().get(0).getDriverId(), "First driver ID mismatch.");
    assertEquals("Driver-10", input.getDrivers().get(9).getDriverId(), "Last driver ID mismatch.");

    // Check specific ride request
    RideshareRequest request = input.getRideRequests().get(0);
    assertEquals("C001", request.getCustomerId(), "Customer ID mismatch.");
    assertEquals("Point A", request.getStartingLocation(), "Starting location mismatch.");
    assertEquals("Point B", request.getDestination(), "Destination mismatch.");
    assertEquals(10.0, request.getDistance(), "Distance mismatch.");
    assertEquals("Express pick-up", request.getRideType(), "Ride type mismatch.");
    assertEquals(1000, request.getRequestTime(), "Request time mismatch.");
  }
}
