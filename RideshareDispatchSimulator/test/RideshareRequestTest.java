package option1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RideshareRequestTest {

  @Test
  void testConstructorAndGetters() {
    RideshareRequest request = new RideshareRequest("123", "Point A", "Point B", 10.5,
        "Express pick-up", 1000L);

    assertEquals("123", request.getCustomerId());
    assertEquals("Point A", request.getStartingLocation());
    assertEquals("Point B", request.getDestination());
    assertEquals(10.5, request.getDistance());
    assertEquals("Express pick-up", request.getRideType());
    assertEquals(1000L, request.getRequestTime());
    assertEquals(4, request.getPriority());
  }

  @Test
  void testPriorityCalculation() {
    RideshareRequest express = new RideshareRequest("123", "Point A", "Point B", 10.5,
        "Express pick-up", 1000L);
    RideshareRequest standard = new RideshareRequest("124", "Point C", "Point D", 15.0,
        "Standard pick-up", 1100L);
    RideshareRequest waitAndSave = new RideshareRequest("125", "Point E", "Point F", 20.0,
        "Wait-and-save pick-up", 1200L);
    RideshareRequest environmental = new RideshareRequest("126", "Point G", "Point H", 5.0,
        "Environmentally conscious pick-up", 1300L);

    assertEquals(4, express.getPriority());
    assertEquals(3, standard.getPriority());
    assertEquals(2, waitAndSave.getPriority());
    assertEquals(1, environmental.getPriority());
  }

  @Test
  void testCompareTo() {
    RideshareRequest highPriority = new RideshareRequest("123", "Point A", "Point B", 10.5,
        "Express pick-up", 1000L);
    RideshareRequest lowPriority = new RideshareRequest("124", "Point C", "Point D", 15.0,
        "Environmentally conscious pick-up", 900L);

    assertTrue(highPriority.compareTo(lowPriority) < 0);

    RideshareRequest samePriorityEarlier = new RideshareRequest("125", "Point E", "Point F", 20.0,
        "Standard pick-up", 800L);
    RideshareRequest samePriorityLater = new RideshareRequest("126", "Point G", "Point H", 5.0,
        "Standard pick-up", 900L);

    assertTrue(samePriorityEarlier.compareTo(samePriorityLater) < 0);
  }

  @Test
  void testEqualsAndHashCode() {
    RideshareRequest request1 = new RideshareRequest("123", "Point A", "Point B", 10.5,
        "Express pick-up", 1000L);
    RideshareRequest request2 = new RideshareRequest("123", "Point A", "Point B", 10.5,
        "Express pick-up", 1000L);
    RideshareRequest request3 = new RideshareRequest("124", "Point C", "Point D", 15.0,
        "Standard pick-up", 1100L);

    assertEquals(request1, request2);
    assertNotEquals(request1, request3);

    assertEquals(request1.hashCode(), request2.hashCode());
    assertNotEquals(request1.hashCode(), request3.hashCode());
  }

  @Test
  void testToString() {
    RideshareRequest request = new RideshareRequest("123", "Point A", "Point B", 10.5,
        "Express pick-up", 1000L);
    String expected = "RideshareRequest{customerId='123', startingLocation='Point A', destination='Point B', " +
        "distance=10.5, rideType='Express pick-up', requestTime=1000, priority=4}";
    assertEquals(expected, request.toString());
  }
}
