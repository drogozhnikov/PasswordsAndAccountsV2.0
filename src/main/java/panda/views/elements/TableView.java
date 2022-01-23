package panda.views.elements;

import javafx.collections.ListChangeListener;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.cell.PropertyValueFactory;
import panda.models.account.Account;

public class TableView extends javafx.scene.control.TableView<Account> {

    public TableView() {
        super.setColumnResizePolicy(javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY);
        super.getSelectionModel().setCellSelectionEnabled(true);
        super.getSelectionModel().getSelectedCells().addListener(this::selectCells);
        super.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<Account, String> name = new TableColumn<>("Name");
        name.setStyle("-fx-alignment: CENTER;");

        TableColumn<Account, String> mail = new TableColumn<>("Mail");
        mail.setStyle("-fx-alignment: CENTER;");

        TableColumn<Account, String> account = new TableColumn<>("Account");
        account.setStyle("-fx-alignment: CENTER;");

        TableColumn<Account, String> password = new TableColumn<>("Password");
        password.setStyle("-fx-alignment: CENTER;");

        super.getColumns().addAll(name, mail, account, password);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        account.setCellValueFactory(new PropertyValueFactory<>("account"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

    }

    private void selectCells(ListChangeListener.Change<? extends TablePosition> c) {
        c.getList().forEach(tablePosition -> {
            //TODO encryption & copy to buffer
        });
    }
}
