package panda;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.controllers.DataManager;
import panda.controllers.ViewServicesManager;

public class PandaRoot extends Application {

    private static final Logger logger = LoggerFactory.getLogger(PandaRoot.class);

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        try {
            logger.info("Panda start");
            DataManager dataManager = new DataManager();
            ViewServicesManager viewServicesManager = new ViewServicesManager(dataManager);
            viewServicesManager.initServices();
            viewServicesManager.initViews();
            viewServicesManager.initRootPositions();
            Scene panda = new Scene(viewServicesManager.getRoot(), 900, 700);
            setCSS(panda);
            primaryStage.setScene(panda);
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> logger.info("Panda closed"));
        } catch (Exception e) {
            logger.info("Panda Error", e);
            primaryStage.close();
        }
    }

    private void setCSS(Scene scene) {
        String cssPath = "file:///" + "D:\\mProjects\\PasswordsAndAccountsV2.0\\src\\main\\resources\\styles\\style.css";
        cssPath = cssPath.replace("\\", "/");
        scene.getStylesheets().add(cssPath);
        // create menu. add controlsButtons. undecorate stage
    }
}
