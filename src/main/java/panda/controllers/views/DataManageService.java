package panda.controllers.views;

import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.models.Account;
import panda.views.elements.DataManageView;
import panda.views.elements.OwnersListView;

import java.util.Optional;

public class DataManageService {

    private final DataManager dataManager;
    private final ViewServicesManager viewServicesManager;

    private DataManageView dataManageView;

    public DataManageService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init(OwnersListView ownersListView) {
        dataManageView = new DataManageView(this, ownersListView);
    }

    public BorderPane getDataManageView() {
        return dataManageView;
    }

    public void addAction(Account account) {
        String validationMessage = dataManager.validateAccount(account);
            if (validationMessage.length() > 0) {
                Optional<ButtonType> option = viewServicesManager.alert(
                        "Add Warning",
                        "Are you sure you want to add a new account with a data that already exists in the database?",
                        validationMessage);
                alertAction(option, account);
            } else {
                dataManager.addAccount(account);

            }
    }

    public void updateAction(Account account) {
        String validationMessage = dataManager.validateAccount(account);
            if (validationMessage.length() > 0) {
                Optional<ButtonType> option = viewServicesManager.alert(
                        "Update Warning",
                        "Are you sure you want to update account with a data that already exists or have some problems&",
                        validationMessage);
                alertAction(option, account);
            } else {
                dataManager.addAccount(account);
            }
    }

    public void cancelAction() {
        viewServicesManager.hideDataManage();
    }

    private void alertAction(Optional<ButtonType> option, Account account){
        if (option.get() == ButtonType.OK) {
            dataManager.addAccount(account);
            viewServicesManager.refresh();
        } else if (option.get() == ButtonType.CANCEL) {

        } else {

        }
    }

    public void hideDataManage(){
        refresh();
        viewServicesManager.hideDataManage();
    }

    public void refresh(){
        viewServicesManager.refresh();
    }
}
