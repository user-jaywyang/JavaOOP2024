package problem1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NameTest {

  @Test
  public void testNameCreation() {
    Name name = new Name("John", "Doe");
    assertEquals("John", name.getFirstName());
    assertEquals("Doe", name.getLastName());
  }

  @Test
  public void testToString() {
    Name name = new Name("Jane", "Smith");
    assertEquals("Jane Smith", name.toString());
  }
}
