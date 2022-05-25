package panda.views.elements.components;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class InfoView extends HBox {

    private Text text = new Text();

    public InfoView() {
        initPosition();
    }

    private void initPosition() {
        super.setAlignment(Pos.CENTER);
        super.getChildren().addAll(text);
    }

    private void initSize() {

    }

    public void setInfoText(String input) {
        text.setText(input);
    }


}
