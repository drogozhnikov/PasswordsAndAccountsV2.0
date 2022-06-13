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
    private Button restoreResolution = new Button("Restore standart resolution");
    private Separator separatorResolution = new Separator();

    private Label labelPasswordGeneration = new Label("Password Generation");
    private Separator separatorPassGen = new Separator();

    private Label labelStyles = new Label("Styles");
    private Separator separatorStyles = new Separator();

    private Label labelBackUp = new Label("BackUps");
    private Button saveXml = new Button("Xml");
    private Button saveJson = new Button("Json");
    private Button load = new Button("Load");
    private Separator separatorBackUps = new Separator();

    private Label labelDBCommand = new Label(" Data Base");
    private Button buttonClearBase = new Button(" Clear Base");
    private Separator separatorBaseCommand = new Separator();

    public GeneralPaneView(OptionsService optionsService) {
        this.optionsService = optionsService;

        initSizes();
        initStyles();
        initPositions();
        initSaveResolution();
        initRestoreResolution();
        initBackupButtons();
        initDataBase();
    }

    private void initSizes() {
        saveCurrentResolution.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(saveCurrentResolution, Priority.ALWAYS);

        restoreResolution.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(restoreResolution, Priority.ALWAYS);

        buttonClearBase.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(buttonClearBase, Priority.ALWAYS);

        saveXml.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(saveXml, Priority.ALWAYS);

        saveJson.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(saveJson, Priority.ALWAYS);

        load.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(load, Priority.ALWAYS);
    }

    private void initPositions() {
        VBox centerGeneralbox = new VBox();

        VBox resolutionBox = new VBox();
        resolutionBox.setAlignment(Pos.CENTER);
        HBox resolution = new HBox();
        resolution.setAlignment(Pos.CENTER);
        resolution.getChildren().addAll(saveCurrentResolution, restoreResolution);
        resolutionBox.getChildren().addAll(labelResolution, resolution, separatorResolution);
        centerGeneralbox.getChildren().add(resolutionBox);

        VBox passGen = new VBox();
        passGen.getChildren().addAll(labelPasswordGeneration, separatorPassGen);
        passGen.setAlignment(Pos.CENTER);
        centerGeneralbox.getChildren().add(passGen);

        VBox styles = new VBox();
        styles.getChildren().addAll(labelStyles, separatorStyles);
        styles.setAlignment(Pos.CENTER);
        centerGeneralbox.getChildren().add(styles);

        VBox backupBox = new VBox();
        backupBox.setAlignment(Pos.CENTER);
        HBox backup = new HBox();
        backup.setAlignment(Pos.CENTER);
        backup.getChildren().addAll(saveXml, saveJson, load);
        backupBox.getChildren().addAll(labelBackUp, backup, separatorBackUps);
        centerGeneralbox.getChildren().add(backupBox);

        VBox dataBase = new VBox();
        dataBase.getChildren().addAll(labelDBCommand, buttonClearBase, separatorBaseCommand);
        dataBase.setAlignment(Pos.CENTER);
        centerGeneralbox.getChildren().add(dataBase);

        super.setCenter(centerGeneralbox);
    }

    private void initStyles() {
        int headersSize = 15;
        String family = "Verdana";
        FontPosture fontPosture = FontPosture.ITALIC;

        labelResolution.setFont(Font.font(family, fontPosture, headersSize));
        labelPasswordGeneration.setFont(Font.font(family, fontPosture, headersSize));
        labelStyles.setFont(Font.font(family, fontPosture, headersSize));
        labelDBCommand.setFont(Font.font(family, fontPosture, headersSize));
        labelBackUp.setFont(Font.font(family, fontPosture, headersSize));
    }

    private void initSaveResolution() {
        saveCurrentResolution.setOnAction(event -> {
            optionsService.saveCurrentResolution();
        });
    }

    private void initRestoreResolution() {
        restoreResolution.setOnAction(event -> {
            optionsService.restoreResolution();
        });
    }

    private void initDataBase() {
        buttonClearBase.setOnAction(event -> {
            optionsService.clearBase();
        });
    }

    private void initBackupButtons() {
        saveXml.setOnAction(event -> {
            optionsService.saveXml();
        });
        saveJson.setOnAction(event -> {
            //TODO JSON load
        });
        load.setOnAction(event -> {
            optionsService.load();
        });

    }

}
