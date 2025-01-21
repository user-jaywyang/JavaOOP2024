package problem1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CleaningServiceTest {

  private static final String TEST_ADDRESS = "123 Main St";
  private static final boolean IS_MONTHLY = true;
  private static final int VALID_PREVIOUS_SERVICES_COUNT = 5;
  private static final int PETS_COUNT_ONE = 1;
  private static final int PETS_COUNT_THREE = 3;

  @Test
  void testCalculatePrice_SmallPropertyWithOnePet() throws UnknownPropertySizeException{
    CleaningService service = new CleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, PETS_COUNT_ONE);
    double expectedPrice = 80.0 * 1; // Base rate per hour * hours for small property
    expectedPrice += expectedPrice * 0.05; // 5% surcharge for 1-2 pets
    expectedPrice *= 0.9; // 10% monthly discount
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  void testCalculatePrice_LargePropertyWithThreePets() throws UnknownPropertySizeException{
    CleaningService service = new CleaningService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, PETS_COUNT_THREE);
    double expectedPrice = 80.0 * 4; // Base rate per hour * hours for large property
    expectedPrice += expectedPrice * 0.07; // 7% surcharge for 3 or more pets
    expectedPrice *= 0.9; // 10% monthly discount
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  void testCalculatePrice_TenthServiceDiscount() throws UnknownPropertySizeException{
    CleaningService service = new CleaningService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, 9, PETS_COUNT_ONE); // 9 previous services, this is the 10th
    double expectedPrice = 80.0 * 2; // Base rate per hour * hours for medium property
    expectedPrice += expectedPrice * 0.05; // 5% surcharge for 1-2 pets
    expectedPrice *= 0.5; // 50% discount for 10th service
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  void testEquals() {
    // Create two identical instances
    CleaningService service1 = new CleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, PETS_COUNT_ONE);
    CleaningService service2 = new CleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, PETS_COUNT_ONE);

    // Create instances with different attributes
    CleaningService serviceDifferentAddress = new CleaningService("456 Maple St", PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, PETS_COUNT_ONE);
    CleaningService serviceDifferentSize = new CleaningService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, PETS_COUNT_ONE);
    CleaningService serviceDifferentPets = new CleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, PETS_COUNT_THREE);

    // Test equality for identical instances
    assertEquals(service1, service2);  // Should be equal

    // Test inequality for different instances
    assertNotEquals(service1, serviceDifferentAddress);  // Different address
    assertNotEquals(service1, serviceDifferentSize);     // Different property size
    assertNotEquals(service1, serviceDifferentPets);     // Different pets count
  }

  @Test
  void testHashCode() {
    // Create two identical instances
    CleaningService service1 = new CleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, PETS_COUNT_ONE);
    CleaningService service2 = new CleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, PETS_COUNT_ONE);

    // Create instances with different attributes
    CleaningService serviceDifferentSize = new CleaningService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, PETS_COUNT_ONE);
    CleaningService serviceDifferentPets = new CleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, PETS_COUNT_THREE);

    // Test hashCode consistency for equal instances
    assertEquals(service1.hashCode(), service2.hashCode());  // Equal objects should have equal hashCodes

    // Test hashCode inequality for different instances
    assertNotEquals(service1.hashCode(), serviceDifferentSize.hashCode());  // Different sizes should result in different hashCodes
    assertNotEquals(service1.hashCode(), serviceDifferentPets.hashCode());  // Different pets count should result in different hashCodes
  }

  @Test
  void testToString() {
    CleaningService service = new CleaningService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, PETS_COUNT_ONE);
    String expectedString = "CleaningService{" +
        "propertyAddress='" + TEST_ADDRESS + '\'' +
        ", propertySize=" + PropertySize.SMALL +
        ", monthly=" + IS_MONTHLY +
        ", previousServicesCount=" + VALID_PREVIOUS_SERVICES_COUNT +
        ", petsCount=" + PETS_COUNT_ONE +
        ", baseRatePerHour=" + 80.0 +
        ", petSurcharge1_2=" + 0.05 +
        ", petSurcharge3Plus=" + 0.07 +
        '}';
    assertEquals(expectedString, service.toString());
  }

  @Test
  void testIllegalArgumentExceptionForUnknownPropertySize() {
    CleaningService service = new CleaningService(TEST_ADDRESS, null, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, PETS_COUNT_ONE);

    // Using a lambda expression instead of a method reference
    assertThrows(UnknownPropertySizeException.class, () -> {
      service.calculatePrice();
    });
  }

}
