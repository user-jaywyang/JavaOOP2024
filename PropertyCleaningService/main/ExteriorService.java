package problem1;

/**
 * The ExteriorService class is an abstract class representing exterior property services.
 * It implements the Service interface and includes common attributes such as property address,
 * property size, service frequency, and the number of previous services.
 */
public abstract class ExteriorService implements Service {

  /** The base rate per hour for exterior services. */
  private static final double BASE_RATE_PER_HOUR = 80.0;

  /** The number of hours required for small properties. */
  private static final int SMALL_PROPERTY_HOURS = 1;

  /** The number of hours required for medium properties. */
  private static final int MEDIUM_PROPERTY_HOURS = 2;

  /** The number of hours required for large properties. */
  private static final int LARGE_PROPERTY_HOURS = 4;

  /** The address of the property where the service is provided. */
  private String propertyAddress;

  /** The size of the property (SMALL, MEDIUM, LARGE). */
  private PropertySize propertySize;

  /** Indicates whether the service is recurring on a monthly basis. */
  private boolean monthly;

  /** The number of previous services provided at the property address. */
  private int previousServicesCount;



  /**
   * Constructs an ExteriorService object with the specified property details.
   *
   * @param propertyAddress the address of the property where the service is being provided
   * @param propertySize the size of the property (SMALL, MEDIUM, LARGE)
   * @param monthly whether the service is a recurring monthly service
   * @param previousServicesCount the number of previous services provided at the address
   */
  public ExteriorService(String propertyAddress, PropertySize propertySize, boolean monthly, int previousServicesCount) {
    this.propertyAddress = propertyAddress;
    this.propertySize = propertySize;
    this.monthly = monthly;
    this.previousServicesCount = previousServicesCount;
  }

  /**
   * Returns the address of the property where the service is being provided.
   *
   * @return the property address
   */
  @Override
  public String getPropertyAddress() {
    return propertyAddress;
  }

  /**
   * Returns the size of the property (SMALL, MEDIUM, LARGE).
   *
   * @return the property size
   */
  @Override
  public PropertySize getPropertySize() {
    return propertySize;
  }

  /**
   * Returns whether the service is a recurring monthly service.
   *
   * @return true if the service is monthly, false otherwise
   */
  @Override
  public boolean isMonthly() {
    return monthly;
  }

  /**
   * Returns the number of previous services provided at the property address.
   *
   * @return the number of previous services
   */
  @Override
  public int getPreviousServicesCount() {
    return previousServicesCount;
  }

  /**
   * Returns the base rate per hour for exterior services.
   *
   * @return the base rate per hour
   */
  public double getBaseRate() {
    return BASE_RATE_PER_HOUR;
  }

  /**
   * Returns Minimum hours need for Small size property
   *
   * @return minimum hours for SMALL
   */
  public int getSmallPropertyHours() {
    return SMALL_PROPERTY_HOURS;
  }
  /**
   * Returns Minimum hours need for Medium size property
   *
   * @return minimum hours for MEDIUM
   */
  public int getMediumPropertyHours() {
    return MEDIUM_PROPERTY_HOURS;
  }

  /**
   * Returns Minimum hours need for Large size property
   *
   * @return minimum hours for LARGE
   */

  public int getLargePropertyHours() {
    return LARGE_PROPERTY_HOURS;

  }
  /**
   * Abstract method to calculate the total price for the service.
   *
   * @return the total price for the service
   */
  @Override
  public abstract double calculatePrice();
}
