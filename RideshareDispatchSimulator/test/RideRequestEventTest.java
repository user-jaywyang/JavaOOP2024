package option1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RideRequestEventTest {

  @Test
  void testConstructorAndGetters() {
    RideshareRequest request = new RideshareRequest("C001", "Point A", "Point B", 10.0, "Express pick-up", 1000L);
    RideRequestEvent event = new RideRequestEvent(1000L, request);

    assertEquals(1000L, event.getEventTime());
    assertEquals(request, event.getRideRequest());
  }

  @Test
  void testCompareTo() {
    RideshareRequest request1 = new RideshareRequest("C001", "Point A", "Point B", 10.0, "Express pick-up", 1000L);
    RideshareRequest request2 = new RideshareRequest("C002", "Point C", "Point D", 5.0, "Standard pick-up", 2000L);

    RideRequestEvent earlierEvent = new RideRequestEvent(1000L, request1);
    RideRequestEvent laterEvent = new RideRequestEvent(2000L, request2);

    assertTrue(earlierEvent.compareTo(laterEvent) < 0);
    assertTrue(laterEvent.compareTo(earlierEvent) > 0);
  }

  @Test
  void testEqualsAndHashCode() {
    RideshareRequest request1 = new RideshareRequest("C001", "Point A", "Point B", 10.0, "Express pick-up", 1000L);
    RideRequestEvent event1 = new RideRequestEvent(1000L, request1);
    RideRequestEvent event2 = new RideRequestEvent(1000L, request1);

    assertEquals(event1, event2);
    assertEquals(event1.hashCode(), event2.hashCode());
  }

  @Test
  void testToString() {
    RideshareRequest request = new RideshareRequest("C001", "Point A", "Point B", 10.0, "Express pick-up", 1000L);
    RideRequestEvent event = new RideRequestEvent(1000L, request);
    String expected = "RideRequestEvent{eventTime=1000, rideRequest=" + request.toString() + "}";
    assertEquals(expected, event.toString());
  }
}
