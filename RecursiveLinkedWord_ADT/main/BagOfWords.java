package problem2;

/**
 * The BagOfWords interface defines the operations for an immutable bag
 * of words represented as strings. This interface allows adding words to the bag,
 * checking for the presence of words, and retrieving information such as size and equality.
 * The bag can contain duplicate words, and order is disregarded in equality.
 */
public interface BagOfWords {

  /**
   * Creates and returns an empty BagOfWords instance.
   *
   * @return a new instance of BagOfWords that contains no elements.
   */
  BagOfWords emptyBagOfWords();

  /**
   * Checks if this BagOfWords is empty.
   *
   * @return true if the BagOfWords contains no elements, false otherwise.
   */
  Boolean isEmpty();

  /**
   * Returns the total number of elements in this BagOfWords, including duplicates.
   *
   * @return the number of elements in the BagOfWords.
   */
  Integer size();

  /**
   * Returns a new BagOfWords that contains all elements in the current BagOfWords
   * plus the specified word.
   *
   * @param s the word to be added
   * @return a new BagOfWords containing all elements in the current bag plus the new word.
   */
  BagOfWords add(String s);

  /**
   * Checks if the specified word is present in the BagOfWords.
   *
   * @param s the word to check for
   * @return true if the BagOfWords contains the word, false otherwise.
   */
  Boolean contains(String s);

  /**
   * Compares the specified object with this BagOfWords for equality. Two BagOfWords
   * instances are considered equal if they contain the same words, regardless of order.
   *
   * @param o the object to be compared for equality with this BagOfWords
   * @return true if the specified object is equal to this BagOfWords, false otherwise.
   */
  @Override
  boolean equals(Object o);

  /**
   * Returns the hash code value for this BagOfWords. The hash code of a BagOfWords
   * is computed based on its contents and disregards order.
   *
   * @return the hash code value for this BagOfWords.
   */
  @Override
  int hashCode();
}
