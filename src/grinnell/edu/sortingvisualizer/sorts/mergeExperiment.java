package grinnell.edu.sortingvisualizer.sorts;

public class mergeExperiment {

  public static void main (String[] args) throws Exception {
    String[] arr = {"a", "a", "h", "b", "d", "c"};
    Integer[] arr1 = {0, 2, 9, 1, 9, 8};
    //Sorts<String> obj = new Sorts<String>();
    Sorts.mergeSort(arr);
    Sorts.mergeSort(arr1);
    for (int i = 0; i < arr.length ; i++) {
      System.out.println(arr[i]);
    }
    for (int i = 0; i < arr1.length ; i++) {
      System.out.println(arr1[i]);
    }
  }
  
}
