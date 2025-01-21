package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PledgeDonationTest {

  private PledgeDonation pledge1;
  private PledgeDonation pledge2;
  private PledgeDonation pledge3;
  private LocalDateTime creationDate1;
  private LocalDateTime creationDate2;
  private LocalDateTime processingDate1;

  @BeforeEach
  public void setUp() {
    creationDate1 = LocalDateTime.of(2024, 1, 1, 12, 0);
    creationDate2 = LocalDateTime.of(2023, 6, 1, 10, 30);
    processingDate1 = LocalDateTime.of(2024, 8, 1, 12, 0);

    pledge1 = new PledgeDonation(200.0, creationDate1);
    pledge2 = new PledgeDonation(200.0, creationDate1, processingDate1);
    pledge3 = new PledgeDonation(300.0, creationDate2);
  }

  // Test constructor with valid input
  @Test
  public void testConstructorValid() {
    assertEquals(200.0, pledge1.getAmount());
    assertEquals(creationDate1, pledge1.getCreationDate());
  }

  // Test constructor with invalid input (negative amount)
  @Test
  public void testConstructorInvalidAmount() {
    assertThrows(IllegalArgumentException.class, () -> {
      new PledgeDonation(-200.0, LocalDateTime.now());
    });
  }

  // Test constructor with invalid processing date (before creation date)
  @Test
  public void testConstructorInvalidProcessingDate() {
    assertThrows(IllegalArgumentException.class, () -> {
      new PledgeDonation(200.0, creationDate1, creationDate1.minusDays(1));
    });
  }

  // Test getDonationAmountForYear when the pledge is processed in the specified year
  @Test
  public void testGetDonationAmountForYearProcessed() {
    assertEquals(200.0, pledge2.getDonationAmountForYear(2024));
  }

  // Test getDonationAmountForYear when the pledge is not processed in the specified year
  @Test
  public void testGetDonationAmountForYearNotProcessed() {
    assertEquals(0, pledge1.getDonationAmountForYear(2023));
  }

  // Test setProcessingDate with valid input
  @Test
  public void testSetProcessingDateValid() {
    pledge1.setProcessingDate(LocalDateTime.of(2025, 1, 1, 12, 0));
    assertEquals(200.0, pledge1.getDonationAmountForYear(2025));
  }

  // Test setProcessingDate with invalid input (before creation date)
  @Test
  public void testSetProcessingDateInvalid() {
    assertThrows(IllegalArgumentException.class, () -> {
      pledge1.setProcessingDate(creationDate1.minusDays(1));
    });
  }

  // Test removeProcessingDate
  @Test
  public void testRemoveProcessingDate() {
    pledge2.removeProcessingDate();
    assertEquals(0, pledge2.getDonationAmountForYear(2024));
  }

  // Test equals method for true case
  @Test
  public void testEqualsSameObject() {
    assertEquals(pledge1, pledge1);
  }

  // Test equals method for objects with same data
  @Test
  public void testEqualsEqualObjects() {
    PledgeDonation pledgeCopy = new PledgeDonation(200.0, creationDate1);
    assertEquals(pledge1, pledgeCopy);
  }

  // Test equals method for false case (different amount)
  @Test
  public void testEqualsDifferentAmount() {
    assertNotEquals(pledge1, pledge3);
  }

  // Test equals method with null
  @Test
  public void testEqualsNull() {
    assertNotEquals(pledge1, null);
  }

  // Test equals method with different type
  @Test
  public void testEqualsDifferentType() {
    assertNotEquals(pledge1, "lolololololololo");
  }

  // Test hashCode method
  @Test
  public void testHashCode() {
    PledgeDonation pledgeCopy = new PledgeDonation(200.0, creationDate1);
    assertEquals(pledge1.hashCode(), pledgeCopy.hashCode());
  }

  // Test toString method
  @Test
  public void testToString() {
    String expected = "PledgeDonation{amount=200.0, creationDate=" + creationDate1 + ", processingDate=None}";
    assertEquals(expected, pledge1.toString());
  }
}
