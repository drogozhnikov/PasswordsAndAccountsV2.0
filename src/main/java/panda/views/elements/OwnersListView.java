package panda.views.elements;

import javafx.scene.control.ChoiceBox;

public class OwnersListView extends ChoiceBox<String> {

    private int ownerListSize = 100;

    public OwnersListView(){

    }
    private void initSize(){
        super.setMaxWidth(ownerListSize);
    }
}
