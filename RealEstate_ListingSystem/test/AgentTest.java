package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgentTest {

  private Agent<ResidentialProperty, SaleContract> residentialSaleAgent;
  private Agent<CommercialProperty, RentalContract> commercialRentalAgent;
  private ResidentialProperty residentialProperty;
  private CommercialProperty commercialProperty;
  private SaleContract saleContract;
  private RentalContract rentalContract;
  private Listing<ResidentialProperty, SaleContract> residentialSaleListing;
  private Listing<CommercialProperty, RentalContract> commercialRentalListing;

  @BeforeEach
  void setUp()
      throws InvalidCommissionRateException, InvalidSizeException, InvalidAddressException, InvalidAskingPriceException, InvalidTermInMonthsException {
    residentialSaleAgent = new Agent<>("Alice", 0.05);
    commercialRentalAgent = new Agent<>("Bob", 0.03);

    residentialProperty = new ResidentialProperty("123 Main St", 1500, 3, 2.5);
    commercialProperty = new CommercialProperty("456 Commerce Blvd", 3000, 5, true);

    saleContract = new SaleContract(400000.0, true);
    rentalContract = new RentalContract(2000.0, false, 12);

    residentialSaleListing = new Listing<>(residentialProperty, saleContract);
    commercialRentalListing = new Listing<>(commercialProperty, rentalContract);
  }

  @Test
  void testAddListing() throws InvalidCommissionRateException {
    residentialSaleAgent.addListing(residentialSaleListing);
    assertEquals(saleContract.calculateEarnings(0.05), residentialSaleAgent.getTotalPortfolioValue());
  }

  @Test
  void testCompleteListing() throws NoListingException, InvalidCommissionRateException {
    residentialSaleAgent.addListing(residentialSaleListing);
    residentialSaleAgent.completeListing(residentialSaleListing);
    double expectedEarnings = saleContract.calculateEarnings(0.05);
    assertEquals(expectedEarnings, residentialSaleAgent.getTotalEarnings());
    assertEquals(0, residentialSaleAgent.getTotalPortfolioValue());
  }

  @Test
  void testCompleteListingNotFound() {
    Exception exception = assertThrows(NoListingException.class, () -> {
      residentialSaleAgent.completeListing(residentialSaleListing);
    });
    assertEquals("Listing not found in agent's collection.", exception.getMessage());
  }

  @Test
  void testDropListing() throws NoListingException, InvalidCommissionRateException {
    residentialSaleAgent.addListing(residentialSaleListing);
    residentialSaleAgent.dropListing(residentialSaleListing);
    assertEquals(0, residentialSaleAgent.getTotalPortfolioValue());
    assertEquals(0, residentialSaleAgent.getTotalEarnings());
  }

  @Test
  void testDropListingNotFound() {
    Exception exception = assertThrows(NoListingException.class, () -> {
      residentialSaleAgent.dropListing(residentialSaleListing);
    });
    assertEquals("Listing not found in agent's collection.", exception.getMessage());
  }

  @Test
  void testGetTotalPortfolioValue() throws InvalidCommissionRateException {
    residentialSaleAgent.addListing(residentialSaleListing);
    commercialRentalAgent.addListing(commercialRentalListing);
    double residentialValue = saleContract.calculateEarnings(0.05);
    double commercialValue = rentalContract.calculateEarnings(0.03);
    assertEquals(residentialValue, residentialSaleAgent.getTotalPortfolioValue());
    assertEquals(commercialValue, commercialRentalAgent.getTotalPortfolioValue());
  }

  @Test
  void testGetName() {
    assertEquals("Alice", residentialSaleAgent.getName());
    assertEquals("Bob", commercialRentalAgent.getName());
  }

  @Test
  void testGetTotalEarnings() throws NoListingException, InvalidCommissionRateException {
    assertEquals(0.0, residentialSaleAgent.getTotalEarnings());
    residentialSaleAgent.addListing(residentialSaleListing);
    residentialSaleAgent.completeListing(residentialSaleListing);
    assertEquals(saleContract.calculateEarnings(0.05), residentialSaleAgent.getTotalEarnings());
  }

  @Test
  void testGetCommissionRate() {
    assertEquals(0.05, residentialSaleAgent.getCommissionRate());
    assertEquals(0.03, commercialRentalAgent.getCommissionRate());
  }

  @Test
  void testEqualsAndHashCode() throws InvalidCommissionRateException {
    Agent<ResidentialProperty, SaleContract> sameAgent = new Agent<>("Alice", 0.05);
    Agent<ResidentialProperty, SaleContract> differentName = new Agent<>("Charlie", 0.05);
    Agent<ResidentialProperty, SaleContract> differentRate = new Agent<>("Alice", 0.07);

    assertEquals(residentialSaleAgent, sameAgent);
    assertNotEquals(residentialSaleAgent, differentName);
    assertNotEquals(residentialSaleAgent, differentRate);
    assertEquals(residentialSaleAgent.hashCode(), sameAgent.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Agent{name='Alice', commissionRate=0.05, totalEarnings=0.0, listings=[]}";
    assertEquals(expectedString, residentialSaleAgent.toString());
  }

  @Test
  void testConstructorInvalidCommissionRateLow() {
    Exception exception = assertThrows(InvalidCommissionRateException.class, () -> {
      new Agent<>("Invalid", -0.1);
    });
    assertEquals("Commission rate must be between 0 and 1 inclusive.", exception.getMessage());
  }

  @Test
  void testConstructorInvalidCommissionRateHigh() {
    Exception exception = assertThrows(InvalidCommissionRateException.class, () -> {
      new Agent<>("Invalid", 1.1);
    });
    assertEquals("Commission rate must be between 0 and 1 inclusive.", exception.getMessage());
  }
}
