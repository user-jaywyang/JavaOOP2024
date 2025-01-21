package problem1;

import java.io.File;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CSVParserTest {

  private static final String TEST_FILE_PATH = "src/test/resources/nonprofit-supporters.csv";

  @Test
  void testParseCsvFile() throws IOException {
    CSVParser parser = new CSVParser();
    List<Map<String, String>> rows = parser.parse(TEST_FILE_PATH);

    assertEquals(500, rows.size(), "Expected 500 rows in the CSV file");

    // Validate first supporter
    Map<String, String> firstSupporter = rows.get(0);
    assertEquals("James", firstSupporter.get("first_name"));
    assertEquals("Butt", firstSupporter.get("last_name"));
    assertEquals("Benton, John B Jr", firstSupporter.get("company_name"));
    assertEquals("6649 N Blue Gum St", firstSupporter.get("address"));
    assertEquals("New Orleans", firstSupporter.get("city"));
    assertEquals("Orleans", firstSupporter.get("county"));
    assertEquals("LA", firstSupporter.get("state"));
    assertEquals("70116", firstSupporter.get("zip"));
    assertEquals("504-621-8927", firstSupporter.get("phone1"));
    assertEquals("504-845-1427", firstSupporter.get("phone2"));
    assertEquals("jbutt@gmail.com", firstSupporter.get("email"));
    assertEquals("http://www.bentonjohnbjr.com", firstSupporter.get("web"));

    // Validate second supporter
    Map<String, String> secondSupporter = rows.get(1);
    assertEquals("Josephine", secondSupporter.get("first_name"));
    assertEquals("Darakjy", secondSupporter.get("last_name"));
    assertEquals("Chanay, Jeffrey A Esq", secondSupporter.get("company_name"));
    assertEquals("4 B Blue Ridge Blvd", secondSupporter.get("address"));
    assertEquals("Brighton", secondSupporter.get("city"));
    assertEquals("Livingston", secondSupporter.get("county"));
    assertEquals("MI", secondSupporter.get("state"));
    assertEquals("48116", secondSupporter.get("zip"));
    assertEquals("810-292-9388", secondSupporter.get("phone1"));
    assertEquals("810-374-9840", secondSupporter.get("phone2"));
    assertEquals("josephine_darakjy@darakjy.org", secondSupporter.get("email"));
    assertEquals("http://www.chanayjeffreyaesq.com", secondSupporter.get("web"));
  }


  @Test
  void testParseEmptyFile() {
    CSVParser parser = new CSVParser();
    String emptyFilePath = "src/test/resources/empty.csv";  // Ensure this file exists
    Exception exception = assertThrows(IOException.class, () -> parser.parse(emptyFilePath));
    assertEquals("CSV file is empty or has no header.", exception.getMessage());
  }
}
