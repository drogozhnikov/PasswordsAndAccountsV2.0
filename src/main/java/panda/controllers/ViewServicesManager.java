package panda.controllers;

import panda.controllers.views.*;
import panda.views.PandaRootView;
import panda.views.elements.HelloPandaView;

public class ViewServicesManager {

    private PandaRootView pandaRootView;

    private HelloPandaService helloPandaService;
    private ContextMenuService contextMenuService;
    private ControlMenuService controlMenuService;
    private DataManageService dataManageService;
    private MenuService menuService;
    private OptionsService optionsService;
    private OwnersService ownersService;
    private TableService tableService;
    private InfoService infoService;

    private DataManager dataManager;
    private StageManager stageManager;

    public ViewServicesManager(StageManager stageManager, DataManager dataManager) {
        this.dataManager = dataManager;
        this.stageManager = stageManager;
        initServices();
    }

    public PandaRootView init(){
        initViews();
        initRootPositions();
        return pandaRootView;
    }

    private void initServices() {
        contextMenuService = new ContextMenuService(this, dataManager);
        controlMenuService = new ControlMenuService(this, dataManager);
        dataManageService = new DataManageService(this, dataManager);
        helloPandaService = new HelloPandaService(this, dataManager);
        menuService = new MenuService(this, dataManager);
        optionsService = new OptionsService(this, dataManager);
        ownersService = new OwnersService(this, dataManager);
        tableService = new TableService(this, dataManager);
        infoService = new InfoService(this, dataManager);

    }

    private void initViews() {
        ownersService.init();
        contextMenuService.init();
        menuService.init();
        optionsService.init();
        tableService.init();
        infoService.init();
    }

    private void initRootPositions(){
        pandaRootView = new PandaRootView();
        pandaRootView.setMenuPane(menuService.getMenuView());
        showControlPanel();

        pandaRootView.setRootCenter(tableService.getTableView());
        pandaRootView.setBottom(infoService.getInfoView());
        tableService.initContext(contextMenuService.getContextMenuView());
    }

//    public PandaRootView getRoot(){
//        return pandaRootView;
//    }

    public void showDataManage(){
        dataManageService.init(ownersService.getOwnerListView());
        pandaRootView.setRootTop(dataManageService.getDataManageView());
    }
    public void showControlPanel(){
        controlMenuService.init(ownersService.getOwnerListView());
        pandaRootView.setRootTop(controlMenuService.getControlMenuView());
    }
    public void showOptionslPanel(){
        optionsService.init();
        pandaRootView.setRootTop(optionsService.getOptionsView());
    }

    public HelloPandaView getHelloPandaView(){
        return helloPandaService.getHelloPandaView();
    }

    public void showPandaScene(){
        stageManager.showPandaScene();
    }

}
