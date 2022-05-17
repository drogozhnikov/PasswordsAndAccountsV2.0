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
import java.util.Iterator;

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
        if (pandaList.size() == 0) {
            logger.info("Selected pandas list is empty");
        }
        ObservableList<PandaAccount> list = FXCollections.observableArrayList(pandaList);
        tableView.refresh(list);

    }

    public void selectCells() {
        Iterator iterator = tableView.getSelectionModel().getSelectedItems().iterator();
        ArrayList<Integer> selectedAccountsList = new ArrayList<>();
        while (iterator.hasNext()) {
            PandaAccount temp = (PandaAccount) iterator.next();
            selectedAccountsList.add(temp.getAccountId());
        }
        if (selectedAccountsList != null && selectedAccountsList.size() > 0) {
            viewServicesManager.setLastSelectedAccounts(selectedAccountsList);
        }
    }

    public TableView getTableView() {
        return tableView;
    }

    public void initContext(ContextMenu input) {
//        tableView.setOnContextMenuRequested(event -> input.show(tableView, event.getScreenX(), event.getScreenY()));
        tableView.setContextMenu(input);
    }

    public boolean isHidePass() {
        return dataManager.isHidePass();
    }
}
