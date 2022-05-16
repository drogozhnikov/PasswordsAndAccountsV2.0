package panda.views.elements.components;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class LimitedTextField extends TextField {

    private final int limit;

    public LimitedTextField(int limit) {
        this.limit = limit;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        super.replaceText(start, end, text);
        verify();
    }

    @Override
    public void replaceSelection(String text) {
        super.replaceSelection(text);
        verify();
    }

    private void verify() {
        if (getText().length() > limit) {
            setText(getText().substring(0, limit));
            end();
            alert();
        }
    }

    private void alert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Field owersize");
        alert.setHeaderText("Text field limit reached");
        alert.showAndWait();
    }

}
