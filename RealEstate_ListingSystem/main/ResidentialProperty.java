package problem1;

/**
 * The ResidentialProperty class represents a type of property intended for residential use.
 * It includes attributes like number of bedrooms and bathrooms.
 */
public class ResidentialProperty extends Property<ResidentialProperty> {
  private int bedrooms;
  private double bathrooms;

  /**
   * Constructs a new residential property with address, size, number of bedrooms and bathrooms.
   *
   * @param address   The address of the residential property.
   * @param size      The size of the residential property.
   * @param bedrooms  The number of bedrooms in the residential property.
   * @param bathrooms The number of bathrooms in the residential property.
   * @throws InvalidSizeException if the size is invalid.
   * @throws InvalidAddressException if the address is invalid.
   */
  public ResidentialProperty(String address, int size, int bedrooms, double bathrooms)
      throws InvalidSizeException, InvalidAddressException {
    super(address, size);
    this.bedrooms = bedrooms;
    this.bathrooms = bathrooms;
  }

  /**
   * Returns the number of bedrooms in a residential property.
   *
   * @return The number of bedrooms.
   */
  public int getBedrooms() {
    return bedrooms;
  }

  /**
   * Returns the number of bathrooms in a residential property.
   *
   * @return The number of bathrooms.
   */
  public double getBathrooms() {
    return bathrooms;
  }
}
