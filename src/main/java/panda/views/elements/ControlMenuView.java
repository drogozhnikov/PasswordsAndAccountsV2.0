package panda.views.elements;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import panda.controllers.views.ControlMenuService;
import panda.models.Owner;

public class ControlMenuView extends HBox {

    private Button addButton = new Button("Add");
    private TextField searchField = new TextField();

    private ControlMenuService controlMenuService;

    private ChoiceBox<Owner> ownerList = new ChoiceBox<>();

    private int minFieldSize = 300;

    public ControlMenuView(ControlMenuService controlMenuService) {
        this.controlMenuService = controlMenuService;
        initSizes();
        initAddButton();
        initSearchField();
        initOwnersList();
        super.getChildren().addAll(addButton, searchField, ownerList);
    }

    private void initSizes() {
        addButton.setMaxWidth(Double.MAX_VALUE);
        searchField.setMaxWidth(Double.MAX_VALUE);
        ownerList.setMaxWidth(Double.MAX_VALUE);

        addButton.setMinWidth(minFieldSize);
        searchField.setMinWidth(minFieldSize);
        ownerList.setMinWidth(minFieldSize);

        super.setHgrow(addButton, Priority.ALWAYS);
        super.setHgrow(ownerList, Priority.ALWAYS);
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
        ownerList.setItems(controlMenuService.getOwnersList());
        Owner lastSelectedOwner = controlMenuService.getLastSelectedOwner();
        ownerList.setValue(lastSelectedOwner);
        controlMenuService.ownerAction(lastSelectedOwner);

            ownerList.setOnAction(event -> {
                controlMenuService.ownerAction(getOwner());
            });
    }

    public void refresh(ObservableList<Owner> input, Owner lastSelectedOwner){
        ownerList.setItems(input);
        ownerList.setValue(lastSelectedOwner);
    }

    public Owner getOwner(){
       return ownerList.getValue();
    }

}
