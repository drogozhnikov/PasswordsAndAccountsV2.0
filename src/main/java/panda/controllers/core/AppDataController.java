package panda.controllers.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.models.AppData;
import panda.models.Owner;

import java.util.HashMap;

public class AppDataController {

    private static final Logger logger = LoggerFactory.getLogger(AppDataController.class);
    private DatabaseController databaseController;

    private AppData appData;

    public AppDataController(DatabaseController databaseController) {
        this.databaseController = databaseController;
        refreshAppData();
    }

//    public int checkAccess(StringBuilder inputPassword) {
//        int value = -1;
//        try {
//            value = databaseController.checkPass(inputPassword);
//        } catch (SQLException pass) {
//            logger.error("DataBase check pass exception", pass);
//        }
//        return value;
//    }

    public void refreshAppData() {
        appData = databaseController.selectAppData();
        if (appData == null) {
            this.appData = new AppData();
        }
    }

    public String getPassTeamplate() {
        return appData.getPassGenPattern();
    }

    public AppData getAppData() {
        return appData;
    }

    public void setResolution(HashMap<String, Double> input) {

        int infelicityH = 39;
        int infelicityW = 16;

        appData.setScreenHeight(input.get("height").intValue() - infelicityH);
        appData.setScreenWidth(input.get("width").intValue() - infelicityW);

        logger.info("Updating Resolution to: " + appData.getScreenWidth() + "x" + appData.getScreenHeight());
    }

    public void setLastSelectedOwner(Owner owner) {
        appData.setSelectedOwner(owner);
    }

    public Owner getLastSelectedOwner() {
        return appData.getSelectedOwner();
    }

    public void updateAppData() {
        databaseController.updateAppData(appData);
    }


}
