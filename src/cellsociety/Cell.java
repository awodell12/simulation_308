package cellsociety;


public class Cell {
  int myType;
  int  myXCoordinate;
  int myYCoordinate;
  public Cell(int type, int x, int y){
    myType = type;
    myXCoordinate = x;
    myYCoordinate = y;
  }

  public int getMyType() {
    return myType;
  }

  public void setMyType(int type) {
    this.myType = type;
  }
  public int getMyXCoordinate(){
    return myXCoordinate;
  }
  public int getMyYCoordinate(){
    return myYCoordinate;
  }

}
