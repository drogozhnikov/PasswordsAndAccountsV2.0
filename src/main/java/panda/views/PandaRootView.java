package panda.views;

import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

public class PandaRootView extends BorderPane {

    private BorderPane rootView = new BorderPane();

    public PandaRootView() {
        super.setCenter(rootView);
    }

    public void setMenuPane(MenuBar menuView) {
        super.setTop(menuView);
    }

    public void setRootTop(Node inputNode) {
        rootView.setTop(inputNode);
    }

    public void setRootCenter(Node inputNode) {
        rootView.setCenter(inputNode);
    }

    public void setRootBottom(Node inputNode) {
        rootView.setBottom(inputNode);
    }

    public void setInfoPane() {

    }
}
