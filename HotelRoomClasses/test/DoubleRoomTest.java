package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoubleRoomTest {

  private DoubleRoom doubleRoom;

  @BeforeEach
  public void setUp() throws InvalidPriceException {
    // Create a new DoubleRoom object before each test with a valid price
    doubleRoom = new DoubleRoom(150);
  }

  @Test
  public void testValidPrice() throws InvalidPriceException {
    // Test that a valid DoubleRoom can be created
    assertEquals(150, doubleRoom.getPrice());
    assertEquals(2, doubleRoom.getMaxOccupancy());
  }

  @Test
  public void testInvalidPrice() {
    // Test that the constructor throws InvalidPriceException for zero or negative prices
    assertThrows(InvalidPriceException.class, () -> new DoubleRoom(0));
    assertThrows(InvalidPriceException.class, () -> new DoubleRoom(-100));
  }

  @Test
  public void testGetMaxOccupancy() {
    // Test that the max occupancy is always 2 for DoubleRoom
    assertEquals(2, doubleRoom.getMaxOccupancy());
  }

  @Test
  public void testGetPrice() {
    assertEquals(150, doubleRoom.getPrice());
  }

  @Test
  public void testIsAvailableEmpty() {
    // Test isAvailable method when no guests are in the room
    assertTrue(doubleRoom.isAvailable());
  }

  @Test
  public void testIsAvailableBooked() throws InvalidOccupancyException, InvalidAvailabilityException {
    // Test isAvailable method after booking the room
    doubleRoom.bookRoom(2);
    assertFalse(doubleRoom.isAvailable());
  }

  @Test
  public void testBookRoomValidGuests() throws InvalidOccupancyException, InvalidAvailabilityException {
    // Test booking the room with 1 guest (valid)
    doubleRoom.bookRoom(1);
    assertEquals(1, doubleRoom.getGuestsIN());

    // Test booking the room with 2 guests (also valid)
    doubleRoom.guestsIN = 0; // Simulate checkout
    doubleRoom.bookRoom(2);
    assertEquals(2, doubleRoom.getGuestsIN());
  }

  @Test
  public void testBookRoomInvalidGuests() {
    // Test that bookRoom throws InvalidOccupancyException when trying to book more than 2 guests
    assertThrows(InvalidOccupancyException.class, () -> doubleRoom.bookRoom(3));
  }

  @Test
  public void testBookRoomUnavailable() throws InvalidOccupancyException, InvalidAvailabilityException {
    // Test that bookRoom throws InvalidAvailabilityException when room is already booked
    doubleRoom.bookRoom(2); // Book the room with 2 guests
    assertThrows(InvalidAvailabilityException.class, () -> doubleRoom.bookRoom(1)); // Try to book again
  }
  @Test
  void testEqualsSameObject() {
    // Test that the same object is equal to itself
    DoubleRoom sameRoom = doubleRoom;
    assertEquals(doubleRoom, sameRoom);
  }

  @Test
  void testEqualsDifferentObjectSameValues() throws InvalidPriceException {
    // Test that two DoubleRoom objects with the same values are considered equal
    DoubleRoom anotherRoom = new DoubleRoom(150);
    assertEquals(doubleRoom, anotherRoom);
  }

  @Test
  void testNotEqualsDifferentPrice() throws InvalidPriceException {
    // Test that two DoubleRoom objects with different prices are not considered equal
    DoubleRoom anotherRoom = new DoubleRoom(200);
    assertNotEquals(doubleRoom, anotherRoom);
  }

  @Test
  void testHashCodeSameValues() throws InvalidPriceException {
    // Test that two DoubleRoom objects with the same values have the same hashCode
    DoubleRoom anotherRoom = new DoubleRoom(150);
    assertEquals(doubleRoom.hashCode(), anotherRoom.hashCode());
  }

  @Test
  void testHashCodeDifferentValues() throws InvalidPriceException {
    // Test that two DoubleRoom objects with different values have different hashCodes
    DoubleRoom anotherRoom = new DoubleRoom(200);
    assertNotEquals(doubleRoom.hashCode(), anotherRoom.hashCode());
  }


}
