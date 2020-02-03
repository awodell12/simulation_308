package cellsociety;


public class Cell {

  private int myType;
  private int myXCoordinate;
  private int myYCoordinate;
  int myAge;
  int timeSinceEat;

  public Cell(int type, int x, int y) {
    myType = type;
    myXCoordinate = x;
    myYCoordinate = y;
  }

  public int getType() {
    return myType;
  }

  public int getMyXCoordinate() {
    return myXCoordinate;
  }

  public int getMyYCoordinate() {
    return myYCoordinate;
  }


}
