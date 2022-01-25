package panda.controllers.views;

import javafx.scene.layout.BorderPane;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.views.elements.OptionsView;

public class OptionsService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private OptionsView optionsView;

    public OptionsService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init() {
        optionsView = new OptionsView(this);
    }

    public BorderPane getOptionsView() {
        return optionsView;
    }
}
