package cellsociety;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 * Cells Types: 0-Tree 1-Burning 2-Empty
 */
public class FireGrid extends Grid {

  public static final int EMPTY = 2;
  public static final int TREE = 0;
  public static final int BURNING = 1;

  private double burnChance;

  public FireGrid(int cols, int rows, double chance) {
    super(cols, rows);
    burnChance = chance;
  }

  @Override
  public List<Cell> checkForUpdates() {
    ArrayList<Cell> updateList = new ArrayList<>();
    boolean hasBurningNeighbor;
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        int curType = myCellGrid[i][j].myType;
        if (curType == EMPTY) {
          continue;
        }
        if (curType == BURNING) {
          updateList.add(new Cell(EMPTY, i, j));
          continue;
        }
        Pair neighbors = checkLikeNeighbors(i, j, BURNING, true);
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
