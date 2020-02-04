package cellsociety;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Cell {

  public int myType;
  public int myX;
  public int myY;
  Image pic;
  int myAge = 0;
  int timeSinceEat = 0;
  String pic_name;


  public Cell(int type, int x, int y) {
    myType = type;
    this.myX = x;
    this.myY = y;

   /* try {
      pic = ImageIO.read(new File("src/images/"+s));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }*/
  }

  public void draw(Graphics g, Component c) {
    g.drawImage(pic, 400 + (myX * 750/simulationPanel.cols), 100 + (myY * 750/simulationPanel.rows), 750/simulationPanel.cols, 750/simulationPanel.rows, c);
  }
}
