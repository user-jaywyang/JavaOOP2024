package problem2;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BandTest {

  @Test
  void testEquals_sameObject() {
    List<RecordingArtist> members = new ArrayList<>();
    members.add(new RecordingArtist("John", "Doe"));
    Band band = new Band("The Test Band", members);
    assertTrue(band.equals(band)); // Test self-comparison
  }

  @Test
  void testEquals_differentObjectSameValues() {
    List<RecordingArtist> members1 = new ArrayList<>();
    members1.add(new RecordingArtist("John", "Doe"));
    Band band1 = new Band("The Test Band", members1);

    List<RecordingArtist> members2 = new ArrayList<>();
    members2.add(new RecordingArtist("John", "Doe"));
    Band band2 = new Band("The Test Band", members2);

    assertTrue(band1.equals(band2)); // Test equal values
  }

  @Test
  void testEquals_nullObject() {
    List<RecordingArtist> members = new ArrayList<>();
    members.add(new RecordingArtist("John", "Doe"));
    Band band = new Band("The Test Band", members);
    assertFalse(band.equals(null)); // Test comparison with null
  }

  @Test
  void testEquals_differentType() {
    List<RecordingArtist> members = new ArrayList<>();
    members.add(new RecordingArtist("John", "Doe"));
    Band band = new Band("The Test Band", members);
    assertFalse(band.equals("some string")); // Test comparison with different type
  }

  @Test
  void testEquals_differentValues() {
    List<RecordingArtist> members1 = new ArrayList<>();
    members1.add(new RecordingArtist("John", "Doe"));
    Band band1 = new Band("The Test Band", members1);

    List<RecordingArtist> members2 = new ArrayList<>();
    members2.add(new RecordingArtist("Jane", "Doe"));
    Band band2 = new Band("The Test Band", members2);

    assertFalse(band1.equals(band2)); // Test different members
  }

  @Test
  void testHashCode_sameValues() {
    List<RecordingArtist> members1 = new ArrayList<>();
    members1.add(new RecordingArtist("John", "Doe"));
    Band band1 = new Band("The Test Band", members1);

    List<RecordingArtist> members2 = new ArrayList<>();
    members2.add(new RecordingArtist("John", "Doe"));
    Band band2 = new Band("The Test Band", members2);

    assertEquals(band1.hashCode(), band2.hashCode()); // Test equal hashCodes
  }

  @Test
  void testHashCode_differentValues() {
    List<RecordingArtist> members1 = new ArrayList<>();
    members1.add(new RecordingArtist("John", "Doe"));
    Band band1 = new Band("The Test Band", members1);

    List<RecordingArtist> members2 = new ArrayList<>();
    members2.add(new RecordingArtist("Jane", "Doe"));
    Band band2 = new Band("The Test Band", members2);

    assertNotEquals(band1.hashCode(), band2.hashCode()); // Test different hashCodes
  }

  @Test
  void testToString() {
    List<RecordingArtist> members = new ArrayList<>();
    members.add(new RecordingArtist("John", "Doe"));
    Band band = new Band("The Test Band", members);
    String expected = "Band{name='The Test Band', members=[" +
        "RecordingArtist{firstName='John', lastName='Doe'}]}";
    assertEquals(expected, band.toString()); // Test toString format
  }

  @Test
  void testGetBandName() {
    List<RecordingArtist> members = new ArrayList<>();
    members.add(new RecordingArtist("John", "Doe"));
    Band band = new Band("The Test Band", members);
    assertEquals("The Test Band", band.getBandName()); // Test getBandName
  }

  @Test
  void testGetMembers() {
    List<RecordingArtist> members = new ArrayList<>();
    RecordingArtist member = new RecordingArtist("John", "Doe");
    members.add(member);
    Band band = new Band("The Test Band", members);
    assertEquals(members, band.getMembers()); // Test getMembers
  }

  @Test
  void testHasMember_true() {
    List<RecordingArtist> members = new ArrayList<>();
    RecordingArtist member = new RecordingArtist("John", "Doe");
    members.add(member);
    Band band = new Band("The Test Band", members);
    assertTrue(band.hasMember(member)); // Test hasMember returns true
  }

  @Test
  void testHasMember_false() {
    List<RecordingArtist> members = new ArrayList<>();
    members.add(new RecordingArtist("John", "Doe"));
    Band band = new Band("The Test Band", members);
    RecordingArtist other = new RecordingArtist("Jane", "Doe");
    assertFalse(band.hasMember(other)); // Test hasMember returns false
  }

  @Test
  void testConstructor_invalidBandName() {
    List<RecordingArtist> members = new ArrayList<>();
    members.add(new RecordingArtist("John", "Doe"));
    assertThrows(IllegalArgumentException.class, () -> new Band(null, members)); // Test invalid band name
  }

  @Test
  void testConstructor_invalidMembers() {
    assertThrows(IllegalArgumentException.class, () -> new Band("The Test Band", null)); // Test null members
  }

  @Test
  void testConstructor_emptyMembers() {
    assertThrows(IllegalArgumentException.class, () -> new Band("The Test Band", new ArrayList<>())); // Test empty members
  }
}
