package xml;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Reader {
    public static final String DATA_FILE_EXTENSION = "*.xml";
    public static final FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);

    public Reader () {

    }

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
