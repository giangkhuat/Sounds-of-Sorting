package edu.grinnell.sortingvisualizer;

import java.util.Random;

/**
 * A collection of indices into a Scale object.
 * These indices are the subject of the various sorting algorithms
 * in the program.
 */
public class NoteIndices {
  
  Integer[] notes = new Integer[0];
  
  /**
   * Helper: Create an random array of integers from 0 to size-1 (type Object Integer).
   */
  public static Integer[] randomIntegers(int size) {
    Random rand = new Random();
    Integer[] result = new Integer[size];
    result[0] = Integer.valueOf(rand.nextInt(size));
    for (int i = 0; i < size; i++) {
      result[i] = Integer.valueOf(rand.nextInt(size));
    } // for
    return result;
  } // randomIntegers(int)

    /**
     * @param n the size of the scale object that these indices map into
     */
    public NoteIndices(int n) {
       this.notes = new Integer[n];
    }
    
    /**
     * Reinitializes this collection of indices to map into a new scale object
     * of the given size.  The collection is also shuffled to provide an
     * initial starting point for the sorting process.
     * @param n the size of the scale object that these indices map into
     */
    public void initializeAndShuffle(int n) {      
      Random rand = new Random();
      Integer[] result = new Integer[n];
      result[0] = Integer.valueOf(rand.nextInt(n));
      for (int i = 0; i < n; i++) {
        result[i] = Integer.valueOf(rand.nextInt(n));
      } // for
      this.notes = result;
    }
    
    /** @return the indices of this NoteIndices object */
    public Integer[] getNotes() { 
        return notes;
    }
    
    /**
     * Highlights the given index of the note array
     * @param index the index to highlight
     */
    public void highlightNote(int index) {
        // TODO: fill me in
    }
    
    /** @return true if the given index is highlighted */
    public boolean isHighlighted(int index) {
        // TODO: fill me in
        return false;
    }
    
    /** Clears all highlighted indices from this collection */
    public void clearAllHighlighted() {
        // TODO: fill me in
    }
}
