package problem1;

/**
 * The MultimediaArtist class represents an artist who works in multimedia.
 * It extends the Artist class and adds specific attributes related to multimedia.
 *
 * Subclasses of MultimediaArtist should implement the displayInfo() method to provide details about the artist.
 */

public abstract class MultimediaArtist extends Artist {

  /**
   * A list of movies that the artist has worked on.
   */
  private String[] movies;

  /**
   * A list of TV series that the artist has worked on.
   */
  private String[] series;

  /**
   * A list of other multimedia that the artist has worked on.
   */
  private String[] otherMultimedia;

  /**
   * Constructs a MultimediaArtist object with the specified name, age, genres, awards, movies, series,
   * and other multimedia.
   *
   * @param name the name of the artist
   * @param age the age of the artist
   * @param genres the genres associated with the artist
   * @param awards the awards received by the artist
   * @param movies the movies the artist has worked on
   * @param series the TV series the artist has worked on
   * @param otherMultimedia the other multimedia content the artist has worked on
   * @throws InvalidAgeException if the age is not within the valid range
   */
  public MultimediaArtist(Name name, int age, String[] genres, String[] awards, String[] movies, String[] series, String[] otherMultimedia) throws InvalidAgeException {
    super(name, age, genres, awards);
    this.movies = movies;
    this.series = series;
    this.otherMultimedia = otherMultimedia;
  }

  /**
   * Returns the list of movies the artist has worked on.
   *
   * @return the list of movies
   */
  public String[] getMovies() {
    return movies;
  }

  /**
   * Returns the list of TV series the artist has worked on.
   *
   * @return the list of TV series
   */
  public String[] getSeries() {
    return series;
  }

  /**
   * Returns the list of other multimedia content the artist has worked on.
   *
   * @return the list of other multimedia content
   */
  public String[] getOtherMultimedia() {
    return otherMultimedia;
  }

  /**
   * Displays information specific to the multimedia artist.
   * This method must be implemented by subclasses.
   */
  @Override
  public abstract void displayInfo();
}
