package cellsociety;

import cellsociety.simulation.Cell;
import cellsociety.simulation.FireGrid;
import cellsociety.simulation.Grid;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class FireTestMain {

  /**
   * Start of the program.
   */
  public static void main(String[] args) {
    List<Point> neighbors = new ArrayList<>();
    neighbors.add(new Point(0,1));
    neighbors.add(new Point(0,-1));
    neighbors.add(new Point(1,0));
    neighbors.add(new Point(-1,0));
    Grid myGrid = new FireGrid(6, 6, 0.6, neighbors);
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
