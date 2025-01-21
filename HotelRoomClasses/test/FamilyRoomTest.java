package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FamilyRoomTest {

  private FamilyRoom familyRoom;

  @BeforeEach
  public void setUp() throws InvalidPriceException {
    // Create a new FamilyRoom object before each test with a valid price
    familyRoom = new FamilyRoom(200);
  }

  @Test
  public void testValidPrice() throws InvalidPriceException {
    // Test that a valid FamilyRoom can be created
    assertEquals(200, familyRoom.getPrice());
    assertEquals(4, familyRoom.getMaxOccupancy());
  }

  @Test
  public void testInvalidPrice() {
    // Test that the constructor throws InvalidPriceException for zero or negative prices
    assertThrows(InvalidPriceException.class, () -> new FamilyRoom(0));
    assertThrows(InvalidPriceException.class, () -> new FamilyRoom(-150));
  }

  @Test
  public void testGetMaxOccupancy() {
    // Test that the max occupancy is always 4 for FamilyRoom
    assertEquals(4, familyRoom.getMaxOccupancy());
  }

  @Test
  public void testGetPrice() {
    assertEquals(200, familyRoom.getPrice());
  }

  @Test
  public void testIsAvailableEmpty() {
    // Test isAvailable method when no guests are in the room
    assertTrue(familyRoom.isAvailable());
  }

  @Test
  public void testIsAvailableBooked() throws InvalidOccupancyException, InvalidAvailabilityException {
    // Test isAvailable method after booking the room
    familyRoom.bookRoom(3);
    assertFalse(familyRoom.isAvailable());
  }


  @Test
  public void testBookRoomValidGuests() throws InvalidOccupancyException, InvalidAvailabilityException {
    // Test booking the room with 1 to 4 guests (valid cases)
    familyRoom.bookRoom(1);
    assertEquals(1, familyRoom.getGuestsIN());

    // Reset for the next booking
    familyRoom.guestsIN = 0;
    familyRoom.bookRoom(4);
    assertEquals(4, familyRoom.getGuestsIN());
  }

  @Test
  public void testBookRoomInvalidGuests() {
    // Test that bookRoom throws InvalidOccupancyException when trying to book more than 4 guests
    assertThrows(InvalidOccupancyException.class, () -> familyRoom.bookRoom(5));
  }

  @Test
  public void testBookRoomWhenUnavailable() throws InvalidOccupancyException, InvalidAvailabilityException {
    // Test that bookRoom throws InvalidAvailabilityException when room is already booked
    familyRoom.bookRoom(4); // Book the room with max guests
    assertThrows(InvalidAvailabilityException.class, () -> familyRoom.bookRoom(1)); // Try to book again
  }
  @Test
  void testEqualsSameObject() {
    // Test that the same object is equal to itself
    FamilyRoom sameRoom = familyRoom;
    assertEquals(familyRoom, sameRoom);
  }

  @Test
  void testEqualsDifferentObjectSameValues() throws InvalidPriceException {
    // Test that two FamilyRoom objects with the same values are considered equal
    FamilyRoom anotherRoom = new FamilyRoom(200);
    assertEquals(familyRoom, anotherRoom);
  }

  @Test
  void testNotEqualsDifferentPrice() throws InvalidPriceException {
    // Test that two FamilyRoom objects with different prices are not considered equal
    FamilyRoom anotherRoom = new FamilyRoom(250);
    assertNotEquals(familyRoom, anotherRoom);
  }

  @Test
  void testHashCodeSameValues() throws InvalidPriceException {
    // Test that two FamilyRoom objects with the same values have the same hashCode
    FamilyRoom anotherRoom = new FamilyRoom(200);
    assertEquals(familyRoom.hashCode(), anotherRoom.hashCode());
  }

  @Test
  void testHashCodeDifferentValues() throws InvalidPriceException {
    // Test that two FamilyRoom objects with different values have different hashCodes
    FamilyRoom anotherRoom = new FamilyRoom(250);
    assertNotEquals(familyRoom.hashCode(), anotherRoom.hashCode());
  }
}
