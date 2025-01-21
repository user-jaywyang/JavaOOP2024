package problem1;

import java.util.Objects;

/**
 * The PlumbingService class represents a specialized service for plumbing work.
 * It extends the SpecialistService class and follows specific rules for licensed employees,
 * complexity of the job, and additional permitting fees.
 */
public class PlumbingService extends SpecialistService {

  /** The permitting fee required for plumbing services. */
  private static final double PERMITTING_FEE = 20.0;

  /**
   * Constructs a new PlumbingService instance with the provided details about the property,
   * service frequency, number of licensed employees, and complexity of the work.
   *
   * @param propertyAddress the address of the property where the service is being provided
   * @param propertySize the size of the property (SMALL, MEDIUM, LARGE)
   * @param monthly whether the service is a recurring monthly service
   * @param previousServicesCount the number of previous services provided at the address
   * @param licensedEmployees the number of licensed employees assigned to the service
   * @param isComplex whether the plumbing work is considered complex
   */
  public PlumbingService(String propertyAddress, PropertySize propertySize, boolean monthly, int previousServicesCount,
      int licensedEmployees, boolean isComplex) {
    super(propertyAddress, propertySize, monthly, previousServicesCount, licensedEmployees, isComplex);
  }

  /**
   * Calculates the price of the plumbing service based on the number of licensed employees
   * and the permitting fee.
   *
   * @return the total price for the plumbing service
   */
  @Override
  public double calculatePrice() {
    int licensedEmployees = getLicensedEmployees();
    double price = getBaseRate() * licensedEmployees;  // Calculate base price for the service
    price += PERMITTING_FEE;  // Add the permitting fee
    return price;
  }

  /**
   * Checks if this PlumbingService object is equal to another object. Equality is based on
   * the number of licensed employees, complexity of the work, and service-related fields.
   *
   * @param o the object to be compared
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PlumbingService that = (PlumbingService) o;
    return getLicensedEmployees() == that.getLicensedEmployees() &&
        isComplex() == that.isComplex() &&
        Objects.equals(getPropertyAddress(), that.getPropertyAddress()) &&
        getPropertySize() == that.getPropertySize() &&
        isMonthly() == that.isMonthly() &&
        getPreviousServicesCount() == that.getPreviousServicesCount();
  }

  /**
   * Generates a hash code for this PlumbingService object based on its property details
   * and service attributes.
   *
   * @return the hash code for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(getPropertyAddress(), getPropertySize(), isMonthly(),
        getPreviousServicesCount(), getLicensedEmployees(), isComplex());
  }

  /**
   * Returns a string representation of this PlumbingService object, including details about
   * the property, service, and pricing.
   *
   * @return a string representation of the plumbing service
   */
  @Override
  public String toString() {
    return "PlumbingService{" +
        "propertyAddress='" + getPropertyAddress() + '\'' +
        ", propertySize=" + getPropertySize() +
        ", monthly=" + isMonthly() +
        ", previousServicesCount=" + getPreviousServicesCount() +
        ", licensedEmployees=" + getLicensedEmployees() +
        ", isComplex=" + isComplex() +
        ", permittingFee=" + PERMITTING_FEE +
        ", baseRatePerEmployee=" + getBaseRate() +
        '}';
  }
}
