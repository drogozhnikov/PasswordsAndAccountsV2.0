package panda;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.controllers.DataManager;
import panda.controllers.StageManager;
import panda.controllers.ViewServicesManager;
import panda.controllers.core.DatabaseController;

import java.sql.SQLException;

public class PandaRoot extends Application {

    private static final Logger logger = LoggerFactory.getLogger(PandaRoot.class);

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
//        try {
//            StageManager stageManager = new StageManager(primaryStage);
//            stageManager.init();
//            logger.info("Panda start");
//        } catch (Exception e) {
//            logger.info("Panda Error", e);
//            primaryStage.close();
//        }

        test();
    }

    private static void test(){
        try {
            DatabaseController databaseController = new DatabaseController("D:\\mProjects\\PasswordsAndAccountsV2.0\\src\\main\\resources\\panda.db");
//            databaseController.selectPandas("ALL");
//            databaseController.isPasswordExist("JOJOPASS2");
            System.out.println(databaseController.getOnwerIndex("test2"));
            databaseController.search("fi", "all");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
