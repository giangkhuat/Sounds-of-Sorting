package grinnell.edu.sortingvisualizer.sortevents;

import java.util.ArrayList;
import java.util.List;

public class CopyEvent<T extends Comparable<T>> implements SortEvent <T> {
  private int index;
  private T val;
  
  public CopyEvent(int index, T val) {
    this.index = index;
    this.val = val;
  }
  
  @Override
  public void apply(T[] arr) {
    arr[index] = val;
  }

  @Override
  public List<Integer> getAffectedIndices() {
    List<Integer> listIndices = new ArrayList<Integer> ();
    listIndices.add(Integer.valueOf(index));
    return listIndices;
  }

  @Override
  public boolean isEmphasized() {
    return true;
  }

}
