package problem1;

/**
 * The Filmmaker class represents an artist who works in movies, TV series with a focus on directing.
 * It extends the MultimediaArtist class, inheriting attributes.
 *
 * The Filmmaker class is used to represent a specific type of multimedia artist who specializes in film production.
 */
public class Filmmaker extends MultimediaArtist {

  /**
   * Constructs a Filmmaker object with the specified name, age, genres, awards, movies, series,
   * and other multimedia content.
   *
   * @param name the name of the filmmaker
   * @param age the age of the filmmaker
   * @param genres the genres associated with the filmmaker
   * @param awards the awards received by the filmmaker
   * @param movies the movies the filmmaker has worked on
   * @param series the TV series the filmmaker has worked on
   * @param otherMultimedia the other multimedia content the filmmaker has worked on
   * @throws InvalidAgeException if the age is not within the valid range
   */
  public Filmmaker(Name name, int age, String[] genres, String[] awards, String[] movies, String[] series, String[] otherMultimedia) throws InvalidAgeException {
    super(name, age, genres, awards, movies, series, otherMultimedia);
  }

  /**
   * Displays information specific to the filmmaker.
   * This method prints out the filmmaker's details to the console.
   */
  @Override
  public void displayInfo() {
    System.out.println("Filmmaker: " + getName());
    System.out.println("Age: " + getAge());
    System.out.println("Genres: " + String.join(", ", getGenres()));
    System.out.println("Awards: " + String.join(", ", getAwards()));
    System.out.println("Movies: " + String.join(", ", getMovies()));
    System.out.println("Series: " + String.join(", ", getSeries()));
    System.out.println("Other Multimedia: " + String.join(", ", getOtherMultimedia()));
  }
}
