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
        viewServicesManager.showPandaScene();
    }

    public void cancelButton() {
        viewServicesManager.showPandaScene();
    }

    public void saveAccessAction(StringBuilder inputOld, StringBuilder inputTemp, StringBuilder inputNew) {
        if (!inputOld.toString().equals("") && !inputTemp.toString().equals("") && !inputNew.toString().equals("")) {
            int acess = 0;
            if (inputOld.toString().equals(inputTemp.toString())) {
                acess = dataManager.checkAccess(inputOld);
            }
            if (acess == 1) {
                dataManager.reinitAccessPass(new StringBuilder(inputNew));
            } else {
                viewServicesManager.alert(
                        Alert.AlertType.ERROR,
                        "Access denied",
                        "please check the correctness of the entered data");
            }
        }
    }

    public void saveCurrentResolution() {
        dataManager.saveCurrentResolution();
    }

    public void resResolution() {
        dataManager.restoreResolution();
    }

    public void clearBase() {
        StringBuilder pass = viewServicesManager.askPass("Changing Password",
                "By confirming the action, you will completely and irrevocably clear the database");
        if (!pass.equals(new StringBuilder(""))) {
            dataManager.clearBase(pass);
        }
    }

}
