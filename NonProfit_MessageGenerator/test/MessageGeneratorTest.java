package problem1;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class MessageGeneratorTest {

  @Test
  void testGenerateWithValidPlaceholders() {
    String template = "Dear [[FirstName]] [[LastName]],\n" +
        "Thank you for supporting [[Company]]. We appreciate your contribution.";

    Map<String, String> data = new TreeMap<>();
    data.put("FirstName", "John");
    data.put("LastName", "Doe");
    data.put("Company", "Charity Inc");

    MessageGenerator generator = new MessageGenerator();
    String expected = "Dear John Doe,\n" +
        "Thank you for supporting Charity Inc. We appreciate your contribution.";
    String result = generator.generate(template, data);

    assertEquals(expected, result, "Generated message should match the expected output.");
  }

  @Test
  void testGenerateWithMissingPlaceholders() {
    String template = "Hello [[FirstName]],\n" +
        "We noticed you haven't updated your information at [[Company]].";

    Map<String, String> data = new TreeMap<>();
    data.put("FirstName", "Alice");

    MessageGenerator generator = new MessageGenerator();
    String expected = "Hello Alice,\n" +
        "We noticed you haven't updated your information at [[Company]].";
    String result = generator.generate(template, data);

    assertEquals(expected, result, "Generated message should retain unresolved placeholders.");
  }

  @Test
  void testGenerateWithNoPlaceholders() {
    String template = "Thank you for being a valued supporter.";
    Map<String, String> data = new TreeMap<>();

    MessageGenerator generator = new MessageGenerator();
    String expected = "Thank you for being a valued supporter.";
    String result = generator.generate(template, data);

    assertEquals(expected, result, "Generated message should remain unchanged with no placeholders.");
  }

  @Test
  void testGenerateWithExtraData() {
    String template = "Welcome [[FirstName]] [[LastName]]!";

    Map<String, String> data = new TreeMap<>();
    data.put("FirstName", "Jane");
    data.put("LastName", "Smith");
    data.put("Company", "ExtraData Inc");  // Extra data not used in the template

    MessageGenerator generator = new MessageGenerator();
    String expected = "Welcome Jane Smith!";
    String result = generator.generate(template, data);

    assertEquals(expected, result, "Generated message should ignore extra data.");
  }

  @Test
  void testGenerateWithEmptyTemplate() {
    String template = "";
    Map<String, String> data = new TreeMap<>();
    data.put("FirstName", "Test");

    MessageGenerator generator = new MessageGenerator();
    String expected = "";
    String result = generator.generate(template, data);

    assertEquals(expected, result, "Generated message should be empty when template is empty.");
  }


  @Test
  void testGenerateWithNullValues() {
    MessageGenerator generator = new MessageGenerator();
    String template = "Dear [[FirstName]],\nWe hope to see you soon at [[Address]].";

    Map<String, String> data = new HashMap<>();
    data.put("FirstName", "Chris");
    data.put("Address", null);

    String expected = "Dear Chris,\nWe hope to see you soon at ."; // Expect null to be replaced with an empty string
    String actual = generator.generate(template, data);

    assertEquals(expected, actual, "Generated message should handle null values as an empty string.");
  }
}
