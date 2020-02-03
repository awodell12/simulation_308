package cellsociety;

import java.util.List;

/**
 * By default all cells are initialized to their 0 state. If you want initial states you must call
 * UpdateCells with a list of non-zero initial cells.
 */
public abstract class Grid {

  int numColumns;
  int numRows;
  Cell[][] myCellGrid;

  public Grid(int cols, int rows) {
    numColumns = cols;
    numRows = rows;
    myCellGrid = new Cell[numRows][numColumns];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        myCellGrid[i][j] = new Cell(0, i, j);
      }
    }
  }

  public abstract List<Cell> checkForUpdates();

  /**
   * This method is used both to updateCells between generations and also to set the original
   * non-zero grid Cells
   *
   * @param updateList a list of Cells to take the place of the old cells at their coordinates
   */
  public void updateCells(List<Cell> updateList) {
    for (Cell c : updateList) {
      int x = c.myX;
      int y = c.myY;
      myCellGrid[x][y] = c;
    }
  }

  /**
   * debug/testing method to print out current states
   */
  public void printCells() {
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        if (myCellGrid[i][j] == null) {
          System.out.print("x ");
        } else {
          System.out.print(myCellGrid[i][j].myType + " ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }
}
