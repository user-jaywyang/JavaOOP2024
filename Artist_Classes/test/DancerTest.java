package problem1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DancerTest {

  private Dancer dancer;

  @BeforeEach
  public void setUp() throws InvalidAgeException {
    Name name = new Name("Mikhail", "Baryshnikov");
    String[] genres = {"Ballet", "Contemporary"};
    String[] awards = {"Tony Award", "Kennedy Center Honors"};
    String[] movies = {"White Nights"};
    String[] series = {"Sex and the City"};
    String[] otherMultimedia = {"Live Performances"};

    dancer = new Dancer(name, 72, genres, awards, movies, series, otherMultimedia);
  }

  @Test
  public void testDancerCreation() {
    assertEquals("Mikhail", dancer.getName().getFirstName());
    assertEquals(72, dancer.getAge());
    assertArrayEquals(new String[]{"Ballet", "Contemporary"}, dancer.getGenres());
    assertArrayEquals(new String[]{"Tony Award", "Kennedy Center Honors"}, dancer.getAwards());
    assertArrayEquals(new String[]{"White Nights"}, dancer.getMovies());
    assertArrayEquals(new String[]{"Sex and the City"}, dancer.getSeries());
    assertArrayEquals(new String[]{"Live Performances"}, dancer.getOtherMultimedia());
  }

  @Test
  public void testReceiveAward() {
    dancer.receiveAward("Best Dancer");
    assertArrayEquals(new String[]{"Tony Award", "Kennedy Center Honors", "Best Dancer"}, dancer.getAwards());
  }
}
