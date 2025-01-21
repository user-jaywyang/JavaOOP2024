package problem1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaintingServiceTest {

  private static final String TEST_ADDRESS = "555 Pine St";
  private static final int VALID_PREVIOUS_SERVICES_COUNT = 5;
  private static final boolean IS_MONTHLY = true;
  private static final boolean NOT_MONTHLY = false;


  @Test
  public void testCalculatePriceForSmallPropertyWithOnePet() {
    // Small property, 1 pet
    PaintingService service = new PaintingService(TEST_ADDRESS, PropertySize.SMALL, NOT_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 1);
    double basePrice = 80 * 16;  // Base rate * 16 hours for small property
    double expectedPrice = basePrice + basePrice * 0.05;  // Adding 5% surcharge for 1 pet
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceForMediumPropertyWithTwoPets() {
    // Medium property, 2 pets
    PaintingService service = new PaintingService(TEST_ADDRESS, PropertySize.MEDIUM, NOT_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);
    double basePrice = 80 * 16;  // Base rate * 16 hours for medium property
    double expectedPrice = basePrice + basePrice * 0.05;  // Adding 5% surcharge for 2 pets
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceForLargePropertyWithThreePets() {
    // Large property, 3 pets
    PaintingService service = new PaintingService(TEST_ADDRESS, PropertySize.LARGE, NOT_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 3);
    double basePrice = 80 * 24;  // Base rate * 24 hours for large property
    double expectedPrice = basePrice + basePrice * 0.07;  // Adding 7% surcharge for 3 pets
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceWith10thServiceDiscount() {
    // 10th service should apply a 50% discount
    PaintingService service = new PaintingService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, 9, 1);
    double basePrice = 80 * 16;  // Base rate * 16 hours for small property
    double expectedPrice = 672;  // 50% discount for 10th service
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testCalculatePriceForMonthlyServiceDiscount() {
    // Monthly service should apply a 10% discount (for non-10th service)
    PaintingService service = new PaintingService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 1);
    double basePrice = 80 * 16;  // Base rate * 16 hours for medium property
    double expectedPrice = 1209.6;  // 10% discount for monthly service
    assertEquals(expectedPrice, service.calculatePrice(), 0.01);
  }

  @Test
  public void testEquals() {
    // Create two identical instances
    PaintingService service1 = new PaintingService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);
    PaintingService service2 = new PaintingService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);

    // Create instances with different attributes
    PaintingService serviceDifferentAddress = new PaintingService("123 Maple St", PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);
    PaintingService serviceDifferentSize = new PaintingService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);
    PaintingService serviceDifferentPets = new PaintingService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 3);

    // Test equality for identical instances
    assertEquals(service1, service2);

    // Test inequality for instances with different attributes
    assertNotEquals(service1, serviceDifferentAddress);
    assertNotEquals(service1, serviceDifferentSize);
    assertNotEquals(service1, serviceDifferentPets);
  }

  @Test
  public void testHashCode() {
    // Create two identical instances
    PaintingService service1 = new PaintingService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);
    PaintingService service2 = new PaintingService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);

    // Create instances with different attributes
    PaintingService serviceDifferentSize = new PaintingService(TEST_ADDRESS, PropertySize.LARGE, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 2);
    PaintingService serviceDifferentPets = new PaintingService(TEST_ADDRESS, PropertySize.SMALL, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 3);

    // Test hashCode consistency for equal instances
    assertEquals(service1.hashCode(), service2.hashCode());

    // Test hashCode inequality for different instances
    assertNotEquals(service1.hashCode(), serviceDifferentSize.hashCode());
    assertNotEquals(service1.hashCode(), serviceDifferentPets.hashCode());
  }


  @Test
  public void testToString() {
    PaintingService service = new PaintingService(TEST_ADDRESS, PropertySize.MEDIUM, IS_MONTHLY, VALID_PREVIOUS_SERVICES_COUNT, 1);
    String expectedString = "PaintingService{propertyAddress='555 Pine St', propertySize=MEDIUM, monthly=true, " +
        "previousServicesCount=5, petsCount=1, baseRatePerHour=80.0, petSurcharge1_2=0.05, " +
        "petSurcharge3Plus=0.07}";
    assertEquals(expectedString, service.toString());
  }
}
