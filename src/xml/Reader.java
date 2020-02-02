package xml;

/*
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
 */

import java.io.File;
import org.w3c.dom.Element;

public class Reader {

    public static final String GAME_OF_LIFE = "data/GameOfLife.xml";
    private static File dataFile = new File(GAME_OF_LIFE);

    public static void main(String[] args) {
        XMLParser myParser = new XMLParser();
        Element e = myParser.getRootElement(dataFile);
        String output = myParser.getTextValue(e,"config");
        System.out.print(output);
    }


    // public static final String DATA_FILE_EXTENSION = "*.xml";
    // public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);

    /*
    @Override
    public void start (Stage primaryStage) throws Exception {
        File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
        while (dataFile != null) {
            try {
                XMLParser myParser = new XMLParser();
                Element e = myParser.getRootElement(dataFile);
                String output = myParser.getTextValue(e,"config");
                System.out.print(output);

            }
            catch (XMLException e) {
                // handle error of unexpected file format
                showMessage(AlertType.ERROR, e.getMessage());
            }
            dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
        }
        // nothing selected, so quit the application
        Platform.exit();
    }

    private void showMessage (AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }

    // Choose file manually, like in Spike Lab
    private static FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        // start searching for files in data folder
        result.setInitialDirectory(new File(System.getProperty("user.dir") + File.separator + "data"));
        result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
        return result;
    }

    public static void main(String[] args) {
        launch(args);
    }
     */
}
