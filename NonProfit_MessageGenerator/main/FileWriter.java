package problem1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 *  @author Jay
 * A utility class for writing content to files.
 * This class ensures that directories are created if they do not exist
 * and handles file creation and content writing.
 */
public class FileWriter {

  /** Constant representing the success value for file creation. */

  /**
   * Writes content to a file in the specified directory.
   *
   * This method creates the specified directory if it does not already exist.
   * It then writes the provided content to a file within that directory. If the
   * directory cannot be created or an error occurs during writing, an IOException
   * is thrown.
   *
   * @param directory The directory where the file will be created.
   * @param fileName  The name of the file to be created.
   * @param content   The content to be written to the file.
   * @throws IOException If there is an error writing to the file.
   */
  public void writeToFile(String directory, String fileName, String content) throws IOException {
    // Create the directory if it doesn't exist
    File dir = new File(directory);
    if (!dir.exists() && dir.mkdirs() != true) {
      throw new IOException("Failed to create directory: " + directory);
    }

    // Create the file object
    File file = new File(dir, fileName);

    // Write content to the file
    try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(file))) {
      writer.write(content); // Write the content to the file
    }
  }
}
