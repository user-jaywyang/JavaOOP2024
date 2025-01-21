package problem2;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class MusicPieceTest {

  @Test
  void testEquals_sameObject() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    MusicPiece piece = new MusicPiece("Test Song", artist, 2020);
    assertTrue(piece.equals(piece)); // Test self-comparison
  }

  @Test
  void testEquals_differentObjectSameValues() {
    RecordingArtist artist1 = new RecordingArtist("John", "Doe");
    MusicPiece piece1 = new MusicPiece("Test Song", artist1, 2020);

    RecordingArtist artist2 = new RecordingArtist("John", "Doe");
    MusicPiece piece2 = new MusicPiece("Test Song", artist2, 2020);

    assertTrue(piece1.equals(piece2)); // Test equal values
  }

  @Test
  void testEquals_nullObject() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    MusicPiece piece = new MusicPiece("Test Song", artist, 2020);
    assertFalse(piece.equals(null)); // Test comparison with null
  }

  @Test
  void testEquals_differentType() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    MusicPiece piece = new MusicPiece("Test Song", artist, 2020);
    assertFalse(piece.equals("lololololol")); // Test comparison with different type
  }

  @Test
  void testEquals_differentValues() {
    RecordingArtist artist1 = new RecordingArtist("John", "Doe");
    MusicPiece piece1 = new MusicPiece("Test Song", artist1, 2020);

    RecordingArtist artist2 = new RecordingArtist("Jane", "Doe");
    MusicPiece piece2 = new MusicPiece("Different Song", artist2, 2020);

    assertFalse(piece1.equals(piece2)); // Test different title and artist
  }

  @Test
  void testHashCode_sameValues() {
    RecordingArtist artist1 = new RecordingArtist("John", "Doe");
    MusicPiece piece1 = new MusicPiece("Test Song", artist1, 2020);

    RecordingArtist artist2 = new RecordingArtist("John", "Doe");
    MusicPiece piece2 = new MusicPiece("Test Song", artist2, 2020);

    assertEquals(piece1.hashCode(), piece2.hashCode()); // Test equal hashCodes
  }

  @Test
  void testHashCode_differentValues() {
    RecordingArtist artist1 = new RecordingArtist("John", "Doe");
    MusicPiece piece1 = new MusicPiece("Test Song", artist1, 2020);

    RecordingArtist artist2 = new RecordingArtist("Jane", "Doe");
    MusicPiece piece2 = new MusicPiece("Different Song", artist2, 2021);

    assertNotEquals(piece1.hashCode(), piece2.hashCode()); // Test different hashCodes
  }

  @Test
  void testToString() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    MusicPiece piece = new MusicPiece("Test Song", artist, 2020);
    String expected = "MusicPiece{title='Test Song', creator=John Doe, yearReleased=2020}";
    assertEquals(expected, piece.toString()); // Test toString format
  }

  @Test
  void testIsCreatedBy_true() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    MusicPiece piece = new MusicPiece("Test Song", artist, 2020);
    assertTrue(piece.isCreatedBy(artist)); // Test isCreatedBy returns true for correct creator
  }

  @Test
  void testIsCreatedBy_false() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    RecordingArtist otherArtist = new RecordingArtist("Jane", "Doe");
    MusicPiece piece = new MusicPiece("Test Song", artist, 2020);
    assertFalse(piece.isCreatedBy(otherArtist)); // Test isCreatedBy returns false for different creator
  }

  @Test
  void testConstructor_recordingArtist() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    MusicPiece piece = new MusicPiece("Test Song", artist, 2020);
    assertEquals(artist, piece.getCreator()); // Test constructor for recording artist
  }

  @Test
  void testConstructor_band() {
    ArrayList<RecordingArtist> members = new ArrayList<>();
    members.add(new RecordingArtist("John", "Doe"));
    Band band = new Band("Test Band", members);
    MusicPiece piece = new MusicPiece("Test Song", band, 2020);
    assertEquals(band, piece.getCreator()); // Test constructor for band
  }

  @Test
  void testConstructor_invalidTitle() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    assertThrows(IllegalArgumentException.class, () -> new MusicPiece(null, artist, 2020)); // Test invalid title
  }

  @Test
  void testConstructor_invalidYearReleased() {
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    assertThrows(IllegalArgumentException.class, () -> new MusicPiece("Test Song", artist, -1)); // Test invalid year
  }
}
