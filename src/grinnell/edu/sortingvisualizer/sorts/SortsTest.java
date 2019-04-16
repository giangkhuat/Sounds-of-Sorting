package grinnell.edu.sortingvisualizer.sorts;

import static org.junit.jupiter.api.Assertions.*;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;
import grinnell.edu.sortingvisualizer.sorts.utils.TestUtils;

class SortsTest<T extends Comparable<T>> {

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
    for (int i = 0; i < values.length ; i++) {
      System.out.print(values[i]);
      System.out.print(expected[i]);
    }
    System.out.println();
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
    checkSortedInts(sorter, testArray);
  } // testEmptyInts

  @Test
  void testEmptyInts() {
    testEmptyInts((arr) -> Sorts.insertionSort(arr));
    testEmptyInts((arr) -> Sorts.selectionSort(arr));
    testEmptyInts((arr) -> Sorts.mergeSort(arr));
    testEmptyInts((arr) -> Sorts.quickSort(arr));
  } // testEmptyInts

  void testEmptyStrings(Consumer<String[]> sorter) {
    String[] testArray = new String[0];
    checkSortedStrings(sorter, testArray);
  } // testEmptyStrings

  @Test
  void testEmptyStrings() {
    testEmptyStrings((arr) -> Sorts.insertionSort(arr));
    testEmptyStrings((arr) -> Sorts.selectionSort(arr));
    testEmptyStrings((arr) -> Sorts.mergeSort(arr));
    testEmptyStrings((arr) -> Sorts.quickSort(arr));
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
  //  testOrderedInts((arr) -> Sorts.selectionSort(arr));
    testOrderedInts((arr) -> Sorts.mergeSort(arr));
   // testOrderedInts((arr) -> Sorts.quickSort(arr));
    //testOrderedInts((arr) -> Sorts.insertionSort(arr));
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
    testOrderedStrings((arr) -> Sorts.insertionSort(arr));
    testOrderedStrings((arr) -> Sorts.selectionSort(arr));
    // testOrderedStrings((arr)->Sorts.mergeSort(arr));
    testOrderedStrings((arr) -> Sorts.quickSort(arr));
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
    testBackwardsInts((arr) -> Sorts.insertionSort(arr));
    testBackwardsInts((arr) -> Sorts.selectionSort(arr));
    testBackwardsInts((arr) -> Sorts.mergeSort(arr));
    testBackwardsInts((arr) -> Sorts.quickSort(arr));
  } // testBackwardsInts

  void testBackwardsStrings(Consumer<String[]> sorter) {
    for (int size = 1; size < 20; size++) {
      String[] sorted = new String[size];
      String[] backwards = new String[size];
      for (int i = 0; i < size; i++) {
        // get the character representing the number from the end size of the array
        char charLast = (char) (size - i + 26);
        char charBeg = (char) (i + 26);
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
    testBackwardsStrings((arr) -> Sorts.insertionSort(arr));
    testBackwardsStrings((arr) -> Sorts.selectionSort(arr));
    testBackwardsStrings((arr) -> Sorts.mergeSort(arr));
    testBackwardsStrings((arr) -> Sorts.quickSort(arr));
  } // testBackwardsInts

  // @Test
  // void testRandom() {
  // for (int trials = 0; trials < 5; trials++) {
  // // Some comparatively small ones
  // for (int size = 1; size < 20; size++) {
  // checkQuicksort(TestUtils.randomInts(size));
  // } // for size
  // // Some larger ones
  // for (int size = 30; size < 1000; size = size*2 + 1) {
  // checkQuicksort(TestUtils.randomInts(size));
  // } // for size
  // } // for trials
  // } // testRandom()
}


