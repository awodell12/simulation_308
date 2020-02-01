package cellsociety;

import java.lang.reflect.Array;
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
    ArrayList<Pair> emptyCells = new ArrayList<>();
    int newType;
    for (int i = 0; i < numRows ; i ++){
      for (int j = 0; j<numColumns; j++){
        int curType = myCellGrid[i][j].getType();
        if (curType== EMPTY) {
          emptyCells.add(new Pair(i, j));
          continue;
        }

        int similarNeighbors = checkLikeNeighbors(i,j, curType);
        if (similarNeighbors < satisfactionThreshold * totalNeighbors)
          newType = ALIVE;
        else newType = DEAD;
        if (newType != curCell.getType())
          updateList.add(new Cell(newType, i, j));
      }
    }
    return updateList;
  }
  private int checkLikeNeighbors(int i, int j, int type) {
    int similarCount =0;
    int neighborCount = 0;
    boolean isTopEdge = (i == 0); //&& j!=0 && j!=numColumns);
    boolean isBottomEdge = (i == numRows - 1);// && j!=0 && j!=numColumns);
    boolean isLeftEdge = (j == 0); // && i!= numRows && i != 0);
    boolean isRightEdge = (j == numColumns-1); // && i!= numRows && i != 0);

    if (!isLeftEdge && !isTopEdge) {if (myCellGrid[i-1][j-1].getType() == type) similarCount ++;
    neighborCount++;}
    if (!isTopEdge) {
      if (myCellGrid[i - 1][j].getType() == type)
        similarCount++;
      neighborCount++;
    }
    if(!isTopEdge && !isRightEdge) {
      if (myCellGrid[i - 1][j + 1].getType() == type)
        similarCount++;
      neighborCount++;
    }
    if (!isLeftEdge) {
      if (myCellGrid[i][j - 1].getType() == type)
        similarCount++;
      neighborCount++;
    }
      if(!isRightEdge) {
        if (myCellGrid[i][j + 1].getType() == type)
          similarCount++;
        neighborCount++;
      }
    if(!isBottomEdge && !isLeftEdge) {
      if (myCellGrid[i + 1][j - 1].getType() == type)
        similarCount++;
      neighborCount++;
    }
    if(!isBottomEdge) {
      if (myCellGrid[i + 1][j].getType() == type)
        similarCount++;
      neighborCount++;
    }
      if(!isBottomEdge && !isRightEdge) {
        if (myCellGrid[i + 1][j + 1].getType() == type)
          similarCount++;
        neighborCount++;
      }

        return similarCount;
  }
}
