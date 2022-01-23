package panda;

import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PandaRoot extends Application {

    private static final Logger logger = LoggerFactory.getLogger(PandaRoot.class);

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        try {
            logger.info("Panda start");
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> logger.info("Panda closed"));
        } catch (Exception e) {
            logger.info("Panda Error", e);
            primaryStage.close();
        }
    }
}
