package problem1;

import java.util.Objects;

/**
 * A recursive, immutable Priority Queue  where each element has a priority and an associated value.
 * Elements with higher priorities are dequeued before elements with lower priorities.
 */
public class RecursivePriorityQueue implements PriorityQueue {

  /**
   * The priority of the current element. Higher values indicate higher priority.
   */
  private final Integer priority;

  /**
   * The value associated with the priority of the current element.
   */
  private final String value;

  /**
   * The next node in the queue, representing the remaining elements in the queue.
   */
  private final RecursivePriorityQueue next;

  /**
   * A constant representing a null priority for use in empty queues.
   */
  private static final Integer NULL_PRIORITY = null;

  /**
   * A constant representing a null value for use in empty queues.
   */
  private static final String NULL_VALUE = null;

  /**
   * Constructs a RecursivePriorityQueue node with the specified priority, value, and reference
   * to the next node in the queue.
   *
   * @param priority the priority level of this node's element.
   * @param value    the value associated with this priority.
   * @param next     the next node in the priority queue.
   */
  public RecursivePriorityQueue(Integer priority, String value, RecursivePriorityQueue next) {
    this.priority = priority;
    this.value = value;
    this.next = next;
  }

  /**
   * Creates and returns an empty Priority Queue.
   *
   * @return an empty PriorityQueue instance.
   */
  public PriorityQueue createEmpty() {
    return new RecursivePriorityQueue(NULL_PRIORITY, NULL_VALUE, null);
  }

  /**
   * Checks if the Priority Queue is empty.
   *
   * @return true if the PriorityQueue contains no items, false otherwise.
   */
  public Boolean isEmpty() {
    return this.priority == NULL_PRIORITY && this.value == NULL_VALUE && this.next == null;
  }

  /**
   * Adds an element with the specified priority and value to the Priority Queue.
   * Maintains the priority order, where higher integer values indicate higher priority.
   *
   * @param priority the priority of the element to add.
   * @param value    the value associated with the specified priority.
   * @return a new PriorityQueue instance with the specified element added.
   */
  public PriorityQueue add(Integer priority, String value) {
    if (this.isEmpty()) {
      return new RecursivePriorityQueue(priority, value, null);
    } else if (priority > this.priority) {
      return new RecursivePriorityQueue(priority, value, this);
    } else {
      RecursivePriorityQueue newNext = this.next == null ? new RecursivePriorityQueue(priority, value, null)
          : (RecursivePriorityQueue) this.next.add(priority, value);
      return new RecursivePriorityQueue(this.priority, this.value, newNext);
    }
  }

  /**
   * Returns the value of the element in the Priority Queue with the highest priority.
   * If multiple elements share the same highest priority, the first-added element with that priority is returned.
   *
   * @return the value associated with the highest priority in the Priority Queue.
   * @throws EmptyQueueException if the Priority Queue is empty.
   */
  public String peek() {
    if (this.isEmpty()) {
      throw new EmptyQueueException("Cannot peek from an empty queue.");
    } else if (this.next == null || this.priority >= this.next.priority) {
      return this.value;
    } else {
      return this.next.peek();
    }
  }

  /**
   * Returns a new Priority Queue instance without the element with the highest priority.
   * If multiple elements share the same highest priority, the first-added element with that priority is removed.
   *
   * @return a new PriorityQueue instance with the highest-priority element removed.
   * @throws EmptyQueueException if the Priority Queue is empty.
   */
  public PriorityQueue pop() {
    if (this.isEmpty()) {
      throw new EmptyQueueException("Cannot pop from an empty queue.");
    } else if (this.next == null || this.priority >= this.next.priority) {
      return this.next == null ? createEmpty() : this.next;
    } else {
      return new RecursivePriorityQueue(this.priority, this.value, (RecursivePriorityQueue) this.next.pop());
    }
  }

  /**
   * Checks if this Priority Queue is equal to another object.
   * Two queues are considered equal if they contain the same elements in the same order.
   *
   * @param o the object to compare with.
   * @return true if this Priority Queue is equal to the specified object, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RecursivePriorityQueue that = (RecursivePriorityQueue) o;
    return Objects.equals(priority, that.priority) &&
        Objects.equals(value, that.value) &&
        Objects.equals(next, that.next);
  }

  /**
   * Computes a hash code for this Priority Queue based on its priority, value, and next node.
   *
   * @return the hash code representing this Priority Queue.
   */
  @Override
  public int hashCode() {
    return Objects.hash(priority, value, next);
  }

  /**
   * Returns a string representation of the Priority Queue.
   * An empty queue is represented as "[]".
   *
   * @return the string representation of the Priority Queue.
   */
  @Override
  public String toString() {
    if (this.isEmpty()) {
      return "[]";
    }
    return "[" + priority + ": " + value + "] -> " + (next == null ? "[]" : next.toString());
  }

}
