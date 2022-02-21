// I am the sole author of the work in this repository.

import structure5.*;
import java.util.Random;

/**
 * A FrequencyList stores a list of elements and can choose
 * an element at random from list.
 * <p>
 * Genertic class
 */
public class FrequencyList<E> {
  protected Vector<E> freqList;

  /** FrequencyList constructor. Creates an empty vector */
  public FrequencyList() {
    Vector<E> v = new Vector<E>();
    this.freqList = v;
  }

  /** Constructs a FrequencyList with an initial value */
  public FrequencyList(E val) {
    // create empty Vector
    Vector<E> v = new Vector<E>();
    // add initial val to Vector
    v.add(val);
    this.freqList = v;
  }

  /** add(E val)
  * Add value to stored vector
  * @param val the value to add to the FrequencyList
  */
  public void add(E val) {
    this.freqList.add(val);
  }

  /** Selects a value from this FrequencyList
  * @return a value from the FrequencyList at random
  */
  public E choose() {
    Random r = new Random();
    // generate a random number between 0 and the last index of the FrequencyList
    int randInt = r.nextInt(freqList.size());
    // return the selected value from Vector
    return this.freqList.elementAt(randInt);
  }

  /** Produce a string representation of the FrequencyList
   * @return a String representing the FrequencyList
   */
  public String toString() {
    String freqListString = "";
    // loop thru the Vector and add all elements as a String
    for (int i = 0; i < this.freqList.size(); i++) {
      freqListString += this.freqList.elementAt(i) + " ";
    }
    return freqListString;
  }

  // Use main to test your FrequencyList class
  public static void main(String[] args) {
    // all works!!
  }

}
