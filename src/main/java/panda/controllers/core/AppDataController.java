package panda.controllers.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.models.AppData;

import java.sql.SQLException;

public class AppDataController {

    private static final Logger logger = LoggerFactory.getLogger(AppDataController.class);
    private DatabaseController databaseController;

    private AppData appData;

    public AppDataController(DatabaseController databaseController) {
        this.databaseController = databaseController;
        refreshAppData();
    }

    public int checkAccess(String specialCheckWord) {
        int value = -1;
        try {
            value = databaseController.checkPass(specialCheckWord);
        } catch (SQLException pass) {
            logger.error("DataBase check pass exception", pass);
        }
        return value;
    }

    public void refreshAppData() {
        try {
            appData = databaseController.selectAppData();
        } catch (SQLException db) {
            logger.error("App Data getting error");
        }
        if (appData == null) {
            this.appData = new AppData();
        }
    }

    public String getPassTeamplate(){
        return appData.getPassGenPattern();
    }

    public AppData getAppData(){
        return appData;
    }


}
