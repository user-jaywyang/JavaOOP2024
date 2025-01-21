package problem2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MailItemTest {

  private MailItem mailItem;
  private Recipient recipient;

  @BeforeEach
  public void setUp() {
    recipient = new Recipient("John", "Doe", "john.doe@example.com");
    mailItem = new MailItem(10, 20, 30, recipient);
  }

  @Test
  public void testConstructorAndGetter() {
    // Test constructor and getters
    assertEquals(10, mailItem.getWidth());
    assertEquals(20, mailItem.getHeight());
    assertEquals(30, mailItem.getDepth());
    assertEquals(recipient, mailItem.getRecipient());
  }

  @Test
  public void testSetWidth_Valid() throws InvalidMeasurementException {
    mailItem.setWidth(15);
    assertEquals(15, mailItem.getWidth());
  }

  @Test
  public void testSetWidth_Invalid() {
    assertThrows(InvalidMeasurementException.class, () -> mailItem.setWidth(0));
  }

  @Test
  public void testSetHeight_Valid() throws InvalidMeasurementException {
    mailItem.setHeight(25);
    assertEquals(25, mailItem.getHeight());
  }

  @Test
  public void testSetHeight_Invalid() {
    assertThrows(InvalidMeasurementException.class, () -> mailItem.setHeight(0));
  }

  @Test
  public void testSetDepth_Valid() throws InvalidMeasurementException {
    mailItem.setDepth(35);
    assertEquals(35, mailItem.getDepth());
  }

  @Test
  public void testSetDepth_Invalid() {
    assertThrows(InvalidMeasurementException.class, () -> mailItem.setDepth(0));
  }

  @Test
  public void testSetRecipient() {
    Recipient newRecipient = new Recipient("Jane", "Smith", "jane.smith@example.com");
    mailItem.setRecipient(newRecipient);
    assertEquals(newRecipient, mailItem.getRecipient());
  }

  @Test
  public void testEquals_SameObject() {
    assertTrue(mailItem.equals(mailItem));
  }

  @Test
  public void testEquals_NullObject() {
    assertFalse(mailItem.equals(null));
  }

  @Test
  public void testEquals_DifferentClass() {
    assertFalse(mailItem.equals("Some String"));
  }

  @Test
  public void testEquals_DifferentWidth() {
    MailItem otherMailItem = new MailItem(5, 20, 30, recipient);
    assertFalse(mailItem.equals(otherMailItem));
  }

  @Test
  public void testEquals_DifferentHeight() {
    MailItem otherMailItem = new MailItem(10, 15, 30, recipient);
    assertFalse(mailItem.equals(otherMailItem));
  }

  @Test
  public void testEquals_DifferentDepth() {
    MailItem otherMailItem = new MailItem(10, 20, 25, recipient);
    assertFalse(mailItem.equals(otherMailItem));
  }

  @Test
  public void testEquals_DifferentRecipient() {
    Recipient newRecipient = new Recipient("Jane", "Smith", "jane.smith@example.com");
    MailItem otherMailItem = new MailItem(10, 20, 30, newRecipient);
    assertFalse(mailItem.equals(otherMailItem));
  }

  @Test
  public void testEquals_EqualObjects() {
    MailItem otherMailItem = new MailItem(10, 20, 30, recipient);
    assertTrue(mailItem.equals(otherMailItem));
  }

  @Test
  public void testHashCode_EqualObjects() {
    MailItem otherMailItem = new MailItem(10, 20, 30, recipient);
    assertEquals(mailItem.hashCode(), otherMailItem.hashCode());
  }

  @Test
  public void testHashCode_DifferentObjects() {
    MailItem otherMailItem = new MailItem(5, 20, 30, recipient);
    assertNotEquals(mailItem.hashCode(), otherMailItem.hashCode());
  }
}
