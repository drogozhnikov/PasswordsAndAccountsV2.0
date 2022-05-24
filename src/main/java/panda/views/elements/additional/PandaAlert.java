package panda.views.elements.additional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import panda.views.elements.components.LimitedPassField;

import java.util.Optional;

public class PandaAlert {

    private final String info = "Information";
    private final String warning = "Warning";
    private final String error = "Error";

    public PandaAlert() {

    }

    public void show(Alert.AlertType type, String headerText, String contextText) {
        Alert alert = new Alert(type);
        alert.setTitle(getTitle(type));
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    public boolean ask(String title, String headerText, String contextText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }

    public StringBuilder askWithPass(String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        LimitedPassField passField = new LimitedPassField(20);
        alert.getDialogPane().setContent(passField);

        Optional<ButtonType> option = alert.showAndWait();

        LimitedPassField outputPass = (LimitedPassField) alert.getDialogPane().getContent();
        StringBuilder passFieldData = new StringBuilder(outputPass.getText());
        if (option.get() == ButtonType.OK && !passFieldData.equals(new StringBuilder(""))) {
            return passFieldData;
        } else {
            return new StringBuilder("");
        }
    }

    private String getTitle(Alert.AlertType type) {
        switch (type) {
            case INFORMATION:
                return info;
            case WARNING:
                return warning;
            case ERROR:
                return error;
        }
        return info;
    }
}
