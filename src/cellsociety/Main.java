package cellsociety;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) throws InterruptedException {
      Grid myGrid = new PercolationGrid(4,4);
      ArrayList<Cell> arr = new ArrayList<>();
      arr.add(new Cell(2,0,0));
      myGrid.updateCells(arr);
        for (int i = 0; i < 5; i++){
          myGrid.updateCells(myGrid.checkForUpdates());
          myGrid.printCells();
      }
    }
}

