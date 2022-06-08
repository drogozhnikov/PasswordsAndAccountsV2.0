package panda.views.elements;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import panda.controllers.views.ControlMenuService;

public class ControlMenuView extends HBox {

    private Button addButton = new Button("Add");
    private TextField searchField = new TextField();

    private ControlMenuService controlMenuService;

    private int minFieldSize = 300;

    public ControlMenuView(ControlMenuService controlMenuService) {
        this.controlMenuService = controlMenuService;
        initSizes();
        initAddButton();
        initSearchField();
        initOwnersList();
        super.getChildren().addAll(addButton, searchField);//TODO ownersList
    }

    private void initSizes() {
        addButton.setMaxWidth(Double.MAX_VALUE);
        searchField.setMaxWidth(Double.MAX_VALUE);
        //TODO ownersList

        addButton.setMinWidth(minFieldSize);
        searchField.setMinWidth(minFieldSize);
        //TODO ownersList

        super.setHgrow(addButton, Priority.ALWAYS);
        //TODO ownersList
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

//    public Owner getOwner(){
//        //TODO ownersList
//    }

}
