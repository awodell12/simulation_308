package cellsociety;

import java.util.List;

public abstract class Grid {
  int numColumns;
  int numRows;
  Cell [][] myCellGrid;

  public Grid(int cols, int rows){
    numColumns = cols;
    numRows = rows;
    myCellGrid = new Cell[numColumns][numRows];
  }

  public abstract List<Cell> checkForUpdates();

  /**
  This method is used both to updateCells between generations and also to set the original non-zero grid Cells
   @param updateList a list of Cells to take the place of the old cells at their coordinates

   */
  public void updateCells(List<Cell> updateList){
    for (Cell c : updateList){
      int x = (int) c.getMyXCoordinate();
      int y = (int) c.getMyYCoordinate();
      myCellGrid[x][y] = c;
    }
  }
}
