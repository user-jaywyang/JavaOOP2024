package problem1;

import java.util.Objects;

/**
 * The PaintingService class represents a service for painting properties. It extends the InteriorService class,
 * and includes attributes such as fixed hours, pet surcharges, and discounts.
 *
 */
public class PaintingService extends InteriorService {


  private static final int HOURS_SMALL_MEDIUM_PROPERTY = 16;

  /** The number of hours required for large properties. */
  private static final int HOURS_LARGE_PROPERTY = 24;

  /**
   * Constructs a new PaintingService instance with the provided attributes.
   *
   * @param propertyAddress the address of the property where the service is being provided
   * @param propertySize the size of the property (SMALL, MEDIUM, LARGE)
   * @param monthly whether the service is a recurring monthly service
   * @param previousServicesCount the number of previous services provided at the address
   * @param petsCount the number of pets at the property
   */
  public PaintingService(String propertyAddress, PropertySize propertySize, boolean monthly, int previousServicesCount, int petsCount) {
    super(propertyAddress, propertySize, monthly, previousServicesCount, petsCount);
  }

  /**
   * Calculates the total price of the painting service based on the property size, pet surcharges,
   * and applicable discounts.
   *
   * @return the total price for the painting service
   */
  @Override
  public double calculatePrice() {
    int hoursRequired = calculateHoursRequired();
    double price = getBaseRate() * hoursRequired;

    // Add a surcharge based on the number of pets
    price += applyPetSurcharge(price);

    // Apply discounts based on the number of previous services or monthly service
    price = applyDiscounts(price);

    return price;
  }

  /**
   * Determines the number of hours required for the painting service based on the property size.
   *
   * @return the number of hours required for the painting service
   * @throws UnknownPropertySizeException if the property size is not recognized
   */
  private int calculateHoursRequired() {
    switch (getPropertySize()) {
      case SMALL:
      case MEDIUM:
        return HOURS_SMALL_MEDIUM_PROPERTY;  // 16 hours for small and medium properties
      case LARGE:
        return HOURS_LARGE_PROPERTY;  // 24 hours for large properties
      default:
        throw new UnknownPropertySizeException("Unknown property size");
    }
  }

  /**
   * Applies a pet surcharge to the total price based on the number of pets at the property.
   *
   * @param basePrice the base price before applying the surcharge
   * @return the surcharge amount to be added
   */
  private double applyPetSurcharge(double basePrice) {
    int petsCount = getPetsCount();
    double surcharge = 0;
    if (petsCount == 1 || petsCount == 2) {
      surcharge = basePrice * getPetSurchargeFor1To2Pets();  // 5% surcharge for 1-2 pets
    } else if (petsCount >= 3) {
      surcharge = basePrice * getPetSurchargeFor3PlusPets();  // 7% surcharge for 3 or more pets
    }
    return surcharge;
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

    // If it's not a 10th service, apply the monthly service discount if applicable
    if (isMonthly()) {
      return price * getMonthlyDiscount();  // 10% discount for monthly services
    }

    return price;
  }

  /**
   * Checks whether this PaintingService instance is equal to another object. Equality is based on
   * property address, property size, service frequency, service history, and the number of pets.
   *
   * @param o the object to compare to
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PaintingService)) return false;
    PaintingService that = (PaintingService) o;
    return Objects.equals(this.getPropertyAddress(), that.getPropertyAddress()) &&
        this.getPropertySize() == that.getPropertySize() &&
        this.isMonthly() == that.isMonthly() &&
        this.getPreviousServicesCount() == that.getPreviousServicesCount() &&
        this.getPetsCount() == that.getPetsCount();
  }

  /**
   * Returns the hash code for this PaintingService.
   *
   * @return the hash code for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(getPropertyAddress(), getPropertySize(), isMonthly(), getPreviousServicesCount(), getPetsCount());
  }

  /**
   * Returns a string representation of this PaintingService object, including property details,
   * service attributes, and pricing constants.
   *
   * @return a string representation of the painting service
   */
  @Override
  public String toString() {
    return "PaintingService{" +
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
