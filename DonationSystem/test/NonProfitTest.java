package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NonProfitTest {

  private NonProfit nonProfit1;
  private NonProfit nonProfit2;
  private NonProfit nonProfit3;
  private List<Donation> donations1;
  private List<Donation> donations2;
  private Donation donation1;
  private Donation donation2;

  @BeforeEach
  public void setUp() {
    donations1 = new ArrayList<>();
    donations2 = new ArrayList<>();

    donation1 = new OneTimeDonation(100.0, LocalDateTime.of(2024, 10, 13, 12, 0));
    donation2 = new MonthlyDonation(50.0, LocalDateTime.of(2023, 6, 1, 10, 30));

    donations1.add(donation1);
    donations2.add(donation2);

    nonProfit1 = new NonProfit("Helping Hands", donations1);
    nonProfit2 = new NonProfit("Helping Hands", donations1);
    nonProfit3 = new NonProfit("Global Aid", donations2);
  }

  // Test constructor with valid input
  @Test
  public void testConstructorValid() {
    assertEquals("Helping Hands", nonProfit1.getName());
    assertEquals(1, donations1.size());
  }

  // Test adding a donation
  @Test
  public void testAddDonation() {
    Donation newDonation = new OneTimeDonation(200.0, LocalDateTime.of(2024, 5, 10, 12, 0));
    nonProfit1.addDonation(newDonation);
    assertEquals(2, donations1.size());
    assertTrue(donations1.contains(newDonation));
  }

  // Test getTotalDonationsForYear when donations exist for the year
  @Test
  public void testGetTotalDonationsForYearWithDonations() {
    assertEquals(100.0, nonProfit1.getTotalDonationsForYear(2024));
  }

  // Test getTotalDonationsForYear when no donations exist for the year
  @Test
  public void testGetTotalDonationsForYearNoDonations() {
    assertEquals(0.0, nonProfit1.getTotalDonationsForYear(2023));
  }

  // Test equals method for true case
  @Test
  public void testEqualsSameObject() {
    assertEquals(nonProfit1, nonProfit1);
  }

  // Test equals method for objects with same data
  @Test
  public void testEqualsEqualObjects() {
    assertEquals(nonProfit1, nonProfit2);
  }

  // Test equals method for false case (different names)
  @Test
  public void testEqualsDifferentName() {
    assertNotEquals(nonProfit1, nonProfit3);
  }

  // Test equals method with null
  @Test
  public void testEqualsNull() {
    assertNotEquals(nonProfit1, null);
  }

  // Test equals method with different type
  @Test
  public void testEqualsDifferentType() {
    assertNotEquals(nonProfit1, "lolololol");
  }

  // Test hashCode method
  @Test
  public void testHashCode() {
    assertEquals(nonProfit1.hashCode(), nonProfit2.hashCode());
    assertNotEquals(nonProfit1.hashCode(), nonProfit3.hashCode());
  }

  // Test toString method
  @Test
  public void testToString() {
    String expected = "NonProfit{name='Helping Hands', donations=[OneTimeDonation{amount=100.0, creationDate=2024-10-13T12:00}]}";
    assertEquals(expected, nonProfit1.toString());
  }
}
