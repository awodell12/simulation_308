package cellsociety;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class SegregationGrid extends Grid {

  public static final int TYPE_A = 1;
  public static final int TYPE_B = 2;
  public static final int EMPTY = 0;

  private double satisfactionThreshold;

  public SegregationGrid(int cols, int rows, double threshold) {
    super(cols, rows);
    satisfactionThreshold = threshold;
  }

  @Override
  public List<Cell> checkForUpdates() {
    ArrayList<Cell> updateList = new ArrayList<>();
    ArrayList<Pair> emptyCells = findEmptyCells();
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

  private ArrayList<Pair> findEmptyCells() {
    ArrayList<Pair> emptyCells = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        int curType = myCellGrid[i][j].getType();
        if (curType == EMPTY) {
          emptyCells.add(new Pair(i, j));
        }
      }
    }
    return emptyCells;
  }

  private Pair checkLikeNeighbors(int i, int j, int type) {
    int similarCount = 0;
    int neighborCount = 0;
    boolean isTopEdge = (i == 0); //&& j!=0 && j!=numColumns);
    boolean isBottomEdge = (i == numRows - 1);// && j!=0 && j!=numColumns);
    boolean isLeftEdge = (j == 0); // && i!= numRows && i != 0);
    boolean isRightEdge = (j == numColumns - 1); // && i!= numRows && i != 0);

    if (!isLeftEdge && !isTopEdge) {
      if (myCellGrid[i - 1][j - 1].getType() == type) {
        similarCount++;
      }
      if (myCellGrid[i - 1][j - 1].getType() != EMPTY) {
        neighborCount++;
      }
    }
    if (!isTopEdge) {
      if (myCellGrid[i - 1][j].getType() == type) {
        similarCount++;
      }
      if (myCellGrid[i - 1][j].getType() != EMPTY) {
        neighborCount++;
      }
    }
    if (!isTopEdge && !isRightEdge) {
      if (myCellGrid[i - 1][j + 1].getType() == type) {
        similarCount++;
      }
      if (myCellGrid[i - 1][j + 1].getType() != EMPTY) {
        neighborCount++;
      }
    }
    if (!isLeftEdge) {
      if (myCellGrid[i][j - 1].getType() == type) {
        similarCount++;
      }
      if (myCellGrid[i][j - 1].getType() != EMPTY) {
        neighborCount++;
      }
    }
    if (!isRightEdge) {
      if (myCellGrid[i][j + 1].getType() == type) {
        similarCount++;
      }
      if (myCellGrid[i][j + 1].getType() != EMPTY) {
        neighborCount++;
      }
    }
    if (!isBottomEdge && !isLeftEdge) {
      if (myCellGrid[i + 1][j - 1].getType() == type) {
        similarCount++;
      }
      if (myCellGrid[i + 1][j - 1].getType() != EMPTY) {
        neighborCount++;
      }
    }
    if (!isBottomEdge) {
      if (myCellGrid[i + 1][j].getType() == type) {
        similarCount++;
      }
      if (myCellGrid[i + 1][j].getType() != EMPTY) {
        neighborCount++;
      }
    }
    if (!isBottomEdge && !isRightEdge) {
      if (myCellGrid[i + 1][j + 1].getType() == type) {
        similarCount++;
      }
      if (myCellGrid[i + 1][j + 1].getType() != EMPTY) {
        neighborCount++;
      }
    }

    return new Pair(similarCount, neighborCount);
  }
}
