package cellsociety.simulation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 * Cells Types: 0-open 1-blocked 2-Filled We are defining this class to have the condition that system
 * will be percolated once the bottom right-cell is filled
 *
 * @author Austin Odell
 */
public class PercolationGrid extends Grid {

  public static final int FILLED = 2;
  public static final int OPEN = 0;
  public static final int BLOCKED = 1;

  private boolean isPercolated = false;
  private int destX;
  private int destY;

  public PercolationGrid(int cols, int rows, List<Point> neighborLocations, edgeType edges) {
    super(cols, rows, neighborLocations, edges);
    destX = rows -1;
    destY = cols -1;
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
        if (curCell.getType() != OPEN) {
          continue;
        }
        Pair neighbors = checkLikeNeighbors(i, j, FILLED);
        boolean fillUp = (int) neighbors.getKey() > 0;
        if (fillUp) {
          newType = FILLED;
          updateList.add(new Cell(newType, i, j));
          if(i == destX&&j==destY){
            isPercolated = true;
            break;
          }
        }

      }
      if (isPercolated) break;
    }
    if (myCellGrid[destX][destY].getType() == FILLED){
      isPercolated = true;
    }
    return updateList;
  }


}
