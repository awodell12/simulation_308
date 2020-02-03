package cellsociety;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.awt.*;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main {

    // Set the simulation frame
    static JFrame simframe;

    // Set simulation frames
    static JButton perc, life, fire, seg, prey;
    static JButton play, pause, step, speedUp, speedDown;

    // label to display text
    static JLabel label;

    public static final int frameWidth = 1200, frameHeight = 900;

    public static void main (String[] args) {

        // create a new frame to store text field and button
        simframe = new JFrame("Cell Society");

        JPanel p = new simulationPanel();

        // add panel to frame
        simframe.add(p);
        // set the size of frame
        simframe.setSize(frameWidth, frameHeight);
        simframe.setVisible(true);
        simframe.setResizable(false);


        Grid myGrid = new PercolationGrid(4,4);
        ArrayList<Cell> cellsArray = new ArrayList<>();
        cellsArray.add(new Cell(2,0,0));
        myGrid.updateCells(cellsArray);
        for (int i = 0; i < 5; i++){
            myGrid.updateCells(myGrid.checkForUpdates());
            myGrid.printCells();
        }
    }
}

