package problem1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jackie
 * A utility class for parsing command-line arguments.
 * Supports options that require values as well as flags.
 */
public class CommandLineParser {

  /** Constant representing the increment for parsing the next argument. */
  private static final int NEXT_ARGUMENT = 1;

  /** Constant representing the substring index for removing option prefixes. */
  private static final int PREFIX_LENGTH = 2;

  /** Map defining valid options and whether they require values. */
  private final Map<String, Boolean> validOptions;

  /**
   * Constructor for initializing the set of valid command-line options.
   * Options are defined with a flag indicating whether they require a value.
   */
  public CommandLineParser() {
    validOptions = new HashMap<>();
    validOptions.put("email", false);           // Flag, no value required
    validOptions.put("letter", false);          // Flag, no value required
    validOptions.put("email-template", true);   // Requires a value
    validOptions.put("letter-template", true);  // Requires a value
    validOptions.put("csv-file", true);         // Requires a value
    validOptions.put("output-dir", true);       // Requires a value
  }

  /**
   * Parses the command-line arguments and validates them against predefined options.
   *
   * @param args The array of command-line arguments.
   * @return A map of parsed options with their corresponding values.
   * @throws IllegalArgumentException If an invalid option or argument format is encountered.
   */
  public Map<String, String> parse(String[] args) {
    Map<String, String> parsedArgs = new HashMap<>();

    // Iterate through the arguments array
    for (int i = 0; i < args.length; i++) {
      String arg = args[i];

      // Validate option prefix
      if (arg.startsWith("--")) {
        String option = arg.substring(PREFIX_LENGTH); // Remove the "--"

        // Handle unknown options
        if (!validOptions.containsKey(option)) {
          throw new IllegalArgumentException("Unknown option: " + arg);
        }

        // Handle options that require a value
        if (validOptions.get(option)) {
          if (i + NEXT_ARGUMENT < args.length && !args[i + NEXT_ARGUMENT].startsWith("--")) {
            parsedArgs.put(option, args[++i]); // Add the option with its value
          } else {
            throw new IllegalArgumentException("Missing required option: --" + option);
          }
        } else {
          // For flags, set the value to "true"
          parsedArgs.put(option, "true");
        }
      } else {
        // Invalid argument format (not starting with "--")
        throw new IllegalArgumentException("Invalid argument format: " + arg);
      }
    }

    return parsedArgs;
  }
}
