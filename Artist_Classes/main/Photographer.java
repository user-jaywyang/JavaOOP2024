package problem1;

/**
 * The Photographer class represents an artist who does photograph and exhibits their work in galleries.
 * It extends the VisualArtist class, inheriting attributes such as the exhibits where their work has been displayed.
 *
 * The Photographer class is used to represent a visual artist who specializes in photography.
 */
public class Photographer extends VisualArtist {

  /**
   * Constructs a Photographer object with the specified name, age, genres, awards, and exhibits.
   *
   * @param name the name of the photographer
   * @param age the age of the photographer
   * @param genres the genres associated with the photographer
   * @param awards the awards received by the photographer
   * @param exhibits the exhibits where the photographer's work has been displayed
   * @throws InvalidAgeException if the age is not within the valid range
   */
  public Photographer(Name name, int age, String[] genres, String[] awards, String[] exhibits) throws InvalidAgeException {
    super(name, age, genres, awards, exhibits);
  }

  /**
   * Displays information specific to the photographer.
   * This method prints out the photographer's details to the console.
   */
  @Override
  public void displayInfo() {
    System.out.println("Photographer: " + getName());
    System.out.println("Age: " + getAge());
    System.out.println("Genres: " + String.join(", ", getGenres()));
    System.out.println("Awards: " + String.join(", ", getAwards()));
    System.out.println("Exhibits: " + String.join(", ", getExhibits()));
  }
}
