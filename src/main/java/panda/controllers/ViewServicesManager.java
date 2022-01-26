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

    public void initViews() {
        ownersService.init();
        contextMenuService.init();
        menuService.init();
        optionsService.init();
        tableService.init();
        infoService.init();
    }

    public void initRootPositions(){
        pandaRootView = new PandaRootView();
        pandaRootView.setMenuPane(menuService.getMenuView());
        showControlPanel();
        pandaRootView.setRootCenter(tableService.getTableView());

        pandaRootView.setBottom(infoService.getInfoView());

        tableService.initContext(contextMenuService.getContextMenuView());
    }

    public PandaRootView getRoot(){
        return pandaRootView;
    }

    public void showDataManage(){
        dataManageService.init(ownersService.getOwnerListView());
        pandaRootView.setRootTop(dataManageService.getDataManageView());
    }
    public void showControlPanel(){
        controlMenuService.init(ownersService.getOwnerListView());
        pandaRootView.setRootTop(controlMenuService.getControlMenuView());
    }

}
