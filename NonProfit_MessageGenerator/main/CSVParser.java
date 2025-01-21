package problem1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Jackie
 * A utility class to parse CSV (Comma-Separated Values) files.
 * This class reads a CSV file and converts its rows into a list of maps,
 * where each map represents a row with column headers as keys and values
 * as the corresponding cell data.
 */
public class CSVParser {

  /** Constant for index zero. */
  private static final int ZERO = 0;

  /** Constant for increment value one. */
  private static final int ONE = 1;

  /**
   * Parses a CSV file and returns a list of rows as maps of column names to values.
   *
   * @param filePath The path to the CSV file.
   * @return A list of maps where each map represents a row with column headers as keys.
   * @throws IOException If there is an error reading the file.
   */
  public List<Map<String, String>> parse(String filePath) throws IOException {
    List<Map<String, String>> rows = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String headerLine = reader.readLine();

      // Check if the file is empty or lacks headers
      if (headerLine == null || headerLine.isEmpty()) {
        throw new IOException("CSV file is empty or has no header.");
      }

      // Extract headers
      String[] headers = parseCsvLine(headerLine);

      String line;
      while ((line = reader.readLine()) != null) {
        // Parse each line into a map
        String[] values = parseCsvLine(line);
        Map<String, String> row = new HashMap<>();

        // Populate the map with headers as keys and corresponding values
        for (int i = ZERO; i < headers.length; i++) {
          String value = (i < values.length) ? values[i] : "";  // Handle missing values
          row.put(headers[i], value);
        }
        rows.add(row);
      }
    }
    return rows;
  }

  /**
   * Helper method to parse a single line of CSV.
   * This method handles values with commas inside double quotes correctly.
   *
   * @param line The CSV line to be parsed.
   * @return An array of string values parsed from the line.
   */
  private String[] parseCsvLine(String line) {
    List<String> result = new ArrayList<>();
    StringBuilder current = new StringBuilder();
    boolean insideQuote = false;
    boolean previousWasEscape = false;

    // Loop through each character of the line
    for (int i = ZERO; i < line.length(); i++) {
      char c = line.charAt(i);

      if (c == '"' && !previousWasEscape) {  // Quote mark (not escaped)
        insideQuote = !insideQuote;  // Toggle the flag when encountering a quote
      } else if (c == '\\' && insideQuote) {  // Escape character
        previousWasEscape = true;
        continue;  // Skip adding the escape character itself
      } else if (c == ',' && !insideQuote) {  // Split only if outside of quotes
        result.add(current.toString().trim().replace("\"", ""));  // Remove quotes around values
        current = new StringBuilder();  // Reset for next value
        previousWasEscape = false;
      } else {
        current.append(c);  // Add character to the current value
        previousWasEscape = false;
      }
    }

    // Add the last value (after the last comma or end of line)
    result.add(current.toString().trim().replace("\"", ""));
    return result.toArray(new String[ZERO]);
  }
}
