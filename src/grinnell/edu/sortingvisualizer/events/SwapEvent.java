package grinnell.edu.sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

public class SwapEvent<T extends Comparable<T>> implements SortEvent<T> {
  private int firstIndex;
  private int secondIndex;
  
  public SwapEvent(int first, int second) {
    this.firstIndex = first;
    this.secondIndex = second;
  }
  
  @Override
  public void apply(T[] arr) {
    T temp = arr[firstIndex];
    arr[firstIndex] = arr[secondIndex];
    arr[secondIndex] = temp;
  }

  @Override
  public List<Integer> getAffectedIndices() {
    List<Integer> listIndices = new ArrayList<Integer> ();
    listIndices.add(Integer.valueOf(firstIndex));
    listIndices.add(Integer.valueOf(secondIndex));
    return listIndices;
  }

  @Override
  public boolean isEmphasized() {
    return true;
  }

}
