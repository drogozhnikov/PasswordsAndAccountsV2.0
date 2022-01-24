package panda.controllers.views;

import javafx.scene.layout.HBox;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.views.elements.ControlMenuView;

public class ControlMenuService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private ControlMenuView controlMenuView;

    public ControlMenuService(ViewServicesManager viewServicesManager, DataManager dataManager){
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init(){
        controlMenuView = new ControlMenuView(this);
        controlMenuView.init();
    }

    public HBox getControlMenuView(){
        return controlMenuView;
    }
}
