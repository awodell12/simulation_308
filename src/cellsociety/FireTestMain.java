package cellsociety;

import java.util.ArrayList;

public class FireTestMain {

  /**
   * Start of the program.
   */
  public static void main(String[] args) {
    Grid myGrid = new FireGrid(6, 6, 0.6);
    ArrayList<Cell> arr = new ArrayList<>();
    arr.add(new Cell(2, 3, 0));
    arr.add(new Cell(1, 3, 3));
    arr.add(new Cell(1, 3, 4));
    arr.add(new Cell(1, 5, 5));
    myGrid.updateCells(arr);
    myGrid.printCells();

    for (int i = 0; i < 10; i++) {
      myGrid.updateCells(myGrid.checkForUpdates());
      //myGrid.printCells();
    }
    myGrid.printCells();
  }
}
