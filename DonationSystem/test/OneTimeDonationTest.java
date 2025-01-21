package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OneTimeDonationTest {

  private OneTimeDonation donation1;
  private OneTimeDonation donation2;
  private OneTimeDonation donation3;
  private LocalDateTime date1;
  private LocalDateTime date2;

  @BeforeEach
  public void setUp() {
    date1 = LocalDateTime.of(2024, 10, 13, 12, 0);
    date2 = LocalDateTime.of(2023, 5, 1, 10, 30);
    donation1 = new OneTimeDonation(100.0, date1);
    donation2 = new OneTimeDonation(100.0, date1);
    donation3 = new OneTimeDonation(200.0, date2);
  }

  // Test constructor with valid input
  @Test
  public void testConstructorValid() {
    assertEquals(100.0, donation1.getAmount());
    assertEquals(date1, donation1.getCreationDate());
  }

  // Test constructor with invalid input
  @Test
  public void testConstructorInvalidAmount() {
    assertThrows(IllegalArgumentException.class, () -> {
      new OneTimeDonation(-10.0, LocalDateTime.now());
    });
  }

  // Test getDonationAmountForYear when the year matches
  @Test
  public void testGetDonationAmountForYearMatchingYear() {
    assertEquals(100.0, donation1.getDonationAmountForYear(2024));
  }

  // Test getDonationAmountForYear when the year does not match
  @Test
  public void testGetDonationAmountForYearNonMatchingYear() {
    assertEquals(0, donation1.getDonationAmountForYear(2023));
  }

  // Test equals method for true case
  @Test
  public void testEqualsSameObject() {
    assertEquals(donation1, donation2);
  }

  // Test equals method for false case
  @Test
  public void testEqualsDifferentObject() {
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
    assertNotEquals(donation1, "lolololololol");
  }

  // Test hashCode method
  @Test
  public void testHashCode() {
    assertEquals(donation1.hashCode(), donation2.hashCode());
    assertNotEquals(donation1.hashCode(), donation3.hashCode());
  }

  // Test toString method
  @Test
  public void testToString() {
    String expected = "OneTimeDonation{amount=100.0, creationDate=" + date1 + "}";
    assertEquals(expected, donation1.toString());
  }
}
