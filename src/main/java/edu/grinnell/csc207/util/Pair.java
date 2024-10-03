package edu.grinnell.csc207.util;

public class Pair {
  //Fields for pair

  //Holds index of the character in the original string
  int index;
  //Holds the character value
  char val;

  /**
   * Constructor for Pair type
   * @param index
   * @param val
   * @return new pair with values index and val
   */
  public Pair(int index, char val){
    this.index = index;
    this.val = val;
  } //Pair


  /**
   * Returns index
   * @return index of this Pair
   */
  public int getIndex(){
    return this.index;
  } //getIndex

  /**
   * Returns val
   * @return value of this Pair
   */
  public char getVal(){
    return this.val;
  } //getVal
} //Pair
