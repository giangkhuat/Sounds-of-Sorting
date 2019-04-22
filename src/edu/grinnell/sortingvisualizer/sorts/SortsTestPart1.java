package edu.grinnell.sortingvisualizer.sorts;

import static org.junit.jupiter.api.Assertions.*;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;
import edu.grinnell.sortingvisualizer.sorts.utils.TestUtils;

class SortsTestPart1<T extends Comparable<T>> {

  // +-----------------+--------------------------------------------------
  // | Ultilities |
  // +-----------------+/*

  /**
   * Test a sorting function, given that function, an expected Integer result and an input array.
   */

  @SuppressWarnings("hiding")
  <T extends Comparable<T>> void checkSortInts(Consumer<Integer[]> sorter, Integer[] expected,
      Integer[] values) {
    sorter.accept(values);
    assertArrayEquals(expected, values);
  } // checkSortInts

  /**
   * Test a sorting function, given that function, an expected String result and an input array.
   */

  @SuppressWarnings("hiding")
  <T extends Comparable<T>> void checkSortStrings(Consumer<String[]> sorter, String[] expected,
      String[] values) {
    sorter.accept(values);
    assertArrayEquals(expected, values);
  } // checkSortStrings

  /**
   * Permute an already sorted array (Ints)
   */

  @SuppressWarnings("hiding")
  <T extends Comparable<T>> void checkSortedInts(Consumer<Integer[]> sorter, Integer[] sorted) {
    Integer[] copy = sorted.clone();
    TestUtils.randomlyPermute(copy);
    checkSortInts(sorter, sorted, copy);
  } // checkSortedString

  /**
   * Permute an already sorted array (String)
   */

  @SuppressWarnings("hiding")
  <T extends Comparable<T>> void checkSortedStrings(Consumer<String[]> sorter, String[] sorted) {
    String[] copy = sorted.clone();
    TestUtils.randomlyPermute(copy);
    checkSortStrings(sorter, sorted, copy);
  } // checkSortedString


  // +----------------------------------+--------------------------------------------------
  // | Generic Tests and Applied Tests |
  // +----------------------------------+

  void testEmptyInts(Consumer<Integer[]> sorter) {
    Integer[] testArray = new Integer[0];
    checkSortedInts(sorter, testArray.clone()); 
  } // testEmptyInts

  @Test
  void testEmptyInts() {
    testEmptyInts((arr) -> SortsPart1.insertionSort(arr));
    testEmptyInts((arr) -> SortsPart1.selectionSort(arr));
    testEmptyInts((arr) -> SortsPart1.mergeSort(arr));
    testEmptyInts((arr) -> SortsPart1.quickSort(arr));
    testEmptyInts((arr) -> SortsPart1.bubbleSort(arr));
    
    // create array 
    
    
    
    
  } // testEmptyInts

  void testEmptyStrings(Consumer<String[]> sorter) {
    String[] testArray = new String[0];
    checkSortedStrings(sorter, testArray);
  } // testEmptyStrings

  @Test
  void testEmptyStrings() {
    testEmptyStrings((arr) -> SortsPart1.insertionSort(arr));
    testEmptyStrings((arr) -> SortsPart1.selectionSort(arr));
    testEmptyStrings((arr) -> SortsPart1.mergeSort(arr));
    testEmptyStrings((arr) -> SortsPart1.quickSort(arr));
    testEmptyStrings((arr) -> SortsPart1.bubbleSort(arr));
  } // testEmptyStrings


  void testOrderedInts(Consumer<Integer[]> sorter) {
    for (int size = 1; size < 20; size++) {
      Integer[] sorted = new Integer[size];
      for (int i = 0; i < size; i++) {
        Integer val = Integer.valueOf(i);
        sorted[i] = val;
      } // for
      checkSortedInts(sorter, sorted);
    } // for
  } // testOrderedInts

  @Test
  void testOrderedInts() {
    testOrderedInts((arr) -> SortsPart1.selectionSort(arr));
    testOrderedInts((arr) -> SortsPart1.mergeSort(arr));
    testOrderedInts((arr) -> SortsPart1.quickSort(arr));
    testOrderedInts((arr) -> SortsPart1.insertionSort(arr));
    testOrderedInts((arr) -> SortsPart1.bubbleSort(arr));
  } // testOrderedInts


