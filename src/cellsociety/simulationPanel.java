package cellsociety;

import xml.Configuration;
import xml.XMLParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static java.awt.Font.BOLD;

public class simulationPanel extends JPanel implements ActionListener {

    public static final String PERCOLATION = "data/Percolation.xml";
    public static final String GAME_OF_LIFE = "data/GameOfLife.xml";
    public static final String FIRE = "data/Fire.xml";
    public static final String SEGREGATION = "data/Segregation.xml";
    public static final String WATOR = "data/Wator.xml";
    private Configuration config;

    // Create text
    String txt = "<html><h1 align='center'>Choose Simulation</h1>";
    String nowRunning;

    // Create Labels
    JLabel simNameLabel = new JLabel(txt);
    JLabel MenuLabel = new JLabel("Choose the Simulation");

    // Create Buttons for Simulation Types
    JButton percButton = new JButton("Percolation");
    JButton lifeButton = new JButton("Game of Life");
    JButton segButton = new JButton("Segregation");
    JButton preyButton = new JButton("Prey Predator");
    JButton fireButton = new JButton("Fire");

    private boolean step, play;
    private boolean perc = false, life = false, prey = false, seg = false, fire = false;

    ImageIcon stepIcon = new ImageIcon("src/images/step.png");
    ImageIcon playIcon = new ImageIcon("src/images/play.png");
    ImageIcon speedUpIcon = new ImageIcon("src/images/plus.png");
    ImageIcon speedDownIcon = new ImageIcon("src/images/minus.png");

    JButton stepButton = new JButton(stepIcon);
    JButton playButton = new JButton(playIcon);
    JButton speedUpButton = new JButton(speedUpIcon);
    JButton speedDownButton = new JButton(speedDownIcon);


    // Set Panel Variables
    private int width = Main.frameWidth;
    private int height = Main.frameHeight;

    public static int cols = 6, rows = 6;

    public float cellWidth = 750/cols;
    public float cellHeight = 750/rows;

    //Set Panel Thread
    Thread thread;
    private int threadSpeed = 500;

    //Create Initial Grids
    private Grid mainGrid;

    ArrayList<Cell> cellsArray = new ArrayList<>();

