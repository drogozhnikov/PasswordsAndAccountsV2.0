package panda.controllers.views;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;
import panda.models.Account;
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
            ButtonType temp = viewServicesManager.alert(
                    "Warning",
                    "Do you really want to remove this line?",
                    ""); //TODO Row data in alert
            if(temp.equals(ButtonType.OK)){
                dataManager.deleteAccount(viewServicesManager.getIdLastSelectedAccounts());
                viewServicesManager.refresh();
            }
        }
    }

    public void update(){
        if(isSelectedExist()){
            int selected = checkAndGetIfOneSelected();
            if(selected!=0){
                Account account = dataManager.getAccountById(selected);
                viewServicesManager.showUpdateDataManage(account);
            }
        }
    }


    private int checkAndGetIfOneSelected(){
        ArrayList<Integer> list = viewServicesManager.getIdLastSelectedAccounts();
        if(list.size()==1){
            return list.get(0);
        }else{
            viewServicesManager.alert("Warning", "Multiple selections found. Please choose only one row.","");
            return 0;
        }
    }

    private boolean isSelectedExist(){
        if(viewServicesManager.getIdLastSelectedAccounts()==null ||
                viewServicesManager.getIdLastSelectedAccounts().size()==0){
            viewServicesManager.alert("Warning", "No items selected","");
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
                     viewServicesManager.alert("Sorry", "Looks like this row is missing a link.","");
                    }
                }
        }
    }


}
