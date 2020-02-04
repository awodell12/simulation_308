package cellsociety;

import java.util.ArrayList;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class SegTestMain {

  /**
   * Start of the program.
   */
  public static void main(String[] args) {
    Grid myGrid = new SegregationGrid(5, 5, 0.5);
    ArrayList<Cell> arr = new ArrayList<>();
    arr.add(new Cell(1, 0, 0));
    arr.add(new Cell(1, 0, 2));
    arr.add(new Cell(1, 2, 2));
    arr.add(new Cell(1, 3, 2));
    arr.add(new Cell(1, 4, 2));
    arr.add(new Cell(2, 1, 0));
    arr.add(new Cell(2, 1, 2));
    arr.add(new Cell(2, 2, 0));

    myGrid.updateCells(arr);
    myGrid.printCells();
    for (int i = 0; i < 3; i++) {
      myGrid.updateCells(myGrid.checkForUpdates());
      myGrid.printCells();
    }
  }
}