    simulationPanel(){
        //Create a Layout for buttons
        this.setLayout(null);
        percButton.setBounds(width / 24, height / 9, 200, 50);
        lifeButton.setBounds(width / 24, height / 9 + 50, 200, 50);
        segButton.setBounds(width / 24, height / 9 + 100, 200, 50);
        preyButton.setBounds(width / 24, height / 9 + 150, 200, 50);
        fireButton.setBounds(width / 24, height / 9 + 200, 200, 50);
        simNameLabel.setBounds(width / 24 - 20, height / 9 - 100, 300, 50);

        stepButton.setBounds(140, 400, 50, 50);
        playButton.setBounds(80, 400, 50, 50);
        speedUpButton.setBounds(20, 400, 50, 50);
        speedDownButton.setBounds(200, 400, 50, 50);

        buttonSettings(playButton);
        buttonSettings(stepButton);
        buttonSettings(speedUpButton);
        buttonSettings(speedDownButton);

        //Add the Buttons to the panel
        this.add(percButton, lifeButton);
        this.add(lifeButton);
        this.add(segButton);
        this.add(preyButton);
        this.add(fireButton);
        this.add(simNameLabel);
        this.add(playButton);
        this.add(stepButton);
        this.add(speedDownButton);
        this.add(speedUpButton);
        this.setBackground(Color.getHSBColor(100, 100, 100));

        buttonChecker();

        thread = new Thread(() -> {
            while (true) {
                update();
                try {
                    Thread.sleep(threadSpeed);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void update() {
        if(play & (perc || life || prey || fire || seg)) {
            mainGrid.updateCells(mainGrid.checkForUpdates());
        }
        if(step & !play & (perc || life || prey || fire || seg)){
            mainGrid.updateCells(mainGrid.checkForUpdates());
            step = !step;
        }
        repaint();
    }

    public void buttonSettings(JButton b){
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
    }

    private void buttonChecker() {
        fireButton.addActionListener(listener -> { fire=true; createGridFromXML(FIRE); perc = life = prey = seg = false; play =false;});
        segButton.addActionListener(listener ->  { seg=true;  createGridFromXML(SEGREGATION); perc = life = prey = fire = false; play =false;});
        lifeButton.addActionListener(listener -> { life=true; createGridFromXML(GAME_OF_LIFE); perc = prey = fire = seg = false; play =false;});
        preyButton.addActionListener(listener -> { prey=true; createGridFromXML(WATOR); perc = life = fire = seg = false; play =false;});
        percButton.addActionListener(listener -> { perc=true; createGridFromXML(PERCOLATION); life = prey = fire = seg = false; play =false;});

        playButton.addActionListener(listener -> { play = !play; });
        stepButton.addActionListener(listener -> { step = !step; });

        speedUpButton.addActionListener(listener -> { if(threadSpeed > 200){ threadSpeed-= 100;} });
        speedDownButton.addActionListener(listener -> { if(threadSpeed < 1500){ threadSpeed+= 100;} });
    }

    private void createGridFromXML(String file) {
        resetGrid();
        XMLParser myParser = new XMLParser();
        config = myParser.getConfiguration(new File(file));

        cols = config.getWidth();
        rows = config.getHeight();
        double percentage = config.getPercentage();

        if (file.equals(PERCOLATION)) { mainGrid = new PercolationGrid(cols, rows); }
        else if (file.equals(GAME_OF_LIFE)) { mainGrid = new GameOfLifeGrid(cols, rows); }
        else if (file.equals(FIRE)) { mainGrid = new FireGrid(cols, rows, percentage); }
        else if (file.equals(SEGREGATION)) { mainGrid = new SegregationGrid(cols, rows, percentage) ; }
        else if (file.equals(WATOR)) { mainGrid = new WatorGrid(cols, rows, 5, 3, 2); }
        // TODO don't hard code these in

        for (Point p : config.getCellCoordinates().keySet()) {
            cellsArray.add(new Cell(config.getCellCoordinates().get(p), p.x, p.y));
        }
        mainGrid.updateCells(cellsArray);
    }

    public void paintComponent (Graphics g) {
        //Check if the Simulation is indeed running
        if(perc || life || prey || fire || seg) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    try {
                        imageDecider(mainGrid.myCellGrid[i][j]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mainGrid.myCellGrid[i][j].draw(g, this);
                    //System.out.println(threadSpeed);
                }
            }
        }
    }

    public void imageDecider (Cell x) throws IOException {
        if(perc){
            decisionHelper(x, 0, "white_frame.png");
            decisionHelper(x, 1, "black.png");
            decisionHelper(x, 2, "turquoise_frame.png");
        }
        if(fire){
            decisionHelper(x, 0, "tree_frame.png");
            decisionHelper(x, 1, "fire_frame.png");
            decisionHelper(x, 2, "empty_frame.png");
        }
       if(prey){
            decisionHelper(x, 0, "white_frame.png");
            decisionHelper(x, 1, "black.png");
            decisionHelper(x, 2, "turquoise_frame.png");
        }
        if(life){
            decisionHelper(x, 0, "empty_frame.png");
            decisionHelper(x, 1, "man_frame.png");
        }
        if(seg){
            decisionHelper(x, 0, "empty_frame.png");
            decisionHelper(x, 1, "kid_frame.png");
            decisionHelper(x, 2, "man_frame.png");
        }
    }

    private void decisionHelper(Cell x, int i, String s) throws IOException {
        if (x.myType == i & x.pic_name != s) {
            x.pic = ImageIO.read(new File("src/images/" + s));
            x.pic_name = s;
        }
    }

    private void resetGrid() {
        cellsArray = new ArrayList<>();
        /*
        TODO: REMOVE ALL IMAGES IN THE CELLS AND UN-PAINT ALL CELLS @ NEVZAT
         */
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
