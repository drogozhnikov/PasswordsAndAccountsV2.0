package panda.controllers.views;

import javafx.scene.control.ChoiceBox;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.views.elements.OwnersListView;

public class OwnersService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private OwnersListView ownersListView;

    public OwnersService(ViewServicesManager viewServicesManager, DataManager dataManager){
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init(){
        ownersListView = new OwnersListView(this);
    }

    public ChoiceBox<String> getOwnersListView(){
        return ownersListView;
    }
}
