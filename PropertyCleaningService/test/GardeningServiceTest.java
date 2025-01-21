package problem1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GardeningServiceTest {

  private static final String TEST_ADDRESS = "321 Maple St";
  private static final int VALID_PREVIOUS_SERVICES_COUNT = 5;
  private static final boolean IS_MONTHLY = true;
  private static final boolean NOT_MONTHLY = false;

  @Test
  public void testCalculatePriceForSmallProperty() {
    // Small property, no special discounts
    GardeningService service = new GardeningService(TEST_ADDRESS, PropertySize.SMALL, NOT_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);
    double expectedPrice = 80 * 1 + 20;  // Base rate * 1 hour for small property + waste removal fee
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceForMediumProperty() {
    // Medium property, no special discounts
    GardeningService service = new GardeningService(TEST_ADDRESS, PropertySize.MEDIUM, NOT_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);
    double expectedPrice = 80 * 2 + 20;  // Base rate * 2 hours for medium property + waste removal fee
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceForLargeProperty() {
    // Large property, no special discounts
    GardeningService service = new GardeningService(TEST_ADDRESS, PropertySize.LARGE, NOT_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);
    double expectedPrice = 80 * 4 + 20;  // Base rate * 4 hours for large property + waste removal fee
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceFor10thServiceDiscount() {
    // 10th service should apply a 50% discount
    GardeningService service = new GardeningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, 9);
    double basePrice = 80 * 1 + 20;  // Base rate * 1 hour for small property + waste removal fee
    double expectedPrice = basePrice * 0.5;  // 50% discount for 10th service
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceForMonthlyServiceDiscount() {
    // Monthly service should apply a 10% discount
    GardeningService service = new GardeningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);
    double basePrice = 80 * 1 + 20;  // Base rate * 1 hour for small property + waste removal fee
    double expectedPrice = basePrice * 0.9;  // 10% discount for monthly service
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testEquals() {
    // Create two identical instances
    GardeningService service1 = new GardeningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);
    GardeningService service2 = new GardeningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);

    // Create instances with different values
    GardeningService serviceDifferentAddress = new GardeningService("123 Main St", PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);
    GardeningService serviceDifferentSize = new GardeningService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);
    GardeningService serviceDifferentMonthly = new GardeningService(TEST_ADDRESS, PropertySize.SMALL, NOT_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);

    // Test equality for identical instances
    assertEquals(service1, service2);

    // Test inequality for instances that differ in various fields
    assertNotEquals(service1, serviceDifferentAddress);
    assertNotEquals(service1, serviceDifferentSize);
    assertNotEquals(service1, serviceDifferentMonthly);
  }

  @Test
  public void testHashCode() {
    // Create two identical instances
    GardeningService service1 = new GardeningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);
    GardeningService service2 = new GardeningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);

    // Create instances with different values
    GardeningService serviceDifferentSize = new GardeningService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);
    GardeningService serviceDifferentMonthly = new GardeningService(TEST_ADDRESS, PropertySize.SMALL, NOT_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);

    // Test hashCode consistency for equal instances
    assertEquals(service1.hashCode(), service2.hashCode());

    // Test hashCode inequality for different instances
    assertNotEquals(service1.hashCode(), serviceDifferentSize.hashCode());
    assertNotEquals(service1.hashCode(), serviceDifferentMonthly.hashCode());
  }



  @Test
  public void testToString() {
    GardeningService service = new GardeningService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT);
    String expectedString = "GardeningService{propertyAddress='321 Maple St', propertySize=MEDIUM, monthly=true, " +
        "previousServicesCount=5, baseRate=80.0, wasteRemovalFee=20.0}";
    assertEquals(expectedString, service.toString());
  }
}
