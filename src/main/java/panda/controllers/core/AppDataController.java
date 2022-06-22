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

    public Owner getSelectedOwner(){
        return appData.getSelectedOwner();
    }

    public void setResolution(int height, int width) {

        int infelicityH = 39;
        int infelicityW = 16;

        appData.setScreenHeight(height - infelicityH);
        appData.setScreenWidth(width - infelicityW);

        updateAppData();

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
