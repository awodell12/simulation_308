package cellsociety.simulation;


/**
 * @author Austin Odell
 * Purpose: The Cell class is the heart of any cellular automata simulation. It is responsible for keeping
 * track of all data pertaining to a certain cell in the grid.
 * Assumptions: We assume that when the member variables are set, they are set to valid values. For example
 * it is assumed that myX and myY are only set to valid coordinates
 * Dependencies: none
 * Example: new Cell(x coordinate, y-coordinate, type) and then adjust it using any of its methods
 */
public class Cell {

  private int myType;
  private int myX;
  private int myY;
  private int myAge;
  private int timeSinceEat;

  /**
   *
   * @param type: the type of cell it is corresponding to the simulation type. Often 0 is empty
   * @param x x-coordinate of the cell in the Grid
   * @param y y-coordinate of the cell in the Grid
   */
  public Cell(int type, int x, int y) {
    myType = type;
    this.myX = x;
    this.myY = y;
    timeSinceEat = 0;
    myAge = 0;
  }

  /**
   *
   * @return integer representation of the type of cell
   */
  public int getType() {
    return myType;
  }

  /**
   *
   * @return x-coordinate
   */
  public int getX() {
    return myX;
  }

  /**
   *
   * @return y-coordinate
   */
  public int getY() {
    return myY;
  }

  /**
   *
   * @param x change the x-coordinate of a cell to the passed value
   */
  public void setX(int x) {
    myX = x;
  }

  /**
   *
   * @param y  change the y-coordinate of a cell to the passed value
   */
  public void setY(int y) {
    myY = y;
  }

  /**
   *
   * @param type change the cell type to the passed value
   */
  public void setType(int type) {
    myType = type;
  }

  /**
   *
   * @return cell's age
   */
  public int getAge(){return myAge;}

  /**
   *
   * @return time since the shark last ate, doesn't make sense for other cell types/simulations
   */
  public int getTimeSinceEat(){ return  timeSinceEat;}

  /**
   * Increase the age of a cell by 1 for WaTor
   */
  public void increaseAge(){
    myAge ++;
  }

  /**
   * resets the age field to 0
   */
  public void resetAge(){
    myAge = 0;
  }

  /**
   * adjusts the timeSinceEat by adding the passed value to it
   * @param time the change in time, for sharks it gives them "energy" from eating a fish
   */
  public void setTimeSinceEat(int time){
    timeSinceEat += time;
  }


}
