package panda.views.elements.options;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import panda.controllers.views.options.OptionsService;
import panda.views.elements.components.LimitedPassField;

public class AсcessPaneView extends BorderPane {

    private OptionsService optionsService;

    private Label lableHeader = new Label("Access settings");
    private Label lableInfo = new Label("");

    private Label labelOldPassword = new Label("--Your old password: ");
    private Label labelTempPassword = new Label("--Repeat old password: ");
    private Label labelNewPassword = new Label("--Your new password: ");

    private int passLimit = 20;

    private LimitedPassField inputOldPassword = new LimitedPassField(passLimit);
    private LimitedPassField inputTempPassword = new LimitedPassField(passLimit);
    private LimitedPassField inputNewPassword = new LimitedPassField(passLimit);

    private int spacing = 5;

    private HBox boxOldPass = new HBox();
    private HBox boxTempPass = new HBox();
    private HBox boxNewPass = new HBox();

    private VBox fullBox = new VBox(spacing);

    private Button saveButton = new Button("Save");

    public AсcessPaneView(OptionsService optionsService) {
        this.optionsService = optionsService;

        initSizes();
        initPositions();
        initStyles();
        initSaveButton();
        initEnterAction(inputNewPassword);
        initEnterAction(inputTempPassword);
        initEnterAction(inputOldPassword);

    }

    private void initSizes() {
        setPassSize(inputNewPassword);
        setPassSize(inputTempPassword);
        setPassSize(inputOldPassword);

        setLabelMaxSize(labelNewPassword);
        setLabelMaxSize(labelOldPassword);
        setLabelMaxSize(labelTempPassword);

        setLabelMaxSize(lableInfo);

        saveButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(saveButton, Priority.ALWAYS);
    }

    private void initPositions() {
        boxOldPass.getChildren().addAll(labelOldPassword, inputOldPassword);
        boxTempPass.getChildren().addAll(labelTempPassword, inputTempPassword);
        boxNewPass.getChildren().addAll(labelNewPassword, inputNewPassword);

        fullBox.getChildren().addAll(boxOldPass, boxTempPass, boxNewPass, saveButton);

        setMargin(fullBox, new Insets(spacing));

        VBox accessBox = new VBox();
        accessBox.getChildren().addAll(lableHeader,fullBox);
        accessBox.setAlignment(Pos.TOP_CENTER);

        super.setCenter(accessBox);

        VBox infoBox = new VBox();
        infoBox.getChildren().addAll(lableInfo);
        infoBox.setAlignment(Pos.TOP_CENTER);

        super.setBottom(lableInfo);
    }

    private void initSaveButton() {
        saveButton.setOnAction(event -> {
            optionsService.saveAccessAction(
                    new StringBuilder(inputOldPassword.getText()),
                    new StringBuilder(inputTempPassword.getText()),
                    new StringBuilder(inputNewPassword.getText())
            );
            clearFields();
        });
    }

    private void initEnterAction(LimitedPassField input){
        input.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                optionsService.saveAccessAction(
                        new StringBuilder(inputOldPassword.getText()),
                        new StringBuilder(inputTempPassword.getText()),
                        new StringBuilder(inputNewPassword.getText())
                );
                clearFields();
            }
        });
    }

    private void initStyles() {
        int headersSize = 15;
        String family = "Verdana";
        FontPosture fontPosture = FontPosture.ITALIC;

        lableHeader.setAlignment(Pos.CENTER);
        lableInfo.setAlignment(Pos.CENTER);

        labelOldPassword.setAlignment(Pos.CENTER);
        labelTempPassword.setAlignment(Pos.CENTER);
        labelNewPassword.setAlignment(Pos.CENTER);

        lableHeader.setFont(Font.font(family, fontPosture, headersSize));
        lableInfo.setFont(Font.font(family, fontPosture, headersSize));

    }

    private void setPassSize(PasswordField input) {
        input.setMaxWidth(300);
        input.setMinWidth(50);
        HBox.setHgrow(input, Priority.ALWAYS);
    }

    private void setLabelMaxSize(Label input) {
        input.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(input, Priority.ALWAYS);
    }

    private void clearFields(){
        inputNewPassword.setText("");
        inputTempPassword.setText("");
        inputOldPassword.setText("");
    }

    public void setInfoText(String input, Color color){
        lableHeader.setTextFill(color);
        lableHeader.setText(input);
    }

}
