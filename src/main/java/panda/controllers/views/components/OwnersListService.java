package panda.controllers.views.components;

import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.views.elements.components.OwnersListView;

import java.util.ArrayList;

public class OwnersListService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private OwnersListView ownersListView;
    private OwnersListView ownersListClearView;

    public OwnersListService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init(){
        ownersListClearView = new OwnersListView();
        ownersListView = new OwnersListView();
    }

    public void refresh(){
        ownersListView.refresh(dataManager.getOwnerList());
        refreshWithoutItemAll();
    }

    public void refreshWithoutItemAll(){
        ArrayList<String> ownersList = dataManager.getOwnerList();
            ownersList.remove("all");
        ownersListClearView.refresh(ownersList);
    }


    public OwnersListView getOwnersListView(){
        return ownersListView;
    }
    public OwnersListView getOwnersListClearView(){
        return ownersListClearView;
    }
}
