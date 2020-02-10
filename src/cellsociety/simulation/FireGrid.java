package cellsociety.simulation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 * @author Austin Odell
 * Purpose: This class is used to create and implement a Grid for the Fire simulation
 * Assumption: burn chance is a double value between 0 and 1
 * Dependencies: Cell, Grid superclass
 * Use it in the same way as described in the general grid class except with an extra parameter of burnchance
 * Cells Type: 0-Tree 1-Burning 2-Empty
 */
public class FireGrid extends Grid {

  public static final int EMPTY = 2;
  public static final int TREE = 0;
  public static final int BURNING = 1;

  private double burnChance;

  /**
   *
   * @param chance The chance for a tree to catch on fire if at least one of its neighbors is burning
   * Again we assume 0 <= chance <= 1
   */
  public FireGrid(int cols, int rows, double chance, List<Point> neighborLocations, edgeType edges) {
    super(cols, rows, neighborLocations, edges);
    burnChance = chance;
  }

  /**
   * Override the abstract method in Grid to check for cells to update based on their state and the
   * state of their neighbors.
   * @return a List of cells that need to be updated at the end of the generation.
   */
  @Override
  public List<Cell> checkForUpdates() {
    ArrayList<Cell> updateList = new ArrayList<>();
    boolean hasBurningNeighbor;
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        int curType = myCellGrid[i][j].getType();
        if (curType == EMPTY) {
          continue;
        }
        if (curType == BURNING) {
          updateList.add(new Cell(EMPTY, i, j));
          continue;
        }
        Pair neighbors = checkLikeNeighbors(i, j, BURNING);
        hasBurningNeighbor = ((int) neighbors.getKey() >= 1);
        if (hasBurningNeighbor) {
          if (Math.random() < burnChance) {
            updateList.add(new Cell(BURNING, i, j));
          }
        }
      }
    }
    return updateList;
  }


}
