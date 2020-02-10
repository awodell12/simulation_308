package cellsociety.simulation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 * @author Austin Odell
 * purpose: The purpose of this class is to create and implement a grid for the GameOfLife simulation
 * Assumptions: the rules for death/birth/survival
 * Dependencies: Cell and Grid
 * Example: Use it in the same was as the other grids with updateCells(checkForUpdates)
 */
public class GameOfLifeGrid extends Grid {

  public static final int DEAD = 0;
  public static final int ALIVE = 1;

  /**
   * Just calls Grid constructor with all of these params
   * @param cols
   * @param rows
   * @param neighborLocations
   * @param edges
   */
  public GameOfLifeGrid(int cols, int rows, List<Point> neighborLocations, edgeType edges) {
    super(cols, rows, neighborLocations, edges);
  }

  @Override
  public List<Cell> checkForUpdates() {
    ArrayList<Cell> updateList = new ArrayList<>();
    int newType;
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        Cell curCell = myCellGrid[i][j];

        Pair neighbors = checkLikeNeighbors(i, j, ALIVE);
        int aliveNeighbors = (int) neighbors.getKey();
        if (aliveNeighbors >= 2 && aliveNeighbors < 4) {
          newType = ALIVE;
        } else {
          newType = DEAD;
        }
        if (newType != curCell.getType()) {
          updateList.add(new Cell(newType, i, j));
        }
      }
    }
    return updateList;
  }


}
