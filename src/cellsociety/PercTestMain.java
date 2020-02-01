package cellsociety;

import java.util.ArrayList;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class PercTestMain {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
      Grid myGrid = new PercolationGrid(4,4);
      ArrayList<Cell> arr = new ArrayList<>();
      arr.add(new Cell(2,0,0));
      arr.add(new Cell (1,0,1));
      arr.add(new Cell(0, 1,1));
      arr.add(new Cell (1,1,0));
      myGrid.updateCells(arr);
        for (int i = 0; i < 5; i++){
          myGrid.updateCells(myGrid.checkForUpdates());
          myGrid.printCells();
      }
    }
}

