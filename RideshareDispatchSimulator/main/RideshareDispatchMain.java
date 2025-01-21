package option1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Main class for running the Rideshare Dispatch simulation.
 * This class serves as the entry point for the simulation application.
 * It reads input data from a JSON file, initializes the simulation with drivers and ride requests,
 * and runs the simulation to compute the results.
 */
public class RideshareDispatchMain {

  /** Constant for int value zero. */
  private static final int ZERO = 0;

  /** Constant for int value one. */
  private static final int ONE = 1;

  /**
   * The main method that serves as the entry point of the simulation program.
   * It reads the input JSON file specified in the command line arguments,
   * initializes the simulation with the data, and runs the simulation.
   *
   * @param args Command-line arguments where the first argument is the file path to the JSON input file.
   */
  public static void main(String[] args) {
    // Ensure that the file path is provided as a command-line argument
    if (args.length < ONE) {
      System.err.println("Usage: java RideshareDispatchMain <path_to_json_file>");
      return;
    }

    // Retrieve the file path from the command-line argument
    String jsonFilePath = args[ZERO];

    try {
      // Initialize Jackson ObjectMapper to handle JSON deserialization
      ObjectMapper objectMapper = new ObjectMapper();

      // Read the JSON file into the RideshareInput object
      RideshareInput input = objectMapper.readValue(new File(jsonFilePath), RideshareInput.class);

      // Initialize the simulator with the number of drivers
      RideshareDispatchSimulator simulator = new RideshareDispatchSimulator(input.getDrivers().size());

      // Add each ride request to the simulator
      for (RideshareRequest request : input.getRideRequests()) {
        simulator.addRideRequest(request);
      }

      // Run the simulation
      simulator.runSimulation();

      // Output the results of the simulation
      System.out.println("Simulation Results:");
      System.out.println("Average Wait Time: " + simulator.getAverageWaitTime() + " ms");
      System.out.println("Average Rides Per Driver: " + simulator.getAverageRidesPerDriver());
    } catch (IOException e) {
      // Handle errors related to reading the JSON file
      System.err.println("Error reading JSON file: " + e.getMessage());
    } catch (Exception e) {
      // Handle unexpected errors
      System.err.println("An unexpected error occurred: " + e.getMessage());
    }
  }
}
