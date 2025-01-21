package problem2;

/**
 * The ABagOfWords abstract class provides a base implementation for the BagOfWords
 * interface. This class represents a node in a recursive, immutable structure
 * for storing words. Each node contains a word and a reference to the next node
 * in the sequence.
 */
public abstract class ABagOfWords implements BagOfWords {

  /**
   * The word stored at this node of the BagOfWords.
   */
  protected final String word;

  /**
   * The next node in the BagOfWords structure, allowing for recursive
   * traversal of the words contained in the bag.
   */
  protected final BagOfWords next;


  /**
   * Constructs a new ABagOfWords node with the specified word and next node.
   *
   * @param word the word to store at this node
   * @param next the next node in the BagOfWords structure
   */
  protected ABagOfWords(String word, BagOfWords next) {
    this.word = word;
    this.next = next;
  }

  /**
   * Checks if this BagOfWords is empty.
   *
   * @return true if the BagOfWords contains no elements, false otherwise.
   */
  @Override
  public abstract Boolean isEmpty();

  /**
   * Returns the total number of elements in this BagOfWords, including duplicates.
   *
   * @return the number of elements in the BagOfWords.
   */
  @Override
  public abstract Integer size();

  /**
   * Returns a new BagOfWords that contains all elements in the current BagOfWords
   * plus the specified word.
   *
   * @param s the word to be added
   * @return a new BagOfWords containing all elements in the current bag plus the new word.
   */
  @Override
  public abstract BagOfWords add(String s);

  /**
   * Checks if the specified word is present in the BagOfWords.
   *
   * @param s the word to check for
   * @return true if the BagOfWords contains the word, false otherwise.
   */
  @Override
  public abstract Boolean contains(String s);

  /**
   * Compares the specified object with this BagOfWords for equality. Two BagOfWords
   * instances are considered equal if they contain the same words, regardless of order.
   *
   * @param o the object to be compared for equality with this BagOfWords
   * @return true if the specified object is equal to this BagOfWords, false otherwise.
   */
  @Override
  public abstract boolean equals(Object o);

  /**
   * Returns the hash code value for this BagOfWords. The hash code of a BagOfWords
   * is computed based on its contents and disregards order.
   *
   * @return the hash code value for this BagOfWords.
   */
  @Override
  public abstract int hashCode();
}
