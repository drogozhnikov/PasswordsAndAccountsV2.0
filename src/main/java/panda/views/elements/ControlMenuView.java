package panda.views.elements;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import panda.controllers.views.ControlMenuService;

public class ControlMenuView extends HBox {

    private Button addButton = new Button("Add");
    private TextField searchField = new TextField();
    private OwnersListView ownersListView;

    private ControlMenuService controlMenuService;

    public ControlMenuView(ControlMenuService controlMenuService){
        this.controlMenuService = controlMenuService;
    }

    public void init(){
        initSizes();
        initAddButton();
        initSearchField();
        super.getChildren().addAll(addButton,ownersListView,searchField);
    }

    private void initSizes(){
        addButton.setMaxWidth(Double.MAX_VALUE);
        searchField.setMaxWidth(Double.MAX_VALUE);
        super.setHgrow(addButton, Priority.ALWAYS);
        super.setHgrow(ownersListView, Priority.ALWAYS);
        super.setHgrow(searchField, Priority.ALWAYS);
    }

    private void initAddButton() {
        addButton.setOnAction(event -> {
            //TODO addButton - > showDataManage
        });
    }

    private void initSearchField() {
        searchField.setOnKeyReleased(event -> {
            //TODO viewController.searchAction(event.getCode(), searchField.getText())
        });
    }

}
