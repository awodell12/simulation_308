package cellsociety;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import xml.Configuration;
import xml.Reader;
import xml.XMLException;
import xml.XMLParser;

import java.io.File;


public class toolBar extends ToolBar {

    private simulationPanelFX fx;
    private ComboBox<String> simulationChoiceBox;
    private Slider sizeSlider;
    public Slider speedSlider;
    private Button playButton, graphButton, loadButton, newWindow, changesButton, stepButton, resizeButton;
    private String playStop = "Play";

    private Configuration config;
    private ListView listview;

    public toolBar(simulationPanelFX fx){
        //Get the Main Panel in
        this.fx = fx;

        //Generate the Labels
        final Label sizeLabel = new Label("Size:");
        final Label speedLabel = new Label("Speed:");

        createButtons();
        createSliders();
        createDropDowns(fx);

        //Add everything in
        this.getItems().addAll( simulationChoiceBox, changesButton, new Separator(),
                fx.drawChoiceBox, new Separator(),
                speedLabel, speedSlider, playButton, stepButton, new Separator(),
                sizeLabel, sizeSlider, resizeButton, new Separator(),
                newWindow, loadButton, graphButton);
    }

    /*The next three methods are used to generate required tools*/

    private void createDropDowns(simulationPanelFX fx) {
        //Get the Drop Down Menus
        fx.drawChoiceBox = new ComboBox<>();
        fx.drawChoiceBox.getItems().addAll(0, 1, 2);
        fx.drawChoiceBox.setPromptText("Draw Type");
        fx.drawChoiceBox.setValue(0);

        simulationChoiceBox = new ComboBox<>();
        simulationChoiceBox.getItems().addAll("Percolation","Fire","Prey/Predator","Segregation","Game of Life");
        simulationChoiceBox.setValue("Percolation");
    }

    private void createSliders() {
        //Generate Sliders
        this.speedSlider = new Slider();
        this.speedSlider.setPrefWidth(100);
        this.speedSlider.setMin(1);
        this.speedSlider.setMax(10);

        this.sizeSlider = new Slider();
        this.sizeSlider.setPrefWidth(100);
        this.sizeSlider.setValue(20);
        this.sizeSlider.setMin(1);
        this.sizeSlider.setMax(50);
    }

    private void createButtons() {
        //Generate the Buttons
        this.graphButton = new Button("Graph");
        this.graphButton.setOnAction(this::handleGraph);

        this.loadButton = new Button("Load XML");
        this.loadButton.setOnAction(this::handleLoad);

        this.newWindow = new Button("New");
        this.newWindow.setOnAction(this::handleWindow);

        this.changesButton = new Button("Reset");
        this.changesButton.setOnAction(this::handleChanges);

        this.stepButton = new Button("Step");
        this.stepButton.setOnAction(this::handleSteps);

        this.resizeButton = new Button("Resize");
        this.resizeButton.setOnAction(this::handleResize);

        this.playButton = new Button(playStop);
        this.playButton.setOnAction(this::handlePlay);
    }

    /*From here and on, every method is created to handle button inputs*/

    private void handleGraph(ActionEvent actionEvent) {
        if (fx.iterationNo != 0) {
            NumberAxis xAxis = new NumberAxis(0, fx.iterationNo, 10);
            xAxis.setLabel("Time");

            NumberAxis yAxis = new NumberAxis(0, fx.getCols() * fx.getRows(), 50);
            yAxis.setLabel("No.of members");

            LineChart lineChart = new LineChart(xAxis, yAxis);

            fx.type0Data.setName("Type 0");
            fx.type1Data.setName("Type 1");
            fx.type2Data.setName("Type 2");

            lineChart.getData().addAll(fx.type0Data, fx.type1Data, fx.type2Data);
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(lineChart);
            Scene scene = new Scene(stackPane, 950, 950);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }

    private void handleLoad(ActionEvent actionEvent) {
        File dataFile = Reader.FILE_CHOOSER.showOpenDialog(new Stage());
        while (dataFile != null) {
            try {
                XMLParser myParser = new XMLParser();
                this.fx.config = myParser.getConfiguration(dataFile);
                this.fx.dataFile = dataFile;
                this.fx.currentSim = fx.config.getType();
                this.fx.simChecker(fx.currentSim);
                this.fx.draw();
            }
            catch (XMLException e) {
                // handle error of unexpected file format
                Reader.showMessage(Alert.AlertType.ERROR, e.getMessage());
            }
            break;
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
