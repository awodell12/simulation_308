package cellsociety;

import javafx.event.Event;
import javafx.geometry.Pos;
import xml.Configuration;
import xml.XMLParser;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class MainFX extends Application {
    private final int simWidth = 950, simHeight = 950;
    int newX = 50;
    int newY = 50;
    int stageNo = 1;

    @Override
    public void start(Stage stage) throws Exception {

        simulationPanelFX mainPanel = new simulationPanelFX();
        Scene scene = new Scene(mainPanel, simWidth, simHeight);
        stage.setTitle("Main Simulation");
        stage.setScene(scene);
        stage.show();

        mainPanel.draw();
    }




    public static void main (String[] args) {
        launch();
    }


}

