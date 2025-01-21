package problem2;

import java.util.HashSet;
import java.util.Set;

/**
 * A linked list implementation that stores strings. Provides various operations such as adding,
 * checking for elements, filtering, and removing duplicates.
 */
public class StringLinkedList {

  // Head of the list
  private Node head;
  private int size;

  /**
   * Constructs an empty StringLinkedList.
   */
  public StringLinkedList() {
    this.head = null;
    this.size = 0;
  }

  /**
   * Checks if the linked list is empty.
   *
   * @return true if the list is empty, false otherwise
   */
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * Returns the number of elements in the list.
   *
   * @return the size of the list
   */
  public int size() {
    return size;
  }

  /**
   * Adds a string to the end of the list.
   *
   * @param data the string to be added
   */
  public void add(String data) {
    Node newNode = new Node(data);
    if (head == null) {
      head = newNode;
    } else {
      Node current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
    size++;
  }

  /**
   * Checks if the list contains a specific string.
   *
   * @param str the string to check for
   * @return true if the string is in the list, false otherwise
   */
  public boolean contains(String str) {
    Node current = head;
    while (current != null) {
      if (current.data.equals(str)) {
        return true;
      }
      current = current.next;
    }
    return false;
  }

  /**
   * Checks if the list contains all elements from another StringLinkedList.
   *
   * @param otherList the other StringLinkedList to compare with
   * @return true if all elements of the other list are in this list, false otherwise
   */
  public boolean containsAll(StringLinkedList otherList) {
    Node current = otherList.head;
    while (current != null) {
      if (!this.contains(current.data)) {
        return false;
      }
      current = current.next;
    }
    return true;
  }

  /**
   * Filters the list by removing all strings whose length is greater than or equal to the
   * specified maximum length.
   *
   * @param maxLength the maximum allowable string length
   * @return a new StringLinkedList with only strings whose length is less than the specified maxLength
   */
  public StringLinkedList filterLargerThan(int maxLength) {
    StringLinkedList filteredList = new StringLinkedList();
    Node current = head;
    while (current != null) {
      if (current.data.length() < maxLength) {
        filteredList.add(current.data);
      }
      current = current.next;
    }
    return filteredList;
  }

  /**
   * Checks if the list contains any duplicate elements.
   *
   * @return true if there is at least one duplicate string, false otherwise
   */
  public boolean hasDuplicates() {
    Set<String> set = new HashSet<>();
    Node current = head;
    while (current != null) {
      if (!set.add(current.data)) {
        return true;
      }
      current = current.next;
    }
    return false;
  }

  /**
   * Removes all duplicate elements from the list.
   *
   * @return a new StringLinkedList with duplicates removed
   */
  public StringLinkedList removeDuplicates() {
    StringLinkedList uniqueList = new StringLinkedList();
    Set<String> set = new HashSet<>();
    Node current = head;
    while (current != null) {
      if (set.add(current.data)) {
        uniqueList.add(current.data);
      }
      current = current.next;
    }
    return uniqueList;
  }

  /**
   * Displays the elements of the list in sequence from head to tail.
   */
  public void display() {
    Node current = head;
    while (current != null) {
      System.out.print(current.data + " -> ");
      current = current.next;
    }
    System.out.println("null");
  }
}
