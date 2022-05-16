package panda.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.controllers.core.*;
import panda.models.Account;
import panda.models.AppData;
import panda.models.PandaAccount;
import panda.utils.PathFinder;
import panda.utils.cryption.AesCrypt;
import panda.utils.io.xml.XMLio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DataManager {

    private static final Logger logger = LoggerFactory.getLogger(DataManager.class);

    private AppDataController appDataController;
    private BackupController backupController;
    private CryptionController cryptionController;
    private DatabaseController databaseController;
    private PropertiesController propertiesController;

    private PathFinder pathFinder;

    public DataManager(String modulePath) {
        pathFinder = new PathFinder(modulePath);
        try {
            databaseController = new DatabaseController(pathFinder.findPath("panda.db"));
            propertiesController = new PropertiesController(pathFinder.findPath("Properties.properties"));
            backupController = new BackupController("backups", new XMLio());
            cryptionController = new CryptionController(new AesCrypt());
            appDataController = new AppDataController(databaseController);
        } catch (SQLException e) {
            logger.error("DataBase init exception", e);
        } catch (Exception e) {
            logger.error("File not found exception", e);
        }
    }

    public void addAccount(Account account){
        try{
            databaseController.insertAccount(account);

        }catch (SQLException inserting){
            logger.error("Error while inserting new account");
            inserting.printStackTrace();
        }
    }
    public void updateAccount(Account account){
        try{
            databaseController.updateFullAccount(account);
        }catch (SQLException inserting){
            logger.error("Error while updating account");
        }
    }

    public String findPath(String fileName) {
        return pathFinder.findPath(fileName);
    }

    public int checkAccess(String input) {
        String specialCheckWord = cryptionController.getSpecialCheckWord(new AesCrypt(), input);
        return  appDataController.checkAccess(specialCheckWord);
    }

    public AppData getAppData(){
        return appDataController.getAppData();
    }

    public ArrayList<PandaAccount> selectPandaAccounts(){
        ArrayList<PandaAccount> output = null;
        try{
            output = databaseController.selectPandas(databaseController.getOwnerName(appDataController.getAppData().getOwner()));
        }catch (SQLException selectAll){
            logger.error("Select All Error");
        }
        return output;
    }

    public int validatePassword(String inputPassword){
        String crypted = cryptionController.cryptIt(inputPassword);
        int result = 0;
        try{
            result = databaseController.checkPass(crypted);
        }catch (SQLException passcheck){
            logger.error("DB pass check Error ");
        }
        return result;
    }

    public ArrayList<String> getOwnerList(){
        Set<String> ownersList = new HashSet<>();
        try{
            ownersList = new HashSet<>(databaseController.selectOwnerList());
        }catch (SQLException ownerList){
            logger.error("DB owners get error");
            ownerList.printStackTrace();
        }
        return new ArrayList<>(ownersList);
    }




}
