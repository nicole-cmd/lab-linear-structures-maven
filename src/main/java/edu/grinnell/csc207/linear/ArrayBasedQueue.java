package edu.grinnell.csc207.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Queues implemented with arrays.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class ArrayBasedQueue<T> implements Queue<T> {
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The values stored in the queue.
   */
  T[] values;

  /**
   * The index of the front of the queue.
   */
  int front;

  /**
   * The number of elements in the queue.
   */
  int size;

  int back;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new queue that holds up to capacity elements.
   */
  @SuppressWarnings({"unchecked"})
  public ArrayBasedQueue(int capacity) throws Exception {
    if (capacity <= 0) {
      throw new Exception("Queues must have a positive capacity.");
    } // if (capacity <= 0)
    // Yay Java! It's not possible to say new T[capacity], so we use
    // this hack and suppress warnings with the annotation above.
    this.values = (T[]) new Object[capacity];
    this.front = 0;
    this.size = 0;
  } // ArayBasedQueue(int capacity)

  // +---------------+---------------------------------------------------
  // | Queue Methods |
  // +---------------+

  public int findFront() {
    return this.front % this.values.length;
  }

  public int findBack() {
    return this.back % this.values.length;
  }

  @Override
  public boolean isEmpty() {
    return this.size <= 0;
  } // isEmpty()

  @Override
  public boolean isFull() {
    return this.size >= this.values.length;
  } // isFull()

  @Override
  public void put(T val) throws Exception {
    if (this.isFull()) {
      throw new Exception("full");
    } // this.isFull()
    this.values[this.findBack()] = val;
    ++this.size;
    ++this.back;
  } // put(T)

  @Override
  public T get() throws Exception {
    if (this.isEmpty()) {
      throw new Exception("empty");
    } // if empty
    // Grab and clear the element at the front of the queue
    T result = this.values[this.findFront()];
      this.values[this.findFront()] = null;
      this.front++;
    
    // We're removing an element, so decrement the size
    --this.size;
    // And we're done
    return result;
  } // get(T)

  @Override
  public T peek() throws Exception {
    if (this.isEmpty()) {
      throw new Exception("empty");
    } // if empty
    return this.values[this.findFront()];
  } // peek()

  @Override
  public T dequeue() throws Exception {
    return this.get();
  } // dequeue

  @Override
  public void enqueue(T val) throws Exception {
    this.put(val);
  } // enqueue

  @Override
  public Iterator<T> iterator() {
    return new ArrayBasedQueueIterator<T>(this);
  } // iterator()

  // +----------------+--------------------------------------------------
  // | Helper Methods |
  // +----------------+

  /**
   * Get the index of the back of the queue. The back is where we add the next element.
   */
  

} // class ArrayBasedQueue<T>


class ArrayBasedQueueIterator<T> implements Iterator<T> {
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+
  int current;
  int end;
  ArrayBasedQueue<T> arr;
  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new iterator.
   */
  public ArrayBasedQueueIterator(ArrayBasedQueue<T> q) {
    arr = q;
    end = q.back;
    current = q.front;
  } // ArrayBasedQueueIterator

  // +---------+---------------------------------------------------------
  // | Methods |
  // +---------+

  @Override
  public T next() throws NoSuchElementException {
    if (!this.hasNext()) {
      throw new NoSuchElementException("no elements remain");
    } // if no elements
    return arr.values[current++];
    
  } // next()

  @Override
  public boolean hasNext() {
    
    return (current <= end);
  } // hasNext()

  @Override
  public void remove() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  } // remove()
} // ArrayBasedQueueIterator<T>
