package problem1;

import java.util.Objects;

/**
 * The GardeningService class represents a service for maintaining gardens at residential or commercial properties.
 * It extends the ExteriorService class and includes specific pricing rules such as fees and discounts.
 */
public class GardeningService extends ExteriorService {


  /** The waste removal fee for gardening services. */
  private static final double WASTE_REMOVAL_FEE = 20.0;

  /**
   * Constructs a new GardeningService instance with the provided details about the property and service.
   *
   * @param propertyAddress the address of the property where the service is being provided
   * @param propertySize the size of the property (SMALL, MEDIUM, LARGE)
   * @param monthly whether the service is a recurring monthly service
   * @param previousServicesCount the number of previous services provided at the address
   */
  public GardeningService(String propertyAddress, PropertySize propertySize, boolean monthly, int previousServicesCount) {
    super(propertyAddress, propertySize, monthly, previousServicesCount);
  }

  /**
   * Calculates the price of the gardening service based on the size of the property, waste removal fee,
   * and any applicable discounts.
   *
   * @return the total price for the gardening service
   */
  @Override
  public double calculatePrice() {
    int hoursRequired = calculateHoursRequired();

    // Calculate the base price for gardening services
    double price = getBaseRate() * hoursRequired;

    // Add the waste removal fee
    price += WASTE_REMOVAL_FEE;

    // Apply any discounts based on the number of previous services or monthly service
    price = applyDiscounts(price);

    return price;
  }

  /**
   * Determines the number of hours required for the gardening service based on the size of the property.
   *
   * @return the number of hours required for the service
   * @throws UnknownPropertySizeException if the property size is unknown
   */
  private int calculateHoursRequired() {
    switch (getPropertySize()) {
      case SMALL:
        return getSmallPropertyHours();
      case MEDIUM:
        return getMediumPropertyHours();
      case LARGE:
        return getLargePropertyHours();
      default:
        throw new UnknownPropertySizeException("Unknown property size");
    }
  }

  /**
   * Applies any applicable discounts to the total service price.
   *
   * @param price the current total price before discounts
   * @return the total price after applying discounts
   */
  private double applyDiscounts(double price) {
    int previousServicesCount = getPreviousServicesCount();

    // Apply a 50% discount for every 10th service
    if (previousServicesCount % 10 == 9) {
      return price * 0.5;
    }

    // Apply a 10% discount for monthly services
    if (isMonthly()) {
      return price * 0.9;
    }

    return price;
  }

  /**
   * Checks if this GardeningService object is equal to another object. Equality is based on the property details
   * and service attributes.
   *
   * @param o the object to be compared
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GardeningService that = (GardeningService) o;
    return Objects.equals(getPropertyAddress(), that.getPropertyAddress()) &&
        getPropertySize() == that.getPropertySize() &&
        isMonthly() == that.isMonthly() &&
        getPreviousServicesCount() == that.getPreviousServicesCount();
  }

  /**
   * Generates a hash code for this GardeningService object.
   *
   * @return the hash code for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(getPropertyAddress(), getPropertySize(), isMonthly(), getPreviousServicesCount());
  }

  /**
   * Returns a string representation of this GardeningService object, including details about the property, service,
   * and pricing.
   *
   * @return a string representation of the gardening service
   */
  @Override
  public String toString() {
    return "GardeningService{" +
        "propertyAddress='" + getPropertyAddress() + '\'' +
        ", propertySize=" + getPropertySize() +
        ", monthly=" + isMonthly() +
        ", previousServicesCount=" + getPreviousServicesCount() +
        ", baseRate=" + getBaseRate() +
        ", wasteRemovalFee=" + WASTE_REMOVAL_FEE +
        '}';
  }
}
