package edu.grinnell.sortingvisualizer.sorts;

public class SortsPart1<T extends Comparable<T>> {
  public SortsPart1() {

  }

  /**
   * Selection sort
   * 
   * @param arr
   */
  public static <T extends Comparable<T>> void selectionSort(T[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        if (arr[j].compareTo(arr[minIndex]) <= 0) {
          minIndex = j;
        }
      }
      swap(arr, i, minIndex);
    }
  }

  /**
   * Insertion sort
   * 
   * @param arr
   */
  public static <T extends Comparable<T>> void insertionSort(T[] arr) {
    int n = arr.length;
    for (int i = 1; i < n; i++) {
      T val = arr[i];
      int j = i - 1;
      while (j >= 0 && (arr[j].compareTo(val) > 0)) {
        arr[j + 1] = arr[j];
        j--;
      }
      arr[j + 1] = val;
    }
  }

  /**
   * Merge sort
   * 
   * @param arr
   */
  public static <T extends Comparable<T>> void mergeSort(T[] arr) {
    mergeSortHelper(arr, 0, arr.length);
  }

  /**
   * Quick sort
   * 
   * @param arr
   */
  public static <T extends Comparable<T>> void quickSort(T[] arr) {
    quickSortHelper(arr, 0, arr.length - 1);
  }

  /**
   * Bubble Sort - taken from https://www.geeksforgeeks.org/bubble-sort/
   */

  public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j].compareTo(arr[j + 1]) > 0) {
          // swap arr[j+1] and arr[i]
          T temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
  }


  /*
   * HELPERS
   */
  // Procedure to swap elements in array
  public static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  private static <T extends Comparable<T>> int partition(T[] arr, int lo, int hi) {
    // get the last element to be the pivot
    T pivot = arr[hi];
    // start at the beginning
    int pivotIndex = lo;
    for (int i = lo; i <= hi - 1; i++) {
      if (arr[i].compareTo(pivot) <= 0) {
        swap(arr, i, pivotIndex);
        pivotIndex++;
      }
    }
    // break out of the loop, put pivot back to position
    swap(arr, pivotIndex, hi);
    return pivotIndex;
  }

  private static <T extends Comparable<T>> void quickSortHelper(T[] arr, int start, int end) {
    if (start >= end) {
      return;
    } else {
      int partitionIndex = partition(arr, start, end);
      quickSortHelper(arr, start, partitionIndex - 1);
      quickSortHelper(arr, partitionIndex + 1, end);
    }
  }



  /*
   * Procedure mergeSortHelper
   */

  public static <T extends Comparable<T>> void mergeSortHelper(T[] arr, int lo, int hi) {
    if (lo < hi - 1) {
      int mid = lo + (hi - lo) / 2;
      mergeSortHelper(arr, lo, mid);
      mergeSortHelper(arr, mid, hi);
      merge(arr, lo, mid, hi);
    } else {
      return;
    }
  }

  /*
   * Procedure to perform merge for mergeSort
   */

  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void merge(T[] arr, int lo, int mid, int hi) {
    Object[] temp = new Object[hi - lo];
    int i = lo, j = mid, k = 0;

    while (i < mid && j < hi) {
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
    }
  } // merge

  /*
   * public static <T extends Comparable<T>> void merge(T[] arr, int lo, int hi, int mid) { int i =
   * lo, j = mid, k = 0;
   * 
   * //T[] temp = (T[]) new Object[hi - lo]; T[] temp = Arrays.copyOf(arr, hi-lo);
   * 
   * while (i < mid && j < hi) { if ((arr[i]).compareTo(arr[j]) <= 0) { temp[k++] = arr[i++]; } else
   * { temp[k++] = arr[j++]; } }
   * 
   * while (i < mid) { temp[k++] = arr[i++]; }
   * 
   * while (j < hi) { temp[k++] = arr[j++]; }
   * 
   * for (int n = 0; n < (hi - lo); n++) { arr[n + lo] = temp[n]; } }
   */
}
