package panda.controllers.views;

import javafx.scene.layout.HBox;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.views.elements.ControlMenuView;
import panda.views.elements.OwnersListView;

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
//        dataManager.test();
    }
}
