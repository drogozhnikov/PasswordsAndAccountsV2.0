package panda.views.elements;

import javafx.collections.ListChangeListener;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.cell.PropertyValueFactory;
import panda.controllers.views.TableService;
import panda.models.PandaAccount;

import java.util.GregorianCalendar;

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

        TableColumn<PandaAccount, GregorianCalendar> updated = new TableColumn<>("Updated");
        password.setStyle("-fx-alignment: CENTER;");

        super.getColumns().addAll(name, mail, account, password, updated);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        account.setCellValueFactory(new PropertyValueFactory<>("account"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        updated.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void selectCells(ListChangeListener.Change<? extends TablePosition> c) {
        c.getList().forEach(tablePosition -> {
            tableService.selectCells();
        });
    }

}
