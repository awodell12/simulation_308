package cellsociety;

import cellsociety.simulation.Cell;
import cellsociety.simulation.Grid;
import cellsociety.simulation.WatorGrid;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class WatorTestMain {
  public static void main(String[] args) {
    List<Point> neighbors = new ArrayList<>();
    neighbors.add(new Point(0,1));
    neighbors.add(new Point(0,-1));
    neighbors.add(new Point(1,0));
    neighbors.add(new Point(-1,0));
    Grid myGrid = new WatorGrid(5, 5, 5, 3, 2, neighbors);
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