package problem2;

/**
 * A class representing a node in a singly linked list. Each node stores a string
 * and a reference to the next node in the list.
 */
public class Node {

  /** The string data stored in this node */
  String data;

  /** The reference to the next node in the linked list */
  Node next;

  /**
   * Constructs a new node with the specified data. The next node is initially set to null.
   *
   * @param data the string to be stored in the node
   */
  public Node(String data) {
    this.data = data;
    this.next = null;
  }
}
