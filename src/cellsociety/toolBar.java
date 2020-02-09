package cellsociety;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class toolBar extends ToolBar {

    private simulationPanelFX fx;
    ComboBox<String> simulationChoiceBox;
    Slider sizeSlider;
    Slider speedSlider;
    Button playButton;
    String playStop = "play";



    public toolBar(simulationPanelFX fx){
        //Get the Main Panel in
        this.fx = fx;

        //Generate the Buttons
        Button changesButton = new Button("Reset");
        changesButton.setOnAction(this::handleChanges);
        Button stepButton = new Button("Step");
        stepButton.setOnAction(this::handleSteps);
        Button resizeButton = new Button("Resize");
        resizeButton.setOnAction(this::handleResize);
        playButton = new Button("Play");
        playButton.setOnAction(this::handlePlay);

        //Generate the Labels
        final Label sizeLabel = new Label("Size:");
        final Label speedLabel = new Label("Speed:");


        this.speedSlider = new Slider();
        this.speedSlider.setPrefWidth(100);
        this.speedSlider.setMin(1);
        this.speedSlider.setMax(10);


        this.sizeSlider = new Slider();
        this.sizeSlider.setPrefWidth(100);
        this.sizeSlider.setValue(20);
        this.sizeSlider.setMin(1);
        this.sizeSlider.setMax(50);

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
                sizeLabel, sizeSlider, resizeButton);
    }

    private void handlePlay(ActionEvent actionEvent) {
        if(playStop.equals("play")){playStop = "stop";}
        else if(playStop.equals("stop")){playStop = "play";}
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
