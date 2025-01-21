package problem1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlumbingServiceTest {

  private static final String TEST_ADDRESS = "456 Elm St";
  private static final int VALID_PREVIOUS_SERVICES_COUNT = 7;
  private static final boolean IS_MONTHLY = true;

  @Test
  public void testCalculatePriceForStandardJob() {
    // Standard plumbing job with 2 licensed employees
    PlumbingService service = new PlumbingService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    double expectedPrice = 200 * 2 + 20;  // Base rate for 2 employees + permitting fee
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceForComplexJobWithMinimumEmployees() {
    // Complex plumbing job with 2 licensed employees (minimum for small/medium properties)
    PlumbingService service = new PlumbingService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, true);
    double expectedPrice = 200 * 2 + 20;  // Base rate for 2 employees + permitting fee
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceForComplexJobWithLargeProperty() {
    // Complex plumbing job for a large property should require at least 3 employees
    PlumbingService service = new PlumbingService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 1, true);
    double expectedPrice = 200 * 3 + 20;  // Base rate for 3 employees (minimum for large property) + permitting fee
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceWithMaximumEmployees() {
    // Plumbing job with 4 licensed employees
    PlumbingService service = new PlumbingService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 4, false);
    double expectedPrice = 200 * 4 + 20;  // Base rate for 4 employees + permitting fee
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testEquals() {
    // Create two identical instances
    PlumbingService service1 = new PlumbingService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    PlumbingService service2 = new PlumbingService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);

    // Create instances with different attributes
    PlumbingService serviceDifferentAddress = new PlumbingService("789 Oak St", PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    PlumbingService serviceDifferentSize = new PlumbingService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    PlumbingService serviceDifferentEmployees = new PlumbingService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 3, false);
    PlumbingService serviceDifferentComplexity = new PlumbingService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, true);

    // Test equality for identical instances
    assertEquals(service1, service2);

    // Test inequality for instances with different attributes
    assertNotEquals(service1, serviceDifferentAddress);
    assertNotEquals(service1, serviceDifferentSize);
    assertNotEquals(service1, serviceDifferentEmployees);
    assertNotEquals(service1, serviceDifferentComplexity);
  }

  @Test
  public void testHashCode() {
    // Create two identical instances
    PlumbingService service1 = new PlumbingService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    PlumbingService service2 = new PlumbingService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);

    // Create instances with different attributes
    PlumbingService serviceDifferentSize = new PlumbingService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    PlumbingService serviceDifferentEmployees = new PlumbingService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 3, false);

    // Test hashCode consistency for equal instances
    assertEquals(service1.hashCode(), service2.hashCode());

    // Test hashCode inequality for different instances
    assertNotEquals(service1.hashCode(), serviceDifferentSize.hashCode());
    assertNotEquals(service1.hashCode(), serviceDifferentEmployees.hashCode());
  }

  @Test
  public void testToString() {
    PlumbingService service = new PlumbingService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    String expectedString = "PlumbingService{propertyAddress='456 Elm St', propertySize=MEDIUM, monthly=true, " +
        "previousServicesCount=7, licensedEmployees=2, isComplex=false, permittingFee=20.0, baseRatePerEmployee=200.0}";
    assertEquals(expectedString, service.toString());
  }
}
