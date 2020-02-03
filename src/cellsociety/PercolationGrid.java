package cellsociety;

import java.util.ArrayList;
import java.util.List;

/**
 * Cells Types: 0-open 1-blocked 2-Filled We are defining this class to have the condition that on
 * start-up only cell(s) on the left side will be filled and that the system will be percolated once
 * a right edge is filled.
 *
 * @author Austin Odell
 */
public class PercolationGrid extends Grid {

  public static final int FILLED = 2;
  public static final int OPEN = 0;
  public static final int BLOCKED = 1;

  private boolean isPercolated = false;

  public PercolationGrid(int cols, int rows) {
    super(cols, rows);
  }

  @Override
  public List<Cell> checkForUpdates() {
    ArrayList<Cell> updateList = new ArrayList<>();
    if (isPercolated) {
      return updateList;
    }
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        Cell curCell = myCellGrid[i][j];
        if (curCell.myType != OPEN) {
          continue;
        }
        int newType = checkNeighbors(i, j);
        if (newType == FILLED) {
          updateList.add(new Cell(newType, i, j));
          if (j == numColumns - 1) {
            isPercolated = true;
          }
        }
      }
    }
    return updateList;
  }

  private int checkNeighbors(int i, int j) {
    int count = 0;
    boolean isTopEdge = (i == 0); //&& j!=0 && j!=numColumns);
    boolean isBottomEdge = (i == numRows - 1);// && j!=0 && j!=numColumns);
    boolean isLeftEdge = (j == 0); // && i!= numRows && i != 0);
    boolean isRightEdge = (j == numColumns - 1); // && i!= numRows && i != 0);

    if (!isLeftEdge && !isTopEdge) {
      if (myCellGrid[i - 1][j - 1].myType == FILLED) {
        count++;
      }
    }

    if (!isTopEdge) {
      if (myCellGrid[i - 1][j].myType == FILLED) {
        count++;
      }
    }

    if (!isTopEdge && !isRightEdge) {
      if (myCellGrid[i - 1][j + 1].myType == FILLED) {
        count++;
      }
    }

    if (!isLeftEdge) {
      if (myCellGrid[i][j - 1].myType == FILLED) {
        count++;
      }
    }

    if (!isRightEdge) {
      if (myCellGrid[i][j + 1].myType == FILLED) {
        count++;
      }
    }

    if (!isBottomEdge && !isLeftEdge) {
      if (myCellGrid[i + 1][j - 1].myType == FILLED) {
        count++;
      }
    }

    if (!isBottomEdge) {
      if (myCellGrid[i + 1][j].myType == FILLED) {
        count++;
      }
    }

    if (!isBottomEdge && !isRightEdge) {
      if (myCellGrid[i + 1][j + 1].myType == FILLED) {
        count++;
      }
    }

    if (count > 0) {
      return FILLED;
    }
    return OPEN;
  }

}
