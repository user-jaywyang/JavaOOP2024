package problem1;

/**
 * The Poet class represents an artist who writes poetry and has their work published.
 * It extends the Artist class, adding specific attributes such as the publishing company and the title of the last published collection.
 *
 * The Poet class is used to represent a literary artist who specializes in poetry.
 */
public class Poet extends Artist {

  /**
   * The publishing company that publishes the poet's work.
   */
  private String publishingCompany;

  /**
   * The title of the last collection of poems published by the poet.
   */
  private String lastPublishedCollection;

  /**
   * Constructs a Poet object with the specified name, age, genres, awards, publishing company,
   * and the last published collection of poems.
   *
   * @param name the name of the poet
   * @param age the age of the poet
   * @param genres the genres associated with the poet
   * @param awards the awards received by the poet
   * @param publishingCompany the publishing company that publishes the poet's work
   * @param lastPublishedCollection the title of the last published collection of poems
   * @throws InvalidAgeException if the age is not within the valid range
   */
  public Poet(Name name, int age, String[] genres, String[] awards, String publishingCompany, String lastPublishedCollection) throws InvalidAgeException {
    super(name, age, genres, awards);
    this.publishingCompany = publishingCompany;
    this.lastPublishedCollection = lastPublishedCollection;
  }

  /**
   * Returns the publishing company that publishes the poet's work.
   *
   * @return the poet's publishing company
   */
  public String getPublishingCompany() {
    return publishingCompany;
  }

  /**
   * Returns the title of the last published collection of poems by the poet.
   *
   * @return the title of the last published collection
   */
  public String getLastPublishedCollection() {
    return lastPublishedCollection;
  }

  /**
   * Displays information specific to the poet.
   * This method prints out the poet's details to the console.
   */
  @Override
  public void displayInfo() {
    System.out.println("Poet: " + getName());
    System.out.println("Age: " + getAge());
    System.out.println("Genres: " + String.join(", ", getGenres()));
    System.out.println("Awards: " + String.join(", ", getAwards()));
    System.out.println("Publishing Company: " + publishingCompany);
    System.out.println("Last Published Collection: " + lastPublishedCollection);
  }
}
