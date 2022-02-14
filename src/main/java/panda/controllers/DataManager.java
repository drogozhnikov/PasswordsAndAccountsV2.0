package panda.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.controllers.core.*;
import panda.models.AppData;
import panda.utils.PathFinder;
import panda.utils.cryption.AesCrypt;
import panda.utils.io.xml.XMLio;

import java.sql.SQLException;

public class DataManager {

    private static final Logger logger = LoggerFactory.getLogger(DataManager.class);

    private AppDataController appDataController;
    private BackupController backupController;
    private CryptionController cryptionController;
    private DatabaseController databaseController;
    private PropertiesController propertiesController;

    private PathFinder pathFinder;
    private AppData appData;

    public DataManager(String modulePath) {
        pathFinder = new PathFinder(modulePath);
        try {
            databaseController = new DatabaseController(pathFinder.findPath("panda.db"));
            propertiesController = new PropertiesController(pathFinder.findPath("Properties.properties"));
            backupController = new BackupController("backups", new XMLio());
//            cryptionController = new CryptionController(new AesCrypt());

        } catch (SQLException e) {
           logger.error("DataBase init exception", e);
        } catch (Exception e){
            logger.error("File not found exception", e);
        }
    }


}
