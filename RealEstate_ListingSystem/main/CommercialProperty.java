package problem1;

/**
 * The CommercialProperty class represents a type of property intended for commercial use.
 * It includes specific attributes like the number of offices and suitability for retail use.
 */
public class CommercialProperty extends Property<CommercialProperty> {
  private int offices;
  private boolean isRetail;

  /**
   * Constructs a new CommercialProperty with the specified address, size, number of
   * offices, and retail suitability.
   *
   * @param address  The address of the commercial property.
   * @param size     The size of the commercial property in square feet.
   * @param offices  The number of office spaces available in the property.
   * @param isRetail A boolean indicating if the property is suitable for retail use.
   * @throws InvalidSizeException if the size is invalid.
   * @throws InvalidAddressException if the address is invalid.
   */
  public CommercialProperty(String address, int size, int offices, boolean isRetail)
      throws InvalidSizeException, InvalidAddressException {
    super(address, size);
    this.offices = offices;
    this.isRetail = isRetail;
  }

  /**
   * Returns the number of office spaces available in this commercial property.
   *
   * @return The number of offices.
   */
  public int getOffices() {
    return offices;
  }

  /**
   * Returns whether the commercial property is suitable for retail use.
   *
   * @return True if the property is suitable for retail use, false otherwise.
   */
  public boolean isRetail() {
    return isRetail;
  }
}
