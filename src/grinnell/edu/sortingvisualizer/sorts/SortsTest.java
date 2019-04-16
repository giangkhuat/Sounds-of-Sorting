package grinnell.edu.sortingvisualizer.sorts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import grinnell.edu.sortingvisualizer.sorts.utils.TestUtils;

class SortsTest<T extends Comparable<T>> {
  
  // +-----------------+--------------------------------------------------
  // | Insertion Sort  |
  // +-----------------+
  
  /*
   * Ultilities
   */

  /**
   * Test Insertionsort, given an expected result and an input array.
   */
    
  <T extends Comparable<T>> void checkInsertionsort(T[] expected, T[] values) {
    Sorts.insertionSort(values);
    assertArrayEquals(expected, values);
  } // checkInsertionsort
  
  /**
   * Permute an already sorted array, 
   */
    
  <T extends Comparable<T>> void checkInsertionsort(T[] sorted) {
    T[] copy = sorted.clone();
    TestUtils.randomlyPermute(copy);
    checkInsertionsort(sorted, copy);
  } // checkQuicksort

/*
 * Tests
 */

  @SuppressWarnings("unchecked")
  @Test
  // <T extends Comparable<T>>
   void testEmpty() {
    checkInsertionsort((T[]) (new Object[0]));
  } // testEmpty

  @SuppressWarnings("unchecked")
  @Test
  <T extends Comparable<T>> void testOrderedInts() {
    for (int size = 1; size < 20; size++) {
      @SuppressWarnings("unchecked")
      T[] sorted = (T[]) new Object[size];
      for (int i = 0; i < size; i++) {
        Integer val = Integer.valueOf(i);
        sorted[i] = (T) val;
      } // for
      checkInsertionsort(sorted, sorted.clone());
    } // for
  } // testOrderedInts
  
  
  @SuppressWarnings("unchecked")
  @Test
  <T extends Comparable<T>> void testOrderedStrings() {
    for (int size = 1; size < 20; size++) {
      @SuppressWarnings("unchecked")
      T[] sorted = (T[]) new Object[size];
      for (int i = 0; i < size; i++) {
        char val = (char) i;
        String str = Character.toString(val);
        sorted[i] = (T) str;
      } // for
      checkInsertionsort(sorted, sorted.clone());
    } // for
  } // testOrderedStrings
  
  @SuppressWarnings("unchecked")
  @Test
  <T extends Comparable<T>> void testBackwardsInts() {
    for (int size = 1; size < 20; size++) {
      T[] sorted = (T[]) new Object[size];
      T[] backwards = (T[]) new Object[size];
      for (int i = 0; i < size; i++) {
        backwards[i] =  (T) Integer.valueOf(size-i);
        sorted[i] = (T) Integer.valueOf(i+1);
      } // for
      checkInsertionsort(sorted, backwards);
    } // for
  } // testBackwards

  
  @SuppressWarnings("unchecked")
  @Test
  <T extends Comparable<T>> void testBackwardsStrings() {
    for (int size = 1; size < 20; size++) {
      String[] sorted = new String[size];
      String[] backwards = new String[size];
      for (int i = 0; i < size; i++) {
        // get the character representing the number from the end size of the array
        char charLast = (char) (size - i);
        char charBeg = (char) i;
        String strLast = Character.toString(charLast);
        String strBeg = Character.toString(charBeg);
        // put in the two arrays
        backwards[i] =   strLast;
        sorted[i] = strBeg;
      } // for
      checkInsertionsort(sorted, backwards);
    } // for
  } // testBackwardsString
  
  //@Test
//  void testRandom() {
//    for (int trials = 0; trials < 5; trials++) {
//      // Some comparatively small ones
//      for (int size = 1; size < 20; size++) {
//        checkQuicksort(TestUtils.randomInts(size));
//      } // for size
//      // Some larger ones
//      for (int size = 30; size < 1000; size = size*2 + 1) {
//        checkQuicksort(TestUtils.randomInts(size));
//      } // for size
//    } // for trials
//  } // testRandom()
}
