package problem1;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class SupporterTest {

  @Test
  void testGetAttribute() {
    Map<String, String> attributes = new TreeMap<>();
    attributes.put("FirstName", "Mary");
    attributes.put("LastName", "Smith");
    attributes.put("Company", "Helping Hands");
    attributes.put("Address", "456 Charity St");
    attributes.put("City", "Seattle");
    attributes.put("State", "WA");
    attributes.put("Zip", "98101");
    attributes.put("Phone1", "206-555-1234");
    attributes.put("Phone2", "206-555-5678");
    attributes.put("Email", "mary.smith@helpinghands.org");
    attributes.put("Web", "www.helpinghands.org");
    attributes.put("County", "King");

    Supporter supporter = new Supporter(attributes);

    assertEquals("Mary", supporter.getAttribute("FirstName"));
    assertEquals("Smith", supporter.getAttribute("LastName"));
    assertEquals("Helping Hands", supporter.getAttribute("Company"));
    assertEquals("456 Charity St", supporter.getAttribute("Address"));
    assertEquals("Seattle", supporter.getAttribute("City"));
    assertEquals("WA", supporter.getAttribute("State"));
    assertEquals("98101", supporter.getAttribute("Zip"));
    assertEquals("206-555-1234", supporter.getAttribute("Phone1"));
    assertEquals("206-555-5678", supporter.getAttribute("Phone2"));
    assertEquals("mary.smith@helpinghands.org", supporter.getAttribute("Email"));
    assertEquals("www.helpinghands.org", supporter.getAttribute("Web"));
    assertEquals("King", supporter.getAttribute("County"));
    assertNull(supporter.getAttribute("NonExistentAttribute"));
  }

  @Test
  void testGetAttributes() {
    Map<String, String> attributes = new TreeMap<>();
    attributes.put("FirstName", "John");
    attributes.put("LastName", "Doe");
    attributes.put("Company", "Charity Organization");
    attributes.put("Address", "123 Giving Blvd");
    attributes.put("City", "Portland");
    attributes.put("State", "OR");
    attributes.put("Zip", "97201");
    attributes.put("Phone1", "503-555-6789");
    attributes.put("Phone2", "503-555-9876");
    attributes.put("Email", "john.doe@charity.org");
    attributes.put("Web", "www.charity.org");
    attributes.put("County", "Multnomah");

    Supporter supporter = new Supporter(attributes);

    assertEquals(attributes, supporter.getAttributes());
  }

  @Test
  void testToString() {
    Map<String, String> attributes = new TreeMap<>();
    attributes.put("FirstName", "Jane");
    attributes.put("LastName", "Doe");
    attributes.put("Company", "Support Co");
    attributes.put("Address", "789 Volunteer Way");
    attributes.put("City", "Los Angeles");
    attributes.put("State", "CA");
    attributes.put("Zip", "90001");
    attributes.put("Phone1", "213-555-4321");
    attributes.put("Phone2", "213-555-8765");
    attributes.put("Email", "jane.doe@supportco.com");
    attributes.put("Web", "www.supportco.com");
    attributes.put("County", "Los Angeles");

    Supporter supporter = new Supporter(attributes);

    String expected = "Supporter{attributes=" +
        "{Address=789 Volunteer Way, City=Los Angeles, Company=Support Co, County=Los Angeles, " +
        "Email=jane.doe@supportco.com, FirstName=Jane, LastName=Doe, Phone1=213-555-4321, " +
        "Phone2=213-555-8765, State=CA, Web=www.supportco.com, Zip=90001}}";

    assertEquals(expected, supporter.toString());
  }

  @Test
  void testEqualsAndHashCode() {
    Map<String, String> attributes1 = new TreeMap<>();
    attributes1.put("FirstName", "Alice");
    attributes1.put("LastName", "Johnson");
    attributes1.put("Company", "Kindness Corp");
    attributes1.put("Address", "321 Caring Ln");
    attributes1.put("City", "San Francisco");
    attributes1.put("State", "CA");
    attributes1.put("Zip", "94101");
    attributes1.put("Phone1", "415-555-1122");
    attributes1.put("Phone2", "415-555-2211");
    attributes1.put("Email", "alice.johnson@kindnesscorp.com");
    attributes1.put("Web", "www.kindnesscorp.com");
    attributes1.put("County", "San Francisco");

    Map<String, String> attributes2 = new TreeMap<>(attributes1);

    Map<String, String> attributes3 = new TreeMap<>();
    attributes3.put("FirstName", "Bob");
    attributes3.put("LastName", "Smith");
    attributes3.put("Company", "Charity Inc");
    attributes3.put("Address", "123 Giving Rd");
    attributes3.put("City", "Austin");
    attributes3.put("State", "TX");
    attributes3.put("Zip", "73301");
    attributes3.put("Phone1", "512-555-1234");
    attributes3.put("Phone2", "512-555-4321");
    attributes3.put("Email", "bob.smith@charityinc.com");
    attributes3.put("Web", "www.charityinc.com");
    attributes3.put("County", "Travis");

    Supporter supporter1 = new Supporter(attributes1);
    Supporter supporter2 = new Supporter(attributes2);
    Supporter supporter3 = new Supporter(attributes3);

    assertEquals(supporter1, supporter2);
    assertEquals(supporter1.hashCode(), supporter2.hashCode());
    assertNotEquals(supporter1, supporter3);
    assertNotEquals(supporter1.hashCode(), supporter3.hashCode());
  }
}
