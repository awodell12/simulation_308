package cellsociety;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

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
    int newType = 0;
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
        Pair neighbors = checkLikeNeighbors(i, j, FILLED, false);
        boolean fillUp = (int) neighbors.getKey() > 0;
        if (fillUp) {
          newType = FILLED;
        } else {
          newType = OPEN;
        }
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


}
