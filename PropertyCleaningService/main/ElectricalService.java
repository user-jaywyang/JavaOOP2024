package problem1;

import java.util.Objects;

/**
 * The ElectricalService class represents a service for electrical work. It extends the SpecialistService class
 * and follows specific rules for licensed employees, complexity of the job, and additional permitting fees.
 */
public class ElectricalService extends SpecialistService {


  /** The permitting fee required for electrical services. */
  private static final double PERMITTING_FEE = 50.0;

  /** The maximum number of licensed employees allowed for electrical services. */
  private static final int MAX_LICENSED_EMPLOYEES = 4;

  /**
   * Constructs a new ElectricalService instance with the provided details about the property, service frequency,
   * number of licensed employees, and complexity of the work. Ensures that the number of licensed employees does not
   * exceed the maximum limit.
   *
   * @param propertyAddress the address of the property where the service is being provided
   * @param propertySize the size of the property (SMALL, MEDIUM, LARGE)
   * @param monthly whether the service is a recurring monthly service
   * @param previousServicesCount the number of previous services provided at the address
   * @param licensedEmployees the number of licensed employees assigned to the service
   * @param isComplex whether the electrical work is considered complex
   * @throws InvalidElectricalEmployeeException if the number of licensed employees exceeds the allowed maximum
   */
  public ElectricalService(String propertyAddress, PropertySize propertySize, boolean monthly, int previousServicesCount,
      int licensedEmployees, boolean isComplex) throws InvalidElectricalEmployeeException {
    super(propertyAddress, propertySize, monthly, previousServicesCount, validateLicensedEmployees(licensedEmployees), isComplex);
  }

  /**
   * Validates the number of licensed employees, ensuring it does not exceed the maximum limit allowed
   * for electrical services.
   *
   * @param licensedEmployees the number of licensed employees assigned to the service
   * @return the validated number of licensed employees
   * @throws InvalidElectricalEmployeeException if the number of licensed employees exceeds the allowed maximum
   */
  private static int validateLicensedEmployees(int licensedEmployees) throws InvalidElectricalEmployeeException {
    if (licensedEmployees > MAX_LICENSED_EMPLOYEES) {
      throw new InvalidElectricalEmployeeException("Electrical services cannot have more than " + MAX_LICENSED_EMPLOYEES + " licensed employees.");
    }
    return licensedEmployees;
  }

  /**
   * Calculates the price of the electrical service based on the number of licensed employees and the permitting fee.
   *
   * @return the total price for the electrical service
   */
  @Override
  public double calculatePrice() {
    int licensedEmployees = getLicensedEmployees();
    double price = getBaseRate() * licensedEmployees;  // Calculate base price for the service
    price += PERMITTING_FEE;  // Add the permitting fee
    return price;
  }

  /**
   * Checks if this ElectricalService object is equal to another object.
   *
   * @param o the object to be compared
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ElectricalService that = (ElectricalService) o;
    return getLicensedEmployees() == that.getLicensedEmployees() &&
        isComplex() == that.isComplex() &&
        Objects.equals(getPropertyAddress(), that.getPropertyAddress()) &&
        getPropertySize() == that.getPropertySize() &&
        isMonthly() == that.isMonthly() &&
        getPreviousServicesCount() == that.getPreviousServicesCount();
  }

  /**
   * Generates a hash code for this object based on its property details and service.
   *
   * @return the hash code for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(getPropertyAddress(), getPropertySize(), isMonthly(), getPreviousServicesCount(), getLicensedEmployees(), isComplex());
  }

  /**
   * Returns a string representation of this ElectricalService object, including details about the property, service,
   * and pricing.
   *
   * @return a string representation of the electrical service
   */
  @Override
  public String toString() {
    return "ElectricalService{" +
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
