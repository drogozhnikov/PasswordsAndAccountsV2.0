package panda.controllers.views;

import javafx.scene.control.ContextMenu;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.views.elements.ContextMenuView;

public class ContextMenuService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private ContextMenuView contextMenuView;

    public ContextMenuService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init() {
        contextMenuView = new ContextMenuView(this);
    }

    public ContextMenu getContextMenuView() {
        return contextMenuView;
    }

    public void delete(){
        dataManager.deleteAccount(viewServicesManager.getIdLastSelectedAccounts());
        viewServicesManager.refresh();
    }
}
