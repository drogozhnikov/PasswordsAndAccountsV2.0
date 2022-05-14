package panda.controllers.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.models.PandaAccount;
import panda.views.elements.TableView;

import java.util.ArrayList;

public class TableService {

    private final Logger logger = LoggerFactory.getLogger(TableService.class);

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private TableView tableView;

    public TableService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init() {
        tableView = new TableView(this);
    }

    public void refresh() {
        ArrayList<PandaAccount> pandaList = dataManager.selectPandaAccounts();
        if(pandaList != null && pandaList.size()>0){
            ObservableList<PandaAccount> list = FXCollections.observableArrayList(pandaList);
            tableView.refresh(list);
        }else{
            logger.error("Selected pandas list is empty");
        }
    }

    public void selectCells() {
        System.out.println("cellSelected"); //TODO selectCellAction
    }

    public TableView getTableView() {
        return tableView;
    }

    public void initContext(ContextMenu input) {
        tableView.setOnContextMenuRequested(event -> input.show(tableView, event.getScreenX(), event.getScreenY()));
    }
}