  void testOrderedStrings(Consumer<String[]> sorter) {
    for (int size = 1; size < 20; size++) {
      String[] sorted = new String[size];
      for (int i = 0; i < size; i++) {
        char val = (char) (i + 97);
        String str = Character.toString(val);
        sorted[i] = str;
      } // for
      checkSortedStrings(sorter, sorted);
    } // for
  } // testOrderedStrings

  @Test
  void testOrderedStrings() {
    testOrderedStrings((arr) -> SortsPart1.insertionSort(arr));
    testOrderedStrings((arr) -> SortsPart1.selectionSort(arr));
    testOrderedStrings((arr) -> SortsPart1.mergeSort(arr));
    testOrderedStrings((arr) -> SortsPart1.quickSort(arr));
    testOrderedStrings((arr) -> SortsPart1.bubbleSort(arr));
  } // testOrderedStrings


  void testBackwardsInts(Consumer<Integer[]> sorter) {
    for (int size = 1; size < 20; size++) {
      Integer[] sorted = new Integer[size];
      Integer[] backwards = new Integer[size];
      for (int i = 0; i < size; i++) {
        backwards[i] = Integer.valueOf(size - i);
        sorted[i] = Integer.valueOf(i + 1);
      } // for
      checkSortInts(sorter, sorted, backwards);
    } // for
  } // testBackwards

  @Test
  void testBackwardsInts() {
    testBackwardsInts((arr) -> SortsPart1.insertionSort(arr));
    testBackwardsInts((arr) -> SortsPart1.selectionSort(arr));
    testBackwardsInts((arr) -> SortsPart1.mergeSort(arr));
    testBackwardsInts((arr) -> SortsPart1.quickSort(arr));
    testBackwardsInts((arr) -> SortsPart1.bubbleSort(arr));
  } // testBackwardsInts

  void testBackwardsStrings(Consumer<String[]> sorter) {
    for (int size = 1; size < 20; size++) {
      String[] sorted = new String[size];
      String[] backwards = new String[size];
      for (int i = 0; i < size; i++) {
        // get the character representing the number from the end size of the array
        // add 97 to start from 'a'
        // minus 1 since size start from 1
        char charLast = (char) (size - i + 96);
        char charBeg = (char) (i + 97);
        String strLast = Character.toString(charLast);
        String strBeg = Character.toString(charBeg);
        // put in the two arrays
        backwards[i] = strLast;
        sorted[i] = strBeg;
      } // for
      checkSortStrings(sorter, sorted, backwards);
    } // for
  } // testBackwardsStrings

  @Test
  void testBackwardsStrings() {
    testBackwardsStrings((arr) -> SortsPart1.insertionSort(arr));
    testBackwardsStrings((arr) -> SortsPart1.selectionSort(arr));
    testBackwardsStrings((arr) -> SortsPart1.mergeSort(arr));
    testBackwardsStrings((arr) -> SortsPart1.quickSort(arr));
    testBackwardsStrings((arr) -> SortsPart1.bubbleSort(arr));
  } // testBackwardsInts


  void testRandom(Consumer<Integer[]> sorter) {
    for (int trials = 0; trials < 5; trials++) {
      // Some comparatively small ones
      for (int size = 1; size < 20; size++) {
        Integer[] sorted = TestUtils.randomIntegers(size);
        checkSortedInts(sorter, sorted);
      } // for size
        // Some larger ones
      for (int size = 30; size < 1000; size = size * 2 + 1) {
        Integer[] sorted = TestUtils.randomIntegers(size);
        checkSortedInts(sorter, sorted);
      } // for size
    } // for trials
  } // testRandom()

  @Test
  void testRandomInts() {
    testRandom((arr) -> SortsPart1.insertionSort(arr));
    testRandom((arr) -> SortsPart1.selectionSort(arr));
    testRandom((arr) -> SortsPart1.mergeSort(arr));
    testRandom((arr) -> SortsPart1.quickSort(arr));
    testRandom((arr) -> SortsPart1.bubbleSort(arr));
  } // testRandom
}
