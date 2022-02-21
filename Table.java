// I am the sole author of the work in this repository.

import structure5.*;

/**
* A Table holds a collection of associtations which have keys and
* to each key there is a associated FrequencyList
*/
public class Table<K, V> {
  protected Vector<Association<K, FrequencyList<V>>> keyWithFrequency;

  /** Construct an empty table */
  public Table() {
    Vector<Association<K, FrequencyList<V>>> v = new Vector<Association<K, FrequencyList<V>>>();
    this.keyWithFrequency = v;
  }

  /** Construct a table with an initial association with key and value in a FrequencyList
  *
  * @param key key which to set as key to inital association in Table
  * @param val value which to add to FrequencyList as inital value
  */
  public Table(K key, V val) {
    // create FrequencyList with initial value being val
    FrequencyList<V> freqList = new FrequencyList<V>(val);
    // create association which will connect key and FrequencyList
    Association<K, FrequencyList<V>> association = new Association<K, FrequencyList<V>>(key, freqList);
    // create empty Vector
    Vector<Association<K, FrequencyList<V>>> v = new Vector<Association<K, FrequencyList<V>>>();
    // add association to the vector
    v.add(association);
    this.keyWithFrequency = v;
  }

  /**
  * Updates the table as follows
  * If key already exists in the table, update its FrequencyList
  * by adding value to it
  * Otherwise, create a FrequencyList for key and add value to it
  *
  * @param key is the desired k-length sequence
  * @param val is the value to add to the FrequencyList of key
  */
  public void add(K key, V val) {
    int index = getIndexOfKey(key);
    // not found
    if (index == -1) {
      // add new Association to Vector
      FrequencyList<V> freqList = new FrequencyList<V>(val);
      Association<K, FrequencyList<V>> association = new Association<K, FrequencyList<V>>(key, freqList);
      this.keyWithFrequency.add(association);
    } else {
      // add val to FrequencyList

      // get Association for specified value
      Association<K, FrequencyList<V>> association = this.keyWithFrequency.get(index);

      // add val to FrequencyList which is value of association
      association.getValue().add(val);
      // set updated element to the intance variable
      this.keyWithFrequency.setElementAt(association, index);
    }
  }

  /**
  * If key is in the table, return one of the values from
  * its FrequencyList with probability equal to its relative frequency
  * Otherwise, determine a reasonable value to return
  *
  * @param key is the k-length sequence whose frequencies we want to use
  * @return a value selected from the corresponding FrequencyList
  */
  public V choose(K key) {
    int index = getIndexOfKey(key);

    // use try catch to catch if index is not found (will only be not found if index is -1)
    // if index is not found we asked for a key that does not exist in our Table
    try {
      // get specified association
      Association<K, FrequencyList<V>> association = this.keyWithFrequency.get(index);
      // return a choosen character from the FrequencyList
      return association.getValue().choose();
    } catch (Exception e) {
      // this means index is -1 so throw the error message
      System.out.println("Something went wrong. You asked for a key that doesn't exist.");
      throw e;
    }
  }

  /**
  * method to get the index of a certain key.
  *
  * @return the 0-indexed index of key or -1 if not found
  */
  private int getIndexOfKey(K key) {
    return this.keyWithFrequency.indexOf(new Association<K, FrequencyList<V>>(key, new FrequencyList<V>()));
  }

  /** Produce a string representation of the Table
  * @return a String representing this Table
  */
  public String toString() {
    String tableToString = "";
    // loop thru the Table rows and print the Key and the FrequencyList
    for (int i = 0; i < this.keyWithFrequency.size(); i++) {
      tableToString +=
                  this.keyWithFrequency.elementAt(i).getKey()
                  + ": "
                  + this.keyWithFrequency.elementAt(i).getValue().toString()
                  + "\n";
    }
    return tableToString;
  }

  /**
  * Gets the number of rows in the table
  *
  * @return an int which represents the number of rows in table
  */
  public int size() {
    return this.keyWithFrequency.size();
  }

  // Use main to test your Table class
  public static void main(String[] args) {
    // all works!!
  }

}
