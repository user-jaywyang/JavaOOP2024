package problem2;

/**
 * The EmptyBagOfWords class represents an empty node in the BagOfWords structure.
 * This class serves as the base case in the recursive structure, representing a
 * BagOfWords with no elements.
 */
public class EmptyBagOfWords extends ABagOfWords {

  /**
   * A constant representing a 0 value for use in empty queues.
   */
  private static final Integer EMPTY = 0;

  /**
   * Constructs an EmptyBagOfWords instance. This represents an empty bag
   * with no words and serves as the end of the recursive structure.
   */
  public EmptyBagOfWords() {
    super(null, null);
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
   * @return true, as this represents an empty bag.
   */
  @Override
  public Boolean isEmpty() {
    return true;
  }

  /**
   * Returns the total number of elements in this BagOfWords, which is zero
   * since it is empty.
   *
   * @return 0, indicating the bag contains no elements.
   */
  @Override
  public Integer size() {
    return EMPTY;
  }

  /**
   * Returns a new BagOfWords with the specified word added. Since this bag
   * is empty, it will create and return a new NonEmptyBagOfWords instance
   * containing the specified word.
   *
   * @param s the word to be added
   * @return a new instance of NonEmptyBagOfWords containing the specified word.
   */
  @Override
  public BagOfWords add(String s) {
    return new NonEmptyBagOfWords(s, this);
  }

  /**
   * Checks if the specified word is present in the BagOfWords. Always
   * returns false, as this bag is empty and contains no elements.
   *
   * @param s the word to check for
   * @return false, as an empty bag contains no words.
   */
  @Override
  public Boolean contains(String s) {
    return false;
  }

  /**
   * Compares the specified object with this EmptyBagOfWords for equality.
   * Returns true if the object is also an instance of EmptyBagOfWords.
   *
   * @param o the object to be compared for equality with this EmptyBagOfWords
   * @return true if the specified object is also an instance of EmptyBagOfWords, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    return o instanceof EmptyBagOfWords;
  }

  /**
   * Returns the hash code value for this EmptyBagOfWords. As it represents an
   * empty structure, the hash code is zero.
   *
   * @return the hash code value for an empty bag.
   */
  @Override
  public int hashCode() {
    return EMPTY.hashCode();
  }

  /**
   * Returns a string representation of this EmptyBagOfWords.
   *
   * @return "EmptyBagOfWords", representing the empty state of the bag.
   */
  @Override
  public String toString() {
    return "EmptyBagOfWords";
  }
}
