package cellsociety;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

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

        Pair neighbors = checkLikeNeighbors(i, j, ALIVE, false);
        int aliveNeighbors = (int) neighbors.getKey();
        if (aliveNeighbors >= 2 && aliveNeighbors < 4) {
          newType = ALIVE;
        } else {
          newType = DEAD;
        }
        if (newType != curCell.myType) {
          updateList.add(new Cell(newType, i, j));
        }
      }
    }
    return updateList;
  }


}
