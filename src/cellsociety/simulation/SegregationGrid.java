package cellsociety.simulation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 * @author Austin Odell
 * Purpose: The purpose of this class is to create and mantain a grid for the Segregation simulation
 * Assumptions: The threshold value given is between 0 and 1.
 * Dependencies: Grid and Cell
 * Example: Use in the same way as other Grid(checkForUpdates and updateCells), except with additional
 * parameter of threshold for satisfaction which determines if someone will be happy with their current neighbors
 */
public class SegregationGrid extends Grid {

  public static final int TYPE_A = 1;
  public static final int TYPE_B = 2;
  public static final int EMPTY = 0;

  private double satisfactionThreshold;

  /**
   *
   * @param cols
   * @param rows
   * @param threshold The percentage at or above which a person is happy with the amount of neighbors
   *                  that are like them, otherwise they will want to move
   * @param neighborLocations
   * @param edges
   */
  public SegregationGrid(int cols, int rows, double threshold,
      List<Point> neighborLocations, edgeType edges) {
    super(cols, rows, neighborLocations, edges);
    satisfactionThreshold = threshold;
  }

  @Override
  public List<Cell> checkForUpdates() {
    ArrayList<Cell> updateList = new ArrayList<>();
    List<Pair> emptyCells = findEmptyCells();
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        int curType = myCellGrid[i][j].getType();
        if (curType == EMPTY) {
          continue;
        }

        Pair neighbors = checkLikeNeighbors(i, j, curType);
        int similarNeighbors = (int) neighbors.getKey();
        int totalNeighbors = (int) neighbors.getValue();
        if (similarNeighbors < satisfactionThreshold * totalNeighbors) {
          if (emptyCells.isEmpty()) {
            continue;
          }

          int randIndex = (int) Math.floor(Math.random() * emptyCells.size());
          Pair newLoc = emptyCells.get(randIndex);
          emptyCells.remove(randIndex);
          updateList.add(new Cell(curType, (int) newLoc.getKey(), (int) newLoc.getValue()));
          updateList.add(new Cell(EMPTY, i, j));
        }

      }
    }
    return updateList;
  }





}
