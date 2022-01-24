package panda.controllers.views;

import javafx.scene.layout.BorderPane;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.views.elements.DataManageView;

public class DataManageService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private DataManageView dataManageView;

    public DataManageService(ViewServicesManager viewServicesManager, DataManager dataManager){
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init(){
        dataManageView = new DataManageView(this);
    }

    public BorderPane getDataManageView(){
        return dataManageView;
    }
}
