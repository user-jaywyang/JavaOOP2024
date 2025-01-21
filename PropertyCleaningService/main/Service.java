package problem1;

/**
 * The Service interface represents a general service to be provided for a property.
 * It defines the methods that any specific service must implement.
 */
public interface Service {

   /**
    * Calculates the price of the service based on the specific service details.
    *
    *
    * @return the calculated price of the service
    */
   double calculatePrice();

   /**
    * Gets the address of the property where the service is provided.
    *
    * @return the property address
    */
   String getPropertyAddress();

   /**
    * Gets the size of the property where the service is provided.
    *
    * @return the size of the property
    */
   PropertySize getPropertySize();

   /**
    * Indicates whether the service is provided on a monthly basis.
    *
    * @return true if the service is monthly, false otherwise
    */
   boolean isMonthly();

   /**
    * Gets the number of previous services that have been provided for the property.
    *
    * @return the number of previous services
    */
   int getPreviousServicesCount();
}
