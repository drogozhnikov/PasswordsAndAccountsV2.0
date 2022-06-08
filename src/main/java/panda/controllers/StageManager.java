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
import panda.views.elements.options.OptionsView;

import java.util.HashMap;
import java.util.Map;

public class StageManager {

    private static final Logger logger = LoggerFactory.getLogger(StageManager.class);

    private Stage rootStage;
    private Scene rootHelloScene;
    private Scene rootPandaScene;
    private Scene rootOptionsScene;

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

        dm.initViewServiceManager(vm);

        initHelloScene(dm.getAppData().getHelloScreenWidth(), dm.getAppData().getHelloScreenHeight());

//        setCSS(rootPandaScene);

        showHelloScene();
        rootStage.setOnCloseRequest(event -> {
            onExitActions();
            logger.info("Panda closed");
        });
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

    public void initPandaScene() {
        PandaRootView pandaRootView = vm.init();
        rootPandaScene = new Scene(pandaRootView, dm.getAppData().getScreenWidth(), dm.getAppData().getScreenHeight());
    }

    public void initOptionsScene() {
        if(rootOptionsScene == null){
            OptionsView optionsView = vm.getOptionsView();
            rootOptionsScene = new Scene(optionsView, dm.getAppData().getScreenWidth(), dm.getAppData().getScreenHeight());
        }
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

    public void showOptionsScene(){
        rootStage.close();
        rootStage = new Stage();
        rootStage.initStyle(StageStyle.DECORATED);
        rootStage.setScene(rootOptionsScene);
        rootStage.show();
    }

    private void setCSS(Scene scene) {
        String cssPath = "file:///" + "D:\\mProjects\\PasswordsAndAccountsV2.0\\src\\main\\resources\\styles\\style1.css";
        cssPath = cssPath.replace("\\", "/");
        scene.getStylesheets().add(cssPath);
        // create menu. add controlsButtons. undecorate stage
    }

    public HashMap<String, Double> getStageDimension(){
       HashMap<String, Double> dimension = new HashMap<>();
        dimension.put("height", rootStage.getHeight());
        dimension.put("width", rootStage.getWidth());
        return dimension;
    }

    private void onExitActions(){
        dm.onExitActions();
    }

}
