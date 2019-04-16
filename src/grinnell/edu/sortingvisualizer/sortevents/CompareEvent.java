package grinnell.edu.sortingvisualizer.sortevents;

import java.util.ArrayList;
import java.util.List;

public class CompareEvent<T extends Comparable<T>> implements SortEvent<T> {
  private int firstIndex;
  private int secondIndex;

  // why cant we parametrize constructor
  public CompareEvent(int first, int second) {
    this.firstIndex = first;
    this.secondIndex = second;
  }

  @Override
  public void apply(T[] arr) {
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
    return false;
  }

}
