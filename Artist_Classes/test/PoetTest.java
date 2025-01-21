package problem1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PoetTest {

  private Poet poet;

  @BeforeEach
  public void setUp() throws InvalidAgeException {
    Name name = new Name("Emily", "Dickinson");
    String[] genres = {"Lyric Poetry"};
    String[] awards = {"Pulitzer Prize"};
    String publishingCompany = "Random House";
    String lastPublishedCollection = "Collected Poems";

    poet = new Poet(name, 55, genres, awards, publishingCompany, lastPublishedCollection);
  }

  @Test
  public void testPoetCreation() {
    assertEquals("Emily", poet.getName().getFirstName());
    assertEquals("Dickinson", poet.getName().getLastName());
    assertEquals(55, poet.getAge());
    assertArrayEquals(new String[]{"Lyric Poetry"}, poet.getGenres());
    assertArrayEquals(new String[]{"Pulitzer Prize"}, poet.getAwards());
    assertEquals("Random House", poet.getPublishingCompany());
    assertEquals("Collected Poems", poet.getLastPublishedCollection());
  }

  @Test
  public void testReceiveAward() {
    poet.receiveAward("Lifetime Achievement");
    assertArrayEquals(new String[]{"Pulitzer Prize", "Lifetime Achievement"}, poet.getAwards());
  }
}
