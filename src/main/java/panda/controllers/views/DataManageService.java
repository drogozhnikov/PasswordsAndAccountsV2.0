package panda.controllers.views;

import javafx.scene.layout.BorderPane;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.views.elements.DataManageView;
import panda.views.elements.OwnersListView;

public class DataManageService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

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

    public void addAction(){
        viewServicesManager.showControlPanel();
    }

    public void updateAction(){
        viewServicesManager.showControlPanel();
    }

    public void cancelAction(){
        viewServicesManager.showControlPanel();
    }
}
