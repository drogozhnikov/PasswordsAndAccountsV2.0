package panda.views.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import panda.controllers.views.HelloPandaService;


public class HelloPandaView extends BorderPane {

    private ImageView imageViewLeft;
    private ImageView imageViewRight;

    private TextField inputPassword = new TextField();
    private Label helloLabel = new Label("     Welcome to Panda! \nPlease enter your secret word:");

    private Button confirmButton = new Button("Confirm");
    private Button cancelButton = new Button("Cancel");

    private HBox buttonBox = new HBox();
    private VBox viewBox = new VBox();

    private HelloPandaService helloPandaService;

    public HelloPandaView(HelloPandaService helloPandaService, Image pandaImage){
        this.helloPandaService = helloPandaService;

        imageViewLeft = new ImageView(pandaImage);
        imageViewRight = new ImageView(pandaImage);
        imageViewRight.setScaleX(-imageViewRight.getScaleX());

        initConfirmButton();
        initCancelButton();
        initSizes();
        initPositions();
    }

    private void initPositions(){
        super.setLeft(imageViewLeft);
        super.setRight(imageViewRight);
        super.setCenter(viewBox);
    }

    private void initSizes(){
        confirmButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        buttonBox.setHgrow(confirmButton, Priority.ALWAYS);
        buttonBox.setHgrow(cancelButton, Priority.ALWAYS);
        buttonBox.getChildren().addAll(confirmButton,cancelButton);

        viewBox.setAlignment(Pos.CENTER);
        viewBox.getChildren().addAll(helloLabel,inputPassword,buttonBox);
    }

    private void initConfirmButton(){
        confirmButton.setOnAction(event -> {
            helloPandaService.confirm(inputPassword.getText());
        });
    }
    private void initCancelButton(){
        cancelButton.setOnAction(event -> {
            helloPandaService.cancel();
        });
    }

}
