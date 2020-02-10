package cellsociety.simulation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javafx.util.Pair;

/**
 * By default all cells are initialized to their 0 state. If you want initial states you must call
 * UpdateCells with a list of non-zero initial cells.
 */
public abstract class Grid {

  public final int EMPTY = 0;
  int numColumns;
  int numRows;
  Cell[][] myCellGrid;
  private List<Point> neighborCoords;
  boolean rapAroundEdges;
  enum edgeType{
    FIXED, TOROIDAL
  }
  edgeType myEdgeType = edgeType.TOROIDAL;

  public Grid(int cols, int rows) {
    numColumns = cols;
    numRows = rows;
    myCellGrid = new Cell[numRows][numColumns];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        myCellGrid[i][j] = new Cell(EMPTY, i, j);
      }
    }
  }
  public Grid(int cols, int rows, List<Point> neighborLocations) { // edgeType edges
    this(cols, rows);
    neighborCoords = neighborLocations;
  }

  public abstract List<Cell> checkForUpdates();

  /**
   * This method is used both to updateCells between generations and also to set the original
   * non-zero grid Cells
   *
   * @param updateList a list of Cells to take the place of the old cells at their coordinates
   */
  public void updateCells(List<Cell> updateList) {
    for (Cell c : updateList) {
      int x = c.getX();
      int y = c.getY();
      myCellGrid[x][y] = c;
    }
  }

  /**
   * Because its an array I thought returning a copy would be a good way to make it immutable
   *
   * @return
   */
  public Cell[][] getGrid() {
    //Cell[][] retu = new Cell[myCellGrid.length][myCellGrid[0].length];
    //System.arraycopy(myCellGrid, 0, retu, 0, myCellGrid.length);
    return myCellGrid;
  }

  /**
   * debug/testing method to print out current states
   *
   * @return
   */
  public void printCells() {
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        if (myCellGrid[i][j] == null) {
          System.out.print("x ");
        } else {
          System.out.print(myCellGrid[i][j].getType() + " ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  Pair checkLikeNeighbors(int i, int j, int type) {
    int similarCount = 0;
    int notEmptyNeighborCount = 0;

    Queue<Cell> neighborsToCheck = findNeighbors(i, j);

    while (neighborsToCheck.peek() != null) {
      Cell currentCell = neighborsToCheck.remove();
      if (currentCell.getType() == type) {
        similarCount++;
      } else if (currentCell.getType() == EMPTY) {
        notEmptyNeighborCount++;
      }
    }
    return new Pair(similarCount, notEmptyNeighborCount);
  }

  protected Queue<Cell> findNeighbors(int i, int j) {
    LinkedList<Cell> neighbors = new LinkedList<>();
    for (Point p : neighborCoords) {
//      if (isLegalCell(i + p.x, j + p.y)) {
//        neighbors.add(myCellGrid[i + p.x][j + p.y]);
//      }
      Integer validNeighbor = legalNeighbor(i+ p.x,j + p.y);
      if(validNeighbor != null) {
        neighbors.add(myCellGrid[validNeighbor/numColumns][validNeighbor%numColumns]);
      }
    }
    return neighbors;
  }
  boolean isLegalCell(int i, int j) {
    boolean notLegal = (i >= numRows || i < 0 || j >= numColumns || j < 0);
    return !notLegal;
  }
  Integer legalNeighbor(int i, int j){
    switch (myEdgeType){
      case FIXED:
        if (isLegalCell(i,j)){
          return i*numColumns + j;
        }
        return null;

      case TOROIDAL:
        if (isLegalCell(i,j)){
          return i*numColumns + j;
        }
        return wrappedLoc(i,j);
      default:
        throw new IllegalStateException("Unacceptable Edge Style: " + myEdgeType);
    }
  }

  protected Integer wrappedLoc(int i, int j){
    if (i<0)
    return (numRows-1)*(numColumns ) + j;
    if (i >= numColumns){
      return j;
    }
    if(j<0){
      return i*numColumns + (numColumns -1);
    }
    else {
      return i *numColumns;
    }
  }

  public List<Pair> findEmptyCells() {
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
}
