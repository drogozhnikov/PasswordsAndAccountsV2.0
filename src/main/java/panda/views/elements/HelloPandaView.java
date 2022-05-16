package panda.views.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import panda.controllers.views.HelloPandaService;


public class HelloPandaView extends BorderPane {

    private ImageView imageViewLeft;
    private ImageView imageViewRight;

    private PasswordField inputPassword = new PasswordField();
    private TextField inputText = new TextField();
    private Label helloLabel = new Label("     Welcome to Panda! \nPlease enter your secret word:");

    private Button confirmButton = new Button("Confirm");
    private Button cancelButton = new Button("Cancel");

    private HBox buttonBox = new HBox();
    private VBox viewBox = new VBox();

    private HelloPandaService helloPandaService;

    public HelloPandaView(HelloPandaService helloPandaService, Image pandaImage) {
        this.helloPandaService = helloPandaService;

        imageViewLeft = new ImageView(pandaImage);
        imageViewRight = new ImageView(pandaImage);
        imageViewRight.setScaleX(-imageViewRight.getScaleX());

        inputPassword.setText("root"); //TODO Delete when finish prog

        initConfirmButton();
        initCancelButton();
        initSizes();
        initPositions();
        setStyleInputPassword();
        setStylePandaImage();
    }

    private void initPositions() {
        super.setLeft(imageViewLeft);
        super.setRight(imageViewRight);
        super.setCenter(viewBox);
    }

    private void initSizes() {
        confirmButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        buttonBox.setHgrow(confirmButton, Priority.ALWAYS);
        buttonBox.setHgrow(cancelButton, Priority.ALWAYS);
        buttonBox.getChildren().addAll(confirmButton, cancelButton);

        viewBox.setAlignment(Pos.CENTER);
        viewBox.getChildren().addAll(helloLabel, inputPassword, buttonBox);
    }

    private void initConfirmButton() {
        confirmButton.setOnAction(event -> {
            helloPandaService.confirm(inputPassword.getText());
        });
    }

    private void initCancelButton() {
        cancelButton.setOnAction(event -> {
            helloPandaService.cancel();
        });
    }

    private void setStyleInputPassword() {

        inputPassword.setAlignment(Pos.CENTER);
        inputText.setAlignment(Pos.CENTER);

        inputPassword.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                helloPandaService.confirm(inputPassword.getText());
            }
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                helloPandaService.cancel();
            }
        });
    }

    private void setStylePandaImage() {
        inputPassword.setVisible(true);
        inputText.setVisible(false);

        imageViewLeft.setOnMousePressed(event -> {
            showPass();
            imageViewRight.setScaleX(-imageViewRight.getScaleX());
            imageViewLeft.setScaleX(-imageViewLeft.getScaleX());
        });
        imageViewLeft.setOnMouseReleased(event -> {
            hidePass();
            imageViewRight.setScaleX(-imageViewRight.getScaleX());
            imageViewLeft.setScaleX(-imageViewLeft.getScaleX());
        });
        imageViewRight.setOnMousePressed(event -> {
            showPass();
            imageViewRight.setScaleX(-imageViewRight.getScaleX());
            imageViewLeft.setScaleX(-imageViewLeft.getScaleX());
        });
        imageViewRight.setOnMouseReleased(event -> {
            hidePass();
            imageViewRight.setScaleX(-imageViewRight.getScaleX());
            imageViewLeft.setScaleX(-imageViewLeft.getScaleX());
        });
    }

    private void showPass() {
        String tempInputData = inputPassword.getText();
        inputText.setVisible(true);
        inputPassword.setVisible(false);
        inputText.setText(tempInputData);
        inputText.end();
        viewBox.getChildren().set(1,inputText);
    }

    private void hidePass() {
        String tempInputData = inputText.getText();
        inputText.setVisible(false);
        inputPassword.setVisible(true);
        inputPassword.setText(tempInputData);
        inputPassword.end();
        viewBox.getChildren().set(1,inputPassword);
    }

}
