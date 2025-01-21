package problem1;

import java.io.IOException;
import java.util.*;

/**
 * @author Jay
 * Main class for the NonProfit application.
 */
public class NonProfit {

  /** Path to the CSV file containing supporter data. */
  private String csvFilePath;

  /** Directory where output files will be stored. */
  private String outputDirectory;

  /** Path to the email template file. */
  private String emailTemplatePath;

  /** Path to the letter template file. */
  private String letterTemplatePath;

  /** Flag to indicate whether to generate emails. */
  private boolean generateEmails;

  /** Flag to indicate whether to generate letters. */
  private boolean generateLetters;

  /** Map of parsed command-line options. */
  private Map<String, String> parsedOptions = new HashMap<>();

  /**
   * Main entry point for the NonProfit application.
   *
   * @param args Command-line arguments
   */
  public static void main(String[] args) {
    try {
      System.out.println("Starting NonProfit application...");

      // Debug: Print received arguments to confirm they're being passed correctly
      System.out.println("Arguments received: " + Arrays.toString(args));

      // Initialize the application
      NonProfit app = new NonProfit();

      // Process command line arguments
      app.processArguments(args);

      // Debug: Print parsed options to confirm successful parsing
      System.out.println("Parsed options: ");
      app.getParsedOptions().forEach((key, value) -> System.out.println(key + ": " + value));

      // Load supporters from CSV
      CSVParser csvParser = new CSVParser();
      List<Map<String, String>> supporters = csvParser.parse(app.csvFilePath);
      System.out.println("Loaded " + supporters.size() + " supporters from CSV.");

      // Generate communications (email or letter)
      app.generateCommunications(supporters);
      System.out.println("Communications generated successfully.");

    } catch (IllegalArgumentException | IOException e) {
      // Error handling with debugging info
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
      printUsage();
    }
  }

  /**
   * Processes command-line arguments using the CommandLineParser.
   *
   * @param args Command-line arguments
   */
  public void processArguments(String[] args) {
    CommandLineParser parser = new CommandLineParser();
    parsedOptions = parser.parse(args);

    // Extract required options and validate them
    csvFilePath = parsedOptions.get("csv-file");
    outputDirectory = parsedOptions.get("output-dir");
    generateEmails = "true".equals(parsedOptions.get("email"));
    generateLetters = "true".equals(parsedOptions.get("letter"));
    emailTemplatePath = parsedOptions.get("email-template");
    letterTemplatePath = parsedOptions.get("letter-template");

    // Debug output to confirm correct parsing
    System.out.println("Output Directory: " + outputDirectory);

    // Validation
    if (generateEmails && emailTemplatePath == null) {
      throw new IllegalArgumentException("--email requires --email-template to be specified.");
    }
    if (generateLetters && letterTemplatePath == null) {
      throw new IllegalArgumentException("--letter requires --letter-template to be specified.");
    }
  }

  /**
   * Generates email and/or letter communications for the supporters.
   *
   * @param supporters List of supporters represented as maps of attributes.
   * @throws IOException If template files cannot be read or files cannot be written.
   */
  public void generateCommunications(List<Map<String, String>> supporters) throws IOException {
    // Generate email messages if the flag is set
    if (generateEmails) {
      TemplateProcessor emailTemplateProcessor = new TemplateProcessor(emailTemplatePath);
      String emailTemplate = emailTemplateProcessor.getTemplate();
      generateMessages(supporters, emailTemplate, "email");
    }

    // Generate letter messages if the flag is set
    if (generateLetters) {
      TemplateProcessor letterTemplateProcessor = new TemplateProcessor(letterTemplatePath);
      String letterTemplate = letterTemplateProcessor.getTemplate();
      generateMessages(supporters, letterTemplate, "letter");
    }
  }

