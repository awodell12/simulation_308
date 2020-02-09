package cellsociety.simulation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import javafx.util.Pair;

@SuppressWarnings("SpellCheckingInspection")
public class WatorGrid extends Grid {

  public static final int SHARK = 2;
  public static final int FISH = 1;
  public static final int EMPTY = 0;
  public static final int ENERGY_FROM_FISH = 1;

  private int fishTimeToBreed;
  private int sharkTimeToBreed;
  private int sharkDeathTime;

  private Set<Integer> myFish = new HashSet();
  private Set<Integer> mySharks= new HashSet();
  private Set<Integer> emptyCells = new HashSet<>();
  private Set<Integer> fishToRemove = new HashSet<>();
  private Set<Integer> myFishToAdd = new HashSet<>();
  private List<Point> neighborLocs;
  private boolean initialize = true;



  public WatorGrid(int cols, int rows, int sharkBreed, int fishBreed, int sharkDie, List<Point> neighbors ) {
    super(cols, rows, neighbors);
    fishTimeToBreed = fishBreed;
    sharkDeathTime = sharkDie;
    sharkTimeToBreed = sharkBreed;
    neighborLocs = neighbors;
    //setNeighborLocs(neighbors);
  }

//  private void setNeighborLocs(List<Point> neighbors) {
//    for (Point p : neighbors){
//      neighborLocs.add(p.x * numColumns + p.y);
//    }
//  }

  @Override
  public void updateCells(List<Cell> updateList) {
    if (initialize) {
      for (Cell cell : updateList) {
        if (cell.getType() == SHARK) {
          mySharks.add(cell.getX()*numColumns+ cell.getY());
        }
        else if (cell.getType() == FISH){
          myFish.add(cell.getX() * numColumns + cell.getY());
        }
      }
      for (Pair pair:findEmptyCells()){
        emptyCells.add(calcLocation((int)pair.getKey(),(int)pair.getValue()));
        emptyCells.removeAll(myFish);
        emptyCells.removeAll(mySharks);
      }
      initialize = false;
    }
    super.updateCells(updateList);
  }

  @Override
  public List<Cell> checkForUpdates() {
    ArrayList<Cell> updateList = new ArrayList<>();
    updateList.addAll(updateSharks());
    updateList.addAll(updateFish());
    myFish.removeAll(fishToRemove);
    myFish.addAll(myFishToAdd);
    fishToRemove.clear();
    myFishToAdd.clear();
    return updateList;
  }

  private Collection<Cell> updateFish() {
    ArrayList<Cell> updateList = new ArrayList<>();
    for(Integer location: myFish){
      int x = location/numColumns ;
      int y = location % numColumns;
        Cell curCell = myCellGrid[x][y];
        List<Pair> emptySpots;
        emptySpots = checkForNeighbors(x,y,emptyCells);
        if (emptySpots.isEmpty()){
          continue;
        }
        updateList.add(moveFish(emptySpots, x,y,curCell,updateList));
        curCell.increaseAge();
      }
    return updateList;
  }

  private Cell moveFish(List<Pair> emptySpots, int x, int y, Cell curCell, List updateList) {
    int randIndex = (int) (Math.random()*emptySpots.size());
    Pair newCoordinates = emptySpots.get(randIndex);
    int newx =(int) newCoordinates.getKey();
    int newy = (int) newCoordinates.getValue();
    curCell.setX(newx);
    curCell.setY(newy);
    myFishToAdd.add(calcLocation(newx,newy));
    if (curCell.getAge() > fishTimeToBreed){
      curCell.resetAge();
      updateList.add(new Cell(FISH,x,y));
    }
    else{
      updateList.add(new Cell(EMPTY,x,y));
      fishToRemove.add(calcLocation(x,y));
      emptyCells.add(calcLocation(x,y));
    }
    return curCell;
  }

  private Collection<Cell> updateSharks() {
    ArrayList<Cell> updateList = new ArrayList<>();
    List<Pair> fish;
    List<Pair> emptySpots;
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        Cell curCell = myCellGrid[i][j];
        if (curCell.getType() == SHARK){
          if (curCell.getTimeSinceEat() > sharkDeathTime){
            updateList.add(new Cell(EMPTY,i,j));
            emptyCells.add(calcLocation(i,j));
            mySharks.remove(curCell);
            continue;
          }
          fish = checkForNeighbors(i,j,myFish);
          if (fish.isEmpty()){
            emptySpots = checkForNeighbors(i,j,emptyCells);
            if (!emptySpots.isEmpty()){
              updateList.add(moveShark(emptySpots,i,j,curCell,false));
              addNewSharkOrEmpty(updateList, i, j, curCell);
            }
            else{
              curCell.increaseAge();
              curCell.setTimeSinceEat(1);
            }
          }
          else {
            updateList.add(moveShark(fish, i, j, curCell, true));
            addNewSharkOrEmpty(updateList, i, j, curCell);
          }

        }
      }
    }
    return updateList;
  }

  private Integer calcLocation(int i, int j) {
    return i * numColumns + j;
  }

  private void addNewSharkOrEmpty(ArrayList<Cell> updateList, int i, int j, Cell curCell) {
    if (curCell.getAge() > sharkTimeToBreed) {
      updateList.add(new Cell(SHARK, i, j));
      mySharks.add(i*numColumns + j);
      curCell.resetAge();
    } else {
      updateList.add(new Cell(EMPTY, i, j));
      emptyCells.add(calcLocation(i,j));
    }
  }

  private Cell moveShark(List<Pair> placesToMove, int i, int j, Cell curCell, boolean killFish) {
    int randIndex = (int) (Math.random()*placesToMove.size());
    Pair newCoordinates = placesToMove.get(randIndex);
    int newx =(int) newCoordinates.getKey();
    int newy = (int) newCoordinates.getValue();
    if(killFish) {
      myFish.remove(newx * numColumns + newy);
      curCell.setTimeSinceEat(-ENERGY_FROM_FISH);
    }
    else {
      emptyCells.remove(newx * numColumns + newy);
      curCell.setTimeSinceEat(1);
    }
    mySharks.remove(i*numColumns + j);
    mySharks.add(newx*numColumns + newy);
    curCell.setX(newx);
    curCell.setY(newy);
    curCell.increaseAge();
    return curCell;
  }

  private List<Pair> checkForNeighbors(int i, int j, Set lookingFor) {
    List<Pair> neighbors = new ArrayList<>();
    Integer sharkCoordinate = calcLocation(i,j);

    Queue<Integer> neighborsToCheck = findNeighbors(sharkCoordinate);
    while (neighborsToCheck.peek() != null) {
      Integer currentCell = neighborsToCheck.remove();
      if (lookingFor.contains(currentCell)) {
        neighbors.add(new Pair(currentCell / numColumns, currentCell % numColumns));
      }
    }
    return neighbors;
  }

  protected Queue<Integer> findNeighbors(int location) {
    LinkedList<Integer> neighbors = new LinkedList<>();
    for (Point p : neighborLocs) {
      if (isLegalCell((location / numColumns) + p.x , (location % numColumns) + p.y)) {
        neighbors.add(location + calcLocation(p.x,p.y));
      }
    }
    return neighbors;
  }

}

