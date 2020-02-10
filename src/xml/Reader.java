package xml;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Robert Chen
 * Purpose: Create a FileChooser that allows the user to select an XML file to read.
 * Assumptions: None
 * Dependencies: None
 * Example: create a new Reader object and call makeChooser
 */
public class Reader {
    public static final String DATA_FILE_EXTENSION = "*.xml";
    public static final FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);

    public Reader () {

    }

    /**
     * Call this method if the XML file has an unexpected format
     * @param type
     * @param message
     */
    public static void showMessage (AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }

    private static FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        // start searching for files in data folder
        result.setInitialDirectory(new File(System.getProperty("user.dir") + File.separator + "data"));
        result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
        return result;
    }
}
