package problem1;

/**
 * The Artist class represents a general artist with attributes name, age, genres, and awards.
 * It serves as the base class for all specific types of artists and provides methods common to all artists.
 *
 * Subclasses of Artist should implement the abstract method displayInfo() to display artist-specific information.
 */
public abstract class Artist {

  /**
   * The name of the artist.
   */
  private Name name;

  /**
   * The age of the artist, must be between 0 and 128.
   */
  private int age;

  /**
   * The genres associated with the artist.
   */
  private String[] genres;

  /**
   * The awards received by the artist.
   */
  private String[] awards;

  /**
   * Constructs an Artist object with the specified name, age, genres, and awards.
   *
   * @param name the name of the artist
   * @param age the age of the artist, must be in the range [0, 128]
   * @param genres the genres associated with the artist
   * @param awards the awards received by the artist
   * @throws InvalidAgeException if the age is not within the valid range
   */
  public Artist(Name name, int age, String[] genres, String[] awards) throws InvalidAgeException {
    if (age < 0 || age > 128) {
      throw new InvalidAgeException("Age must be between 0 and 128.");
    }
    this.name = name;
    this.age = age;
    this.genres = genres;
    this.awards = awards;
  }

  /**
   * Returns the name of the artist.
   *
   * @return the name of the artist
   */
  public Name getName() {
    return name;
  }

  /**
   * Returns the age of the artist.
   *
   * @return the age of the artist
   */
  public int getAge() {
    return age;
  }

  /**
   * Returns the genres associated with the artist.
   *
   * @return the genres associated with the artist
   */
  public String[] getGenres() {
    return genres;
  }

  /**
   * Returns the awards received by the artist.
   *
   * @return the awards received by the artist
   */
  public String[] getAwards() {
    return awards;
  }

  /**
   * Adds a new award to the artist's list of awards.
   *
   * @param award the award to be added
   */
  public void receiveAward(String award) {
    // Create a new array with one more slot than the current array
    String[] newAwards = new String[awards.length + 1];

    // Copy old awards into the new array
    System.arraycopy(awards, 0, newAwards, 0, awards.length);

    // Add the new award at the end of the new array
    newAwards[awards.length] = award;

    // Replace the old array with the new array
    this.awards = newAwards;
  }

  /**
   * Displays information specific to the artist.
   * This method must be implemented by subclasses.
   */
  public abstract void displayInfo();
}
