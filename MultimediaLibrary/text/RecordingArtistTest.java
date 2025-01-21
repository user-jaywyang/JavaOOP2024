package problem2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecordingArtistTest {

  @Test
  void testEquals_sameObject() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    assertTrue(artist.equals(artist)); // Test self-comparison
  }

  @Test
  void testEquals_differentObjectSameValues() {
    RecordingArtist artist1 = new RecordingArtist("John", "Doe");
    RecordingArtist artist2 = new RecordingArtist("John", "Doe");
    assertTrue(artist1.equals(artist2)); // Test equal values
  }

  @Test
  void testEquals_nullObject() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    assertFalse(artist.equals(null)); // Test comparison with null
  }

  @Test
  void testEquals_differentType() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    assertFalse(artist.equals("lolololololo")); // Test comparison with different type
  }

  @Test
  void testEquals_differentValues() {
    RecordingArtist artist1 = new RecordingArtist("John", "Doe");
    RecordingArtist artist2 = new RecordingArtist("Jane", "Doe");
    assertFalse(artist1.equals(artist2)); // Test different first name
  }

  @Test
  void testHashCode_sameValues() {
    RecordingArtist artist1 = new RecordingArtist("John", "Doe");
    RecordingArtist artist2 = new RecordingArtist("John", "Doe");
    assertEquals(artist1.hashCode(), artist2.hashCode()); // Test equal hashCodes
  }

  @Test
  void testHashCode_differentValues() {
    RecordingArtist artist1 = new RecordingArtist("John", "Doe");
    RecordingArtist artist2 = new RecordingArtist("Jane", "Doe");
    assertNotEquals(artist1.hashCode(), artist2.hashCode()); // Test different hashCodes
  }

  @Test
  void testToString() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    String expected = "RecordingArtist{firstName='John', lastName='Doe'}";
    assertEquals(expected, artist.toString()); // Test toString format
  }

  @Test
  void testGetFirstName() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    assertEquals("John", artist.getFirstName()); // Test firstName getter
  }

  @Test
  void testGetLastName() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    assertEquals("Doe", artist.getLastName()); // Test lastName getter
  }

  @Test
  void testConstructor_invalidFirstName() {
    assertThrows(IllegalArgumentException.class, () -> new RecordingArtist(null, "Doe")); // Test invalid firstName
  }

  @Test
  void testConstructor_invalidLastName() {
    assertThrows(IllegalArgumentException.class, () -> new RecordingArtist("John", null)); // Test invalid lastName
  }

  @Test
  void testConstructor_emptyFirstName() {
    assertThrows(IllegalArgumentException.class, () -> new RecordingArtist("", "Doe")); // Test empty firstName
  }

  @Test
  void testConstructor_emptyLastName() {
    assertThrows(IllegalArgumentException.class, () -> new RecordingArtist("John", "")); // Test empty lastName
  }
}
