package edu.grinnell.csc207.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple array-based stack.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class LinkedQueue<T> implements Queue<T> {
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The front of the queue.
   */
  Node<T> front;
  
  /**
   * The back of the queue.
   */
  Node<T> back;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new queue.
   */
  public LinkedQueue() {
    this.front = null;
    this.back = null;
  } // LinkedQueue(int)

  // +-------------------------+-----------------------------------------
  // | LinearStructure Methods |
  // +-------------------------+

  @Override
  public boolean isEmpty() {
    return this.front == null;
  } // isEmpty()

  @Override
  public boolean isFull() {
    return false;
  } // isFull()

  @Override
  public T peek() throws Exception {
    return this.front.value;
  } // peek()

  @Override
  public void put(T val) throws Exception {
    if (this.isFull()) {
      // this shouldn't happen; linked structures do not
      // get full
      throw new Exception("cannot add to a full structure");
    } // if
    Node<T> newNode = new Node<T>(val, null);
    if (this.isEmpty()) {
      this.front = newNode;
      this.back = this.front;
    } else {
    this.back.next = newNode;
    this.back = this.back.next;
    } // if/else
  } // put(T);

  @Override
  public T get() throws Exception {
    if (this.isEmpty()) {
      throw new Exception("cannot get values from the empty queue");
    } // if empty
    Node<T> current = this.front;
    this.front = this.front.next;
    return current.value;
  } // get()

  @Override
  public Iterator<T> iterator() {
    return new LinkedQueueIterator<T>(this);
  } // iterator()

  // +---------------+---------------------------------------------------
  // | Queue Methods |
  // +---------------+

  @Override
  public void enqueue(T val) throws Exception {
    this.put(val);
  } // enqueue(T)

  @Override
  public T dequeue() throws Exception {
    return this.get();
  } // dequeue()

} // LinkedQueue<T>


class LinkedQueueIterator<T> implements Iterator<T> {
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The next node in the iteration.
   */
  Node<T> next;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new iterator.
   */
  public LinkedQueueIterator(LinkedQueue<T> lq) {
    this.next = lq.front;
  } // LinkedQueueIterator

  // +---------+---------------------------------------------------------
  // | Methods |
  // +---------+

  @Override
  public T next() throws NoSuchElementException {
    T result = this.next.value;
    this.next = this.next.next;
    return result;
  } // next()

  @Override
  public boolean hasNext() {
    return (this.next != null);
  } // hasNext()

  @Override
  public void remove() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  } // remove()
} // LinkedQueueIterator<T>

// class LinkedQueueIterator<T> implements Iterable<T> {
//   /**
//    * The next node in the iteration.
//    */
//    Node<T> next;

//   /**
//    * The position we are at.
//    */
//    int i = 0;

//   // +--------------+----------------------------------------------------
//   // | Constructors |
//   // +--------------+
//   /**
//    * Create a new iterator.
//    */
//    public LinkedQueueIterator(LinkedQueue<T> lq) {
//      this.next = lq.front;
//    } // LinkedQueueIterator

//     public Iterator<T> iterator() {
//     return new Iterator<T>() {
//       @Override 
//       public T next() throws NoSuchElementException {
//         if (!this.hasNext()) {
//           throw new NoSuchElementException();
//         } // if (hasNext())
//         Node<T> current = LinkedQueueIterator.this.next;
//         LinkedQueueIterator.this.next = LinkedQueueIterator.this.next.next;
//         LinkedQueueIterator.this.i++;
//         return current.getValue();
//       } // next()

//       @Override
//       public boolean hasNext() {
//         return (LinkedQueueIterator.this.next != null);
//       } // hasNext()

//       @Override
//       public void remove() {
//         throw new UnsupportedOperationException();
//       } // remove()
//     }; // new Iterator<T>
//   } // iterator()
//} // class LinkedQueueIterator<T>