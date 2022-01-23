package panda.controllers;

import panda.controllers.views.*;
import panda.views.PandaRootView;
import panda.views.elements.DataManageView;

public class ViewServicesManager {

    private PandaRootView pandaRootView;

    private ContextMenuService contextMenuService;
    private ControlMenuService controlMenuService;
    private DataManageService dataManageService;
    private MenuService menuService;
    private OptionsService optionsService;
    private OwnersService ownersService;
    private TableService tableService;

    public ViewServicesManager(){

    }

    public void init(){
        contextMenuService = new ContextMenuService();
        controlMenuService = new ControlMenuService();
        dataManageService = new DataManageService();
        menuService = new MenuService();
        optionsService = new OptionsService();
        ownersService = new OwnersService();
        tableService = new TableService();

    }
}
