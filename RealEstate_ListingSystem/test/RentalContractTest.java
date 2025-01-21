package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalContractTest {

  private RentalContract rentalContract;

  @BeforeEach
  void setUp() throws InvalidAskingPriceException, InvalidTermInMonthsException {
    rentalContract = new RentalContract(2000.0, false, 12);
  }

  @Test
  void testGetAskingPrice() {
    assertEquals(2000.0, rentalContract.getAskingPrice());
  }

  @Test
  void testIsNegotiable() {
    assertFalse(rentalContract.isNegotiable());
  }

  @Test
  void testGetTermInMonths() {
    assertEquals(12, rentalContract.getTermInMonths());
  }

  @Test
  void testCalculateEarningsValidRate() throws InvalidCommissionRateException {
    double commissionRate = 0.05;
    double expectedEarnings = 2000.0 * commissionRate * 12;
    assertEquals(expectedEarnings, rentalContract.calculateEarnings(commissionRate));
  }

  @Test
  void testCalculateEarningsInvalidLowRate() {
    Exception exception = assertThrows(InvalidCommissionRateException.class, () -> {
      rentalContract.calculateEarnings(-0.1);
    });
    assertEquals("Commission rate must be between 0 and 1 inclusive.", exception.getMessage());
  }

  @Test
  void testCalculateEarningsInvalidHighRate() {
    Exception exception = assertThrows(InvalidCommissionRateException.class, () -> {
      rentalContract.calculateEarnings(1.1);
    });
    assertEquals("Commission rate must be between 0 and 1 inclusive.", exception.getMessage());
  }

  @Test
  void testConstructorInvalidAskingPrice() {
    Exception exception = assertThrows(InvalidAskingPriceException.class, () -> {
      new RentalContract(-2000.0, true, 12);
    });
    assertEquals("Asking price must be non-negative.", exception.getMessage());
  }

  @Test
  void testConstructorInvalidTermInMonths() {
    Exception exception = assertThrows(InvalidTermInMonthsException.class, () -> {
      new RentalContract(2000.0, true, 0);
    });
    assertEquals("Term in months must be greater than 0.", exception.getMessage());
  }

  @Test
  void testEqualsAndHashCode() throws InvalidAskingPriceException, InvalidTermInMonthsException {
    RentalContract sameContract = new RentalContract(2000.0, false, 12);
    RentalContract differentPrice = new RentalContract(1500.0, false, 12);
    RentalContract differentTerm = new RentalContract(2000.0, false, 6);

    assertEquals(rentalContract, sameContract, "Contracts with identical properties should be equal.");
    assertNotEquals(rentalContract, differentPrice, "Contracts with different asking prices should not be equal.");
    assertNotEquals(rentalContract, differentTerm, "Contracts with different terms should not be equal.");
    assertEquals(rentalContract.hashCode(), sameContract.hashCode(), "Equal contracts should have the same hash code.");
  }

  @Test
  void testToString() {
    String expectedString = "RentalContract{askingPrice=2000.0, isNegotiable=false, termInMonths=12}";
    assertEquals(expectedString, rentalContract.toString());
  }
}
