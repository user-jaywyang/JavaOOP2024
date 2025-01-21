package problem1;

/**
 * The Dancer class represents an artist who works in movies, TV series with a focus on dance.
 * It extends the MultimediaArtist class, inheriting attributes such as movies, series.
 *
 * The Dancer class is used to represent an artist who specializes in dance.
 */
public class Dancer extends MultimediaArtist {

  /**
   * Constructs a Dancer object with the specified name, age, genres, awards, movies, series,
   * and other multimedia content.
   *
   * @param name the name of the dancer
   * @param age the age of the dancer
   * @param genres the genres associated with the dancer
   * @param awards the awards received by the dancer
   * @param movies the movies the dancer has worked on
   * @param series the TV series the dancer has worked on
   * @param otherMultimedia the other multimedia content the dancer has worked on
   * @throws InvalidAgeException if the age is not within the valid range
   */
  public Dancer(Name name, int age, String[] genres, String[] awards, String[] movies, String[] series, String[] otherMultimedia) throws InvalidAgeException {
    super(name, age, genres, awards, movies, series, otherMultimedia);
  }

  /**
   * Displays information specific to the dancer.
   * This method prints out the dancer's details to the console.
   */
  @Override
  public void displayInfo() {
    System.out.println("Dancer: " + getName());
    System.out.println("Age: " + getAge());
    System.out.println("Genres: " + String.join(", ", getGenres()));
    System.out.println("Awards: " + String.join(", ", getAwards()));
    System.out.println("Movies: " + String.join(", ", getMovies()));
    System.out.println("Series: " + String.join(", ", getSeries()));
    System.out.println("Other Multimedia: " + String.join(", ", getOtherMultimedia()));
  }
}
