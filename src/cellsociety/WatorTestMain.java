package cellsociety;

import java.util.ArrayList;

public class WatorTestMain {
  public static void main(String[] args) {
    Grid myGrid = new WatorGrid(5, 5, 5, 3, 2);
    ArrayList<Cell> arr = new ArrayList<>();
    arr.add(new Cell(1, 0, 0));
    arr.add(new Cell(1, 0, 2));
    arr.add(new Cell(1, 2, 2));
    arr.add(new Cell(1, 2, 0));
    arr.add(new Cell(1, 4, 2));
    arr.add(new Cell(2, 1, 0));
    arr.add(new Cell(1, 3, 2));

    myGrid.updateCells(arr);
    myGrid.printCells();
    for (int i = 0; i < 10; i++) {
      myGrid.updateCells(myGrid.checkForUpdates());
      myGrid.printCells();
    }
  }
}