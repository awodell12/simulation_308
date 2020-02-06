package cellsociety;

import java.awt.*;

public class Cell {

  private int myType;
  private int myX;
  private int myY;
  Image pic;
  int myAge = 0;
  int timeSinceEat = 0;
  String pic_name;


  public Cell(int type, int x, int y) {
    myType = type;
    this.myX = x;
    this.myY = y;
  }

  public int getType() {
    return myType;
  }

  public int getX() {
    return myX;
  }

  public int getY() {
    return myY;
  }

  public void setX(int x) {
    myX = x;
  }

  public void setY(int y) {
    myY = y;
  }

  public void setType(int type) {
    myType = type;
  }

  public void draw(Graphics g, Component c) {
    g.drawImage(pic, 400 + (myX * 750/simulationPanel.cols), 100 + (myY * 750/simulationPanel.rows), 750/simulationPanel.cols, 750/simulationPanel.rows, c);
  }
}
