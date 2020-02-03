package cellsociety;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

@SuppressWarnings("SpellCheckingInspection")
public class WatorGrid extends Grid {

  public static final int SHARK = 2;
  public static final int FISH = 1;
  public static final int EMPTY = 0;

  private int fishTimeToBreed;
  private int sharkTimeToBreed;
  private int sharkDeathTime;

  private List myFish;
  private List mySharks;



  public WatorGrid(int cols, int rows, int sharkBreed, int fishBreed, int sharkDie ) {
    super(cols, rows);
    fishTimeToBreed = fishBreed;
    sharkDeathTime = sharkDie;
    sharkTimeToBreed = sharkBreed;
  }

  @Override
  public List<Cell> checkForUpdates() {
    ArrayList<Cell> updateList = new ArrayList<>();
    ArrayList<Pair> emptyCells = new ArrayList<>();
    emptyCells = findEmptyCells();
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        Cell curCell = myCellGrid[i][j];

        if (curCell.myType==FISH){

        }
      }
    }
    return updateList;
  }
}

