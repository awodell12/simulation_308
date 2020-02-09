package cellsociety;

import cellsociety.simulation.Cell;
import cellsociety.simulation.FireGrid;
import cellsociety.simulation.GameOfLifeGrid;
import cellsociety.simulation.Grid;
import cellsociety.simulation.PercolationGrid;
import cellsociety.simulation.SegregationGrid;
import cellsociety.simulation.WatorGrid;
import xml.Configuration;
import xml.XMLParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class simulationPanel extends JPanel implements ActionListener {

    public static final String PERCOLATION_XML = "data/Percolation.xml";
    public static final String GAME_OF_LIFE_XML = "data/GameOfLife.xml";
    public static final String FIRE_XML = "data/Fire.xml";
    public static final String SEGREGATION_XML = "data/Segregation.xml";
    public static final String WATOR_XML = "data/Wator.xml";
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
    private boolean buttonPressed;

    ImageIcon stepIcon = new ImageIcon("src/images/step.png");
    ImageIcon playIcon = new ImageIcon("src/images/play.png");
    ImageIcon speedUpIcon = new ImageIcon("src/images/plus.png");
    ImageIcon speedDownIcon = new ImageIcon("src/images/minus.png");

    JButton stepButton = new JButton(stepIcon);
    JButton playButton = new JButton(playIcon);
    JButton speedUpButton = new JButton(speedUpIcon);
    JButton speedDownButton = new JButton(speedDownIcon);


    // Set Panel Variables
    private int width = Main.FRAME_WIDTH;
    private int height = Main.FRAME_HEIGHT;

    public static int cols = 6, rows = 6;

    public float cellWidth = 750/cols;
    public float cellHeight = 750/rows;

    //Set Panel Thread
    Thread thread;
    private int threadSpeed = 500;

    //Create Initial Grids
    private Grid mainGrid;

    simulationPanel() {
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
        buttonPressed = (perc || life || prey || fire || seg);
        if(play && buttonPressed) {
            mainGrid.updateCells(mainGrid.checkForUpdates());
        }
        if(step && !play && buttonPressed){
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
        fireButton.addActionListener(listener -> { fire=true; createGridFromXML(FIRE_XML); perc = life = prey = seg = false; play =false;});
        segButton.addActionListener(listener ->  { seg=true;  createGridFromXML(SEGREGATION_XML); perc = life = prey = fire = false; play =false;});
        lifeButton.addActionListener(listener -> { life=true; createGridFromXML(GAME_OF_LIFE_XML); perc = prey = fire = seg = false; play =false;});
        preyButton.addActionListener(listener -> { prey=true; createGridFromXML(WATOR_XML); perc = life = fire = seg = false; play =false;});
        percButton.addActionListener(listener -> { perc=true; createGridFromXML(PERCOLATION_XML); life = prey = fire = seg = false; play =false;});

        playButton.addActionListener(listener -> { play = !play; });
        stepButton.addActionListener(listener -> { step = !step; });

        speedUpButton.addActionListener(listener -> { if(threadSpeed > 200){ threadSpeed-= 100;} });
        speedDownButton.addActionListener(listener -> { if(threadSpeed < 1500){ threadSpeed+= 100;} });
    }

    private void createGridFromXML(String file) {
        XMLParser myParser = new XMLParser();
        config = myParser.getConfiguration(new File(file));

        cols = config.getWidth();
        rows = config.getHeight();
        double percentage = config.getPercentage();

        if (file.equals(PERCOLATION_XML)) { mainGrid = new PercolationGrid(cols, rows); }
        else if (file.equals(GAME_OF_LIFE_XML)) { mainGrid = new GameOfLifeGrid(cols, rows); }
        else if (file.equals(FIRE_XML)) { mainGrid = new FireGrid(cols, rows, percentage); }
        else if (file.equals(SEGREGATION_XML)) { mainGrid = new SegregationGrid(cols, rows, percentage) ; }
        else if (file.equals(WATOR_XML)) { mainGrid = new WatorGrid(cols, rows, 5, 3, 2); }
        // TODO don't hard code these in

        ArrayList<Cell> cellsArray = new ArrayList<>();
        for (Point p : config.getCellCoordinates().keySet()) {

            cellsArray.add(new Cell(config.getCellCoordinates().get(p), p.y, p.x));
        }
        mainGrid.updateCells(cellsArray);
    }

    public void paintComponent (Graphics g) {
        //Check if the Simulation is indeed running
        buttonPressed = (perc || life || prey || fire || seg);
        if(buttonPressed) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    try {
                        imageDecider(mainGrid.getGrid()[i][j]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mainGrid.getGrid()[i][j].draw(g, this);
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
            decisionHelper(x, 0, "empty_frame.png");
            decisionHelper(x, 1, "fish_frame.png");
            decisionHelper(x, 2, "shark_frame.png");
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
        if (x.getType() == i && x.getPic_name() != s) {
            x.setPic(ImageIO.read(new File("src/images/" + s)));
            x.setPicName(s);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
