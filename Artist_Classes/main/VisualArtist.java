package problem1;

/**
 * The VisualArtist class represents an artist whose work is displayed: painters and photographers.
 * It extends the Artist class and adds attributes related to exhibits where the artist has been shown.
 *
 * Subclasses of VisualArtist should implement the displayInfo() method to provide specific details about the artist.
 */
public abstract class VisualArtist extends Artist {

  /**
   * A list of exhibits where the artist's work has been displayed.
   */
  private String[] exhibits;

  /**
   * Constructs a VisualArtist object with the specified name, age, genres, awards, and exhibits.
   *
   * @param name the name of the artist
   * @param age the age of the artist
   * @param genres the genres associated with the artist
   * @param awards the awards received by the artist
   * @param exhibits the exhibits where the artist's work has been displayed
   * @throws InvalidAgeException if the age is not within the valid range
   */
  public VisualArtist(Name name, int age, String[] genres, String[] awards, String[] exhibits) throws InvalidAgeException {
    super(name, age, genres, awards);
    this.exhibits = exhibits;
  }

  /**
   * Returns the list of exhibits where the artist's work has been displayed.
   *
   * @return the list of exhibits
   */
  public String[] getExhibits() {
    return exhibits;
  }

  /**
   * Displays information specific to the visual artist.
   * This method must be implemented by subclasses to provide details about the artist.
   */
  @Override
  public abstract void displayInfo();
}
