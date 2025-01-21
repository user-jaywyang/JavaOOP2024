package problem1;

/**
 * The Musician class represents an artist who works in the music industry.
 * It extends the Artist class, adding specific attributes such as the musician's
 * recording company and last recorded album.
 *
 * The Musician class is used to represent a musical artist and their association with the music industry.
 */
public class Musician extends Artist {

  /**
   * The recording company the musician is signed with.
   */
  private String recordingCompany;

  /**
   * The title of the last album recorded by the musician.
   */
  private String lastRecordAlbum;

  /**
   * Constructs a Musician object with the specified name, age, genres, awards, recording company,
   * and last recorded album.
   *
   * @param name the name of the musician
   * @param age the age of the musician
   * @param genres the genres associated with the musician
   * @param awards the awards received by the musician
   * @param recordingCompany the recording company the musician is signed with
   * @param lastRecordAlbum the title of the last recorded album
   * @throws InvalidAgeException if the age is not within the valid range
   */
  public Musician(Name name, int age, String[] genres, String[] awards, String recordingCompany, String lastRecordAlbum) throws InvalidAgeException {
    super(name, age, genres, awards);
    this.recordingCompany = recordingCompany;
    this.lastRecordAlbum = lastRecordAlbum;
  }

  /**
   * Returns the recording company the musician is signed with.
   *
   * @return the musician's recording company
   */
  public String getRecordingCompany() {
    return recordingCompany;
  }

  /**
   * Returns the title of the last album recorded by the musician.
   *
   * @return the musician's last recorded album
   */
  public String getLastRecordAlbum() {
    return lastRecordAlbum;
  }

  /**
   * Displays information specific to the musician, including their name, age, genres, awards,
   * recording company, and last recorded album.
   * This method prints out the musician's details to the console.
   */
  @Override
  public void displayInfo() {
    System.out.println("Musician: " + getName());
    System.out.println("Age: " + getAge());
    System.out.println("Genres: " + String.join(", ", getGenres()));
    System.out.println("Awards: " + String.join(", ", getAwards()));
    System.out.println("Recording Company: " + recordingCompany);
    System.out.println("Last Record Album: " + lastRecordAlbum);
  }
}
