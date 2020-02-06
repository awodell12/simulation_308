package cellsociety;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 * By default all cells are initialized to their 0 state. If you want initial states you must call
 * UpdateCells with a list of non-zero initial cells.
 */
public abstract class Grid {

  public final int EMPTY = 0;
  int numColumns;
  int numRows;
  Cell[][] myCellGrid;

  public Grid(int cols, int rows) {
    numColumns = cols;
    numRows = rows;
    myCellGrid = new Cell[numRows][numColumns];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        myCellGrid[i][j] = new Cell(EMPTY, i, j);
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

  Pair checkLikeNeighbors(int i, int j, int type, boolean onlyFourNeighbors) {
    int similarCount = 0;
    int neighborCount = 0;
    boolean isTopEdge = (i == 0); //&& j!=0 && j!=numColumns);
    boolean isBottomEdge = (i == numRows - 1);// && j!=0 && j!=numColumns);
    boolean isLeftEdge = (j == 0); // && i!= numRows && i != 0);
    boolean isRightEdge = (j == numColumns - 1); // && i!= numRows && i != 0);

    if (!isLeftEdge && !isTopEdge && !onlyFourNeighbors) {
      if (myCellGrid[i - 1][j - 1].myType == type) {
        similarCount++;
      }
      if (myCellGrid[i - 1][j - 1].myType != EMPTY) {
        neighborCount++;
      }
    }
    if (!isTopEdge) {
      if (myCellGrid[i - 1][j].myType == type) {
        similarCount++;
      }
      if (myCellGrid[i - 1][j].myType != EMPTY) {
        neighborCount++;
      }
    }
    if (!isTopEdge && !isRightEdge && !onlyFourNeighbors) {
      if (myCellGrid[i - 1][j + 1].myType == type) {
        similarCount++;
      }
      if (myCellGrid[i - 1][j + 1].myType != EMPTY) {
        neighborCount++;
      }
    }
    if (!isLeftEdge) {
      if (myCellGrid[i][j - 1].myType == type) {
        similarCount++;
      }
      if (myCellGrid[i][j - 1].myType != EMPTY) {
        neighborCount++;
      }
    }
    if (!isRightEdge) {
      if (myCellGrid[i][j + 1].myType == type) {
        similarCount++;
      }
      if (myCellGrid[i][j + 1].myType != EMPTY) {
        neighborCount++;
      }
    }
    if (!isBottomEdge && !isLeftEdge && !onlyFourNeighbors) {
      if (myCellGrid[i + 1][j - 1].myType == type) {
        similarCount++;
      }
      if (myCellGrid[i + 1][j - 1].myType != EMPTY) {
        neighborCount++;
      }
    }
    if (!isBottomEdge) {
      if (myCellGrid[i + 1][j].myType == type) {
        similarCount++;
      }
      if (myCellGrid[i + 1][j].myType != EMPTY) {
        neighborCount++;
      }
    }
    if (!isBottomEdge && !isRightEdge && !onlyFourNeighbors) {
      if (myCellGrid[i + 1][j + 1].myType == type) {
        similarCount++;
      }
      if (myCellGrid[i + 1][j + 1].myType != EMPTY) {
        neighborCount++;
      }
    }

    return new Pair(similarCount, neighborCount);
  }

  public ArrayList<Pair> findEmptyCells() {
    ArrayList<Pair> emptyCells = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        int curType = myCellGrid[i][j].myType;
        if (curType == EMPTY) {
          emptyCells.add(new Pair(i, j));
        }
      }
    }
    return emptyCells;
  }
}
