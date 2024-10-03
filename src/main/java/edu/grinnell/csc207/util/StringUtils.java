package edu.grinnell.csc207.util;

import edu.grinnell.csc207.linear.Stack;
import edu.grinnell.csc207.linear.LinkedStack;

import java.io.PrintWriter;

/**
 * Assorted utilities for working with strings.
 * 
 * @author Samuel A. Rebelsky
 * @author Alyssa Ryan
 * @author Nicole Gorrell
 */ 
public class StringUtils {
  // +------------------+--------------------------------------------
  // | Provided methods |
  // +------------------+
    
  /**
   * Determine whether the parens match in string.
   */
  public static boolean checkMatching(String str) throws Exception{
    Stack<Pair> parens = new LinkedStack<Pair>();
    PrintWriter error = new PrintWriter(System.err, true);
    Pair check = null;
    
    for(int i=0; i<str.length(); i++){
      if(str.charAt(i) == '(' || str.charAt(i) == '['){
        Pair temp = new Pair(i, str.charAt(i));
        parens.push(temp);
      } else if(str.charAt(i) == ')' || str.charAt(i) == ']'){
        if(parens.isEmpty() == false){
          check = parens.pop();
          
          if(check.val == '(' && str.charAt(i)!= ')'){
            error.printf("Error. %c at index %d, but %c at index %d does not match for closing.\n", check.val, check.index, str.charAt(i), i);
            return false;
          } else if(check.val == '[' && str.charAt(i)!= ']'){
            error.printf("Error. %c at index %d, but %c at index %d does not match for closing.\n", check.val, check.index, str.charAt(i), i);
            return false;
          }
        }
        else{
          error.printf("Error. Characters in string, without any closers:   ");
          for(int j=i; j<str.length(); j++){
            error.printf("%c     ", str.charAt(j));
          }
          while(parens.isEmpty()==false){
            Pair cerror = parens.pop();
            error.printf("%c     ", cerror.val);
          }
          error.printf("\n");
          return false;
        }
      }
    }
    return parens.isEmpty();
  } // checkMatching
} // class StringUtils    

