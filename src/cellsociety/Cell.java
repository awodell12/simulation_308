package cellsociety;

import javafx.util.Pair;

public class Cell {
  int myType;
  Pair myCoordinates;
  public Cell(int type, int x, int y){
    myType = type;
    myCoordinates = new Pair(x, y);
  }

  public int getMyType() {
    return myType;
  }

  public void setMyType(int type) {
    this.myType = type;
  }
  public Pair getCoordinates(){
    return myCoordinates;
  }

}
