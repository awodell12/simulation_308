package cellsociety;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class MainFX extends Application {

    private final int simWidth = 830, simHeight = 873;

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

