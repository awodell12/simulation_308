package cellsociety;

import java.util.ArrayList;
import java.util.List;

/**
 * Cells Types: 0-open 1-blocked 2-Filled
 */
public class PercolationGrid extends Grid {

  public static final int FILLED = 2;
  public static final int OPEN = 0;
  public static final int BLOCKED = 1;

  private boolean isPercolated;

  public PercolationGrid(int cols, int rows) {
    super(cols, rows);
  }

  @Override
  public List<Cell> checkForUpdates() {
    ArrayList<Cell> updateList = new ArrayList<Cell>();
    if (isPercolated) return updateList;
    for (int i = 0; i < numRows ; i ++){
      for (int j = 0; j<numColumns; j++){
        Cell curCell = myCellGrid[i][j];
        if (curCell.getMyType()!=OPEN){
          continue;
        }
        int newType = checkNeighbors(i,j);
        if (newType != OPEN)
          updateList.add(new Cell(newType, i, j));
      }
    }
    return updateList;
  }

  private int checkNeighbors(int i, int j) {
    int count =0;
    boolean isTopEdge = (i == 0); //&& j!=0 && j!=numColumns);
    boolean isBottomEdge = (i == numRows - 1);// && j!=0 && j!=numColumns);
    boolean isLeftEdge = (j == 0); // && i!= numRows && i != 0);
    boolean isRightEdge = (j == numColumns-1); // && i!= numRows && i != 0);

      if (!isLeftEdge && !isTopEdge) if (myCellGrid[i-1][j-1].getMyType() ==2) count ++;

      if (!isTopEdge) if (myCellGrid[i-1][j].getMyType()==2) count ++;

      if(!isTopEdge && !isRightEdge) if (myCellGrid[i-1][j+1].getMyType()==2) count ++;

      if (!isLeftEdge) if (myCellGrid[i][j-1].getMyType()==2) count ++;

      if(!isRightEdge) if (myCellGrid[i][j+1].getMyType()==2) count ++;

      if(!isBottomEdge && !isLeftEdge) if (myCellGrid[i+1][j-1].getMyType()==2) count ++;

      if(!isBottomEdge) if (myCellGrid[i+1][j].getMyType()==2) count ++;

      if(!isBottomEdge && !isRightEdge) if (myCellGrid[i+1][j+1].getMyType()==2) count ++;

    if (count > 0)
    return FILLED;
    return OPEN;
  }

}
