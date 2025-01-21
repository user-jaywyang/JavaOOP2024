package option1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

  @Test
  void testConstructorAndGetters() {
    Driver driver = new Driver("D123");

    assertEquals("D123", driver.getDriverId());
    assertTrue(driver.isAvailable());
    assertTrue(driver.getCompletedRides().isEmpty());
  }

  @Test
  void testSetAvailable() {
    Driver driver = new Driver("D123");

    driver.setAvailable(false);
    assertFalse(driver.isAvailable());
    driver.setAvailable(true);
    assertTrue(driver.isAvailable());
  }

  @Test
  void testAssignRide() {
    Driver driver = new Driver("D123");
    RideshareRequest ride = new RideshareRequest("C001", "Point A", "Point B", 10.0,
        "Express pick-up", 1000L);

    driver.assignRide(ride);
    assertFalse(driver.isAvailable());

    // Test exception if driver is already busy
    assertThrows(IllegalStateException.class, () -> driver.assignRide(ride));
  }

  @Test
  void testCompleteRide() {
    Driver driver = new Driver("D123");
    RideshareRequest ride = new RideshareRequest("C001", "Point A", "Point B", 10.0,
        "Express pick-up", 1000L);

    driver.assignRide(ride);
    driver.completeRide(ride);

    assertTrue(driver.isAvailable());
    assertEquals(1, driver.getCompletedRides().size());
    assertEquals(ride, driver.getCompletedRides().get(0));

    // Test exception if driver is already free
    assertThrows(IllegalStateException.class, () -> driver.completeRide(ride));
  }

  @Test
  void testCompareTo() {
    Driver driver1 = new Driver("D001");
    Driver driver2 = new Driver("D002");
    Driver driver3 = new Driver("D001");

    assertTrue(driver1.compareTo(driver2) < 0);
    assertTrue(driver2.compareTo(driver1) > 0);
    assertEquals(0, driver1.compareTo(driver3));
  }

  @Test
  void testEqualsAndHashCode() {
    Driver driver1 = new Driver("D123");
    Driver driver2 = new Driver("D123");
    Driver driver3 = new Driver("D456");

    assertEquals(driver1, driver2);
    assertNotEquals(driver1, driver3);

    assertEquals(driver1.hashCode(), driver2.hashCode());
    assertNotEquals(driver1.hashCode(), driver3.hashCode());
  }

  @Test
  void testToString() {
    Driver driver = new Driver("D123");
    String expected = "Driver{driverId='D123', available=true, completedRides=0}";
    assertEquals(expected, driver.toString());
  }
}
