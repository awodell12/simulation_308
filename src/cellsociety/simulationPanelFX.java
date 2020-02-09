package cellsociety;

import cellsociety.simulation.*;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.util.Duration;
import xml.Configuration;
import xml.XMLParser;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class simulationPanelFX extends VBox implements EventHandler {

    public boolean play;
    public int simSpeed = 500;
    Timeline timeline;
    toolBar tb;

    //create Panel features
    public ComboBox<Integer> drawChoiceBox;
    private Canvas canvas;
    private Affine affine;

    //create grid modifiers
    private Color type0Color, type1Color, type2Color;
    public String currentSim;
    private Grid mainGrid;
    double percentage;

    //create XML helpers
    public static final String PERCOLATION = "data/Percolation.xml";
    public static final String GAME_OF_LIFE = "data/GameOfLife.xml";
    public static final String FIRE = "data/Fire.xml";
    public static final String SEGREGATION = "data/Segregation.xml";
    public static final String WATOR = "data/Wator.xml";

    //create simulation parameters
    private  double canvasWidth = 600;
    private  double canvasHeight = 600;
    private int cols, rows;

  public List<Point> neighbors;

    public simulationPanelFX() {
        //add the canvas to the panel and handle it
        this.canvas = new Canvas(canvasWidth,canvasHeight);
        this.canvas.setOnMousePressed(this::handleDraw);
        this.canvas.setOnMouseDragged(this::handleDraw);

      neighbors = new ArrayList<>();
      neighbors.add(new Point(0,1));
      neighbors.add(new Point(0,-1));
      neighbors.add(new Point(1,0));
      neighbors.add(new Point(-1,0));

        //create initial grid and scale the simulation
        createGridFromXML(PERCOLATION);
        currentSim = "Percolation";
        this.affine = new Affine();
        this.affine.appendScale(canvasWidth/cols,canvasHeight/rows);

        //add buttons and menus via toolBar
        tb = new toolBar(this);
        this.getChildren().addAll(tb, this.canvas);

        //Simulate
        this.timeline = new Timeline(new KeyFrame(Duration.millis(800), this::doStep));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void doStep(ActionEvent actionEvent) {
        simSpeed = (int) this.tb.speedSlider.getValue();
        this.timeline.setRate(simSpeed);
        if(this.play) {
          //mainGrid.printCells();
            mainGrid.updateCells(mainGrid.checkForUpdates());
            draw();
        }
    }

    private void handleDraw(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        int drawType = drawChoiceBox.getValue();

        try {
            Point2D simCord = this.affine.inverseTransform(mouseX, mouseY);
            int xCord = (int) simCord.getX();
            int yCord = (int) simCord.getY();

            if (xCord >= 0 & xCord < cols & yCord >= 0 & yCord < rows) {
              List <Cell> updateList = new ArrayList<>();
              updateList.add(new Cell(drawType,xCord,yCord));
                this.mainGrid.updateCells(updateList);
                    //getGrid()[xCord][yCord].setType(drawType);
                draw();
            }
        } catch (NonInvertibleTransformException e) {
            System.out.println("Could not inverse transform");
        }
    }

    public void simChecker(String str) {
        if (str.equals("Percolation")) { createGridFromXML(PERCOLATION);}
        if (str.equals("Fire")) { createGridFromXML(FIRE);}
        if (str.equals("Segregation")) { createGridFromXML(SEGREGATION);}
        if (str.equals("Prey/Predator")) { createGridFromXML(WATOR);}
        if (str.equals("Game of Life")) { createGridFromXML(GAME_OF_LIFE);}
    }

    public void draw() {
        affine = new Affine();
        affine.appendScale(canvasWidth/cols,canvasHeight/rows);

        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(affine);

        g.setFill(Color.WHITE);
        g.setStroke(Color.GRAY);
        g.setLineWidth(0.02);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        colorDecider();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mainGrid.getGrid()[i][j].getType() == 0) {
                    g.setFill(type0Color);
                    g.fillRect(i, j, 1, 3);
                }
                if (mainGrid.getGrid()[i][j].getType() == 1) {
                    g.setFill(type1Color);
                    g.fillRect(i, j, 1, 3);
                }
                if (mainGrid.getGrid()[i][j].getType() == 2) {
                    g.setFill(type2Color);
                    g.fillRect(i, j, 1, 3);
                }
            }
        }

        for (int i = 0; i <= rows; i++) { g.strokeLine(i,0,i,rows); }
        for (int y = 0; y <= cols; y++) { g.strokeLine(0,y,cols,y); }
    }

    public void colorDecider () {
        if(currentSim.equals("Percolation")) {
            type0Color = Color.WHITE;
            type1Color = Color.BLACK;
            type2Color = Color.TURQUOISE;
        }
        if(currentSim.equals("Fire")) {
            type0Color = Color.DARKGREEN;
            type1Color = Color.CRIMSON;
            type2Color = Color.WHITE;
        }
        if(currentSim.equals("Prey/Predator")) {
            type0Color = Color.WHITE;
            type1Color = Color.GOLD;
            type2Color = Color.DARKGRAY;
        }
        if(currentSim.equals("Segregation")) {
            type0Color = Color.WHITE;
            type1Color = Color.ORANGE;
            type2Color = Color.YELLOWGREEN;
        }
        if(currentSim.equals("Game of Life")) {
            type0Color = Color.WHITE;
            type1Color = Color.BLACK;
            type2Color = Color.WHITE;
        }
    }

        @Override
        public void handle (Event event){
        }

        private void createGridFromXML (String file){
            XMLParser myParser = new XMLParser();
            Configuration config = myParser.getConfiguration(new File(file));

            cols = config.getWidth();
            rows = config.getHeight();
            percentage = config.getPercentage();

            if (file.equals(PERCOLATION)) {
                mainGrid = new PercolationGrid(cols, rows, neighbors);
            } else if (file.equals(GAME_OF_LIFE)) {
                mainGrid = new GameOfLifeGrid(cols, rows, neighbors);
            } else if (file.equals(FIRE)) {
                mainGrid = new FireGrid(cols, rows, percentage, neighbors);
            } else if (file.equals(SEGREGATION)) {
                mainGrid = new SegregationGrid(cols, rows, percentage, neighbors);
            } else if (file.equals(WATOR)) {
                mainGrid = new WatorGrid(cols, rows, 5, 3, 2, neighbors);
            }
            // TODO don't hard code these in

            ArrayList<Cell> cellsArray = new ArrayList<>();
            for (Point p : config.getCellCoordinates().keySet()) {

                cellsArray.add(new Cell(config.getCellCoordinates().get(p), p.y, p.x));
            }
            mainGrid.updateCells(cellsArray);
        }

        public void resizeGrid(int size, String str){
        cols = size;
        rows = size;
            if (str.equals("Percolation")) { mainGrid = new PercolationGrid(size,size,neighbors);}
            if (str.equals("Fire")) { mainGrid = new FireGrid(size,size,percentage,neighbors);}
            if (str.equals("Segregation")) { mainGrid = new SegregationGrid(size,size,percentage,neighbors);}
            if (str.equals("Prey/Predator")) { mainGrid = new WatorGrid(size,size,5,3,2,neighbors);}
            if (str.equals("Game of Life")) { mainGrid = new GameOfLifeGrid(size,size,neighbors);}
    }

    public Grid getMainGrid() {
        return this.mainGrid;
    }
}
