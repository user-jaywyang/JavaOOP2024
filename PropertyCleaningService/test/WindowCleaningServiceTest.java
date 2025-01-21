package problem1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WindowCleaningServiceTest {

  private static final String TEST_ADDRESS = "789 Oak St";
  private static final int VALID_PREVIOUS_SERVICES_COUNT = 8;
  private static final boolean IS_MONTHLY = true;
  private static final boolean NOT_MONTHLY = false;


  @Test
  public void testCalculatePriceForSingleFloorSmallProperty() {
    // Single floor small property
    WindowCleaningService service = new WindowCleaningService(TEST_ADDRESS, PropertySize.SMALL, NOT_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 1);
    double expectedPrice = 80 * 1;  // Base rate * hours required for small property
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceForMultiFloorMediumProperty() {
    // Multi-floor medium property (2 floors)
    WindowCleaningService service = new WindowCleaningService(TEST_ADDRESS, PropertySize.MEDIUM, NOT_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);
    double basePrice = 80 * 2;  // Base rate * hours required for medium property
    double expectedPrice = basePrice + basePrice * 0.05;  // Adding 5% surcharge for multi-floor
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceForLargePropertyWithThreeFloors() {
    // Three-floor large property (maximum floors allowed)
    WindowCleaningService service = new WindowCleaningService(TEST_ADDRESS, PropertySize.LARGE, NOT_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 3);
    double basePrice = 80 * 4;  // Base rate * hours required for large property
    double expectedPrice = basePrice + basePrice * 0.05;  // Adding 5% surcharge for multi-floor
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceFor10thServiceDiscount() {
    // 10th service should apply a 50% discount
    WindowCleaningService service = new WindowCleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, 9, 1);
    double basePrice = 80 * 1;  // Base rate * hours for small property
    double expectedPrice = basePrice * 0.5;  // 50% discount for 10th service
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceForMonthlyServiceDiscount() {
    // Monthly service should apply a 10% discount (for non-10th service)
    WindowCleaningService service = new WindowCleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 1);
    double basePrice = 80 * 1;  // Base rate * hours for small property
    double expectedPrice = basePrice * 0.9;  // 10% discount for monthly service
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testInvalidNumberOfFloorsThrowsException() {
    // Attempt to create a service with more than 3 floors should throw an exception
    assertThrows(OverMaxFloorException.class, () -> {
      new WindowCleaningService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 4);
    });
  }

  @Test
  public void testEquals() {
    // Create two identical instances
    WindowCleaningService service1 = new WindowCleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);
    WindowCleaningService service2 = new WindowCleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);

    // Create instances with different values
    WindowCleaningService serviceDifferentAddress = new WindowCleaningService("123 Main St", PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);
    WindowCleaningService serviceDifferentSize = new WindowCleaningService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);
    WindowCleaningService serviceDifferentFloors = new WindowCleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 3);
    WindowCleaningService serviceDifferentMonthly = new WindowCleaningService(TEST_ADDRESS, PropertySize.SMALL, NOT_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);

    // Test equality for identical instances
    assertEquals(service1, service2);

    // Test inequality for instances that differ in various fields
    assertNotEquals(service1, serviceDifferentAddress);
    assertNotEquals(service1, serviceDifferentSize);
    assertNotEquals(service1, serviceDifferentFloors);
    assertNotEquals(service1, serviceDifferentMonthly);
  }

  @Test
  public void testHashCode() {
    // Create two identical instances
    WindowCleaningService service1 = new WindowCleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);
    WindowCleaningService service2 = new WindowCleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);

    // Create instances with different values
    WindowCleaningService serviceDifferentSize = new WindowCleaningService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);
    WindowCleaningService serviceDifferentFloors = new WindowCleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 3);

    // Test hashCode consistency for equal instances
    assertEquals(service1.hashCode(), service2.hashCode());

    // Test hashCode inequality for different instances
    assertNotEquals(service1.hashCode(), serviceDifferentSize.hashCode());
    assertNotEquals(service1.hashCode(), serviceDifferentFloors.hashCode());
  }

  @Test
  public void testToString() {
    WindowCleaningService service = new WindowCleaningService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);
    String expectedString = "WindowCleaningService{propertyAddress='789 Oak St', propertySize=MEDIUM, monthly=true, " +
        "previousServicesCount=8, numberOfFloors=2, baseRate=80.0, multiFloorSurcharge=0.05}";
    assertEquals(expectedString, service.toString());
  }
}
