package problem1;

/**
 * The Property class represents a property listed, with the attributes address and size of property.
 *
 * @param <T> A generic parameter representing a property type.
 */
public abstract class Property<T>{
  protected String address;
  protected int size;

  /** A constant representing the value zero, used for comparisons and calculations. */
  private static final int ZERO = 0;


  /**
   * Constructs a property object with the address and size.
   * Checks if an address is empty or if a property size is negative and returns an
   * exception with a message.
   *
   * @param address The property address.
   * @param size The size of the property.
   */
  public Property(String address, int size) throws InvalidAddressException, InvalidSizeException {
    if (address == null || address.isEmpty()){
      throw new InvalidAddressException ("Address cannot be null or empty");
    }
    if (size < ZERO){
      throw new InvalidSizeException ("Size cannot be negative");
    }
    this.address = address;
    this.size = size;
  }

  /**
   * Returns the address of the property.
   * @return The address of the property.
   */
  public String getAddress(){
    return address;
  }

  /**
   * Returns the size of the property.
   * @return The size of the property.
   */
  public int getSize(){
    return size;
  }

}
