package panda.views.elements.options;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import panda.controllers.views.options.OptionsService;

public class ResolutionPaneView extends BorderPane {

    private OptionsService optionsService;

    private Label labelScreenResolution = new Label();
    private Button saveCurrentResolution = new Button("Save current resolution");

    public ResolutionPaneView(OptionsService optionsService) {
        this.optionsService = optionsService;

        initSizes();
        initPositions();
        initSaveResolution();
    }

    private void initSizes() {
        labelScreenResolution.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(labelScreenResolution, Priority.ALWAYS);

        saveCurrentResolution.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(saveCurrentResolution, Priority.ALWAYS);
    }

    private void initPositions() {
        super.setTop(saveCurrentResolution);
    }

    private void initSaveResolution() {
        saveCurrentResolution.setOnAction(event -> {
            optionsService.saveCurrentResolution();
        });
    }

}
