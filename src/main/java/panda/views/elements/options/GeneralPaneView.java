package panda.views.elements.options;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import panda.controllers.views.options.OptionsService;

public class GeneralPaneView extends BorderPane {

    private OptionsService optionsService;

    private Label labelResolution = new Label("Resolution");
    private Button saveCurrentResolution = new Button("Save current resolution");
    private Separator separatorResolution = new Separator();

    private Label labelPasswordGeneration = new Label("Password Generation");
    private Separator separatorPassGen = new Separator();

    private Label labelStyles = new Label("Styles");
    private Separator separatorStyles = new Separator();

    public GeneralPaneView(OptionsService optionsService) {
        this.optionsService = optionsService;

        initSizes();
        initStyles();
        initPositions();
        initSaveResolution();
    }

    private void initSizes() {
        saveCurrentResolution.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(saveCurrentResolution, Priority.ALWAYS);
    }

    private void initPositions() {
        VBox centerGeneralbox = new VBox();

            VBox resolution = new VBox();
            resolution.getChildren().addAll(labelResolution,saveCurrentResolution,separatorResolution);
            resolution.setAlignment(Pos.CENTER);
        centerGeneralbox.getChildren().add(resolution);

            VBox passGen = new VBox();
            passGen.getChildren().addAll(labelPasswordGeneration, separatorPassGen);
            passGen.setAlignment(Pos.CENTER);
        centerGeneralbox.getChildren().add(passGen);

            VBox styles = new VBox();
            styles.getChildren().addAll(labelStyles, separatorStyles);
            styles.setAlignment(Pos.CENTER);
        centerGeneralbox.getChildren().add(styles);

        super.setCenter(centerGeneralbox);
    }

    private void initStyles(){
        int headersSize = 15;
        String family = "Verdana";
        FontPosture fontPosture = FontPosture.ITALIC;

        labelResolution.setFont(Font.font(family, fontPosture, headersSize));
        labelPasswordGeneration.setFont(Font.font(family, fontPosture, headersSize));
        labelStyles.setFont(Font.font(family, fontPosture, headersSize));

    }

    private void initSaveResolution() {
        saveCurrentResolution.setOnAction(event -> {
            optionsService.saveCurrentResolution();
        });
    }

}
