package problem1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResidentialPropertyTest {
  private ResidentialProperty residentialPropertyTest;

  @BeforeEach
  void setUp() throws InvalidSizeException, InvalidAddressException {
    residentialPropertyTest = new ResidentialProperty("2017 Ruston way", 2000, 3, 2.5);

  }
  @Test
  void testGetAddress(){
    assertEquals("2017 Ruston way",residentialPropertyTest.getAddress(), "Address should match the constructor");
  }

  @Test
  void testGetSize () {
    assertEquals (2000,residentialPropertyTest.getSize(), "Size should match the one set in the constructor.");
  }

  @Test
  void testGetBedrooms () {
    assertEquals(3, residentialPropertyTest.getBedrooms(), "Bedrooms should match what's in the constructor.");
  }

  @Test
  void testGetBathrooms() {
    assertEquals(2.5, residentialPropertyTest.getBathrooms(),"Bathrooms should match what's in the constructor.");
  }
}
