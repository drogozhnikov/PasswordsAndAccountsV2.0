package panda.views.elements.components;

import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class OwnersListView extends ComboBox<String> {

    public OwnersListView() {

    }

    public void refresh(ArrayList<String> owners){
        super.getItems().clear();
        for (String s : owners) {
            super.getItems().add(s);
            super.getSelectionModel().select(0);
        }
    }

    //TODO SplitMenuButton
}
