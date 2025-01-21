package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonthlyDonationTest {

  private MonthlyDonation donation1;
  private MonthlyDonation donation2;
  private MonthlyDonation donation3;
  private LocalDateTime creationDate1;
  private LocalDateTime creationDate2;
  private LocalDateTime cancellationDate1;

  @BeforeEach
  public void setUp() {
    creationDate1 = LocalDateTime.of(2024, 1, 1, 12, 0);
    creationDate2 = LocalDateTime.of(2023, 6, 1, 10, 30);
    cancellationDate1 = LocalDateTime.of(2024, 8, 31, 12, 0);

    donation1 = new MonthlyDonation(100.0, creationDate1);
    donation2 = new MonthlyDonation(100.0, creationDate1, cancellationDate1);
    donation3 = new MonthlyDonation(200.0, creationDate2);
  }

  // Test constructor with valid input
  @Test
  public void testConstructorValid() {
    assertEquals(100.0, donation1.getAmount());
    assertEquals(creationDate1, donation1.getCreationDate());
  }

  // Test constructor with invalid input (negative amount)
  @Test
  public void testConstructorInvalidAmount() {
    assertThrows(IllegalArgumentException.class, () -> {
      new MonthlyDonation(-100.0, LocalDateTime.now());
    });
  }

  // Test getDonationAmountForYear when the donation was active for the full year
  @Test
  public void testGetDonationAmountForYearFullYear() {
    assertEquals(1200.0, donation1.getDonationAmountForYear(2024)); // 12 months * 100
  }

  // Test getDonationAmountForYear when the donation was canceled during the year
  @Test
  public void testGetDonationAmountForYearWithCancellation() {
    assertEquals(800.0, donation2.getDonationAmountForYear(2024)); // 8 months * 100
  }

  // Test getDonationAmountForYear when the donation starts in the middle of the year
  @Test
  public void testGetDonationAmountForYearStartingMidYear() {
    assertEquals(1400.0, donation3.getDonationAmountForYear(2023)); // 7 months * 200
  }

  // Test cancelDonation with valid input
  @Test
  public void testCancelDonationValid() {
    donation1.cancelDonation(LocalDateTime.of(2024, 7, 1, 12, 0));
    assertEquals(700.0, donation1.getDonationAmountForYear(2024)); // 7 months * 100
  }

  // Test cancelDonation with invalid input (cancellation before creation date)
  @Test
  public void testCancelDonationInvalid() {
    assertThrows(IllegalArgumentException.class, () -> {
      donation1.cancelDonation(LocalDateTime.of(2023, 12, 31, 12, 0));
    });
  }

  // Test equals method for true case
  @Test
  public void testEqualsSameObject() {
    assertEquals(donation1, donation1);
  }

  // Test equals method for objects with same data
  @Test
  public void testEqualsEqualObjects() {
    MonthlyDonation donationCopy = new MonthlyDonation(100.0, creationDate1);
    assertEquals(donation1, donationCopy);
  }

  // Test equals method for false case (different amount)
  @Test
  public void testEqualsDifferentAmount() {
    assertNotEquals(donation1, donation3);
  }

  // Test equals method with null
  @Test
  public void testEqualsNull() {
    assertNotEquals(donation1, null);
  }

  // Test equals method with different type
  @Test
  public void testEqualsDifferentType() {
    assertNotEquals(donation1, "lololololololo");
  }

  // Test hashCode method
  @Test
  public void testHashCode() {
    MonthlyDonation donationCopy = new MonthlyDonation(100.0, creationDate1);
    assertEquals(donation1.hashCode(), donationCopy.hashCode());
  }

  // Test toString method
  @Test
  public void testToString() {
    String expected = "MonthlyDonation{amount=100.0, creationDate=" + creationDate1 + ", cancellationDate=None}";
    assertEquals(expected, donation1.toString());
  }
}
