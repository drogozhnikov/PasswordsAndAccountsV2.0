package panda.views.elements;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import panda.controllers.views.TableService;
import panda.models.PandaAccount;

import java.io.File;
import java.net.MalformedURLException;

public class TableView extends javafx.scene.control.TableView<PandaAccount> {

    private TableService tableService;

    public TableView(TableService tableService) {
        this.tableService = tableService;

        super.setColumnResizePolicy(javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY);
        super.getSelectionModel().setCellSelectionEnabled(true);
        super.getSelectionModel().getSelectedCells().addListener(this::selectCells);
        super.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<PandaAccount, String> name = new TableColumn<>("Name");
        name.setStyle("-fx-alignment: CENTER;");

        TableColumn<PandaAccount, String> mail = new TableColumn<>("Mail");
        mail.setStyle("-fx-alignment: CENTER;");

        TableColumn<PandaAccount, String> account = new TableColumn<>("Account");
        account.setStyle("-fx-alignment: CENTER;");

        TableColumn<PandaAccount, String> password = new TableColumn<>("Password");
        password.setStyle("-fx-alignment: CENTER;");

        super.getColumns().addAll(name, mail, account, password);

        name.setCellValueFactory(new PropertyValueFactory<>("tableFieldName"));
        mail.setCellValueFactory(new PropertyValueFactory<>("tableFieldMail"));
        account.setCellValueFactory(new PropertyValueFactory<>("tableFieldAccount"));

        if(tableService.isHidePass()){
            password.setCellValueFactory(new PropertyValueFactory<>("tableFieldPasswordHiden"));
        }else{
            password.setCellValueFactory(new PropertyValueFactory<>("tableFieldPassword"));
        }

    }

    public void init() {
        tableService.refresh();
    }

    private void selectCells(ListChangeListener.Change<? extends TablePosition> c) {
        c.getList().forEach(tablePosition -> {
            tableService.selectCells();
        });
    }

    public void refresh(ObservableList<PandaAccount> inputList){
        super.setItems(inputList);
        super.refresh();
    }

    private void setBackgroundImage(String imagePath){
        File file = new File(imagePath);
        String localUrl = null;
        try {
            localUrl = file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        super.setStyle("-fx-background-image: url("+localUrl+");\n -fx-background-size: stretch;");
    }

}
