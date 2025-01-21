package problem1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElectricalServiceTest {

  private static final String TEST_ADDRESS = "123 Main St";
  private static final int VALID_PREVIOUS_SERVICES_COUNT = 5;
  private static final boolean IS_MONTHLY = false;

  @Test
  public void testCalculatePriceForStandardJob() throws InvalidElectricalEmployeeException{
    // Standard job with 2 licensed employees
    ElectricalService service = new ElectricalService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    double expectedPrice = 200 * 2 + 50;  // Base rate for 2 employees + permitting fee
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceForComplexJobWithMinimumEmployees()throws InvalidElectricalEmployeeException{
    // Complex job with 2 licensed employees (which is the minimum for small/medium properties)
    ElectricalService service = new ElectricalService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, true);
    double expectedPrice = 200 * 2 + 50;  // Base rate for 2 employees + permitting fee
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceForComplexJobWithLargeProperty() throws InvalidElectricalEmployeeException {
    // Complex job for a large property should require at least 3 employees
    ElectricalService service = new ElectricalService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 1, true);
    double expectedPrice = 200 * 3 + 50;  // Base rate for 3 employees (minimum for large) + permitting fee
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceWithMaximumEmployees() throws InvalidElectricalEmployeeException {
    // Job with the maximum allowed number of licensed employees (4)
    ElectricalService service = new ElectricalService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 4, false);
    double expectedPrice = 200 * 4 + 50;  // Base rate for 4 employees + permitting fee
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testValidateLicensedEmployeesThrowsException() {
    // Attempting to create a service with more than 4 licensed employees should throw an exception
    assertThrows(InvalidElectricalEmployeeException.class, () -> {
      new ElectricalService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 5, false);
    });
  }

  @Test
  public void testEquals() throws InvalidElectricalEmployeeException {
    // Create two identical instances
    ElectricalService service1 = new ElectricalService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    ElectricalService service2 = new ElectricalService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);

    // Create instances with different values
    ElectricalService serviceDifferentAddress = new ElectricalService("456 Elm St", PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    ElectricalService serviceDifferentSize = new ElectricalService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    ElectricalService serviceDifferentEmployees = new ElectricalService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 3, false);
    ElectricalService serviceDifferentComplexity = new ElectricalService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, true);

    // Test equality for identical instances
    assertEquals(service1, service2);

    // Test inequality for instances that differ in various fields
    assertNotEquals(service1, serviceDifferentAddress);
    assertNotEquals(service1, serviceDifferentSize);
    assertNotEquals(service1, serviceDifferentEmployees);
    assertNotEquals(service1, serviceDifferentComplexity);
  }

  @Test
  public void testHashCode() throws InvalidElectricalEmployeeException {
    // Create two identical instances
    ElectricalService service1 = new ElectricalService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    ElectricalService service2 = new ElectricalService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);

    // Create instances with different values
    ElectricalService serviceDifferentSize = new ElectricalService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    ElectricalService serviceDifferentEmployees = new ElectricalService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 3, false);

    // Test hashCode consistency for equal instances
    assertEquals(service1.hashCode(), service2.hashCode());

    // Test hashCode inequality for different instances
    assertNotEquals(service1.hashCode(), serviceDifferentSize.hashCode());
    assertNotEquals(service1.hashCode(), serviceDifferentEmployees.hashCode());
  }


  @Test
  public void testToString() throws InvalidElectricalEmployeeException {
    ElectricalService service = new ElectricalService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2, false);
    String expectedString = "ElectricalService{propertyAddress='123 Main St', propertySize=MEDIUM, monthly=false, " +
        "previousServicesCount=5, licensedEmployees=2, isComplex=false, permittingFee=50.0, baseRatePerEmployee=200.0}";
    assertEquals(expectedString, service.toString());
  }
}
