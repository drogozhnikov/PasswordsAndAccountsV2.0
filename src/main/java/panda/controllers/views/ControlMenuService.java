package panda.controllers.views;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.controllers.views.components.OwnersListService;
import panda.models.Account;
import panda.models.PandaAccount;
import panda.views.elements.ControlMenuView;
import panda.views.elements.components.OwnersListView;

import java.util.ArrayList;

public class ControlMenuService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private ControlMenuView controlMenuView;

    public ControlMenuService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init(OwnersListView ownersListView) {
        controlMenuView = new ControlMenuView(this, ownersListView);
    }

    public HBox getControlMenuView() {
        return controlMenuView;
    }

    public void actionAdd() {
        viewServicesManager.showDataManage();
    }

    public void search(KeyCode code, String value){
        ArrayList<PandaAccount> foundedFields = new ArrayList<>();
        foundedFields = dataManager.search(value, controlMenuView.getOwner());
        switch (code.toString()) {
            case ("BACK_SPACE"):
                foundedFields = dataManager.search(value, controlMenuView.getOwner());
            case ("DELETE"):
                foundedFields = dataManager.search(value, controlMenuView.getOwner());
        }
        if (value.equals("")) {
            foundedFields = dataManager.selectPandaAccounts();
        }
        viewServicesManager.refresh(foundedFields);
    }
}
