package problem1;

/**
 * The SpecialistService class represents a specialized service that requires licensed employees
 * and follows specific rules based on the complexity of the work and the size of the property.
 * This is an abstract class that implements the Service interface.
 */
public abstract class SpecialistService implements Service {

  /** The base rate per licensed employee for specialist services. */
  private static final double BASE_RATE_PER_EMPLOYEE = 200.0;

  /** The minimum number of licensed employees required for any service. */
  private static final int MIN_LICENSED_EMPLOYEES = 1;

  /** The minimum number of employees required for complex small or medium-sized jobs. */
  private static final int COMPLEX_SMALL_MEDIUM_EMPLOYEES = 2;

  /** The minimum number of employees required for complex large-sized jobs. */
  private static final int COMPLEX_LARGE_EMPLOYEES = 3;

  private String propertyAddress;
  private PropertySize propertySize;
  private boolean monthly;
  private int previousServicesCount;
  private int licensedEmployees;
  private boolean isComplex;

  /**
   * Constructs a new SpecialistService with the given property details, number of licensed employees,
   * and complexity status. The number of licensed employees is validated so it meets the minimum
   * requirements based on complexity and property size.
   *
   * @param propertyAddress        the address of the property where the service is being provided
   * @param propertySize           the size of the property (SMALL, MEDIUM, LARGE)
   * @param monthly                whether the service is a monthly recurring service
   * @param previousServicesCount  the number of previous services provided at the address
   * @param licensedEmployees      the number of licensed employees assigned to the service
   * @param isComplex              whether the work being performed is considered complex
   */
  public SpecialistService(String propertyAddress, PropertySize propertySize, boolean monthly,
      int previousServicesCount, int licensedEmployees, boolean isComplex) {
    this.propertyAddress = propertyAddress;
    this.propertySize = propertySize;
    this.monthly = monthly;
    this.previousServicesCount = previousServicesCount;
    this.isComplex = isComplex;

    // Call helper methods to initialize licensed employees and apply complexity rules
    this.licensedEmployees = validateLicensedEmployees(licensedEmployees);
    applyComplexityRules();
  }

  /**
   * Validates the number of licensed employees. Ensures that at least one licensed employee is assigned.
   *
   * @param licensedEmployees the number of licensed employees assigned to the service
   * @return the valid number of licensed employees
   */
  private int validateLicensedEmployees(int licensedEmployees) {
    if (licensedEmployees < MIN_LICENSED_EMPLOYEES) {
      return MIN_LICENSED_EMPLOYEES;  // At least one licensed employee is required
    }
    return licensedEmployees;
  }

  /**
   * Applies rules to adjust the number of licensed employees based on the complexity of the work
   * and the size of the property.
   */
  private void applyComplexityRules() {
    if (isComplex) {
      switch (propertySize) {
        case SMALL:
        case MEDIUM:
          this.licensedEmployees = Math.max(this.licensedEmployees, COMPLEX_SMALL_MEDIUM_EMPLOYEES);
          break;
        case LARGE:
          this.licensedEmployees = Math.max(this.licensedEmployees, COMPLEX_LARGE_EMPLOYEES);
          break;
      }
    }
  }

  /**
   * Gets the address of the property.
   *
   * @return the property address
   */
  @Override
  public String getPropertyAddress() {
    return propertyAddress;
  }

  /**
   * Gets the size of the property.
   *
   * @return the property size (SMALL, MEDIUM, LARGE)
   */
  @Override
  public PropertySize getPropertySize() {
    return propertySize;
  }

  /**
   * Checks if the service is a monthly recurring service.
   *
   * @return true if the service is monthly, false otherwise
   */
  @Override
  public boolean isMonthly() {
    return monthly;
  }

  /**
   * Gets the number of previous services provided at the property.
   *
   * @return the number of previous services
   */
  @Override
  public int getPreviousServicesCount() {
    return previousServicesCount;
  }

  /**
   * Gets the number of licensed employees assigned to the service.
   *
   * @return the number of licensed employees
   */
  public int getLicensedEmployees() {
    return licensedEmployees;
  }

  /**
   * Checks if the work being performed is complex.
   *
   * @return true if the work is complex
   */
  public boolean isComplex() {
    return isComplex;
  }

  /**
   * Returns the base rate per employee for specialist services.
   *
   * @return the base rate per employee
   */
  public double getBaseRate() {
    return BASE_RATE_PER_EMPLOYEE;
  }
  /**
   * Abstract method to calculate the price of the service.
   *
   * @return the price of the service
   */
  @Override
  public abstract double calculatePrice();
}
