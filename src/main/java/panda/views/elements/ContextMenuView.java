package panda.views.elements;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import panda.controllers.views.ContextMenuService;

public class ContextMenuView extends ContextMenu {

    private MenuItem contextMenuitemSave = new MenuItem(" Save selected ");
    private MenuItem contextMenuItemDelete = new MenuItem(" Delete ");
    private MenuItem contextMenuItemUpdate = new MenuItem(" Update ");
    private MenuItem startLinkMenuItem = new MenuItem(" StartLink ");
    private MenuItem descriptionMenuItem = new MenuItem(" ShowDescription ");

    private ContextMenuService contextMenuService;

    public ContextMenuView(ContextMenuService contextMenuService) {
        this.contextMenuService = contextMenuService;
        initContext();
    }

    private void initContext() {
        contextMenuitemSave.setOnAction(event -> {
            //TODO contextMenuitemSave
        });

        contextMenuItemDelete.setOnAction(event -> {
            contextMenuService.delete();
        });

        contextMenuItemUpdate.setOnAction(event -> {
            contextMenuService.update();
        });

        startLinkMenuItem.setOnAction(event -> {
            contextMenuService.startLink();
        });

        descriptionMenuItem.setOnAction(event -> {
            //TODO descriptionMenuItem
        });

        super.getItems().addAll(contextMenuItemUpdate, contextMenuitemSave, startLinkMenuItem, descriptionMenuItem, contextMenuItemDelete);
    }
}
