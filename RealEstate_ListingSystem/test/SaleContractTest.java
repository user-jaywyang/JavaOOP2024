package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleContractTest {

  private SaleContract saleContract;

  @BeforeEach
  void setUp() throws InvalidAskingPriceException {
    saleContract = new SaleContract(500000.0, true);
  }

  @Test
  void testGetAskingPrice() {
    assertEquals(500000.0, saleContract.getAskingPrice());
  }

  @Test
  void testIsNegotiable() {
    assertTrue(saleContract.isNegotiable());
  }

  @Test
  void testCalculateEarningsValidRate() throws InvalidCommissionRateException {
    double commissionRate = 0.03;
    double expectedEarnings = 500000.0 * commissionRate;
    assertEquals(expectedEarnings, saleContract.calculateEarnings(commissionRate));
  }

  @Test
  void testCalculateEarningsInvalidLowRate() {
    Exception exception = assertThrows(InvalidCommissionRateException.class, () -> {
      saleContract.calculateEarnings(-0.1);
    });
    assertEquals("Commission rate must be between 0 and 1 inclusive.", exception.getMessage());
  }

  @Test
  void testCalculateEarningsInvalidHighRate() {
    Exception exception = assertThrows(InvalidCommissionRateException.class, () -> {
      saleContract.calculateEarnings(1.1);
    });
    assertEquals("Commission rate must be between 0 and 1 inclusive.", exception.getMessage());
  }

  @Test
  void testConstructorInvalidAskingPrice() {
    Exception exception = assertThrows(InvalidAskingPriceException.class, () -> {
      new SaleContract(-500000.0, true);
    });
    assertEquals("Asking price must be non-negative.", exception.getMessage());
  }

  @Test
  void testEqualsAndHashCode() throws InvalidAskingPriceException {
    SaleContract sameContract = new SaleContract(500000.0, true);
    SaleContract differentPrice = new SaleContract(300000.0, true);
    SaleContract differentNegotiable = new SaleContract(500000.0, false);

    assertEquals(saleContract, sameContract);
    assertNotEquals(saleContract, differentPrice);
    assertNotEquals(saleContract, differentNegotiable);
    assertEquals(saleContract.hashCode(), sameContract.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Contract{askingPrice=500000.0, isNegotiable=true}";
    assertEquals(expectedString, saleContract.toString());
  }
}
