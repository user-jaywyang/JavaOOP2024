package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecursivePriorityQueueTest {
  private PriorityQueue emptyQueue;

  @BeforeEach
  public void setUp() {
    emptyQueue = new RecursivePriorityQueue(null, null, null).createEmpty();
  }

  @Test
  public void testCreateEmpty() {
    assertTrue(emptyQueue.isEmpty(), "An empty queue should be empty.");
  }

  @Test
  public void testIsEmpty() {
    assertTrue(emptyQueue.isEmpty(), "Empty queue should return true for isEmpty().");

    PriorityQueue queueWithElement = emptyQueue.add(1, "A");
    assertFalse(queueWithElement.isEmpty(), "Queue with one element should not be empty.");
  }

  @Test
  public void testAddSingleElement() {
    PriorityQueue queue = emptyQueue.add(5, "Test");
    assertFalse(queue.isEmpty(), "Queue should not be empty after adding an element.");
    assertEquals("Test", queue.peek(), "The element with the highest priority should be returned by peek.");
  }

  @Test
  public void testAddMultipleElementsInOrder() {
    PriorityQueue queue = emptyQueue.add(1, "Low")
        .add(5, "Medium")
        .add(10, "High");

    assertEquals("High", queue.peek(), "The highest priority element should be 'High'.");
  }

  @Test
  public void testAddMultipleElementsOutOfOrder() {
    PriorityQueue queue = emptyQueue.add(10, "High")
        .add(1, "Low")
        .add(5, "Medium");

    assertEquals("High", queue.peek(), "The highest priority element should still be 'High'.");
  }

  @Test
  public void testPeekWithSingleElement() {
    PriorityQueue queue = emptyQueue.add(3, "Only");
    assertEquals("Only", queue.peek(), "The single element should be returned by peek.");
  }

  @Test
  public void testPeekWithMultipleElements() {
    PriorityQueue queue = emptyQueue.add(3, "Low")
        .add(10, "High")
        .add(5, "Medium");

    assertEquals("High", queue.peek(), "Peek should return the element with the highest priority.");
  }

  @Test
  public void testPeekThrowsExceptionOnEmptyQueue() {
    Exception exception = assertThrows(EmptyQueueException.class, emptyQueue::peek);
    assertEquals("Cannot peek from an empty queue.", exception.getMessage());
  }

  @Test
  public void testPopWithSingleElement() {
    PriorityQueue queue = emptyQueue.add(2, "Only");
    PriorityQueue newQueue = queue.pop();
    assertTrue(newQueue.isEmpty(), "After popping the only element, the queue should be empty.");
  }

  @Test
  public void testPopWithMultipleElements() {
    PriorityQueue queue = emptyQueue.add(1, "Low")
        .add(5, "Medium")
        .add(10, "High");

    PriorityQueue newQueue = queue.pop();
    assertEquals("Medium", newQueue.peek(), "After popping, the next highest priority should be 'Medium'.");
  }

  @Test
  public void testPopThrowsExceptionOnEmptyQueue() {
    Exception exception = assertThrows(EmptyQueueException.class, emptyQueue::pop);
    assertEquals("Cannot pop from an empty queue.", exception.getMessage());
  }

  @Test
  public void testAddElementsWithSamePriority() {
    PriorityQueue queue = emptyQueue.add(5, "First")
        .add(10, "Highest")
        .add(5, "Second");

    assertEquals("Highest", queue.peek(), "Peek should return the element with the highest priority.");
    PriorityQueue newQueue = queue.pop();
    assertEquals("First", newQueue.peek(), "If two elements have the same priority, the first-added should be retained in peek.");
  }

  @Test
  public void testAddNegativePriorities() {
    PriorityQueue queue = emptyQueue.add(-10, "Lowest")
        .add(0, "Neutral")
        .add(5, "High");

    assertEquals("High", queue.peek(), "Negative priorities should be handled, with 'High' as the highest priority.");
  }

  @Test
  public void testChainOfOperations() {
    PriorityQueue queue = emptyQueue.add(2, "Second")
        .add(3, "Third")
        .add(1, "First");

    assertEquals("Third", queue.peek(), "Peek should return 'Third' as the highest priority.");
    queue = queue.pop();
    assertEquals("Second", queue.peek(), "After pop, 'Second' should be the new highest.");
    queue = queue.pop();
    assertEquals("First", queue.peek(), "After another pop, 'First' should be the new highest.");
    queue = queue.pop();
    assertTrue(queue.isEmpty(), "Queue should be empty after all elements are popped.");
  }

  @Test
  public void testEquals() {
    PriorityQueue queue1 = emptyQueue.add(3, "Low").add(5, "Medium").add(10, "High");
    PriorityQueue queue2 = emptyQueue.add(3, "Low").add(5, "Medium").add(10, "High");
    PriorityQueue queue3 = emptyQueue.add(1, "Different");

    assertEquals(queue1, queue2, "Queues with the same elements should be equal.");
    assertNotEquals(queue1, queue3, "Queues with different elements should not be equal.");
  }

  @Test
  public void testHashCode() {
    PriorityQueue queue1 = emptyQueue.add(3, "Low").add(5, "Medium").add(10, "High");
    PriorityQueue queue2 = emptyQueue.add(3, "Low").add(5, "Medium").add(10, "High");
    PriorityQueue queue3 = emptyQueue.add(1, "Different");

    assertEquals(queue1.hashCode(), queue2.hashCode(), "Queues with the same elements should have the same hash code.");
    assertNotEquals(queue1.hashCode(), queue3.hashCode(), "Queues with different elements should have different hash codes.");
  }

  @Test
  public void testToString() {
    PriorityQueue queue = emptyQueue.add(3, "Low").add(5, "Medium").add(10, "High");
    assertEquals("[10: High] -> [5: Medium] -> [3: Low] -> []", queue.toString(), "toString should represent the priority queue in descending order of priority.");

    assertEquals("[]", emptyQueue.toString(), "An empty queue should return '[]' in toString.");
  }

}
