package edu.grinnell.sortingvisualizer.sorts;

import java.util.ArrayList;
import java.util.List;
import edu.grinnell.sortingvisualizer.sortevents.CompareEvent;
import edu.grinnell.sortingvisualizer.sortevents.CopyEvent;
import edu.grinnell.sortingvisualizer.sortevents.SortEvent;
import edu.grinnell.sortingvisualizer.sortevents.SwapEvent;

public class Sorts<T extends Comparable<T>> {

  /**
   * Selection sort
   * 
   * @param arr
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> selectionSort(T[] arr) {
    List<SortEvent<T>> eventList = new ArrayList<>();
    int n = arr.length;
    if (n == 0) {
      return eventList;
    }
    for (int i = 0; i < n - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        if (arr[j].compareTo(arr[minIndex]) < 0) {
          eventList.add(new CompareEvent<T>(j, minIndex));
          minIndex = j;
        }
      }
      swap(arr, i, minIndex);
      eventList.add(new SwapEvent<T>(i, minIndex));
    }
    return eventList;
  }

  /**
   * Insertion sort
   * 
   * @param arr
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> insertionSort(T[] arr) {
    int n = arr.length;
    List<SortEvent<T>> eventList = new ArrayList<>();
    if (n == 0) {
      return eventList;
    }
    for (int i = 1; i < n; i++) {
      T val = arr[i];
      int j = i - 1;
      while (j >= 0 && (arr[j].compareTo(val) > 0)) {
        eventList.add(new CompareEvent<T>(j - 1, j));
        arr[j + 1] = arr[j];
        eventList.add(new CopyEvent<T>(j + 1, arr[j]));
        j--;
      }
      eventList.add(new CopyEvent<T>(j + 1, val));
      arr[j + 1] = val;
    }
    return eventList;
  }

  /**
   * Merge sort
   * 
   * @param arr
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> mergeSort(T[] arr) {
    List<SortEvent<T>> eventList = new ArrayList<>();
    if (arr.length == 0) {
      return eventList;
    }
    mergeSortHelper(arr, 0, arr.length, eventList);
    return eventList;
  }

  /*
   * Procedure to perform merge for mergeSort
   */

  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void merge(T[] arr, int lo, int hi, int mid,
      List<SortEvent<T>> events) {
    int i = lo;
    int j = mid, k = 0;

    Object[] temp = (T[]) new Object[hi - lo];

    while (i < mid && j < hi) {
      events.add(new CompareEvent<T>(i, j));
      if (arr[i].compareTo(arr[j]) <= 0) {
        temp[k++] = arr[i++];
      } else {
        temp[k++] = arr[j++];
      }
    } // while

    // Copies the remaining elements into temp
    while (i < mid) {
      temp[k++] = arr[i++];
    }
    while (j < hi) {
      temp[k++] = arr[j++];
    }
    // Put elements in temp back into arr
    for (int n = 0; n < hi - lo; n++) {
      arr[n + lo] = (T) temp[n];
      events.add(new CopyEvent<T>(n + lo, (T) temp[n]));
    }
  }

  /*
   * Procedure mergeSortHelper
   */

  public static <T extends Comparable<T>> void mergeSortHelper(T[] arr, int lo, int hi,
      List<SortEvent<T>> events) {
    if (hi - lo > 1) {
      int mid = lo + (hi - lo) / 2;
      // Sort the two halves
      mergeSortHelper(arr, lo, mid, events);
      mergeSortHelper(arr, mid, hi, events);
      // Merge two sorted halves
      merge(arr, lo, hi, mid, events);
    }
  }

  /**
   * Quick sort
   * 
   * @param arr
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> quickSort(T[] arr) {
    List<SortEvent<T>> eventList = new ArrayList<>();
    if (arr.length == 0) {
      return eventList;
    }
    quickSortHelper(arr, 0, arr.length - 1, eventList);
    return eventList;
  }

  private static <T extends Comparable<T>> int partition(T[] arr, int lo, int hi,
      List<SortEvent<T>> eventList) {
    // get the last element to be the pivot
    T pivot = arr[hi];
    // start at the beginning
    int pivotIndex = lo;
    for (int i = lo; i <= hi - 1; i++) {
      if (arr[i].compareTo(pivot) <= 0) {
        eventList.add(new CompareEvent<T>(i, hi));
        swap(arr, i, pivotIndex);
        eventList.add(new SwapEvent<T>(i, pivotIndex));
        pivotIndex++;
      }
    }
    // break out of the loop, put pivot back to position
    swap(arr, pivotIndex, hi);
    eventList.add(new SwapEvent<T>(pivotIndex, hi));
    return pivotIndex;
  }

  private static <T extends Comparable<T>> void quickSortHelper(T[] arr, int start, int end,
      List<SortEvent<T>> eventList) {
    if (start >= end) {
      return;
    } else {
      // calculate partition index
      int partitionIndex = partition(arr, start, end, eventList);
      // recursively sort the left and right half
      quickSortHelper(arr, start, partitionIndex - 1, eventList);
      quickSortHelper(arr, partitionIndex + 1, end, eventList);
    }
  }


  /**
   * Heapsort
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> heapSort(T[] arr) {
    List<SortEvent<T>> eventList = new ArrayList<>();
    int n = arr.length;

    // Build heap (rearrange array)
    for (int i = n / 2 - 1; i >= 0; i--)
      heapify(arr, n, i, eventList);

    // One by one extract an element from heap
    for (int i = n - 1; i >= 0; i--) {
      // Move current root to end
      T temp = arr[0];
      arr[0] = arr[i];
      arr[i] = temp;
      eventList.add(new SwapEvent<T>(0, i));
      // call max heapify on the reduced heap
      heapify(arr, i, 0, eventList);
    }
    return eventList;
  }

  // To heapify a subtree rooted with node i which is
  // an index in arr[]. n is size of heap
  public static <T extends Comparable<T>> void heapify(T arr[], int n, int i,
      List<SortEvent<T>> eventList) {
    int largest = i; // Initialize largest as root
    int l = 2 * i + 1; // left = 2*i + 1
    int r = 2 * i + 2; // right = 2*i + 2

    // If left child is larger than root
    eventList.add(new CompareEvent<T>(l, largest));
    if (l < n && arr[l].compareTo(arr[largest]) > 0)
      largest = l;

    // If right child is larger than largest so far
    eventList.add(new CompareEvent<T>(r, largest));
    if (r < n && arr[r].compareTo(arr[largest]) > 0)
      largest = r;

    // If largest is not root
    if (largest != i) {
      T swap = arr[i];
      arr[i] = arr[largest];
      arr[largest] = swap;

      eventList.add(new SwapEvent<T>(i, largest));
      // Recursively heapify the affected sub-tree
      heapify(arr, n, largest, eventList);
    }
  }

  /**
   * Helpers
   */
  // Procedure to swap elements in array
  public static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  /*
   * Procedure to apply events to elements of the array in order
   */
  public static <T extends Comparable<T>> void eventSort(T[] arr, List<SortEvent<T>> eventList) {
    for (int i = 0; i < eventList.size(); i++) {
      eventList.get(i).apply(arr);
    }
  }
}
