package panda.views.elements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import panda.controllers.views.DataManageService;
import panda.models.Account;

public class DataManageView extends BorderPane {

    private int labelsSize = 80;
    private int borderPrefSize = 94;
    private int descriptionFieldSize = 50;

    private Label nameLabel = new Label("Name: ");
    private Label ownerLabel = new Label("Owner: ");
    private Label linkLabel = new Label("Link: ");
    private Label mailLabel = new Label("Mail: ");
    private Label accountLabel = new Label("Account: ");
    private Label passwordLabel = new Label("Password: ");

    private TextField inputName = new TextField();
    private TextField inputOwner = new TextField();
    private TextField inputLink = new TextField();
    private TextField inputMail = new TextField();
    private TextField inputAccount = new TextField();
    private TextField inputPassword = new TextField();

    private TextArea inputDescription = new TextArea("Description");

    private Button actionButton = new Button("action");
    private Button cancelButton = new Button("Cancel");
    private Button clearButton = new Button("Clear");

    private Button generatePasswordButton = new Button("New");

    private OwnersListView ownersListView; //TODO owners list

    private CheckBox stayOnManage = new CheckBox("Stay On Manage");
    private CheckBox clearWhenAction = new CheckBox("Clear When Action");

    private int nameSize = 90;
    private int ownerSize = 50;
    private int linkSize = 500;
    private int mailSize = 100;
    private int accountSize = 100;
    private int passwordSize = 50;
    private int descriptionSize = 500;

    private int spacing = 1;
    private int fieldSize = 300;

    private DataManageService dataManageService;

    public DataManageView(DataManageService dataManageService) {
        this.dataManageService = dataManageService;
    }

    public void init(){
        initActionButton();
        initCancelButton();
        initGenerateButton();
        initSizes();
        initPositions();
        initCancelButton();
        initClearButoon();
        //TODO fields validations
    }

    private void initPositions() {
        HBox name = new HBox(spacing, nameLabel, inputName);
        HBox owner = new HBox(spacing, ownerLabel, inputOwner, ownersListView);
        HBox link = new HBox(spacing, linkLabel, inputLink);
        HBox mail = new HBox(spacing, mailLabel, inputMail);
        HBox acc = new HBox(spacing, accountLabel, inputAccount);
        HBox pass = new HBox(spacing, passwordLabel, inputPassword, generatePasswordButton);

        VBox left = new VBox(spacing, name, acc, stayOnManage);
        VBox center = new VBox(spacing, mail, link, clearWhenAction);
        VBox right = new VBox(spacing, pass, owner);
        HBox top = new HBox(spacing, actionButton, clearButton, cancelButton);

        setMargin(center, new Insets(2));
        setMargin(right, new Insets(2));
        setMargin(left, new Insets(2));

        super.setLeft(left);
        super.setCenter(center);
        super.setRight(right);
        super.setTop(top);
        super.setBottom(inputDescription);
    }

    private void initSizes() {
        super.setPrefHeight(borderPrefSize);

        nameLabel.setMinWidth(labelsSize);
        ownerLabel.setMinWidth(labelsSize);
        linkLabel.setMinWidth(labelsSize);
        mailLabel.setMinWidth(labelsSize);
        accountLabel.setMinWidth(labelsSize);
        accountLabel.setMinWidth(labelsSize);
        passwordLabel.setMinWidth(labelsSize);

        nameLabel.setAlignment(Pos.CENTER);
        ownerLabel.setAlignment(Pos.CENTER);
        linkLabel.setAlignment(Pos.CENTER);
        mailLabel.setAlignment(Pos.CENTER);
        accountLabel.setAlignment(Pos.CENTER);
        passwordLabel.setAlignment(Pos.CENTER);

        inputName.setMaxWidth(Double.MAX_VALUE);
        inputOwner.setMaxWidth(Double.MAX_VALUE);
        inputLink.setMaxWidth(Double.MAX_VALUE);
        inputMail.setMaxWidth(Double.MAX_VALUE);
        inputAccount.setMaxWidth(Double.MAX_VALUE);
        inputPassword.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(inputName, Priority.ALWAYS);
        HBox.setHgrow(inputOwner, Priority.ALWAYS);
        HBox.setHgrow(inputLink, Priority.ALWAYS);
        HBox.setHgrow(inputMail, Priority.ALWAYS);
        HBox.setHgrow(inputAccount, Priority.ALWAYS);
        HBox.setHgrow(inputPassword, Priority.ALWAYS);

        generatePasswordButton.setPrefWidth(labelsSize);
        ownersListView.setPrefWidth(labelsSize);

        HBox.setHgrow(generatePasswordButton, Priority.ALWAYS);
        HBox.setHgrow(ownersListView, Priority.ALWAYS);

        actionButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(actionButton, Priority.ALWAYS);
        HBox.setHgrow(cancelButton, Priority.ALWAYS);

        stayOnManage.setMaxWidth(Double.MAX_VALUE);

        inputDescription.setPrefHeight(descriptionFieldSize);
    }

    private void initGenerateButton() {
        //TODO init Generate new password button
    }

    private void initActionButton() {
        actionButton.setText("Add");
        actionButton.setOnAction(event -> {
            // TODO init action: add button
        });
    }

    private void initActionButton(Account account) {
        actionButton.setText("Save");
        actionButton.setOnAction(event -> {
            // TODO init action: update button
        });
    }

    private void initCancelButton() {
        cancelButton.setOnAction(event -> {
            //TODO show root menu
            clear();
        });
    }

    private void initClearButoon() {
        clearButton.setOnAction(event -> clear());
    }

    private void clear() {
        inputName.clear();
        inputName.clear();
        inputOwner.clear();
        inputLink.clear();
        inputMail.clear();
        inputAccount.clear();
        inputPassword.clear();
        inputDescription.clear();
    }

    public void hide() {
        if (!stayOnManage.isSelected()) {
            //TODO show root menu
        }
    }

    public void show() {
        //TODO Refresh owners
        initActionButton();
    }

    public void show(Account account) {
        //TODO Refresh owners and selectedAccount = account
        initActionButton(account);
    }

}
