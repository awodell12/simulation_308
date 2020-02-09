package cellsociety;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class toolBar extends ToolBar {

    private simulationPanelFX fx;
    ComboBox<String> simulationChoiceBox;
    Slider sizeSlider;
    Slider speedSlider;
    Button playButton;
    String playStop = "Play";
    private ListView listview;

    public toolBar(simulationPanelFX fx){
        //Get the Main Panel in
        this.fx = fx;

        //Generate the Buttons
        Button loadButton = new Button("Load XML");
        loadButton.setOnAction(this::handleLoad);
        
        Button stageButton = new Button("New");
        stageButton.setOnAction(this::handleStage);

        Button changesButton = new Button("Reset");
        changesButton.setOnAction(this::handleChanges);

        Button stepButton = new Button("Step");
        stepButton.setOnAction(this::handleSteps);

        Button resizeButton = new Button("Resize");
        resizeButton.setOnAction(this::handleResize);

        playButton = new Button(playStop);
        playButton.setOnAction(this::handlePlay);

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
                stageButton, loadButton);
    }

    private void handleLoad(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir") + File.separator + "data"));
        File selectedFile = fc.showOpenDialog(new Stage());

        if((selectedFile) != null) {
            listview.getItems().add(selectedFile.getName());

        } else{
            System.out.println("File is not valid");
        }
    }

    private void handleStage(ActionEvent actionEvent) {
        simulationPanelFX mainPanel = new simulationPanelFX();
        Scene scene = new Scene(mainPanel, 830, 900);
        Stage stage = new Stage();
        stage.setTitle("New Window");
        stage.setScene(scene);
        int randomInt = (int)(10.0 * Math.random());
        stage.setX(randomInt);
        stage.setY(randomInt);
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
        }
    }

}
