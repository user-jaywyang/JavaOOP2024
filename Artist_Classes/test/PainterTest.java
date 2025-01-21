package problem1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PainterTest {

  private Painter painter;

  @BeforeEach
  public void setUp() throws InvalidAgeException {
    Name name = new Name("Vincent", "Van Gogh");
    String[] genres = {"Post-Impressionism"};
    String[] awards = {"Best Painter Award"};
    String[] exhibits = {"Amsterdam Exhibit", "Paris Exhibit"};

    painter = new Painter(name, 37, genres, awards, exhibits);
  }

  @Test
  public void testPainterCreation() {
    assertEquals("Vincent", painter.getName().getFirstName());
    assertEquals("Van Gogh", painter.getName().getLastName());
    assertEquals(37, painter.getAge());
    assertArrayEquals(new String[]{"Post-Impressionism"}, painter.getGenres());
    assertArrayEquals(new String[]{"Best Painter Award"}, painter.getAwards());
    assertArrayEquals(new String[]{"Amsterdam Exhibit", "Paris Exhibit"}, painter.getExhibits());
  }

  @Test
  public void testReceiveAward() {
    painter.receiveAward("Artist of the Century");
    assertArrayEquals(new String[]{"Best Painter Award", "Artist of the Century"}, painter.getAwards());
  }
}
