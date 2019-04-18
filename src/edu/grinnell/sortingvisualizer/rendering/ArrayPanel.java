package edu.grinnell.sortingvisualizer.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import edu.grinnell.sortingvisualizer.audio.NoteIndices;

@SuppressWarnings("serial")
public class ArrayPanel extends JPanel {

  private NoteIndices notes;

  /**
   * Constructs a new ArrayPanel that renders the given note indices to the screen.
   * 
   * @param notes the indices to render
   * @param width the width of the panel
   * @param height the height of the panel
   */
  public ArrayPanel(NoteIndices notes, int width, int height) {
    this.notes = notes;
    this.setPreferredSize(new Dimension(width, height));
  }

  @Override
  public void paintComponent(Graphics g) {
    // draw the notes on the panel
    Integer[] noteIndices = notes.getNotes();
    int noteWidth = this.getWidth() / notes.notesLength();
    // divide height into n parts
    int heightPart = this.getHeight() / notes.notesLength();
    // set color
    int colorScale = 10;

    for (int i = 0; i < notes.notesLength(); i++) {
      // scale height of rectangle using heightPart
      int noteHeight = heightPart * (noteIndices[i] + 1);
      // if the note is highlighted
      if (notes.isHighlighted(i)) {
        g.setColor(Color.CYAN);
      } else {
        // scale color according to value
        int comp = noteIndices[i] * colorScale;
        g.setColor(new Color(0, comp, comp));
      }
      // build the rectangle
      g.fillRect(noteWidth * i, this.getHeight() - noteHeight, noteWidth, noteHeight);
      // build the complement of the rectangle to erase the shadow part behind
      g.setColor(Color.WHITE);
      g.fillRect(noteWidth * i, 0, noteWidth, this.getHeight() - noteHeight);
    }
    notes.clearAllHighlighted();
  }
}
