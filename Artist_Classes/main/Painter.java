package problem1;

/**
 * The Painter class represents an artist who creates visual art and exhibits their work in various galleries and shows.
 * It extends the VisualArtist class, inheriting attributes.
 *
 * The Painter class is used to represent a visual artist who specializes in painting.
 */
public class Painter extends VisualArtist {

  /**
   * Constructs a Painter object with the specified name, age, genres, awards, and exhibits.
   *
   * @param name the name of the painter
   * @param age the age of the painter
   * @param genres the genres associated with the painter
   * @param awards the awards received by the painter
   * @param exhibits the exhibits where the painter's work has been displayed
   * @throws InvalidAgeException if the age is not within the valid range
   */
  public Painter(Name name, int age, String[] genres, String[] awards, String[] exhibits) throws InvalidAgeException {
    super(name, age, genres, awards, exhibits);
  }

  /**
   * Displays information specific to the painter.
   * This method prints out the painter's details to the console.
   */
  @Override
  public void displayInfo() {
    System.out.println("Painter Name: " + getName());
    System.out.println("Age: " + getAge());
    System.out.println("Genres: " + String.join(", ", getGenres()));
    System.out.println("Awards: " + String.join(", ", getAwards()));
    System.out.println("Exhibits: " + String.join(", ", getExhibits()));
  }
}
