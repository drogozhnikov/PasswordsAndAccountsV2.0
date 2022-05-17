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
import panda.views.elements.components.LimitedTextField;
import panda.views.elements.components.OwnersListView;

import java.util.HashMap;

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

    private LimitedTextField inputName = new LimitedTextField(90);
    private LimitedTextField inputOwner = new LimitedTextField(50);
    private LimitedTextField inputLink = new LimitedTextField(500);
    private LimitedTextField inputMail = new LimitedTextField(100);
    private LimitedTextField inputAccount = new LimitedTextField(100);
    private LimitedTextField inputPassword = new LimitedTextField(500);

    private TextArea inputDescription = new TextArea("Info");

    private Button actionButton = new Button("action");
    private Button cancelButton = new Button("Cancel");
    private Button clearButton = new Button("Clear");

    private Button generatePasswordButton = new Button("Gen");

    private OwnersListView ownersListView;

    private CheckBox stayOnAction = new CheckBox("Stay On Action");
    private CheckBox clearWhenAction = new CheckBox("Clear When Action");

    private int spacing = 1;
    private int fieldSize = 300;

    private DataManageService dataManageService;

    public DataManageView(DataManageService dataManageService, OwnersListView ownersListView) {
        this.dataManageService = dataManageService;
        this.ownersListView = ownersListView;

        initActionButton();
        initCancelButton();
        initGenerateButton();
        initSizes();
        initPositions();
        initCancelButton();
        initClearButton();
        initOwnersList();

    }

    private void initPositions() {
        HBox name = new HBox(spacing, nameLabel, inputName);
        HBox owner = new HBox(spacing, ownerLabel, inputOwner, ownersListView);
        HBox link = new HBox(spacing, linkLabel, inputLink);
        HBox mail = new HBox(spacing, mailLabel, inputMail);
        HBox acc = new HBox(spacing, accountLabel, inputAccount);
        HBox pass = new HBox(spacing, passwordLabel, inputPassword, generatePasswordButton);

        VBox left = new VBox(spacing, name, acc, stayOnAction);
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

        stayOnAction.setMaxWidth(Double.MAX_VALUE);

        inputDescription.setPrefHeight(descriptionFieldSize);
    }

    private void initGenerateButton() {
        generatePasswordButton.setOnAction(event -> {
            inputPassword.setText(dataManageService.generateButton());
        });
    }

    private void initActionButton() {
        actionButton.setText("Add");
        actionButton.setOnAction(event -> {
            if(validate()){
                dataManageService.addAction(collectFieldsData());
                endAction();
            }
        });
    }

    private void initOwnersList(){
            ownersListView.setOnAction(event -> {
                inputOwner.setText(ownersListView.getValue());
            });
    }

    private void initActionButton(Account account) {
        actionButton.setText("Save");
        fillFields(account);
        actionButton.setOnAction(event -> {
            if(validate()){
                dataManageService.updateAction(collectFieldsData());
                endAction();
            }
        });
    }

    private void initCancelButton() {
        cancelButton.setOnAction(event -> {
            dataManageService.cancelAction();
            clear();
        });
    }

    private void initClearButton() {
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

    private Account collectFieldsData() {
        Account account = new Account();
        account.setName(inputName.getText());
        account.setLink(inputLink.getText());
        account.setMail(inputMail.getText());
        account.setAccount(inputAccount.getText());
        account.setPassword(inputPassword.getText());
        account.setInfo(inputDescription.getText());

        if(inputOwner.getText()!=null && !inputOwner.getText().equals("")){
            account.setOwner(inputOwner.getText());
        }else{
            account.setOwner(ownersListView.getValue());
        }

        return account;
    }

    public void fillFields(Account input) {
        inputName.setText(input.getName());
        inputOwner.setText(input.getOwner());
        inputLink.setText(input.getLink());
        inputMail.setText(input.getMail());
        inputAccount.setText(input.getAccount());
        inputPassword.setText(input.getPassword());
        inputDescription.setText(input.getInfo());
    }

    private boolean validate() {
        HashMap<String,String> validatedFieldsMap = new HashMap<>();
            validatedFieldsMap.put("name", inputName.getText());
            validatedFieldsMap.put("password", inputPassword.getText());
        return dataManageService.validate(validatedFieldsMap);
    }

    private void endAction() {
        if (clearWhenAction.isSelected()) {
            clear();
        }
        if (!stayOnAction.isSelected()) {
            dataManageService.hideDataManage();
        }
        dataManageService.refresh();
    }

}
