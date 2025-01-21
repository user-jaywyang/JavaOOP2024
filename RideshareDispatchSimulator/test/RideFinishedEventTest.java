package option1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RideFinishedEventTest {

  @Test
  void testConstructorAndGetters() {
    Driver driver = new Driver("D001");
    RideshareRequest request = new RideshareRequest("C001", "Point A", "Point B", 10.0, "Express pick-up", 1000L);
    RideFinishedEvent event = new RideFinishedEvent(2000L, driver, request);

    assertEquals(2000L, event.getEventTime());
    assertEquals(driver, event.getDriver());
    assertEquals(request, event.getCompletedRide());
  }

  @Test
  void testCompareTo() {
    Driver driver = new Driver("D001");
    RideshareRequest request = new RideshareRequest("C001", "Point A", "Point B", 10.0, "Express pick-up", 1000L);

    RideFinishedEvent earlierEvent = new RideFinishedEvent(1000L, driver, request);
    RideFinishedEvent laterEvent = new RideFinishedEvent(2000L, driver, request);

    assertTrue(earlierEvent.compareTo(laterEvent) < 0);
    assertTrue(laterEvent.compareTo(earlierEvent) > 0);
  }

  @Test
  void testEqualsAndHashCode() {
    Driver driver = new Driver("D001");
    RideshareRequest request = new RideshareRequest("C001", "Point A", "Point B", 10.0, "Express pick-up", 1000L);

    RideFinishedEvent event1 = new RideFinishedEvent(2000L, driver, request);
    RideFinishedEvent event2 = new RideFinishedEvent(2000L, driver, request);

    assertEquals(event1, event2);
    assertEquals(event1.hashCode(), event2.hashCode());
  }

  @Test
  void testToString() {
    Driver driver = new Driver("D001");
    RideshareRequest request = new RideshareRequest("C001", "Point A", "Point B", 10.0, "Express pick-up", 1000L);
    RideFinishedEvent event = new RideFinishedEvent(2000L, driver, request);

    String expected = "RideFinishedEvent{eventTime=2000, driver=" + driver.toString() +
        ", completedRide=" + request.toString() + "}";
    assertEquals(expected, event.toString());
  }
}
