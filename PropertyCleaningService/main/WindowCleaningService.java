package problem1;

import java.util.Objects;

/**
 * The WindowCleaningService class represents a specialized service for cleaning windows in residential or commercial properties.
 * It extends the ExteriorService class and includes specific rules for multi-floors, pricing, and discounts.
 */
public class WindowCleaningService extends ExteriorService {



  /** The surcharge rate for properties with multiple floors (5%). */
  private static final double MULTI_FLOOR_SURCHARGE = 0.05;

  /** The maximum number of floors allowed for a window cleaning service. */
  private static final int MAX_FLOORS = 3;

  /** The number of floors in the property where the window cleaning service is provided. */
  private int numberOfFloors;

  /**
   * Constructs a new WindowCleaningService instance with the specified property details.
   *
   * @param propertyAddress the address of the property where the service is being provided
   * @param propertySize the size of the property (SMALL, MEDIUM, LARGE)
   * @param monthly whether the service is a recurring monthly service
   * @param previousServicesCount the number of previous services provided at the address
   * @param numberOfFloors the number of floors in the property
   * @throws OverMaxFloorException if the number of floors exceeds the maximum limit of 3
   */
  public WindowCleaningService(String propertyAddress, PropertySize propertySize, boolean monthly, int previousServicesCount, int numberOfFloors) {
    super(propertyAddress, propertySize, monthly, previousServicesCount);

    if (numberOfFloors > MAX_FLOORS) {
      throw new OverMaxFloorException("Window cleaning service cannot be provided for properties with more than " + MAX_FLOORS + " floors.");
    }
    this.numberOfFloors = numberOfFloors;
  }

  /**
   * Calculates the price of the window cleaning service based on the property size, number of floors,
   * and any discounts or surcharges.
   *
   * @return the total price for the window cleaning service
   */
  @Override
  public double calculatePrice() {
    int hoursRequired = calculateHoursRequired();
    double price = getBaseRate() * hoursRequired;

    // Add a 5% surcharge if the property has more than 1 floor
    if (numberOfFloors > 1) {
      price += price * MULTI_FLOOR_SURCHARGE;
    }

    // Apply any discounts (10th service or monthly service)
    price = applyDiscounts(price);

    return price;
  }

  /**
   * Determines the number of hours required for the window cleaning service based on the size of the property.
   *
   * @return the number of hours required
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
   * Applies applicable discounts to the total service price.
   *
   * @param price the current total price before discounts
   * @return the total price after applying discounts
   */
  private double applyDiscounts(double price) {
    int previousServicesCount = getPreviousServicesCount();

    // Apply 50% discount for every 10th service
    if (previousServicesCount % 10 == 9) {
      return price * 0.5;
    }

    // Apply 10% discount for monthly services
    if (isMonthly()) {
      return price * 0.9;
    }

    return price;
  }

  /**
   * Checks if this WindowCleaningService object is equal to another object.
   * Equality is based on the number of floors, property details, and service attributes.
   *
   * @param o the object to be compared
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WindowCleaningService that = (WindowCleaningService) o;
    return numberOfFloors == that.numberOfFloors &&
        Objects.equals(getPropertyAddress(), that.getPropertyAddress()) &&
        getPropertySize() == that.getPropertySize() &&
        isMonthly() == that.isMonthly() &&
        getPreviousServicesCount() == that.getPreviousServicesCount();
  }

  /**
   * Generates a hash code for this WindowCleaningService object.
   *
   * @return the hash code for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(getPropertyAddress(), getPropertySize(), isMonthly(), getPreviousServicesCount(), numberOfFloors);
  }

  /**
   * Returns a string representation of this WindowCleaningService object, including details about the property, service,
   * and pricing.
   *
   * @return a string representation of the window cleaning service
   */
  @Override
  public String toString() {
    return "WindowCleaningService{" +
        "propertyAddress='" + getPropertyAddress() + '\'' +
        ", propertySize=" + getPropertySize() +
        ", monthly=" + isMonthly() +
        ", previousServicesCount=" + getPreviousServicesCount() +
        ", numberOfFloors=" + numberOfFloors +
        ", baseRate=" + getBaseRate() +
        ", multiFloorSurcharge=" + MULTI_FLOOR_SURCHARGE +
        '}';
  }
}
