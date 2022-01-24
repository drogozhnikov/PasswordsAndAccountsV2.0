package panda.views.elements;

import javafx.scene.control.ChoiceBox;
import panda.controllers.views.OwnersService;

public class OwnersListView extends ChoiceBox<String> {

    private OwnersService ownersService;
    private int ownerListSize = 100;

    public OwnersListView(OwnersService ownersService){
        this.ownersService = ownersService;
    }

    private void initSize(){
        super.setMaxWidth(ownerListSize);
    }

    //TODO SplitMenuButton
}
