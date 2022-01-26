package panda.views.elements;

import javafx.scene.control.ComboBox;
import panda.controllers.views.OwnersService;

public class OwnersListView extends ComboBox<String> {

    private OwnersService ownersService;
    private int ownerListSize = 100;

    public OwnersListView(OwnersService ownersService) {
        this.ownersService = ownersService;
//        super.getItems().add("All");
    }

    //TODO SplitMenuButton
}
