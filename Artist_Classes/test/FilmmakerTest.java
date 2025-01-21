package problem1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilmmakerTest {

  private Filmmaker filmmaker;

  @BeforeEach
  public void setUp() throws InvalidAgeException {
    Name name = new Name("Steven", "Spielberg");
    String[] genres = {"Action", "Adventure"};
    String[] awards = {"Academy Award", "Golden Globe"};
    String[] movies = {"Jurassic Park", "E.T."};
    String[] series = {"Band of Brothers"};
    String[] otherMultimedia = {"Documentaries"};

    filmmaker = new Filmmaker(name, 75, genres, awards, movies, series, otherMultimedia);
  }

  @Test
  public void testFilmmakerCreation() {
    assertEquals("Steven", filmmaker.getName().getFirstName());
    assertEquals(75, filmmaker.getAge());
    assertArrayEquals(new String[]{"Action", "Adventure"}, filmmaker.getGenres());
    assertArrayEquals(new String[]{"Academy Award", "Golden Globe"}, filmmaker.getAwards());
    assertArrayEquals(new String[]{"Jurassic Park", "E.T."}, filmmaker.getMovies());
    assertArrayEquals(new String[]{"Band of Brothers"}, filmmaker.getSeries());
    assertArrayEquals(new String[]{"Documentaries"}, filmmaker.getOtherMultimedia());
  }

  @Test
  public void testReceiveAward() {
    filmmaker.receiveAward("Best Director");
    assertArrayEquals(new String[]{"Academy Award", "Golden Globe", "Best Director"}, filmmaker.getAwards());
  }
}
