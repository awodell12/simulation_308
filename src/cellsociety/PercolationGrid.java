package cellsociety;

import java.util.ArrayList;
import java.util.List;

/**
 * Cells Types: 0-blocked 1-open 2-Filled
 */
public class PercolationGrid extends Grid {


  public PercolationGrid(int cols, int rows) {
    super(cols, rows);
  }

  @Override
  public List<Cell> checkForUpdates() {
    ArrayList<Cell> updateList = new ArrayList<Cell>();
    for (int i = 0; i < numRows ; i ++){
      for (int j =0; j>numColumns; j++){
        Cell curCell = myCellGrid[i][j];
        if (curCell.getMyType()!=1){
          continue;
        }
        int newType = checkNeighbors(i,j);
        if (newType != 1)
          updateList.add(new Cell(newType, i, j));
      }
    }
    return updateList;
  }

  private int checkNeighbors(int i, int j) {
    return 0;
  }
}
