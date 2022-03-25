package panda.controllers;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.views.PandaRootView;
import panda.views.elements.HelloPandaView;

public class StageManager {

    private static final Logger logger = LoggerFactory.getLogger(StageManager.class);

    private Stage rootStage;
    private Scene rootHelloScene;
    private Scene rootPandaScene;

    private DataManager dm;
    private ViewServicesManager vm;

    private double xOffset;
    private double yOffset;

    public StageManager(Stage stage) {
        this.rootStage = stage;
    }

    public void init() {
        dm = new DataManager("//src");
        vm = new ViewServicesManager(this, dm);

        //TODO create function to calculate or save widths and heights
        initHelloScene(500, 100);
        initPandaScene(900, 700);

//        setCSS(rootPandaScene);

        showHelloScene();
        rootStage.setOnCloseRequest(event -> logger.info("Panda closed"));
        logger.info("initialization completed successfully");
    }

    private void initHelloScene(int width, int height) {
        HelloPandaView helloPandaView = vm.getHelloPandaView();
        rootHelloScene = new Scene(helloPandaView, width, height);
        rootHelloScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = rootStage.getX() - event.getScreenX();
                yOffset = rootStage.getY() - event.getScreenY();
            }
        });
        rootHelloScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rootStage.setX(event.getScreenX() + xOffset);
                rootStage.setY(event.getScreenY() + yOffset);
            }
        });
    }

    public void initPandaScene(int width, int height) {
        PandaRootView pandaRootView = vm.init();
        rootPandaScene = new Scene(pandaRootView, width, height);
    }

    public void showPandaScene(){
        rootStage.close();
        rootStage = new Stage();
        rootStage.initStyle(StageStyle.DECORATED);
        rootStage.setScene(rootPandaScene);
        rootStage.show();
    }

    public void showHelloScene(){
        rootStage.close();
        rootStage = new Stage();
        rootStage.initStyle(StageStyle.UNDECORATED);
        rootStage.setScene(rootHelloScene);
        rootStage.show();
    }

    private void setCSS(Scene scene) {
        String cssPath = "file:///" + "D:\\mProjects\\PasswordsAndAccountsV2.0\\src\\main\\resources\\styles\\style.css";
        cssPath = cssPath.replace("\\", "/");
        scene.getStylesheets().add(cssPath);
        // create menu. add controlsButtons. undecorate stage
    }
}
