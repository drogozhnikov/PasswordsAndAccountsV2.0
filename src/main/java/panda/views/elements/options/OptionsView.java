package panda.views.elements.options;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import panda.controllers.views.options.OptionsService;

import java.util.ArrayList;

public class OptionsView extends BorderPane {

    private OptionsService optionsService;

    private ListView<Button> buttonListView = new ListView<>();

    private Button buttonGeneral = new Button("General");
    private Button buttonAccess = new Button("Access");

    private Button separator = new Button();

    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");

    private GeneralPaneView generalPaneView;
    private AсcessPaneView aсcessPaneView;

    public OptionsView(OptionsService optionsService) {
        this.optionsService = optionsService;
        generalPaneView = new GeneralPaneView(optionsService);
        aсcessPaneView = new AсcessPaneView(optionsService);

        initSizes();
        initPositions();
        initCancelButton();
        initSaveButton();
        initOptionsList();
        initSeparator();

        initGeneralButton();
        initAccessButton();
    }

    private void initSizes() {
        saveButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(saveButton, Priority.ALWAYS);
        HBox.setHgrow(cancelButton, Priority.ALWAYS);

        setMaxSize(saveButton);
        setMaxSize(cancelButton);
        setMaxSize(buttonGeneral);
        setMaxSize(buttonAccess);
        setMaxSize(separator);

        buttonListView.setMaxWidth(300);
    }

    private void initPositions() {
        super.setLeft(buttonListView);
    }

    private void initSaveButton() {
        saveButton.setOnAction(event -> {
            optionsService.saveButton();
        });
    }

    private void initCancelButton() {
        cancelButton.setOnAction(event -> {
            optionsService.cancelButton();
        });
    }

    private void initOptionsList() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(buttonGeneral);
        buttons.add(buttonAccess);

        buttons.add(separator);

        buttons.add(saveButton);
        buttons.add(cancelButton);

        ObservableList<Button> list = FXCollections.observableArrayList(buttons);
        buttonListView.setItems(list);
        buttonListView.setEditable(false);
    }

    private void initSeparator() {
        separator.setMouseTransparent(true);
        separator.setStyle("-fx-background-color: rgba(255,255,255,0);");
    }

    private void setMaxSize(Button input) {
        input.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(input, Priority.ALWAYS);
    }

    public void setOptionsCenterPane(BorderPane input) {
        super.setCenter(input);
    }

    private void initGeneralButton(){
        buttonGeneral.setOnAction(event -> {
            setOptionsCenterPane(generalPaneView);
        });
    }
    private void initAccessButton(){
        buttonAccess.setOnAction(event -> {
            setOptionsCenterPane(aсcessPaneView);
        });
    }


    public void setMenuPane(MenuBar menuView) {
        super.setTop(menuView);
    }

    /*
     *
     *       CipherWord
     *       PassGenPattern
     *       SelectedTheme
     *
     *
     * */
}
