package problem2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a catalog that stores a collection of items, including books and music pieces.
 * The catalog supports adding, removing, and searching for items based on various criteria.
 */
public class Catalog {

  /**
   * The list of items in the catalog.
   */
  private List<Item> items;

  /**
   * Constructs a new Catalog with the specified list of items.
   * A defensive copy of the provided list is made to avoid external modification.
   *
   * @param items the list of items to initialize the catalog with.
   * @throws IllegalArgumentException if the list of items is null.
   */
  public Catalog(List<Item> items) {
    if (items == null) {
      throw new IllegalArgumentException("Items list cannot be null.");
    }
    this.items = new ArrayList<>(items);  // Defensive copy
  }

  /**
   * Constructs an empty Catalog.
   * The catalog is initialized with an empty list of items.
   */
  public Catalog() {
    this.items = new ArrayList<>();
  }

  /**
   * Adds an item to the catalog.
   *
   * @param item the item to be added.
   * @throws IllegalArgumentException if the item is null.
   */
  public void addItem(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null.");
    }
    items.add(item);
  }

  /**
   * Removes an item from the catalog.
   *
   * @param item the item to be removed.
   * @throws IllegalArgumentException if the item is null.
   */
  public void removeItem(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null.");
    }
    items.remove(item);
  }

  /**
   * Searches for items in the catalog whose titles contain the specified keyword.
   * The search is case-insensitive.
   *
   * @param keyword the keyword to search for in item titles.
   * @return a list of items with titles containing the keyword.
   * @throws IllegalArgumentException if the keyword is null or empty.
   */
  public List<Item> search(String keyword) {
    if (keyword == null || keyword.isEmpty()) {
      throw new IllegalArgumentException("Keyword cannot be null or empty.");
    }
    return items.stream()
        .filter(item -> item.getTitle().toLowerCase().contains(keyword.toLowerCase()))
        .collect(Collectors.toList());
  }

  /**
   * Searches for books in the catalog by the specified author.
   *
   * @param author the author to search for.
   * @return a list of books by the specified author.
   * @throws IllegalArgumentException if the author is null.
   */
  public List<Book> search(Author author) {
    if (author == null) {
      throw new IllegalArgumentException("Author cannot be null.");
    }
    return items.stream()
        .filter(item -> item instanceof Book && ((Book) item).getAuthor().equals(author))
        .map(item -> (Book) item)
        .collect(Collectors.toList());
  }

  /**
   * Searches for music pieces in the catalog by a specific recording artist.
   *
   * @param creator the recording artist to search for.
   * @return a list of music pieces by the specified recording artist.
   * @throws IllegalArgumentException if the recording artist is null.
   */
  public List<MusicPiece> search(RecordingArtist creator) {
    if (creator == null) {
      throw new IllegalArgumentException("RecordingArtist cannot be null.");
    }
    return items.stream()
        .filter(item -> item instanceof MusicPiece && ((MusicPiece) item).getCreator().equals(creator))
        .map(item -> (MusicPiece) item)
        .collect(Collectors.toList());
  }

  /**
   * Searches for music pieces in the catalog by a specific band.
   *
   * @param band the band to search for.
   * @return a list of music pieces by the specified band.
   * @throws IllegalArgumentException if the band is null.
   */
  public List<MusicPiece> search(Band band) {
    if (band == null) {
      throw new IllegalArgumentException("Band cannot be null.");
    }
    return items.stream()
        .filter(item -> item instanceof MusicPiece && ((MusicPiece) item).getCreator().equals(band))
        .map(item -> (MusicPiece) item)
        .collect(Collectors.toList());
  }

  /**
   * Checks if the catalog contains the specified item.
   *
   * @param item the item to check for.
   * @return true if the catalog contains the item, false otherwise.
   * @throws IllegalArgumentException if the item is null.
   */
  public boolean contains(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null.");
    }
    return items.contains(item);
  }

  /**
   * Compares this Catalog to the specified object. The result is true if and only if the
   * argument is not null and is a Catalog object that has the same list of items as this Catalog.
   *
   * @param o the object to compare this Catalog against.
   * @return true if the given object represents a Catalog equivalent to this Catalog, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Catalog catalog = (Catalog) o;
    return Objects.equals(this.items, catalog.items);
  }

  /**
   * Returns a hash code value for the Catalog.
   * This method is supported for the benefit of hash tables.
   *
   * @return the hash code value for this Catalog.
   */
  @Override
  public int hashCode() {
    return Objects.hash(items);
  }

  /**
   * Returns a string representation of the Catalog.
   * The string includes the list of items in the catalog.
   *
   * @return a string representation of the Catalog.
   */
  @Override
  public String toString() {
    return "Catalog{items=" + items + "}";
  }
}
