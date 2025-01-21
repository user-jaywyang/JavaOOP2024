package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

  private Room testRoom;

  @BeforeEach
  void setUp() throws InvalidPriceException {
    // Create a new Room object before each test with a valid price
    testRoom = new Room(3, 100);
  }

  @Test
  void testValidPrice() {
    // Test that a valid room can be created
    assertEquals(100, testRoom.getPrice());
    assertEquals(3, testRoom.getMaxOccupancy());
  }

  @Test
  void testInvalidPrice() {
    // Test that the constructor throws InvalidPriceException for zero or negative prices
    assertThrows(InvalidPriceException.class, () -> new Room(3, 0));
    assertThrows(InvalidPriceException.class, () -> new Room(3, -10));
  }

  @Test
  void testGetMaxOccupancy() {
    // Test getMaxOccupancy method
    assertEquals(3, testRoom.getMaxOccupancy());
  }

  @Test
  void testGetPrice() {
    // Test getPrice method
    assertEquals(100, testRoom.getPrice());
  }

  @Test
  void testGetGuestsIN() {
    // Test getGuestsIN method initially (no guests booked)
    assertEquals(0, testRoom.getGuestsIN());
  }

  @Test
  void testIsAvailableWhenEmpty() {
    // Test isAvailable method when no guests are in the room
    assertTrue(testRoom.isAvailable());
  }

  @Test
  void testIsAvailableWhenBooked() throws InvalidOccupancyException, InvalidAvailabilityException {
    // Test isAvailable method after booking the room
    testRoom.bookRoom(2);
    assertFalse(testRoom.isAvailable());
  }

  @Test
  void testBookRoomValid() throws InvalidOccupancyException, InvalidAvailabilityException {
    // Test bookRoom method with a valid number of guests
    testRoom.bookRoom(2);
    assertEquals(2, testRoom.getGuestsIN());
  }

  @Test
  void testBookRoomInvalidGuestNumber() {
    // Test that bookRoom throws InvalidOccupancyException when guests exceed max occupancy
    assertThrows(InvalidOccupancyException.class, () -> testRoom.bookRoom(4));
  }

  @Test
  void testBookRoomUnavailable() throws InvalidAvailabilityException, InvalidOccupancyException {
    // Test that bookRoom throws InvalidAvailabilityException when room is already booked
    testRoom.bookRoom(2); // Book the room
    assertThrows(InvalidAvailabilityException.class, () -> testRoom.bookRoom(2)); // Try to book again
  }

  @Test
  void testEqualsSameObject() {
    // Test that the same object is equal to itself
    assertEquals(testRoom, testRoom);
  }

  @Test
  void testEqualsDifferentObjectSameValues() throws InvalidPriceException {
    // Test that two Room objects with the same values are considered equal
    Room anotherRoom = new Room(3, 100);
    assertEquals(testRoom, anotherRoom);
  }

  @Test
  void testNotEqualsDifferentMaxOccupancy() throws InvalidPriceException {
    // Test that two Room objects with different maxOccupancy are not considered equal
    Room anotherRoom = new Room(4, 100);
    assertNotEquals(testRoom, anotherRoom);
  }

  @Test
  void testNotEqualsDifferentPrice() throws InvalidPriceException {
    // Test that two Room objects with different prices are not considered equal
    Room anotherRoom = new Room(3, 150);
    assertNotEquals(testRoom, anotherRoom);
  }

  @Test
  void testNotEqualsDifferentGuestsIN() throws InvalidPriceException, InvalidOccupancyException, InvalidAvailabilityException {
    // Test that two Room objects with different guestsIN values are not considered equal
    Room anotherRoom = new Room(3, 100);
    testRoom.bookRoom(2);  // Book one room with guests
    assertNotEquals(testRoom, anotherRoom);
  }

  @Test
  void testHashCodeSameValues() throws InvalidPriceException {
    // Test that two Room objects with the same values have the same hashCode
    Room anotherRoom = new Room(3, 100);
    assertEquals(testRoom.hashCode(), anotherRoom.hashCode());
  }

  @Test
  void testHashCodeDifferentValues() throws InvalidPriceException {
    // Test that two Room objects with different values have different hashCodes
    Room anotherRoom = new Room(4, 150);
    assertNotEquals(testRoom.hashCode(), anotherRoom.hashCode());
  }

}
