package edu.grinnell.sortingvisualizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ArrayPanel extends JPanel {

    private NoteIndices notes;
    
    /**
     * Constructs a new ArrayPanel that renders the given note indices to
     * the screen.
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
        int width = this.getWidth()/ notes.notesLength();
        int height = this.getHeight()/notes.notesLength();
        // set color
        int color = 0;
        
        for (int i = 0 ; i < notes.notesLength(); i++) {
          // set the height of the note
          int noteHeight = height + noteIndices[i] * height;
          // if the note is highlighted
          if (notes.isHighlighted(i)) {
            g.setColor(Color.BLUE);
          } else { 
            int comp = noteIndices[i]*color;
            g.setColor(new Color(0, comp, comp));
          }
        }
    }
}