package panda.views.elements;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import panda.controllers.views.MenuService;

public class MenuView extends MenuBar {

    private MenuService menuService;

    private Menu fileMenu = new Menu("File");
    private Menu editMenu = new Menu("Edit");
    private Menu helpMenu = new Menu("Help");

    private MenuItem optionsMenuItem = new MenuItem("Options");
    private MenuItem saveMenuItem = new MenuItem("Save");
    private MenuItem loadMenuItem = new MenuItem("Load");

    private SeparatorMenuItem fileMenuSeparator = new SeparatorMenuItem();
    private SeparatorMenuItem editMenuSeparator = new SeparatorMenuItem();

    private MenuItem exitMenuItem = new MenuItem("Exit");


    public MenuView(MenuService menuService) {
        this.menuService = menuService;

        initSaveMenuItem();
        initLoadMenuItem();
        initOptionsMenuItem();
        initExitMenuItem();

        fileMenu.getItems().addAll(optionsMenuItem, fileMenuSeparator, exitMenuItem);
        editMenu.getItems().addAll(saveMenuItem, loadMenuItem);

        super.getMenus().addAll(fileMenu, editMenu, helpMenu);
    }

    private void initOptionsMenuItem() {
        optionsMenuItem.setOnAction(event -> {
            menuService.optionAction();
        });
    }

    private void initSaveMenuItem() {
        saveMenuItem.setOnAction(event -> {
            menuService.saveAction();
        });
    }

    private void initLoadMenuItem() {
        loadMenuItem.setOnAction(event -> {
            menuService.loadAction();
        });
    }

    private void initExitMenuItem() {
        exitMenuItem.setOnAction(event -> {
            menuService.exitAction();
        });
    }

}
