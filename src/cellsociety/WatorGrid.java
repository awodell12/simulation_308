package cellsociety;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.util.Pair;

@SuppressWarnings("SpellCheckingInspection")
public class WatorGrid extends Grid {

  public static final int SHARK = 2;
  public static final int FISH = 1;
  public static final int EMPTY = 0;

  private int fishTimeToBreed;
  private int sharkTimeToBreed;
  private int sharkDeathTime;

  private Set<Integer> myFish = new HashSet();
  private Set<Integer> mySharks= new HashSet();
  private Set<Pair> emptyCells = new HashSet<>();
  private boolean initialize = true;



  public WatorGrid(int cols, int rows, int sharkBreed, int fishBreed, int sharkDie ) {
    super(cols, rows);
    fishTimeToBreed = fishBreed;
    sharkDeathTime = sharkDie;
    sharkTimeToBreed = sharkBreed;
  }

  @Override
  public void updateCells(List<Cell> updateList) {
    if (initialize) {
      for (Cell cell : updateList) {
        if (cell.myType == SHARK) {
          myFish.add(cell.myX*numColumns+ cell.myY);
        }
        else{
          mySharks.add(cell.myX * numColumns + cell.myY);
        }
      }
      emptyCells.addAll(findEmptyCells());
      initialize = false;
    }
    super.updateCells(updateList);
  }

  @Override
  public List<Cell> checkForUpdates() {
    ArrayList<Cell> updateList = new ArrayList<>();
    updateList.addAll(updateSharks());
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        Cell curCell = myCellGrid[i][j];

        if (curCell.myType==FISH){

        }
      }
    }
    return updateList;
  }

  private Collection<Cell> updateSharks() {
    ArrayList<Cell> updateList = new ArrayList<>();
    List<Pair> fish;
    List<Pair> emptySpots;
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        Cell curCell = myCellGrid[i][j];
        if (curCell.myType == SHARK){
          fish = checkForNeighbors(i,j,myFish);
          if (fish.isEmpty()){
            emptySpots = checkForNeighbors(i,j,emptyCells);
            if (!emptySpots.isEmpty()){
              updateList.add(moveShark(emptySpots,i,j,curCell,false));
            }
          }
          else {
            updateList.add(moveShark(fish, i, j, curCell, true));
          }
        }
      }
    }
    return updateList;
  }

  private Cell moveShark(List<Pair> fish, int i, int j, Cell curCell, boolean killFish) {
    int randIndex = (int) (Math.random()*fish.size());
    Pair newCoordinates = fish.get(randIndex);
    int newx =(int) newCoordinates.getKey();
    int newy = (int) newCoordinates.getValue();
    if(killFish) {
      myFish.remove(newx * numColumns + newy);
    }
    else {
      emptyCells.remove(newx * numColumns + newy);
    }
    mySharks.remove(i*numColumns + j);
    mySharks.add(newx*numColumns + newy);
    curCell.myX = newx;
    curCell.myY = newy;
    return curCell;
  }

  private List<Pair> checkForNeighbors(int i, int j, Set lookingFor) {
    List<Pair> neighborFish = new ArrayList<>();
    Integer sharkCoordinate = i*numColumns + j;

    boolean isTopEdge = (i == 0); //&& j!=0 && j!=numColumns);
    boolean isBottomEdge = (i == numRows - 1);// && j!=0 && j!=numColumns);
    boolean isLeftEdge = (j == 0); // && i!= numRows && i != 0);
    boolean isRightEdge = (j == numColumns - 1); // && i!= numRows && i != 0);

  if (!isTopEdge && lookingFor.contains(sharkCoordinate - numColumns)){
    neighborFish.add(new Pair(i-1,j));
  }
    if (!isBottomEdge && lookingFor.contains(sharkCoordinate + numColumns)){
      neighborFish.add(new Pair(i+1,j));
    }
    if (!isRightEdge && lookingFor.contains(sharkCoordinate + 1)){
      neighborFish.add(new Pair(i,j+1));
    }
    if (!isLeftEdge && lookingFor.contains(sharkCoordinate - 1)){
      neighborFish.add(new Pair(i,j-1));
    }



    return neighborFish;
  }
}

