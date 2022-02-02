package panda.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.PandaRoot;
import panda.views.PandaRootView;

public class StageManager {

    private static final Logger logger = LoggerFactory.getLogger(StageManager.class);

    private Stage rootStage;
    private Scene rootScene;

    private DataManager dm;
    private ViewServicesManager vm;

    public StageManager(Stage stage){
        this.rootStage = stage;
    }

    public void init(){
        logger.info("Initialization started");
            dm = new DataManager();
            vm = new ViewServicesManager(dm);

            PandaRootView pandaRootView = vm.init();
            rootScene = new Scene(pandaRootView, 900, 700);

            rootStage.setScene(rootScene);
            rootStage.show();
            rootStage.setOnCloseRequest(event -> logger.info("Panda closed"));
        logger.info("initialization completed successfully");
    }

    public void setTheme(String inputCssPath){
        String cssPath = "file:///" + "D:\\mProjects\\PasswordsAndAccountsV2.0\\src\\main\\resources\\styles\\style.css";
        cssPath = cssPath.replace("\\", "/");
        rootScene.getStylesheets().add(cssPath);
        // create menu. add controlsButtons. undecorate stage
    }



}
