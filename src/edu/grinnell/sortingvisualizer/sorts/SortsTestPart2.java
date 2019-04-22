package edu.grinnell.sortingvisualizer.sorts;

import static org.junit.Assert.assertArrayEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import edu.grinnell.sortingvisualizer.sortevents.SortEvent;
import edu.grinnell.sortingvisualizer.sorts.utils.TestUtils;


class SortsTestPart2 {

  @Test
  public void testSelectionSort() {
    testSort("selection");
  }

  @Test
  public void testInsertionSort() {
    testSort("insertion");
  }

  @Test
  public void testBubbleSort() {
    testSort("bubble");
  }

  @Test
  public void testMergeSort() {
    testSort("merge");
  }

  @Test
  public void testQuickSort() {
    testSort("quick");
  }

  public <T extends Comparable<T>> List<SortEvent<T>> test(String sortType, T[] arr) {
    switch (sortType) {
      case "selection":
        return Sorts.selectionSort(arr);
      case "insertion":
        return Sorts.insertionSort(arr);
      case "bubble":
        return Sorts.bubbleSort(arr);
      case "merge":
        return Sorts.mergeSort(arr);
      case "quick":
        return Sorts.quickSort(arr);
      default:
        throw new IllegalArgumentException("No apppropriate sort method available");
    } // switch
  }

  public <T> void testSort(String sortType) {
    // Empty array
    Integer[] emptyArray = new Integer[0];
    List<SortEvent<Integer>> events1 = test(sortType, emptyArray.clone());
    Sorts.eventSort(emptyArray, events1);
    assertArrayEquals("empty array", emptyArray, emptyArray.clone());

    // One element
    Integer[] singleton = {89347589};
    List<SortEvent<Integer>> events2 = test(sortType, singleton.clone());
    Sorts.eventSort(singleton, events2);
    assertArrayEquals("one element", singleton, singleton.clone());

    // Duplicate elements and even number of elements
    Integer[] arr4 = {5, 8, 3, 6, 9, 3, 1, 0, 8, 5, 6, 1};
    Integer[] arr4a = {5, 8, 3, 6, 9, 3, 1, 0, 8, 5, 6, 1};
    List<SortEvent<Integer>> events4 = test(sortType, arr4);
    Sorts.eventSort(arr4a, events4);
    assertArrayEquals("duplicate elements & even number of elements", arr4, arr4a);

    // Negative elements
    Integer[] arr5 = {-100, -200, 60, 50, -30, 0, 1};
    Integer[] arr5a = {-100, -200, 60, 50, -30, 0, 1};
    List<SortEvent<Integer>> events5 = test(sortType, arr5);
    Sorts.eventSort(arr5a, events5);
    assertArrayEquals("negative elements", arr5, arr5a);

    // Ordered array
    for (int size = 1; size < 20; size++) {
      Integer[] sorted = new Integer[size];
      for (int i = 0; i < size; i++) {
        Integer val = Integer.valueOf(i);
        sorted[i] = val;
      } // for
      List<SortEvent<Integer>> events7 = test(sortType, sorted.clone());
      Sorts.eventSort(sorted, events7);
      assertArrayEquals("ordered array", sorted, sorted.clone());
    } // for

    // Backwards array
    for (int size = 1; size < 20; size++) {
      Integer[] sorted = new Integer[size];
      Integer[] backwards = new Integer[size];
      for (int i = 0; i < size; i++) {
        backwards[i] = Integer.valueOf(size - i);
        sorted[i] = Integer.valueOf(i + 1);
      } // for
      List<SortEvent<Integer>> events8 = test(sortType, backwards.clone());
      Sorts.eventSort(backwards, events8);
      assertArrayEquals("ordered array", backwards, sorted);
    } // for

    // Random Array
    for (int trials = 0; trials < 5; trials++) {
      // Some comparatively small ones
      for (int size = 1; size < 20; size++) {
        Integer[] sorted = TestUtils.randomIntegers(size);
        List<SortEvent<Integer>> events9 = test(sortType, sorted.clone());
        Sorts.eventSort(sorted, events9);
        assertArrayEquals("small random array", sorted, sorted.clone());
      } // for size
        // Some larger ones
      for (int size = 30; size < 1000; size = size * 2 + 1) {
        Integer[] sorted = TestUtils.randomIntegers(size);
        List<SortEvent<Integer>> events10 = test(sortType, sorted.clone());
        Sorts.eventSort(sorted, events10);
        assertArrayEquals("large random array", sorted, sorted.clone());
      } // for size
    } // for trials
  } // testSort
}
