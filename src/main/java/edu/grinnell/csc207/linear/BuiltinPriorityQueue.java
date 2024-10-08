package edu.grinnell.csc207.linear;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Priority queues implemented with the standard Java Priority Queues. 
 * (I'd call this an adapter class, but it's implemented by delegating 
 * most of the methods.)
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class BuiltinPriorityQueue<T> implements PriorityQueue<T> {
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The underlying priority queue.
   */
  java.util.PriorityQueue<T> pq;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new priority queue that holds up to capacity elements and 
   * uses order to compare elements.
   */
  public BuiltinPriorityQueue(int capacity, Comparator<T> order) throws Exception {
    this.pq = new java.util.PriorityQueue<T>(capacity, order);
  } // BuiltinPriorityQueue(int capacity)

  // +------------------------+------------------------------------------
  // | Priority Queue Methods |
  // +------------------------+

  @Override
  public boolean isEmpty() {
    return pq.size() <= 0;
  } // isEmpty()

  @Override
  public boolean isFull() {
    // It looks like the standard ones don't fill
    return false;
  } // isFull()

  @Override
  public void put(T val) throws Exception {
    pq.add(val);
  } // put(T)

  @Override
  public T get() throws Exception {
    return (T) pq.remove();
  } // get(T)

  @Override
  public T peek() throws Exception {
    return (T) pq.peek();
  } // peek()

  @Override
  @SuppressWarnings({"unchecked"})
  // Handle different forms of comparator
  public Comparator<T> comparator() {
    return (Comparator<T>) pq.comparator();
  } // comparator()

  @Override
  public Iterator<T> iterator() {
    return pq.iterator();
  } // iterator()

  class StringComparator implements Comparator<String> {
    public int compare(String str1, String str2) {
      // Efficiency hack: If two strings occupy the same memory
      // they are equal.
      if (str1 == str2) { return 0; }
      // Safety check: If either string is null, compareTo may fail,
      // so we make sure neither is null.  We treat null as "smaller"
      // than any other string.
      if (str1 == null) { return -1; }
      if (str2 == null) { return 1; }
      // Finally, we can use the built-in `compareTo` method.
      return str1.compareTo(str2);
    } // compare(String, STring)
  } // StringComparator
  
  class IntComparator implements Comparator<Integer> {
    public int compare(Integer i, Integer j) {
      // While this method sometimes gets implemented as i-j, that
      // implementation presents overflow risks, so we choose a
      // somewhat more verbose approach.
      if (i < j) { return -1; }
      else if (j < i) { return 1; }
      else return 0;
    } // compare(Integer, Integer)
  } // IntegerComparator
  
} // class BuiltinPriorityQueue<T>

