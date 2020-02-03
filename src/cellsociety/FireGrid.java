package cellsociety;

import java.util.ArrayList;
import java.util.List;

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
        int curType = myCellGrid[i][j].getType();
        if (curType == EMPTY) {
          continue;
        }
        if (curType == BURNING) {
          updateList.add(new Cell(EMPTY, i, j));
          continue;
        }
        hasBurningNeighbor = checkNeighbors(i, j);
        if (hasBurningNeighbor) {
          if (Math.random() < burnChance) {
            updateList.add(new Cell(BURNING, i, j));
          }
        }
      }
    }
    return updateList;
  }

  private boolean checkNeighbors(int i, int j) {

    int count = 0;
    boolean isTopEdge = (i == 0); //&& j!=0 && j!=numColumns);
    boolean isBottomEdge = (i == numRows - 1);// && j!=0 && j!=numColumns);
    boolean isLeftEdge = (j == 0); // && i!= numRows && i != 0);
    boolean isRightEdge = (j == numColumns - 1); // && i!= numRows && i != 0);

    if (!isTopEdge) {
      if (myCellGrid[i - 1][j].getType() == BURNING) {
        count++;
      }
    }

    if (!isLeftEdge) {
      if (myCellGrid[i][j - 1].getType() == BURNING) {
        count++;
      }
    }

    if (!isRightEdge) {
      if (myCellGrid[i][j + 1].getType() == BURNING) {
        count++;
      }
    }

    if (!isBottomEdge) {
      if (myCellGrid[i + 1][j].getType() == BURNING) {
        count++;
      }
    }
    return (count > 0);
  }

}
