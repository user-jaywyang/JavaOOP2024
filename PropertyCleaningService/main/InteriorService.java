package problem1;

/**
 * The InteriorService class represents a base class for interior services such as cleaning, painting, etc.
 * It implements the Service interface and provides additional attributes specific to interior services.
 *
 */
public abstract class InteriorService implements Service {

  /** The base rate per hour for exterior services. */
  private static final double BASE_RATE_PER_HOUR = 80.0;

  /** The surcharge rate for 1-2 pets. */
  private static final double PET_SURCHARGE_1_2 = 0.05;

  /** The surcharge rate for 3 or more pets. */
  private static final double PET_SURCHARGE_3_PLUS = 0.07;

  /** The discount rate for every 10th service provided. */
  private static final double DISCOUNT_10TH_SERVICE = 0.5;

  /** The discount rate for monthly services. */
  private static final double DISCOUNT_MONTHLY_SERVICE = 0.9;


  /** The address of the property where the service is being provided. */
  private String propertyAddress;

  /** The size of the property (SMALL, MEDIUM, LARGE). */
  private PropertySize propertySize;

  /** Indicates whether the service is provided on a monthly basis. */
  private boolean monthly;

  /** The number of previous services provided at the property. */
  private int previousServicesCount;

  /** The number of pets in the property, which can influence the surcharge applied. */
  private int petsCount;

  /**
   * Constructs a new InteriorService instance with the specified property details and number of pets.
   *
   * @param propertyAddress the address of the property
   * @param propertySize the size of the property
   * @param monthly whether the service is a recurring monthly service
   * @param previousServicesCount the number of previous services provided at the property
   * @param petsCount the number of pets in the property
   */
  public InteriorService(String propertyAddress, PropertySize propertySize, boolean monthly, int previousServicesCount, int petsCount) {
    this.propertyAddress = propertyAddress;
    this.propertySize = propertySize;
    this.monthly = monthly;
    this.previousServicesCount = previousServicesCount;
    this.petsCount = petsCount;
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
   * Returns the size of the property.
   *
   * @return the property size
   */
  @Override
  public PropertySize getPropertySize() {
    return propertySize;
  }

  /**
   * Returns whether the service is provided on a monthly basis.
   *
   * @return true if the service is monthly, false otherwise
   */
  @Override
  public boolean isMonthly() {
    return monthly;
  }

  /**
   * Returns the number of previous services provided at the property.
   *
   * @return the number of previous services
   */
  @Override
  public int getPreviousServicesCount() {
    return previousServicesCount;
  }

  /**
   * Returns the number of pets at the property.
   *
   * @return the number of pets
   */
  public int getPetsCount() {
    return petsCount;
  }

  /**
   * Returns the surcharge rate applied for 1-2 pets.
   *
   * @return the surcharge rate for 1-2 pets
   */
  public double getPetSurchargeFor1To2Pets() {
    return PET_SURCHARGE_1_2;
  }

  /**
   * Returns the surcharge rate applied for 3 or more pets.
   *
   * @return the surcharge rate for 3+ pets
   */
  public double getPetSurchargeFor3PlusPets() {
    return PET_SURCHARGE_3_PLUS;
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
   * Returns Discount rate for monthly reoccurring service
   *
   * @return monthly discount rate
   */
  public double getMonthlyDiscount() {
    return DISCOUNT_MONTHLY_SERVICE;
  }
  /**
   * Returns Discount rate for 10th service
   *
   * @return 10th service discount rate
   */
  public double get10thDiscount() {
    return DISCOUNT_10TH_SERVICE;
  }


  /**
   * Calculates the price of the service. This method must be implemented by subclasses.
   *
   * @return the price of the service
   */
  @Override
  public abstract double calculatePrice();
}
