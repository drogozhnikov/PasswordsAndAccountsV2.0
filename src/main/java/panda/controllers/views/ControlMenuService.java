package panda.controllers.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.models.Owner;
import panda.models.PandaAccount;
import panda.views.elements.ControlMenuView;

import java.util.ArrayList;
import java.util.Collections;

public class ControlMenuService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private ControlMenuView controlMenuView;

    public ControlMenuService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init() {
        controlMenuView = new ControlMenuView(this);
    }

    public HBox getControlMenuView() {
        return controlMenuView;
    }

    public void actionAdd() {
        viewServicesManager.showAddDataManage();
    }

    public void search(KeyCode code, String value){
        ArrayList<PandaAccount> foundedFields = new ArrayList<>();
        switch (code.toString()) {
            case ("BACK_SPACE"):
                foundedFields = dataManager.search(value, controlMenuView.getOwner());
            case ("DELETE"):
                foundedFields = dataManager.search(value, controlMenuView.getOwner());
        }
        if (value.equals("")) {
            foundedFields = dataManager.selectPandaAccounts();
        }else{
            foundedFields = dataManager.search(value, controlMenuView.getOwner());
        }
        viewServicesManager.refresh(foundedFields);
    }

    public ObservableList<Owner> getOwnersList(){
        ObservableList<Owner> result = FXCollections.observableArrayList(dataManager.getOwnerList());
        Collections.sort(result);
        return result;
    }

    public void refreshOwners(){
        controlMenuView.refresh(getOwnersList(), dataManager.getSelectedOwner());
    }

    public void ownerAction(Owner owner){
        dataManager.setLastSelectedOwner(owner);
        viewServicesManager.refresh(dataManager.search("",owner));
    }

    public Owner getLastSelectedOwner(){
        return dataManager.getSelectedOwner();
    }
}
