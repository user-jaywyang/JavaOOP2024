package problem1;

import java.util.Objects;

/**
 * The CleaningService class represents a service for cleaning properties, which includes base rates,
 * pet surcharges, and discounts. It extends the InteriorService class, utilizing attributes and methods
 * specific to interior services.
 *
 */
public class CleaningService extends InteriorService {

  /** The discount rate for every 10th service provided. */
  private static final double DISCOUNT_10TH_SERVICE = 0.5;

  /** The discount rate for monthly services. */
  private static final double DISCOUNT_MONTHLY_SERVICE = 0.9;

  /** The fixed number of hours required to clean a small property. */
  private static final int HOURS_SMALL_PROPERTY = 1;

  /** The fixed number of hours required to clean a medium property. */
  private static final int HOURS_MEDIUM_PROPERTY = 2;

  /** The fixed number of hours required to clean a large property. */
  private static final int HOURS_LARGE_PROPERTY = 4;

  /**
   * Constructs a new CleaningService instance with the provided details.
   *
   * @param propertyAddress the address of the property where the service is being provided
   * @param propertySize the size of the property (SMALL, MEDIUM, LARGE)
   * @param monthly whether the service is a recurring monthly service
   * @param previousServicesCount the number of previous services provided at the address
   * @param petsCount the number of pets at the property
   */
  public CleaningService(String propertyAddress, PropertySize propertySize, boolean monthly, int previousServicesCount, int petsCount) {
    super(propertyAddress, propertySize, monthly, previousServicesCount, petsCount);
  }

  /**
   * Calculates the total price of the cleaning service based on the property size, pet surcharges, and applicable discounts.
   * The price includes a base rate multiplied by the number of hours required for the property size.
   *
   * @return the total price for the cleaning service
   */
  @Override
  public double calculatePrice() {
    int hoursRequired = calculateHoursRequired();
    double price = getBaseRate() * hoursRequired;

    // Add pet surcharge
    price += applyPetSurcharge(price);

    // Apply discounts (10th service or monthly service)
    price = applyDiscounts(price);

    return price;
  }

  /**
   * Determines the number of hours required for the cleaning service based on the property size.
   *
   * @return the number of hours required
   * @throws UnknownPropertySizeException if the property size is not recognized
   */
  private int calculateHoursRequired() {
    if (getPropertySize() == null) {
      throw new UnknownPropertySizeException("Unknown property size");
    }
     switch (getPropertySize()) {
      case SMALL:
        return HOURS_SMALL_PROPERTY;
      case MEDIUM:
        return HOURS_MEDIUM_PROPERTY;
      case LARGE:
        return HOURS_LARGE_PROPERTY;
      default:
        throw new UnknownPropertySizeException("Unknown property size");
    }
  }

  /**
   * Applies a pet surcharge based on the number of pets at the property.
   *
   * @param basePrice the base price before applying the surcharge
   * @return the surcharge amount to be added
   */
  private double applyPetSurcharge(double basePrice) {
    int petsCount = getPetsCount();
    if (petsCount == 1 || petsCount == 2) {
      return basePrice * getPetSurchargeFor1To2Pets();  // 5% surcharge for 1-2 pets
    } else if (petsCount >= 3) {
      return basePrice * getPetSurchargeFor3PlusPets();  // 7% surcharge for 3 or more pets
    }
    return 0;
  }

  /**
   * Applies any discounts based on the service history and whether the service is monthly.
   *
   * @param price the price before discounts
   * @return the price after applying discounts
   */
  private double applyDiscounts(double price) {
    int previousServicesCount = getPreviousServicesCount();

    // Check if this is the 10th service (every 10th service gets a 50% discount)
    if (previousServicesCount % 10 == 9) {
      return price * get10thDiscount();  // 50% discount
    }

    // If it's not the 10th service, apply the monthly service discount if applicable
    if (isMonthly()) {
      return price * getMonthlyDiscount();  // 10% discount for monthly services
    }

    return price;
  }

  /**
   * Checks whether this CleaningService instance is equal to another object. Equality is based on
   * property address, property size, service frequency, service history, and number of pets.
   *
   * @param o the object to compare to
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CleaningService)) return false;
    CleaningService that = (CleaningService) o;
    return getPetsCount() == that.getPetsCount() &&
        Objects.equals(getPropertyAddress(), that.getPropertyAddress()) &&
        getPropertySize() == that.getPropertySize() &&
        isMonthly() == that.isMonthly() &&
        getPreviousServicesCount() == that.getPreviousServicesCount();
  }

  /**
   * Returns the hash code for this CleaningService instance.
   *
   * @return the hash code for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(getPropertyAddress(), getPropertySize(), isMonthly(), getPreviousServicesCount(), getPetsCount());
  }

  /**
   * Returns a string representation of this CleaningService object, including property details, service attributes, and pricing constants.
   *
   * @return a string representation of the cleaning service
   */
  @Override
  public String toString() {
    return "CleaningService{" +
        "propertyAddress='" + getPropertyAddress() + '\'' +
        ", propertySize=" + getPropertySize() +
        ", monthly=" + isMonthly() +
        ", previousServicesCount=" + getPreviousServicesCount() +
        ", petsCount=" + getPetsCount() +
        ", baseRatePerHour=" + getBaseRate() +
        ", petSurcharge1_2=" + getPetSurchargeFor1To2Pets() +
        ", petSurcharge3Plus=" + getPetSurchargeFor3PlusPets() +
        '}';
  }
}
