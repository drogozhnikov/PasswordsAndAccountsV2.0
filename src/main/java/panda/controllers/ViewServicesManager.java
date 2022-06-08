package panda.controllers;


import javafx.scene.control.Alert;

import panda.controllers.views.*;
import panda.controllers.views.components.InfoService;
import panda.controllers.views.options.OptionsService;
import panda.models.Account;
import panda.models.PandaAccount;
import panda.views.PandaRootView;
import panda.views.elements.HelloPandaView;
import panda.views.elements.additional.PandaAlert;
import panda.views.elements.options.OptionsView;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewServicesManager {

    private PandaRootView pandaRootView;

    private HelloPandaService helloPandaService;
    private ContextMenuService contextMenuService;
    private ControlMenuService controlMenuService;
    private DataManageService dataManageService;
    private MenuService menuService;
    private OptionsService optionsService;
    private TableService tableService;
    private InfoService infoService;

    private DataManager dataManager;
    private StageManager stageManager;

    private PandaAlert alert = new PandaAlert();

    private ArrayList<Integer> lastSelectedAccount;

    public ViewServicesManager(StageManager stageManager, DataManager dataManager) {
        this.dataManager = dataManager;
        this.stageManager = stageManager;
        initServices();
    }

    public PandaRootView init() {
        initViews();
        initRootPositions();
        refresh();
        return pandaRootView;
    }

    private void initServices() {
        contextMenuService = new ContextMenuService(this, dataManager);
        controlMenuService = new ControlMenuService(this, dataManager);
        dataManageService = new DataManageService(this, dataManager);
        helloPandaService = new HelloPandaService(this, dataManager);
        menuService = new MenuService(this, dataManager);
        optionsService = new OptionsService(this, dataManager);
        tableService = new TableService(this, dataManager);
        infoService = new InfoService(this, dataManager);
    }

    private void initViews() {
        tableService.init(); // must init before controlMenuService
        contextMenuService.init();
        controlMenuService.init();
        dataManageService.init();
        menuService.init();
        optionsService.init();
        infoService.init();
    }

    public void refresh() {
        tableService.refresh();
        controlMenuService.refreshOwners();
    }

    public void refresh(ArrayList<PandaAccount> input){
        tableService.refresh(input);
    }

    private void initRootPositions() {
        pandaRootView = new PandaRootView();
        pandaRootView.setMenuPane(menuService.getMenuView());
        hideDataManage();

        pandaRootView.setRootCenter(tableService.getTableView());
        pandaRootView.setBottom(infoService.getInfoView());
        tableService.initContext(contextMenuService.getContextMenuView());
    }


    public void showUpdateDataManage(Account account){
        refresh();
        dataManageService.fillFieldsByAccount(account);
        pandaRootView.setRootTop(dataManageService.getDataManageView());
    }

    public void showDataManage() {
        pandaRootView.setRootTop(dataManageService.getDataManageView());
    }

    public void hideDataManage() {
        pandaRootView.setRootTop(controlMenuService.getControlMenuView());
    }

    public HelloPandaView getHelloPandaView() {
        return helloPandaService.getHelloPandaView();
    }

    public OptionsView getOptionsView() {
        return optionsService.getOptionsView();
    }

    public void initPandaScene(){
        stageManager.initPandaScene();
    }

    public void showPandaScene() {
        stageManager.showPandaScene();
    }

    public void showOptionslPanel() {
        stageManager.initOptionsScene();
        stageManager.showOptionsScene();
    }

    public void alert(Alert.AlertType type, String headerText, String contextText) {
        alert.show(type, headerText, contextText);
    }

    public boolean ask(String title, String headerText, String contextText) {
        return alert.ask(title, headerText, contextText);
    }

    public StringBuilder askPass(String title, String headerText) {
        return alert.askWithPass(title, headerText);
    }

    public void setLastSelectedAccounts(ArrayList<Integer> input){
        this.lastSelectedAccount = input;
    }
    public ArrayList<Integer> getIdLastSelectedAccounts(){
        return lastSelectedAccount;
    }

    public HashMap<String, Double> getCurrentResolution(){
        return stageManager.getStageDimension();
    }

    public void setInfoText(StringBuilder input){
        infoService.setInfoText(input);
    }

}
