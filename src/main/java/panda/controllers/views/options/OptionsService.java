package panda.controllers.views.options;

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

    public void saveAccessAction(String inputOld, String inputTemp, String inputNew) {
        if (inputOld != null && inputTemp != null && inputNew != null) {
            if (!inputOld.equals("") && !inputTemp.equals("") && !inputNew.equals("")) {
                int acess = 0;
                System.out.println(acess);
                if (inputOld.equals(inputTemp)) {
                    acess = dataManager.checkAccess(inputOld);
                }
                if (acess == 1) {
                    dataManager.reinitAccessPass(inputNew, inputOld);
                } else {
                    viewServicesManager.alert(
                            "Access Error",
                            "Access denied",
                            "please check the correctness of the entered data");
                }
            }else{
                viewServicesManager.alert(
                        "Access Info",
                        "Imposible Action",
                        "please check the correctness of the entered data");
            }
        }

    }

    public void saveCurrentResolution() {
        dataManager.saveCurrentResolution();
    }

}
