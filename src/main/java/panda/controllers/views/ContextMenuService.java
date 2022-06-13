package panda.controllers.views;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.models.Account;
import panda.models.PandaAccount;
import panda.utils.io.xml.XMLio;
import panda.utils.io.xml.XmlWriter;
import panda.views.elements.ContextMenuView;

import java.awt.font.OpenType;
import java.util.ArrayList;

public class ContextMenuService {

    private DataManager dataManager;
    private ViewServicesManager viewServicesManager;

    private ContextMenuView contextMenuView;

    public ContextMenuService(ViewServicesManager viewServicesManager, DataManager dataManager) {
        this.viewServicesManager = viewServicesManager;
        this.dataManager = dataManager;
    }

    public void init() {
        contextMenuView = new ContextMenuView(this);
    }

    public ContextMenu getContextMenuView() {
        return contextMenuView;
    }

    public void delete(){
        if(isSelectedExist()){
            boolean confirm = viewServicesManager.ask(
                    "Removing",
                    "Do you really want to remove this line?",
                    ""); //TODO Row data in alert
            if(confirm){
                dataManager.deleteAccount(viewServicesManager.getIdLastSelectedAccounts());
                viewServicesManager.refresh();
            }
        }
    }

    public void update(){
        if(isSelectedExist()){
            StringBuilder enteredPass = viewServicesManager.askPass("Updating", "Please confirm update action");
            if(dataManager.validateAccess(enteredPass)){
                int selected = checkAndGetIfOneSelected();
                if(selected!=0){
                    Account account = dataManager.getAccountById(selected);
                    viewServicesManager.showUpdateDataManage(account);
                }
            }else{
                viewServicesManager.alert(Alert.AlertType.WARNING, "Wrong password", "");
            }
        }
    }

    private int checkAndGetIfOneSelected(){
        ArrayList<Integer> list = viewServicesManager.getIdLastSelectedAccounts();
        if(list.size()==1){
            return list.get(0);
        }else{
            viewServicesManager.alert(Alert.AlertType.WARNING, "Multiple selections found. Please choose only one row.","");
            return 0;
        }
    }

    private boolean isSelectedExist(){
        if(viewServicesManager.getIdLastSelectedAccounts()==null ||
                viewServicesManager.getIdLastSelectedAccounts().size()==0){
            viewServicesManager.alert(Alert.AlertType.WARNING, "No items selected","");
            return false;
        }
        return true;
    }

    public void startLink(){
        if(isSelectedExist()){
            int selected = checkAndGetIfOneSelected();
                if(selected!=0){
                    String link = dataManager.getAccountById(selected).getLink();
                    if(link!=null && !link.equals("")){
                        dataManager.startLink(link);
                    }else{
                     viewServicesManager.alert(Alert.AlertType.WARNING, "Looks like this row is missing a link.","");
                    }
                }
        }
    }

    public void saveXML(){
        if(isSelectedExist()){
            String filePath = viewServicesManager.askSavePath("XML file save","xml_backUp_file");
            dataManager.save(new XMLio(), filePath);
        }
    }

    public void saveJSON(){
        if(isSelectedExist()){

        }
    }


}
