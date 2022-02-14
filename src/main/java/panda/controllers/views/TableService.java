package panda.controllers.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.models.PandaAccount;
import panda.views.elements.TableView;

import java.util.ArrayList;

public class TableService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private TableView tableView;

    public TableService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init() {
        tableView = new TableView(this);
//        refresh();
    }

    public void refresh(ArrayList<PandaAccount> inputList) {
        ObservableList<PandaAccount> list = FXCollections.observableArrayList(inputList);
        tableView.setItems(list);
        tableView.refresh();
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
