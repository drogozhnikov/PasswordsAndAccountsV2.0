package panda.controllers.views;

import javafx.scene.control.MenuBar;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.views.elements.MenuView;

public class MenuService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private MenuView menuView;

    public MenuService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init() {
        menuView = new MenuView(this);
    }

    public MenuBar getMenuView() {
        return menuView;
    }

    public void saveAction() {
        //TODO saveAction
    }

    public void loadAction() {
        //TODO loadAction
    }

    public void optionAction() {
        //TODO optionAction
    }

    public void exitAction() {
        //TODO exitAction
    }
}
