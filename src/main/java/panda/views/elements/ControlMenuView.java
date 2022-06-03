package panda.views.elements;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import panda.controllers.views.ControlMenuService;
import panda.controllers.views.components.OwnersListService;
import panda.models.Owner;
import panda.views.elements.components.OwnersListView;

public class ControlMenuView extends HBox {

    private Button addButton = new Button("Add");
    private TextField searchField = new TextField();

    private OwnersListView ownersListView;

    private ControlMenuService controlMenuService;

    private int minFieldSize = 300;

    public ControlMenuView(ControlMenuService controlMenuService, OwnersListView ownersListView) {
        this.controlMenuService = controlMenuService;
        this.ownersListView = ownersListView;
        initSizes();
        initAddButton();
        initSearchField();
        initOwnersList();
        super.getChildren().addAll(addButton, searchField, ownersListView);
    }

    private void initSizes() {
        addButton.setMaxWidth(Double.MAX_VALUE);
        searchField.setMaxWidth(Double.MAX_VALUE);
        ownersListView.setMaxWidth(Double.MAX_VALUE);

        addButton.setMinWidth(minFieldSize);
        searchField.setMinWidth(minFieldSize);
        ownersListView.setMinWidth(minFieldSize);

        super.setHgrow(addButton, Priority.ALWAYS);
        super.setHgrow(ownersListView, Priority.ALWAYS);
        super.setHgrow(searchField, Priority.ALWAYS);
    }

    private void initAddButton() {
        addButton.setOnAction(event -> {
            controlMenuService.actionAdd();
        });
    }

    private void initSearchField() {
        searchField.setOnKeyReleased(event -> {
            controlMenuService.search(event.getCode(), searchField.getText());
        });
    }

    private void initOwnersList(){

    }

    public Owner getOwner(){
        return ownersListView.getValue();
    }

}
