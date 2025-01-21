package problem2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Objects;

public class RecipientTest {

  private Recipient recipient;

  @BeforeEach
  public void setUp() {
    recipient = new Recipient("John", "Doe", "john.doe@example.com");
  }

  @Test
  public void testConstructorAndGetter() {
    // Testing constructor and getters
    assertEquals("John", recipient.getFirstName());
    assertEquals("Doe", recipient.getLastName());
    assertEquals("john.doe@example.com", recipient.getEmail());
  }

  @Test
  public void testSetFirstName() {
    recipient.setFirstName("Jane");
    assertEquals("Jane", recipient.getFirstName());
  }

  @Test
  public void testSetLastName() {
    recipient.setLastName("Smith");
    assertEquals("Smith", recipient.getLastName());
  }

  @Test
  public void testSetEmail() {
    recipient.setEmail("jane.smith@example.com");
    assertEquals("jane.smith@example.com", recipient.getEmail());
  }

  @Test
  public void testEquals_SameObject() {
    assertTrue(recipient.equals(recipient));
  }

  @Test
  public void testEquals_NullObject() {
    assertFalse(recipient.equals(null));
  }

  @Test
  public void testEquals_DifferentClass() {
    assertFalse(recipient.equals("Some String"));
  }

  @Test
  public void testEquals_DifferentFirstName() {
    Recipient other = new Recipient("Jane", "Doe", "john.doe@example.com");
    assertFalse(recipient.equals(other));
  }

  @Test
  public void testEquals_DifferentLastName() {
    Recipient other = new Recipient("John", "Smith", "john.doe@example.com");
    assertFalse(recipient.equals(other));
  }

  @Test
  public void testEquals_DifferentEmail() {
    Recipient other = new Recipient("John", "Doe", "jane.doe@example.com");
    assertFalse(recipient.equals(other));
  }

  @Test
  public void testEquals_EqualObjects() {
    Recipient other = new Recipient("John", "Doe", "john.doe@example.com");
    assertTrue(recipient.equals(other));
  }

  @Test
  public void testHashCode_SameObjects() {
    Recipient other = new Recipient("John", "Doe", "john.doe@example.com");
    assertEquals(recipient.hashCode(), other.hashCode());
  }

  @Test
  public void testHashCode_DifferentObjects() {
    Recipient other = new Recipient("Jane", "Doe", "john.doe@example.com");
    assertNotEquals(recipient.hashCode(), other.hashCode());
  }
}
