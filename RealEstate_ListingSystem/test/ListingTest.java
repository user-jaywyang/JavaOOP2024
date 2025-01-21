package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListingTest {
  private ResidentialProperty testProperty;
  private SaleContract testContract;
  private Listing <ResidentialProperty, SaleContract> listing;

  @BeforeEach
  void setUp() throws InvalidSizeException, InvalidAddressException, InvalidAskingPriceException {
    testProperty = new ResidentialProperty("2017 Ruston St", 1500, 3, 2.5);
    testContract = new SaleContract (350000, true);

    listing = new Listing<>(testProperty, testContract);
  }

  @Test
  void testGetProperty() {
    assertEquals(testProperty, listing.getProperty(), "The property should match the listing.");
  }

  @Test
  void testGetContract() {
    assertEquals(testContract, listing.getContract(),"The contract should match the listing.");
  }
}
