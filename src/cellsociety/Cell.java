package cellsociety;


public class Cell {
  int myType;
  int myXCoordinate;
  int myYCoordinate;
  public Cell(int type, int x, int y){
    myType = type;
    myXCoordinate = x;
    myYCoordinate = y;
  }

  public int getType() {
    return myType;
  }


  public int getMyXCoordinate(){
    return myXCoordinate;
  }
  public int getMyYCoordinate(){
    return myYCoordinate;
  }

}
