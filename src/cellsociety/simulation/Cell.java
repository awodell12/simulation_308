package cellsociety.simulation;

import cellsociety.simulationPanel;
import java.awt.*;

public class Cell {

  private int myType;
  private int myX;
  private int myY;
  private Image pic;
  private int myAge = 0;
  private int timeSinceEat = 0;
  private String picName;


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

  public Image getPic(){
    return pic;
  }

  public void setPic(Image picture){
    pic = picture;
  }

  public void setPicName(String picName){
    picName= picName;
  }
  public String getPic_name(){
    return picName;
  }

  public void draw(Graphics g, Component c) {
    g.drawImage(pic, 400 + (myX * 750/ simulationPanel.cols), 100 + (myY * 750/simulationPanel.rows), 750/simulationPanel.cols, 750/simulationPanel.rows, c);
  }
}
