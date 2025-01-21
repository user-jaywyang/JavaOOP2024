package problem2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LockerTest {

  private Locker lockerWithMail;
  private Locker emptyLocker;
  private MailItem mailItem;
  private Recipient recipient;

  @BeforeEach
  public void setUp() {
    recipient = new Recipient("John", "Doe", "john.doe@example.com");
    mailItem = new MailItem(10, 20, 30, recipient);
    lockerWithMail = new Locker(15, 25, 35, mailItem);
    emptyLocker = new Locker(15, 25, 35);
  }

  @Test
  public void testConstructorWithMail() throws LockerEmptyException, RecipientMatchException{
    assertEquals(15, lockerWithMail.getWidth());
    assertEquals(25, lockerWithMail.getHeight());
    assertEquals(35, lockerWithMail.getDepth());
    assertTrue(lockerWithMail.isOccupied());
    assertEquals(mailItem, lockerWithMail.pickupMail(recipient));
  }

  @Test
  public void testConstructorWithoutMail() {
    assertEquals(15, emptyLocker.getWidth());
    assertEquals(25, emptyLocker.getHeight());
    assertEquals(35, emptyLocker.getDepth());
    assertFalse(emptyLocker.isOccupied());
  }

  @Test
  public void testSetWidthValid() throws InvalidMeasurementException {
    emptyLocker.setWidth(20);
    assertEquals(20, emptyLocker.getWidth());
  }

  @Test
  public void testSetWidthInvalid() {
    assertThrows(IllegalArgumentException.class, () -> emptyLocker.setWidth(0));
  }

  @Test
  public void testSetHeightValid() throws InvalidMeasurementException {
    emptyLocker.setHeight(30);
    assertEquals(30, emptyLocker.getHeight());
  }

  @Test
  public void testSetHeightInvalid() {
    assertThrows(IllegalArgumentException.class, () -> emptyLocker.setHeight(0));
  }

  @Test
  public void testSetDepthValid() throws InvalidMeasurementException {
    emptyLocker.setDepth(40);
    assertEquals(40, emptyLocker.getDepth());
  }

  @Test
  public void testSetDepthInvalid() {
    assertThrows(InvalidMeasurementException.class, () -> emptyLocker.setDepth(0));
  }

  @Test
  public void testAddMailValid()
      throws LockerOccupiedException, InvalidMeasurementException, LockerEmptyException, RecipientMatchException {
    MailItem newMailItem = new MailItem(10, 20, 30, recipient);
    emptyLocker.addMail(newMailItem);
    assertTrue(emptyLocker.isOccupied());
    assertEquals(newMailItem, emptyLocker.pickupMail(recipient));
  }

  @Test
  public void testAddMailOccupied() {
    MailItem newMailItem = new MailItem(10, 20, 30, recipient);
    assertThrows(LockerOccupiedException.class, () -> lockerWithMail.addMail(newMailItem));
  }

  @Test
  public void testAddMailInvalidMeasurement() {
    MailItem largeMailItem = new MailItem(20, 30, 40, recipient);
    assertThrows(InvalidMeasurementException.class, () -> emptyLocker.addMail(largeMailItem));
  }

  @Test
  public void testPickupMailValid() throws LockerEmptyException, RecipientMatchException {
    MailItem pickedUpMail = lockerWithMail.pickupMail(recipient);
    assertEquals(mailItem, pickedUpMail);
    assertFalse(lockerWithMail.isOccupied());
  }

  @Test
  public void testPickupMailEmptyLocker() {
    assertThrows(LockerEmptyException.class, () -> emptyLocker.pickupMail(recipient));
  }

  @Test
  public void testPickupMailRecipientMismatch() {
    Recipient wrongRecipient = new Recipient("Jane", "Doe", "jane.doe@example.com");
    assertThrows(RecipientMatchException.class, () -> lockerWithMail.pickupMail(wrongRecipient));
  }

  @Test
  public void testEquals_SameObject() {
    // Test that the locker is equal to itself
    assertTrue(lockerWithMail.equals(lockerWithMail));
  }

  @Test
  public void testEquals_NullObject() {
    // Test that the locker is not equal to null
    assertFalse(lockerWithMail.equals(null));
  }

  @Test
  public void testEquals_DifferentClass() {
    // Test that the locker is not equal to an object of a different class
    assertFalse(lockerWithMail.equals("Some String"));
  }

  @Test
  public void testEquals_DifferentWidth() {
    Locker otherLocker = new Locker(10, 25, 35, mailItem);
    // Test that lockers with different widths are not equal
    assertFalse(lockerWithMail.equals(otherLocker));
  }

  @Test
  public void testEquals_DifferentHeight() {
    Locker otherLocker = new Locker(15, 20, 35, mailItem);
    // Test that lockers with different heights are not equal
    assertFalse(lockerWithMail.equals(otherLocker));
  }

  @Test
  public void testEquals_DifferentDepth() {
    Locker otherLocker = new Locker(15, 25, 30, mailItem);
    // Test that lockers with different depths are not equal
    assertFalse(lockerWithMail.equals(otherLocker));
  }

  @Test
  public void testEquals_DifferentMailItem() {
    Recipient newRecipient = new Recipient("Jane", "Doe", "jane.doe@example.com");
    MailItem newMailItem = new MailItem(10, 20, 30, newRecipient);
    Locker otherLocker = new Locker(15, 25, 35, newMailItem);
    // Test that lockers with different mail items are not equal
    assertFalse(lockerWithMail.equals(otherLocker));
  }

  @Test
  public void testEquals_DifferentOccupiedStatus() {
    Locker otherLocker = new Locker(15, 25, 35); // Unoccupied locker
    // Test that lockers with different occupied statuses are not equal
    assertFalse(lockerWithMail.equals(otherLocker));
  }

  @Test
  public void testEquals_EqualObjects() {
    Locker otherLocker = new Locker(15, 25, 35, mailItem);
    // Test that two lockers with the same dimensions, mail item, and occupied status are equal
    assertTrue(lockerWithMail.equals(otherLocker));
  }

  @Test
  public void testHashCode_SameObjects() {
    Locker otherLocker = new Locker(15, 25, 35, mailItem);
    assertEquals(lockerWithMail.hashCode(), otherLocker.hashCode());
  }

  @Test
  public void testHashCode_DifferentObjects() {
    Locker otherLocker = new Locker(10, 25, 35, mailItem);
    assertNotEquals(lockerWithMail.hashCode(), otherLocker.hashCode());
  }

}
