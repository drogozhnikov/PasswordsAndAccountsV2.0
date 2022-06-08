package panda.controllers.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
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
        ArrayList<PandaAccount> pandaList = dataManager.search("",dataManager.getSelectedOwner());
        if (pandaList.size() == 0) {
            logger.info("Selected pandas list is empty");
        }
        ObservableList<PandaAccount> list = FXCollections.observableArrayList(pandaList);
        tableView.refresh(list);
    }

    public void refresh(ArrayList<PandaAccount> input) {
        ObservableList<PandaAccount> list = FXCollections.observableArrayList(input);
        tableView.refresh(list);
    }

    public void selectCells(int row, int column) {
        int minimumArraySize = 1;
        int passColumn = 3;
        Iterator iterator = tableView.getSelectionModel().getSelectedItems().iterator();
        ArrayList<Integer> selectedAccountsList = new ArrayList<>();
        while (iterator.hasNext()) {
            PandaAccount temp = (PandaAccount) iterator.next();
            selectedAccountsList.add(temp.getAccountId());
        }
        if (selectedAccountsList != null && selectedAccountsList.size() > 0) {
            viewServicesManager.setLastSelectedAccounts(selectedAccountsList);
        }
        if(selectedAccountsList.size()==minimumArraySize){
            if(column == passColumn){
                StringBuilder data = dataManager.getAccountById(selectedAccountsList.get(0)).getPassword();
                copyToBuffer(data);
                viewServicesManager.setInfoText(data);
            }
        }
    }

    public TableView getTableView() {
        return tableView;
    }

    public void initContext(ContextMenu input) {
//        tableView.setOnContextMenuRequested(event -> input.show(tableView, event.getScreenX(), event.getScreenY()));
        tableView.setContextMenu(input);
    }

    private void copyToBuffer(Object o) throws NullPointerException {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(o.toString());
        clipboard.setContent(content);
        //TODO info
    }

    public boolean isHidePass() {
        return dataManager.isHidePass();
    }
}
