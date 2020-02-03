package cellsociety;

import java.util.ArrayList;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class PercTestMain {

  /**
   * Start of the program.
   */
  public static void main(String[] args) {
    Grid myGrid = new PercolationGrid(6, 6);
    ArrayList<Cell> arr = new ArrayList<>();
    arr.add(new Cell(2, 3, 0));
    arr.add(new Cell(1, 4, 4));
    arr.add(new Cell(1, 3, 4));
    arr.add(new Cell(1, 5, 5));
    myGrid.updateCells(arr);
    myGrid.printCells();
    for (int i = 0; i < 5; i++) {
      myGrid.updateCells(myGrid.checkForUpdates());
      myGrid.printCells();
    }
  }
}
