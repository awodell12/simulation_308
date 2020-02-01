package cellsociety;

import java.util.List;
import javafx.util.Pair;

public abstract class Grid {
  int numColumns;
  int numRows;
  Cell [][] cells;

  public Grid(int cols, int rows){
    numColumns = cols;
    numRows = rows;
    cells = new Cell[numColumns][numRows];
  }

  public void updateCells(List<Cell> updateList){
    for (Cell c : updateList){
      Pair coords = c.getCoordinates();
      int x = (int) coords.getKey();
      int y = (int) coords.getValue();
      cells [x][y] = c;
    }
  }


}
