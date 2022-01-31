package panda.views.elements;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import panda.controllers.views.OptionsService;

public class OptionsView extends BorderPane {

    private OptionsService optionsService;

    private Label labelGenPattern = new Label("PassGenPattern: ");
    private Label labelCipherWord = new Label("ActualCipherWord: ");
    private Label labelSelectedTheme = new Label("SelectedTheme: ");

    private ComboBox<String> inputThemes = new ComboBox<>();
    private TextField inputCipherWord = new TextField();
    private TextField inputGenPattern = new TextField();

    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");

    private VBox element = new VBox();

    private HBox mainLine = new HBox();
    private HBox firstLine = new HBox();
    private HBox secondLine = new HBox();
    private HBox thirdLine = new HBox();

    private int maxLabelWidthSize = 173;
    private int maxInputWidthSize = 273;

    private int minLabelWidthSize = 100;
    private int minInputWidthSize = 200;

    public OptionsView(OptionsService optionsService) {
        this.optionsService = optionsService;
        initSizes();
        initPositions();
        initCancelButton();
        initSaveButton();
    }

    private void initSizes() {
        saveButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(saveButton, Priority.ALWAYS);
        HBox.setHgrow(cancelButton, Priority.ALWAYS);

        labelCipherWord.setMaxWidth(maxLabelWidthSize);
        labelGenPattern.setMaxWidth(maxLabelWidthSize);
        labelSelectedTheme.setMaxWidth(maxLabelWidthSize);

        labelCipherWord.setMinWidth(minLabelWidthSize);
        labelGenPattern.setMinWidth(minLabelWidthSize);
        labelSelectedTheme.setMinWidth(minLabelWidthSize);

        HBox.setHgrow(labelCipherWord, Priority.ALWAYS);
        HBox.setHgrow(labelGenPattern, Priority.ALWAYS);
        HBox.setHgrow(labelSelectedTheme, Priority.ALWAYS);

        inputThemes.setMaxWidth(maxInputWidthSize);
        inputCipherWord.setMaxWidth(maxInputWidthSize);
        inputGenPattern.setMaxWidth(maxInputWidthSize);

        HBox.setHgrow(inputThemes, Priority.ALWAYS);
        HBox.setHgrow(inputCipherWord, Priority.ALWAYS);
        HBox.setHgrow(inputGenPattern, Priority.ALWAYS);

        labelCipherWord.setAlignment(Pos.CENTER);
        labelGenPattern.setAlignment(Pos.CENTER);
        labelSelectedTheme.setAlignment(Pos.CENTER);
    }

    private void initPositions() {
        mainLine.getChildren().addAll(saveButton, cancelButton);
        firstLine.getChildren().addAll(labelGenPattern, inputGenPattern);
        secondLine.getChildren().addAll(labelCipherWord, inputCipherWord);
        thirdLine.getChildren().addAll(labelSelectedTheme, inputThemes);

        element.getChildren().addAll(firstLine,secondLine,thirdLine);

        super.setTop(mainLine);
        super.setCenter(element);
    }

    private void initSaveButton(){
        saveButton.setOnAction(event -> {
            optionsService.saveButton();
        });
    }

    private void initCancelButton(){
        cancelButton.setOnAction(event -> {
            optionsService.cancelButton();
        });
    }

    /*
     *
     *       CipherWord
     *       PassGenPattern
     *       SelectedTheme
     *
     * */
}
