package problem1;

/**
 * Represents an immutable recursive Priority Queue ADT, a data structure where each element has a priority and a value.
 * Elements with higher priorities are dequeued before elements with lower priorities.
 */
public interface PriorityQueue {

  /**
   * Creates and returns an empty Priority Queue.
   *
   * @return an empty PriorityQueue instance.
   */
  PriorityQueue createEmpty();

  /**
   * Checks if the Priority Queue is empty.
   *
   * @return true if the PriorityQueue contains no items, false otherwise.
   */
  Boolean isEmpty();

  /**
   * Adds an element with the specified priority and value to the Priority Queue.
   *
   * @param priority the priority of the element to add, where a higher integer value indicates a higher priority.
   * @param value    the value associated with the specified priority.
   * @return a new PriorityQueue instance with the specified element added.
   */
  PriorityQueue add(Integer priority, String value);

  /**
   * Returns the value of the element in the Priority Queue with the highest priority.
   *
   * @return the value associated with the highest priority in the Priority Queue.
   * @throws EmptyQueueException if the Priority Queue is empty.
   */
  String peek();

  /**
   * Returns a new Priority Queue instance without the element with the highest priority.
   *
   * @return a new PriorityQueue instance with the highest-priority element removed.
   * @throws EmptyQueueException if the Priority Queue is empty.
   */
  PriorityQueue pop();
}
