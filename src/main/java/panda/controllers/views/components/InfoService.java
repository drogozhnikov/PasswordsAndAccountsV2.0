package panda.controllers.views.components;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.views.elements.components.InfoView;

public class InfoService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private InfoView infoView;

    public InfoService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init() {
        infoView = new InfoView();
    }

    public HBox getInfoView() {
        return infoView;
    }

    public void setInfoText(StringBuilder input){
        infoView.setInfoText(input.toString());
    }
}
