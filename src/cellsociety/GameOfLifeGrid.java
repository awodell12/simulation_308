package cellsociety;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeGrid extends Grid {

  public static final int DEAD = 0;
  public static final int ALIVE = 1;

  public GameOfLifeGrid(int cols, int rows) {
    super(cols, rows);
  }

  @Override
  public List<Cell> checkForUpdates() {
    ArrayList<Cell> updateList = new ArrayList<>();
    int newType;
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        Cell curCell = myCellGrid[i][j];

        int aliveNeighbors = checkNeighbors(i, j);
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

  private int checkNeighbors(int i, int j) {
    int count = 0;
    boolean isTopEdge = (i == 0); //&& j!=0 && j!=numColumns);
    boolean isBottomEdge = (i == numRows - 1);// && j!=0 && j!=numColumns);
    boolean isLeftEdge = (j == 0); // && i!= numRows && i != 0);
    boolean isRightEdge = (j == numColumns - 1); // && i!= numRows && i != 0);

    if (!isLeftEdge && !isTopEdge) {
      if (myCellGrid[i - 1][j - 1].getType() == ALIVE) {
        count++;
      }
    }

    if (!isTopEdge) {
      if (myCellGrid[i - 1][j].getType() == ALIVE) {
        count++;
      }
    }

    if (!isTopEdge && !isRightEdge) {
      if (myCellGrid[i - 1][j + 1].getType() == ALIVE) {
        count++;
      }
    }

    if (!isLeftEdge) {
      if (myCellGrid[i][j - 1].getType() == ALIVE) {
        count++;
      }
    }

    if (!isRightEdge) {
      if (myCellGrid[i][j + 1].getType() == ALIVE) {
        count++;
      }
    }

    if (!isBottomEdge && !isLeftEdge) {
      if (myCellGrid[i + 1][j - 1].getType() == ALIVE) {
        count++;
      }
    }

    if (!isBottomEdge) {
      if (myCellGrid[i + 1][j].getType() == ALIVE) {
        count++;
      }
    }

    if (!isBottomEdge && !isRightEdge) {
      if (myCellGrid[i + 1][j + 1].getType() == ALIVE) {
        count++;
      }
    }

    return count;
  }
}
