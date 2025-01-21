package option1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RideshareDispatchSimulatorTest {

  @Test
  void testSimulationWithNoRequests() {
    RideshareDispatchSimulator simulator = new RideshareDispatchSimulator(50);
    simulator.runSimulation();

    assertEquals(0, simulator.getAverageWaitTime());
    assertEquals(0, simulator.getAverageRidesPerDriver());
  }

  @Test
  void testSimulationWithFewRequests() {
    RideshareDispatchSimulator simulator = new RideshareDispatchSimulator(2);

    simulator.addRideRequest(new RideshareRequest("C001", "Point A", "Point B", 10.0, "Express pick-up", 1000L));
    simulator.addRideRequest(new RideshareRequest("C002", "Point C", "Point D", 20.0, "Standard pick-up", 2000L));
    simulator.runSimulation();

    assertTrue(simulator.getAverageWaitTime() >= 0); // No wait for these
    assertEquals(1.0, simulator.getAverageRidesPerDriver());
  }

  @Test
  void testSimulationWithPendingRequests() {
    RideshareDispatchSimulator simulator = new RideshareDispatchSimulator(1);

    simulator.addRideRequest(new RideshareRequest("C001", "Point A", "Point B", 10.0, "Express pick-up", 1000L));
    simulator.addRideRequest(new RideshareRequest("C002", "Point C", "Point D", 20.0, "Standard pick-up", 2000L));
    simulator.addRideRequest(new RideshareRequest("C003", "Point E", "Point F", 15.0, "Wait-and-save pick-up", 3000L));
    simulator.runSimulation();

    assertTrue(simulator.getAverageWaitTime() > 0); // At least one ride waited
    assertEquals(3.0, simulator.getAverageRidesPerDriver());
  }

  @Test
  void testGetAverageRidesPerDriver() {
    RideshareDispatchSimulator simulator = new RideshareDispatchSimulator(2);

    simulator.addRideRequest(new RideshareRequest("C001", "Point A", "Point B", 10.0, "Express pick-up", 1000L));
    simulator.addRideRequest(new RideshareRequest("C002", "Point C", "Point D", 20.0, "Standard pick-up", 2000L));
    simulator.addRideRequest(new RideshareRequest("C003", "Point E", "Point F", 15.0, "Wait-and-save pick-up", 3000L));
    simulator.runSimulation();

    assertEquals(1.5, simulator.getAverageRidesPerDriver());
  }
}
