package problem1;

/**
 * An exception thrown when attempting to perform an operation that requires elements
 * in a Priority Queue, but the queue is empty.
 */
public class EmptyQueueException extends RuntimeException {

  /**
   * Constructs a new EmptyQueueException with the specified message.
   *
   * @param message the detail message explaining the cause of the exception.
   */
  public EmptyQueueException(String message) {
    super(message);
  }
}
