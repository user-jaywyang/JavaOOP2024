package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SingleRoomTest {

  private SingleRoom singleRoom;

  @BeforeEach
  public void setUp() throws InvalidPriceException {
    // Create a new SingleRoom object before each test with a valid price
    singleRoom = new SingleRoom(100);
  }

  @Test
  public void testValidPrice() throws InvalidPriceException {
    // Test that a valid SingleRoom can be created
    assertEquals(100, singleRoom.getPrice());
    assertEquals(1, singleRoom.getMaxOccupancy());
  }

  @Test
  public void testInvalidPrice() {
    // Test that the constructor throws InvalidPriceException for zero or negative prices
    assertThrows(InvalidPriceException.class, () -> new SingleRoom(0));
    assertThrows(InvalidPriceException.class, () -> new SingleRoom(-50));
  }

  @Test
  public void testGetMaxOccupancy() {
    // Test that the max occupancy is always 1
    assertEquals(1, singleRoom.getMaxOccupancy());
  }

  @Test
  public void testGetPrice() {
    assertEquals(100, singleRoom.getPrice());
  }

  @Test
  public void testIsAvailableEmpty() {
    // Test isAvailable method when no guests are in the room
    assertTrue(singleRoom.isAvailable());
  }

  @Test
  void testIsAvailableBooked() throws InvalidOccupancyException, InvalidAvailabilityException {
    // Test isAvailable method after booking the room
    singleRoom.bookRoom(1);
    assertFalse(singleRoom.isAvailable());
  }

  @Test
  public void testBookRoomValidGuest() throws InvalidOccupancyException, InvalidAvailabilityException {
    // Test booking the room with 1 guest (valid for SingleRoom)
    singleRoom.bookRoom(1);
    assertEquals(1, singleRoom.getGuestsIN());
  }

  @Test
  public void testBookRoomInvalidGuest() {
    // Test that bookRoom throws InvalidOccupancyException when trying to book more than 1 guest
    assertThrows(InvalidOccupancyException.class, () -> singleRoom.bookRoom(2));
  }

  @Test
  public void testBookRoomUnavailable() throws InvalidOccupancyException, InvalidAvailabilityException {
    // Test that bookRoom throws InvalidAvailabilityException when room is already booked
    singleRoom.bookRoom(1); // Book the room with 1 guest
    assertThrows(InvalidAvailabilityException.class, () -> singleRoom.bookRoom(1)); // Try to book again
  }
  @Test
  void testEqualsSameObject() {
    // Test that the same object is equal to itself
    SingleRoom sameRoom = singleRoom;
    assertEquals(singleRoom, sameRoom);
  }

  @Test
  void testEqualsDifferentObjectSameValues() throws InvalidPriceException {
    // Test that two SingleRoom objects with the same values are considered equal
    SingleRoom anotherRoom = new SingleRoom(100);
    assertEquals(singleRoom, anotherRoom);
  }

  @Test
  void testNotEqualsDifferentPrice() throws InvalidPriceException {
    // Test that two SingleRoom objects with different prices are not considered equal
    SingleRoom anotherRoom = new SingleRoom(150);
    assertNotEquals(singleRoom, anotherRoom);
  }

  @Test
  void testHashCodeSameValues() throws InvalidPriceException {
    // Test that two SingleRoom objects with the same values have the same hashCode
    SingleRoom anotherRoom = new SingleRoom(100);
    assertEquals(singleRoom.hashCode(), anotherRoom.hashCode());
  }

  @Test
  void testHashCodeDifferentValues() throws InvalidPriceException {
    // Test that two SingleRoom objects with different values have different hashCodes
    SingleRoom anotherRoom = new SingleRoom(150);
    assertNotEquals(singleRoom.hashCode(), anotherRoom.hashCode());
  }
}
