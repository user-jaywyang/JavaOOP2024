package problem2;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {

  @Test
  void testEquals_sameObject() {
    Catalog catalog = new Catalog();
    assertTrue(catalog.equals(catalog)); // Test self-comparison
  }

  @Test
  void testEquals_differentObjectSameValues() {
    List<Item> items1 = new ArrayList<>();
    items1.add(new Book("Test Book", new Author("John", "Doe"), 2020));
    Catalog catalog1 = new Catalog(items1);

    List<Item> items2 = new ArrayList<>();
    items2.add(new Book("Test Book", new Author("John", "Doe"), 2020));
    Catalog catalog2 = new Catalog(items2);

    assertTrue(catalog1.equals(catalog2)); // Test equal values
  }

  @Test
  void testEquals_nullObject() {
    Catalog catalog = new Catalog();
    assertFalse(catalog.equals(null)); // Test comparison with null
  }

  @Test
  void testEquals_differentType() {
    Catalog catalog = new Catalog();
    assertFalse(catalog.equals("some string")); // Test comparison with different type
  }

  @Test
  void testEquals_differentValues() {
    List<Item> items1 = new ArrayList<>();
    items1.add(new Book("Test Book", new Author("John", "Doe"), 2020));
    Catalog catalog1 = new Catalog(items1);

    List<Item> items2 = new ArrayList<>();
    items2.add(new Book("Different Book", new Author("Jane", "Doe"), 2021));
    Catalog catalog2 = new Catalog(items2);

    assertFalse(catalog1.equals(catalog2)); // Test different items
  }

  @Test
  void testHashCode_sameValues() {
    List<Item> items1 = new ArrayList<>();
    items1.add(new Book("Test Book", new Author("John", "Doe"), 2020));
    Catalog catalog1 = new Catalog(items1);

    List<Item> items2 = new ArrayList<>();
    items2.add(new Book("Test Book", new Author("John", "Doe"), 2020));
    Catalog catalog2 = new Catalog(items2);

    assertEquals(catalog1.hashCode(), catalog2.hashCode()); // Test equal hashCodes
  }

  @Test
  void testHashCode_differentValues() {
    List<Item> items1 = new ArrayList<>();
    items1.add(new Book("Test Book", new Author("John", "Doe"), 2020));
    Catalog catalog1 = new Catalog(items1);

    List<Item> items2 = new ArrayList<>();
    items2.add(new Book("Different Book", new Author("Jane", "Doe"), 2021));
    Catalog catalog2 = new Catalog(items2);

    assertNotEquals(catalog1.hashCode(), catalog2.hashCode()); // Test different hashCodes
  }

  @Test
  void testToString() {
    List<Item> items = new ArrayList<>();
    items.add(new Book("Test Book", new Author("John", "Doe"), 2020));
    Catalog catalog = new Catalog(items);
    String expected = "Catalog{items=[" + items.get(0).toString() + "]}";
    assertEquals(expected, catalog.toString()); // Test toString format
  }

  @Test
  void testAddItem() {
    Catalog catalog = new Catalog();
    Book book = new Book("Test Book", new Author("John", "Doe"), 2020);
    catalog.addItem(book);
    assertTrue(catalog.contains(book)); // Test item addition
  }

  @Test
  void testRemoveItem() {
    Catalog catalog = new Catalog();
    Book book = new Book("Test Book", new Author("John", "Doe"), 2020);
    catalog.addItem(book);
    catalog.removeItem(book);
    assertFalse(catalog.contains(book)); // Test item removal
  }

  @Test
  void testSearchByTitle() {
    Catalog catalog = new Catalog();
    Book book = new Book("Test Book", new Author("John", "Doe"), 2020);
    catalog.addItem(book);
    List<Item> result = catalog.search("Test");
    assertTrue(result.contains(book)); // Test search by title
  }

  @Test
  void testSearchByAuthor() {
    Catalog catalog = new Catalog();
    Author author = new Author("John", "Doe");
    Book book = new Book("Test Book", author, 2020);
    catalog.addItem(book);
    List<Book> result = catalog.search(author);
    assertTrue(result.contains(book)); // Test search by author
  }

  @Test
  void testSearchByRecordingArtist() {
    Catalog catalog = new Catalog();
    RecordingArtist artist = new RecordingArtist("John", "Doe");
    MusicPiece musicPiece = new MusicPiece("Test Song", artist, 2020);
    catalog.addItem(musicPiece);
    List<MusicPiece> result = catalog.search(artist);
    assertTrue(result.contains(musicPiece)); // Test search by recording artist
  }

  @Test
  void testSearchByBand() {
    Catalog catalog = new Catalog();
    List<RecordingArtist> members = new ArrayList<>();
    members.add(new RecordingArtist("John", "Doe"));
    Band band = new Band("The Test Band", members);
    MusicPiece musicPiece = new MusicPiece("Test Song", band, 2020);
    catalog.addItem(musicPiece);
    List<MusicPiece> result = catalog.search(band);
    assertTrue(result.contains(musicPiece)); // Test search by band
  }

  @Test
  void testContains() {
    Catalog catalog = new Catalog();
    Book book = new Book("Test Book", new Author("John", "Doe"), 2020);
    catalog.addItem(book);
    assertTrue(catalog.contains(book)); // Test catalog contains item
  }

  @Test
  void testConstructor_invalidItems() {
    assertThrows(IllegalArgumentException.class, () -> new Catalog(null)); // Test invalid items list
  }

  @Test
  void testAddItem_nullItem() {
    Catalog catalog = new Catalog();
    assertThrows(IllegalArgumentException.class, () -> catalog.addItem(null)); // Test adding null item
  }

  @Test
  void testRemoveItem_nullItem() {
    Catalog catalog = new Catalog();
    assertThrows(IllegalArgumentException.class, () -> catalog.removeItem(null)); // Test removing null item
  }

  @Test
  void testSearchByTitle_invalidKeyword() {
    Catalog catalog = new Catalog();
    assertThrows(IllegalArgumentException.class, () -> catalog.search("")); // Test invalid keyword
  }
}
