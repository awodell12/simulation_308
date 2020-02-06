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
        int curType = myCellGrid[i][j].myType;
        if (curType == EMPTY) {
          continue;
        }

        Pair neighbors = checkLikeNeighbors(i, j, curType, false);
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
