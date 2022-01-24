package panda.controllers;

import panda.controllers.views.*;
import panda.views.PandaRootView;

public class ViewServicesManager {

    private PandaRootView pandaRootView;

    private ContextMenuService contextMenuService;
    private ControlMenuService controlMenuService;
    private DataManageService dataManageService;
    private MenuService menuService;
    private OptionsService optionsService;
    private OwnersService ownersService;
    private TableService tableService;
    private InfoService infoService;

    private DataManager dataManager;

    public ViewServicesManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void initServices() {
        contextMenuService = new ContextMenuService(this, dataManager);
        controlMenuService = new ControlMenuService(this, dataManager);
        dataManageService = new DataManageService(this, dataManager);
        menuService = new MenuService(this, dataManager);
        optionsService = new OptionsService(this, dataManager);
        ownersService = new OwnersService(this, dataManager);
        tableService = new TableService(this, dataManager);
        infoService = new InfoService(this, dataManager);
    }

    public void initPane() {
        pandaRootView = new PandaRootView();
        pandaRootView.setMenuPane(menuService.getMenuView());
        pandaRootView.setRootCenter(tableService.getTableView());
        pandaRootView.setRootTop(controlMenuService.getControlMenuView());
        pandaRootView.setBottom(infoService.getInfoView());

        tableService.initContext(contextMenuService.getContextMenuView());
    }

}
