package problem1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhotographerTest {

  private Photographer photographer;

  @BeforeEach
  public void setUp() throws InvalidAgeException {
    Name name = new Name("Ansel", "Adams");
    String[] genres = {"Landscape", "Black and White"};
    String[] awards = {"Pulitzer Prize", "National Medal of Arts"};
    String[] exhibits = {"New York Exhibition", "Paris Gallery"};

    photographer = new Photographer(name, 75, genres, awards, exhibits);
  }

  @Test
  public void testPhotographerCreation() {
    assertEquals("Ansel", photographer.getName().getFirstName());
    assertEquals(75, photographer.getAge());
    assertArrayEquals(new String[]{"Landscape", "Black and White"}, photographer.getGenres());
    assertArrayEquals(new String[]{"Pulitzer Prize", "National Medal of Arts"}, photographer.getAwards());
    assertArrayEquals(new String[]{"New York Exhibition", "Paris Gallery"}, photographer.getExhibits());
  }

  @Test
  public void testReceiveAward() {
    photographer.receiveAward("Lifetime Achievement");
    assertArrayEquals(new String[]{"Pulitzer Prize", "National Medal of Arts", "Lifetime Achievement"}, photographer.getAwards());
  }
}
