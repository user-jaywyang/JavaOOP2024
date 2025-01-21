package problem1;

/**
 * The Actor class represents an artist who works in movies, TV series.
 * It extends the MultimediaArtist class, inheriting attributes such as movies, series.
 *
 * The Actor class is used to represent a specific type of artist who acts in movies and series.
 */
public class Actor extends MultimediaArtist {

  /**
   * Constructs an Actor object with the specified name, age, genres, awards, movies, series,
   * and other multimedia content.
   *
   * @param name the name of the actor
   * @param age the age of the actor
   * @param genres the genres associated with the actor
   * @param awards the awards received by the actor
   * @param movies the movies the actor has worked on
   * @param series the TV series the actor has worked on
   * @param otherMultimedia the other multimedia content the actor has worked on
   * @throws InvalidAgeException if the age is not within the valid range
   */
  public Actor(Name name, int age, String[] genres, String[] awards, String[] movies, String[] series, String[] otherMultimedia) throws InvalidAgeException {
    super(name, age, genres, awards, movies, series, otherMultimedia);
  }

  /**
   * Displays information specific to the actor.
   * This method prints out the actor's details to the console.
   */
  @Override
  public void displayInfo() {
    System.out.println("Actor Name: " + getName());
    System.out.println("Age: " + getAge());
    System.out.println("Genres: " + String.join(", ", getGenres()));
    System.out.println("Awards: " + String.join(", ", getAwards()));
    System.out.println("Movies: " + String.join(", ", getMovies()));
    System.out.println("TV Series: " + String.join(", ", getSeries()));
    System.out.println("Other Multimedia: " + String.join(", ", getOtherMultimedia()));
  }
}