  /**
   * Generates personalized messages for supporters and writes them to files.
   *
   * @param supporters List of supporters represented as maps of attributes.
   * @param template   The message template.
   * @param type       The type of message (email or letter).
   * @throws IOException If there is an error writing to files.
   */
  public void generateMessages(List<Map<String, String>> supporters, String template, String type) throws IOException {
    MessageGenerator generator = new MessageGenerator();
    FileWriter writer = new FileWriter();

    for (Map<String, String> supporter : supporters) {
      // Extract first and last names from the supporter data
      String firstName = supporter.get("first_name");
      String lastName = supporter.get("last_name");

      // Handle null or empty values for first and last name
      firstName = (firstName == null || firstName.isEmpty()) ? "Unknown" : firstName;
      lastName = (lastName == null || lastName.isEmpty()) ? "Unknown" : lastName;

      // Generate a personalized message
      String personalizedMessage = generator.generate(template, supporter);

      // Create a sanitized file name using the supporter name and message type
      String fileName = firstName + "_" + lastName + "_" + type + ".txt";
      fileName = fileName.replaceAll("[^a-zA-Z0-9_\\-\\.]", "_");

      // Debug output for file writing
      System.out.println("Writing file to: " + outputDirectory + " with name: " + fileName);

      // Write the personalized message to the output file
      writer.writeToFile(outputDirectory, fileName, personalizedMessage);
    }
  }

  /**
   * Prints usage instructions for the application.
   */
  public static void printUsage() {
    System.out.println("Usage:");
    System.out.println("--csv-file <path/to/file>       : Path to the input CSV file. (required)");
    System.out.println("--output-dir <path/to/dir>      : Directory to store generated files. (required)");
    System.out.println("--email                        : Generate email messages. Requires --email-template.");
    System.out.println("--email-template <path/to/file>: Path to the email template file.");
    System.out.println("--letter                       : Generate letter messages. Requires --letter-template.");
    System.out.println("--letter-template <path/to/file>: Path to the letter template file.");
    System.out.println("Examples:");
    System.out.println("--csv-file supporters.csv --output-dir ./output --email --email-template email.txt");
    System.out.println("--csv-file supporters.csv --output-dir ./output --letter --letter-template letter.txt");
  }

  /**
   * Returns the parsed command-line options for debugging or other purposes.
   *
   * @return Map of parsed command-line options.
   */
  public Map<String, String> getParsedOptions() {
    return parsedOptions;
  }

  /**
   * Getter for the CSV file path.
   *
   * @return The path to the CSV file.
   */
  public String getCsvFilePath() {
    return csvFilePath;
  }

  /**
   * Setter for the CSV file path.
   *
   * @param csvFilePath The path to the CSV file.
   */
  public void setCsvFilePath(String csvFilePath) {
    this.csvFilePath = csvFilePath;
  }

  /**
   * Getter for the output directory.
   *
   * @return The directory where output files will be stored.
   */
  public String getOutputDirectory() {
    return outputDirectory;
  }

  /**
   * Setter for the output directory.
   *
   * @param outputDirectory The directory where output files will be stored.
   */
  public void setOutputDirectory(String outputDirectory) {
    this.outputDirectory = outputDirectory;
  }

  /**
   * Getter for the email template path.
   *
   * @return The path to the email template file.
   */
  public String getEmailTemplatePath() {
    return emailTemplatePath;
  }

  /**
   * Setter for the email template path.
   *
   * @param emailTemplatePath The path to the email template file.
   */
  public void setEmailTemplatePath(String emailTemplatePath) {
    this.emailTemplatePath = emailTemplatePath;
  }

  /**
   * Getter for the letter template path.
   *
   * @return The path to the letter template file.
   */
  public String getLetterTemplatePath() {
    return letterTemplatePath;
  }

  /**
   * Setter for the letter template path.
   *
   * @param letterTemplatePath The path to the letter template file.
   */
  public void setLetterTemplatePath(String letterTemplatePath) {
    this.letterTemplatePath = letterTemplatePath;
  }

  /**
   * Checks if email generation is enabled.
   *
   * @return True if email generation is enabled, false otherwise.
   */
  public boolean isGenerateEmails() {
    return generateEmails;
  }

  /**
   * Enables or disables email generation.
   *
   * @param generateEmails True to enable email generation, false to disable.
   */
  public void setGenerateEmails(boolean generateEmails) {
    this.generateEmails = generateEmails;
  }

  /**
   * Checks if letter generation is enabled.
   *
   * @return True if letter generation is enabled, false otherwise.
   */
  public boolean isGenerateLetters() {
    return generateLetters;
  }

/**
 * Enables or disables letter generation.
 *
 * @param generateLetters True to enable letter generation, false to disable
 * Enables or disables letter generation.
 *
 * @param generateLetters True to enable letter generation, false to disable.
 */
  public void setGenerateLetters(boolean generateLetters) {
    this.generateLetters = generateLetters;
}
}
