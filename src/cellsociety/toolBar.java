package cellsociety;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;


public class toolBar extends ToolBar {

    private simulationPanelFX fx;
    private ComboBox<String> simulationChoiceBox;
    private Slider sizeSlider;
    public Slider speedSlider;
    private Button playButton;
    private String playStop = "Play";

    public toolBar(simulationPanelFX fx){
        //Get the Main Panel in
        this.fx = fx;

        //Generate the Buttons
        Button graphButton = new Button("Graph");
        graphButton.setOnAction(this::handleGraph);

        Button loadButton = new Button("Load XML");
        loadButton.setOnAction(this::handleLoad);

        Button newWindow = new Button("New");
        newWindow.setOnAction(this::handleWindow);

        Button changesButton = new Button("Reset");
        changesButton.setOnAction(this::handleChanges);

        Button stepButton = new Button("Step");
        stepButton.setOnAction(this::handleSteps);

        Button resizeButton = new Button("Resize");
        resizeButton.setOnAction(this::handleResize);

        this.playButton = new Button(playStop);
        this.playButton.setOnAction(this::handlePlay);

        //Generate the Labels
        final Label sizeLabel = new Label("Size:");
        final Label speedLabel = new Label("Speed:");

        //Generate Sliders
        this.speedSlider = new Slider();
        this.speedSlider.setPrefWidth(100);
        this.speedSlider.setMin(1); this.speedSlider.setMax(10);

        this.sizeSlider = new Slider();
        this.sizeSlider.setPrefWidth(100); this.sizeSlider.setValue(20);
        this.sizeSlider.setMin(1); this.sizeSlider.setMax(50);

        //Get the Drop Down Menus
        fx.drawChoiceBox = new ComboBox<>();
        fx.drawChoiceBox.getItems().addAll(0, 1, 2);
        fx.drawChoiceBox.setPromptText("Draw Type");
        fx.drawChoiceBox.setValue(0);

        simulationChoiceBox = new ComboBox<>();
        simulationChoiceBox.getItems().addAll("Percolation","Fire","Prey/Predator","Segregation","Game of Life");
        simulationChoiceBox.setValue("Percolation");

        //Add everything in
        this.getItems().addAll( simulationChoiceBox, changesButton, new Separator(),
                fx.drawChoiceBox, new Separator(),
                speedLabel, speedSlider, playButton, stepButton, new Separator(),
                sizeLabel, sizeSlider, resizeButton, new Separator(),
                newWindow, loadButton, graphButton);
    }

    private void handleGraph(ActionEvent actionEvent) {
        //Get Graph
        NumberAxis xAxis = new NumberAxis(0, fx.getIterationNo(), 10);
        xAxis.setLabel("Time");

        NumberAxis yAxis = new NumberAxis(0, fx.getCols()*fx.getRows(), 50);
        yAxis.setLabel("No.of members");

        LineChart lineChart = new LineChart(xAxis,yAxis);


        lineChart.getData().addAll(fx.type0Data, fx.type1Data, fx.type2Data);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(lineChart);
        Scene scene = new Scene(stackPane, 950, 950);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void handleLoad(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir") + File.separator + "data"));
        File selectedFile = fc.showOpenDialog(new Stage());

        if((selectedFile) != null) {
            //listview.getItems().add(selectedFile.getName());

        } else{
            System.out.println("File is not valid");
        }
    }

    private void handleWindow(ActionEvent actionEvent) {
        simulationPanelFX mainPanel = new simulationPanelFX();
        Scene scene = new Scene(mainPanel, 950, 950);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setX(10);
        stage.setY(10);
        stage.show();
    }
    private void handlePlay(ActionEvent actionEvent) {
        if(playStop.equals("Play")){playStop = "Stop";}
        else if(playStop.equals("Stop")){playStop = "Play";}
      playButton.setText(playStop);
      this.fx.play = !this.fx.play;
    }

    private void handleResize(ActionEvent actionEvent) {
        if(!this.fx.play) {
            this.fx.resizeGrid((int) sizeSlider.getValue(), fx.currentSim);
            this.fx.draw();
            this.fx.resetGraphData();
        }
    }

    private void handleSteps(ActionEvent actionEvent) {
        if(!this.fx.play) {
            this.fx.getMainGrid().updateCells(this.fx.getMainGrid().checkForUpdates());
            this.fx.draw();
            this.fx.updateGraphData();
        }
    }

    private void handleChanges(ActionEvent actionEvent) {
        if(!this.fx.play) {
            this.fx.currentSim = simulationChoiceBox.getValue();
            this.fx.simChecker(fx.currentSim);
            this.fx.draw();
            this.fx.resetGraphData();
        }
    }

}
