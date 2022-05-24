package panda.controllers.views;

import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.models.Account;
import panda.views.elements.DataManageView;
import panda.views.elements.components.OwnersListView;

import java.util.*;

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
        dataManager.insertAccount(account);
        viewServicesManager.refresh();
    }

    public void updateAction(Account account) {
        dataManager.updateAccount(account);
        viewServicesManager.refresh();
    }

    public void cancelAction() {
        viewServicesManager.hideDataManage();
    }

    public boolean validate(HashMap<String, String> validatedFieldsMap) {
        Set<String> keys = validatedFieldsMap.keySet();
        Iterator<String> iterator = keys.iterator();
        StringBuilder emptyfields = new StringBuilder();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (validatedFieldsMap.get(key) == null || validatedFieldsMap.get(key).equals("")) {
                emptyfields.append("-" + key);
            }
        }
        if(!emptyfields.toString().equals("")){
            viewServicesManager.alert(Alert.AlertType.WARNING," Not enough data",emptyfields.toString());
            return false;
        }
        return true;
    }

    public void hideDataManage() {
        refresh();
        viewServicesManager.hideDataManage();
    }

    public void refresh() {
        viewServicesManager.refresh();
    }

    public ArrayList<String> getOwnersList(){
        return dataManager.getOwnerList();
    }

    public String generateButton(){
        return dataManager.generatePassword();
    }

    public void fillFieldsByAccount(Account account){
        dataManageView.fillFields(account);
    }

    //Are you sure you want to update account with a data that already exists or have some problems&
    //Are you sure you want to add a new account with a data that already exists in the database?
}
