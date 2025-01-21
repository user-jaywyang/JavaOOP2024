package problem1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActorTest {

  private Actor actor;

  @BeforeEach
  public void setUp() throws InvalidAgeException {
    Name name = new Name("Leonardo", "DiCaprio");
    String[] genres = {"Drama", "Action"};
    String[] awards = {"Oscar Award"};
    String[] movies = {"Inception", "Titanic"};
    String[] series = {"The Great Gatsby"};
    String[] otherMultimedia = {"Documentaries"};

    actor = new Actor(name, 46, genres, awards, movies, series, otherMultimedia);
  }

  @Test
  public void testActorCreation() {
    assertEquals("Leonardo", actor.getName().getFirstName());
    assertEquals("DiCaprio", actor.getName().getLastName());
    assertEquals(46, actor.getAge());
    assertArrayEquals(new String[]{"Drama", "Action"}, actor.getGenres());
    assertArrayEquals(new String[]{"Oscar Award"}, actor.getAwards());
    assertArrayEquals(new String[]{"Inception", "Titanic"}, actor.getMovies());
    assertArrayEquals(new String[]{"The Great Gatsby"}, actor.getSeries());
    assertArrayEquals(new String[]{"Documentaries"}, actor.getOtherMultimedia());
  }

  @Test
  public void testReceiveAward() {
    actor.receiveAward("Golden Globe");
    assertArrayEquals(new String[]{"Oscar Award", "Golden Globe"}, actor.getAwards());
  }
}
