package panda.views.elements.components;

import javafx.scene.control.ChoiceBox;
import panda.controllers.views.components.OwnersListService;
import panda.models.Owner;

import java.util.ArrayList;
import java.util.Collections;

public class OwnersListView extends ChoiceBox<Owner> {

    private OwnersListService ownersListService;

    public OwnersListView(OwnersListService ownersListService) {
        this.ownersListService = ownersListService;
        initOwnersList();
    }

    public void refresh(ArrayList<Owner> owners) {
        Collections.sort(owners);
        super.getItems().clear();
        for (Owner s : owners) {
            super.getItems().add(s);
        }
    }

    public void initOwnersList() {
        super.setOnAction(event -> {
            if (super.getValue() != null) {
                ownersListService.setLastSelectedOwner(super.getValue());
            }
        });
    }
}
