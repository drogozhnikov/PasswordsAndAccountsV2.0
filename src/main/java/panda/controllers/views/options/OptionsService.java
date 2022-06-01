package panda.controllers.views.options;

import javafx.scene.control.Alert;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.views.elements.options.OptionsView;

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
        viewServicesManager.refresh();
        viewServicesManager.showPandaScene();
    }

    public void cancelButton() {
        viewServicesManager.refresh();
        viewServicesManager.showPandaScene();
    }

    public void saveAccessAction(StringBuilder inputOld, StringBuilder inputTemp, StringBuilder inputNew) {
        if (!inputOld.toString().equals("") && !inputNew.toString().equals("") && !inputTemp.toString().equals("")) {
            if (inputNew.toString().equals(inputTemp.toString())) {
                dataManager.reinitAccessPass(new StringBuilder(inputNew),new StringBuilder(inputOld));
            } else {
                viewServicesManager.alert(
                        Alert.AlertType.WARNING,
                        "Access denied",
                        "Passwords missmatch");
            }
        }else {
            viewServicesManager.alert(
                    Alert.AlertType.WARNING,
                    "Access denied",
                    "Empty fields detected");
        }
    }

    public void saveCurrentResolution() {
        dataManager.saveCurrentResolution();
    }

    public void resResolution() {
        dataManager.restoreResolution();
    }

    public void clearBase() {
        StringBuilder pass = viewServicesManager.askPass("Clear Base access",
                "By confirming the action, you will completely and irrevocably clear the database.");
        if (!pass.equals(new StringBuilder(""))) {
            dataManager.clearBase(pass);
        }
    }

}
