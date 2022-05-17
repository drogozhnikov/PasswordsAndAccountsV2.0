package panda.controllers.views.components;

import javafx.scene.text.Text;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;

public class InfoService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private Text infoView;

    public InfoService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init() {
        infoView = new Text("info");
    }

    public Text getInfoView() {
        return infoView;
    }
}
