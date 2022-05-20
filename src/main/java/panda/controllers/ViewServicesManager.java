package panda.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import panda.controllers.views.*;
import panda.controllers.views.components.InfoService;
import panda.controllers.views.components.OwnersListService;
import panda.controllers.views.options.OptionsService;
import panda.models.Account;
import panda.views.PandaRootView;
import panda.views.elements.HelloPandaView;
import panda.views.elements.options.OptionsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

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
    private OwnersListService ownersListService;

    private DataManager dataManager;
    private StageManager stageManager;

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
        ownersListService = new OwnersListService(this, dataManager);
    }

    private void initViews() {
        contextMenuService.init();
        menuService.init();
        optionsService.init();
        tableService.init();
        infoService.init();
        ownersListService.init();
    }

    public void refresh() {
        tableService.refresh();
        ownersListService.refresh();
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
        dataManageService.init(ownersListService.getOwnersListClearView());
        dataManageService.fillFieldsByAccount(account);
        pandaRootView.setRootTop(dataManageService.getDataManageView());
    }

    public void showDataManage() {
        dataManageService.init(ownersListService.getOwnersListClearView());
        pandaRootView.setRootTop(dataManageService.getDataManageView());
    }

    public void hideDataManage() {
        controlMenuService.init(ownersListService.getOwnersListView());
        pandaRootView.setRootTop(controlMenuService.getControlMenuView());
    }

    public HelloPandaView getHelloPandaView() {
        return helloPandaService.getHelloPandaView();
    }

    public OptionsView getOptionsView() {
        optionsService.getOptionsView().setMenuPane(menuService.getMenuView());
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

    public ButtonType alert(String title, String headerText, String contextText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);

        Optional<ButtonType> option = alert.showAndWait();

        return option.get();
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

}
