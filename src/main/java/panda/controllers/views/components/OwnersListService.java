package panda.controllers.views.components;

import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.models.Owner;
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
        ownersListClearView = new OwnersListView(this);
        ownersListView = new OwnersListView(this);
    }

    public void refresh(){
        ArrayList<Owner> ownersList = dataManager.getOwnerList();
        ownersListView.refresh(ownersList);
        refreshWithoutItemAll();
    }

    public void refreshWithoutItemAll(){
        ArrayList<Owner> ownersList = dataManager.getOwnerList();
        ArrayList<Owner> ownersListClear = new ArrayList<>();
            for(Owner owner:ownersList){
                if(!owner.getName().equals("all")){
                    ownersListClear.add(owner);
                }
            }
        ownersListClearView.refresh(ownersListClear);
    }

    public void setLastSelectedOwner(Owner selectedOwner){
        dataManager.setLastSelectedOwner(selectedOwner);
        viewServicesManager.refresh();
    }

    public OwnersListView getOwnersListView(){
        return ownersListView;
    }
    public OwnersListView getOwnersListClearView(){
        return ownersListClearView;
    }
}
