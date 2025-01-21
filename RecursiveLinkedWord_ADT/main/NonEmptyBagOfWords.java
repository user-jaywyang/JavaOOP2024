package problem2;

/**
 * The NonEmptyBagOfWords class represents a node in the recursive BagOfWords structure
 * that contains a word and a reference to the next node. This class supports adding
 * elements, checking for containment, and managing size and equality in the BagOfWords structure.
 */
public class NonEmptyBagOfWords extends ABagOfWords {

  /**
   * A constant representing a 1 value for use in returning queue size.
   */
  private static final Integer GROW = 1;

  /**
   * Constructs a NonEmptyBagOfWords node with the specified word and the next node.
   *
   * @param word the word to store at this node
   * @param next the next node in the BagOfWords structure
   */
  public NonEmptyBagOfWords(String word, BagOfWords next) {
    super(word, next);
  }

  /**
   * Creates and returns an empty BagOfWords instance.
   *
   * @return a new instance of EmptyBagOfWords.
   */
  @Override
  public BagOfWords emptyBagOfWords() {
    return new EmptyBagOfWords();
  }

  /**
   * Checks if this BagOfWords is empty.
   *
   * @return false, as this represents a non-empty bag.
   */
  @Override
  public Boolean isEmpty() {
    return false;
  }

  /**
   * Returns the total number of elements in this BagOfWords, including duplicates.
   * This implementation uses recursion, with each node adding 1 to the total size.
   *
   * @return the number of elements in the BagOfWords.
   */
  @Override
  public Integer size() {
    return GROW + next.size();
  }

  /**
   * Returns a new BagOfWords that contains all elements in the current BagOfWords
   * plus the specified word. Creates a new NonEmptyBagOfWords instance with the
   * new word as the head and the current instance as the next node.
   *
   * @param s the word to be added
   * @return a new BagOfWords containing all elements in the current bag plus the new word.
   */
  @Override
  public BagOfWords add(String s) {
    return new NonEmptyBagOfWords(s, this);
  }

  /**
   * Checks if the specified word is present in the BagOfWords. Recursively searches
   * the structure, returning true if the word matches the current node's word or is found
   * in the subsequent nodes.
   *
   * @param s the word to check for
   * @return true if the BagOfWords contains the word, false otherwise.
   */
  @Override
  public Boolean contains(String s) {
    if (word.equals(s)) {
      return true;
    } else {
      return next.contains(s);
    }
  }

  /**
   * Compares the specified object with this NonEmptyBagOfWords for equality. Two NonEmptyBagOfWords
   * instances are considered equal if they contain the same words in any order, and with the same frequencies.
   *
   * @param o the object to be compared for equality with this NonEmptyBagOfWords
   * @return true if the specified object is equal to this NonEmptyBagOfWords, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof NonEmptyBagOfWords)) return false;
    NonEmptyBagOfWords other = (NonEmptyBagOfWords) o;
    return this.size().equals(other.size()) && this.containsAllElements(other);
  }

  /**
   * Helper method that checks if all elements in the specified BagOfWords are also contained
   * within this BagOfWords, ensuring equality is based on the same words and frequencies.
   *
   * @param other the BagOfWords to compare against
   * @return true if all elements in other are contained within this BagOfWords.
   */
  private Boolean containsAllElements(BagOfWords other) {
    if (other.isEmpty()) return true;
    if (other instanceof NonEmptyBagOfWords) {
      NonEmptyBagOfWords otherNonEmpty = (NonEmptyBagOfWords) other;
      return this.contains(otherNonEmpty.word) && this.containsAllElements(otherNonEmpty.next);
    }
    return false;
  }

  /**
   * Returns the hash code value for this NonEmptyBagOfWords. The hash code is based on
   * the word stored in this node and the hash code of the subsequent nodes.
   *
   * @return the hash code value for this NonEmptyBagOfWords.
   */
  @Override
  public int hashCode() {
    return word.hashCode() + next.hashCode();
  }

  /**
   * Returns a string representation of this NonEmptyBagOfWords, showing the word
   * at this node and the next node in the structure.
   *
   * @return a string representation of this NonEmptyBagOfWords.
   */
  @Override
  public String toString() {
    return "NonEmptyBagOfWords{word='" + word + "', next=" + next.toString() + "}";
  }

}
