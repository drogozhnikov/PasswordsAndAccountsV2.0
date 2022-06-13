package panda.views.elements.additional;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilePathWindow {

    private FileChooser fileChooser = new FileChooser();

    private static final Logger logger = LoggerFactory.getLogger(FilePathWindow.class);

    public String askSaveFilePath(String title, String initialFileName) {
        String filePath = "";
        try {
            fileChooser.setTitle(title);
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"));
            fileChooser.setInitialFileName(initialFileName);
            filePath = fileChooser.showSaveDialog(null).getAbsolutePath();
        } catch (NullPointerException e) {
            logger.warn("Wrong save path");
        }
        if (filePath.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("File path unknown");
            alert.setHeaderText("Please choose correct File.xml path");
            return filePath;
        } else {
            return filePath;
        }
    }

    public String askLoadFilePath(String title) {
        String filePath = "";
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"));
        try {
            filePath = fileChooser.showOpenDialog(null).getAbsolutePath();
        } catch (NullPointerException e) {
            logger.warn("Wrong load path");
        }
        if (filePath.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wrong Path");
            alert.setHeaderText("Please choose correct File.xml path");
            return filePath;
        } else {
            return filePath;
        }
    }
}
