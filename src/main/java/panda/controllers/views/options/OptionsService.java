package panda.controllers.views.options;

import javafx.scene.layout.BorderPane;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.views.elements.options.OptionsView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public OptionsView getOptionsView() {
        return optionsView;
    }

    public void saveButton() {
        viewServicesManager.showPandaScene();
    }

    public void cancelButton() {
        viewServicesManager.showPandaScene();
    }

    public void saveCurrentResolution(){
        dataManager.saveCurrentResolution();
    }

}
