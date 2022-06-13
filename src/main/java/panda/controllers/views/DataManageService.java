package panda.controllers.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.models.Account;
import panda.models.Owner;
import panda.views.elements.components.AddManage;
import panda.views.elements.components.UpdateManage;

import java.util.*;

public class DataManageService {

    private final DataManager dataManager;
    private final ViewServicesManager viewServicesManager;

    private AddManage addManage;
    private UpdateManage updateManage;

    public DataManageService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init() {
        addManage = new AddManage(this);
        updateManage = new UpdateManage(this);
    }

    public BorderPane getAddManage() {
        return addManage;
    }

    public BorderPane getUpdateManage() {
        return updateManage;
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

    public boolean isAccountExist(Account account){
        boolean isExist = dataManager.isAccountExist(account);
        if(isExist){
            viewServicesManager.alert(Alert.AlertType.WARNING,
                    "Same account already exist",
                    "");
            return true;
        }else{
            return false;
        }
    }

    public void hideDataManage() {
        refresh();
        viewServicesManager.hideDataManage();
    }

    public BorderPane getAddDataManage(){
        addManage.init();
        return addManage;
    }
    public BorderPane getUpdateDataManage(Account account){
        updateManage.init(account);
        return updateManage;
    }

    public void refresh() {
        viewServicesManager.refresh();
    }

    public String generateButton(){
        return dataManager.generatePassword();
    }

    public ObservableList<Owner> getOwnersList(){
        ObservableList<Owner> result = FXCollections.observableArrayList(dataManager.getOwnerList());
        Collections.reverse(result);
        return result;
    }

    //Are you sure you want to update account with a data that already exists or have some problems&
    //Are you sure you want to add a new account with a data that already exists in the database?
}
