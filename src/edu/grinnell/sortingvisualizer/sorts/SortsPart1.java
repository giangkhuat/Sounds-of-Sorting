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
   * Heap Sort - taken from https://www.geeksforgeeks.org/heap-sort/
   */
  
  public static <T extends Comparable<T>> void heapSort(T[] arr) 
  { 
      int n = arr.length; 

      // Build heap (rearrange array) 
      for (int i = n / 2 - 1; i >= 0; i--) 
          heapify(arr, n, i); 

      // One by one extract an element from heap 
      for (int i=n-1; i>=0; i--) 
      { 
          // Move current root to end 
          T temp = arr[0]; 
          arr[0] = arr[i]; 
          arr[i] = temp; 

          // call max heapify on the reduced heap 
          heapify(arr, i, 0); 
      } 
  } 

  // To heapify a subtree rooted with node i which is 
  // an index in arr[]. n is size of heap 
 public static <T extends Comparable<T>> void heapify(T arr[], int n, int i) 
  { 
      int largest = i; // Initialize largest as root 
      int l = 2*i + 1; // left = 2*i + 1 
      int r = 2*i + 2; // right = 2*i + 2 

      // If left child is larger than root 
      if (l < n && arr[l].compareTo(arr[largest]) > 0) 
          largest = l; 

      // If right child is larger than largest so far 
      if (r < n && arr[r].compareTo(arr[largest]) > 0) 
          largest = r; 

      // If largest is not root 
      if (largest != i) 
      { 
          T swap = arr[i]; 
          arr[i] = arr[largest]; 
          arr[largest] = swap; 

          // Recursively heapify the affected sub-tree 
          heapify(arr, n, largest); 
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
    }
    else {
      return ;
    }
  }
  
  /*
   * Procedure to perform merge for mergeSort
   */

  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void merge 
  (T[] arr, int lo, int mid, int hi) {
      Object[] temp = new Object[hi-lo];
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
          arr[n + lo] =  (T) temp[n];
      }
  } // merge 

/*
  public static <T extends Comparable<T>> void merge(T[] arr, int lo, int hi, int mid) {
    int i = lo, j = mid, k = 0;

    //T[] temp = (T[]) new Object[hi - lo];
    T[] temp = Arrays.copyOf(arr, hi-lo);
    
    while (i < mid && j < hi) {
      if ((arr[i]).compareTo(arr[j]) <= 0) {
        temp[k++] = arr[i++];
      } else {
        temp[k++] = arr[j++];
      }
    }

    while (i < mid) {
      temp[k++] = arr[i++];
    }

    while (j < hi) {
      temp[k++] = arr[j++];
    }

    for (int n = 0; n < (hi - lo); n++) {
      arr[n + lo] = temp[n];
    }
  }
  */
}
