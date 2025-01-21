package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CommercialPropertyTest {
  private CommercialProperty commercialProperty;

  @BeforeEach
  void setUp() throws InvalidSizeException, InvalidAddressException {
    commercialProperty = new CommercialProperty("124 Industrial way", 1000, 10, true);

  }

  @Test
  void getAddress() {
    assertEquals("124 Industrial way", commercialProperty.getAddress(), "Address should match");

  }

  @Test
  void getSize() {
    assertEquals(1000, commercialProperty.getSize(), "Size should match the one in the constructor");

  }

  @Test
  void getOffices() {
    assertEquals(10, commercialProperty.getOffices(), "Number of offices should match the in the constructor");

  }

  @Test
  void isRetail() {
    assertTrue(commercialProperty.isRetail(), "Should return true for retail suitability");

  }
}
