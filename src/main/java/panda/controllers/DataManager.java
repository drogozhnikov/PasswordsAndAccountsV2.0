package panda.controllers;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.controllers.core.*;
import panda.models.Account;
import panda.models.AppData;
import panda.models.PandaAccount;
import panda.utils.PasswordGenerator;
import panda.utils.PathFinder;
import panda.utils.Utils;
import panda.utils.cryption.AesCrypt;
import panda.utils.io.xml.XMLio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DataManager {

    private static final Logger logger = LoggerFactory.getLogger(DataManager.class);

    private ViewServicesManager viewServicesManager;

    private AppDataController appDataController;
    private BackupController backupController;
    private CryptionController cryptionController;
    private DatabaseController databaseController;
    private PropertiesController propertiesController;
    private PasswordGenerator passwordGenerator;

    private PathFinder pathFinder;

    public DataManager(String modulePath) {
        pathFinder = new PathFinder(modulePath);
        try {
            databaseController = new DatabaseController(pathFinder.findPath("panda.db"));
            propertiesController = new PropertiesController(pathFinder.findPath("Properties.properties"));
            backupController = new BackupController("backups", new XMLio());
            cryptionController = new CryptionController(new AesCrypt());
            appDataController = new AppDataController(databaseController);
            passwordGenerator = new PasswordGenerator();
        } catch (SQLException e) {
            logger.error("DataBase init exception", e);
        } catch (Exception e) {
            logger.error("File not found exception", e);
        }
    }

    public void initViewServiceManager(ViewServicesManager viewServicesManager) {
        this.viewServicesManager = viewServicesManager;
    }

    public void insertAccount(Account account) {
        try {
            if (!databaseController.isPasswordExist(cryptionController.cryptIt(account.getPassword()))) {
                account.setPassword(cryptionController.cryptIt(new StringBuilder(account.getPassword()))); // CRYPTION!
                databaseController.insertAccount(account);
            } else {
                viewServicesManager.alert(Alert.AlertType.WARNING,
                        "Same account already exist",
                        "");
            }
        } catch (SQLException inserting) {
            logger.error("Error while inserting new account");
            viewServicesManager.alert(Alert.AlertType.WARNING,
                    "Same account already exist",
                    "");
        }
    }

    private void insertAndCryptList(ArrayList<Account> inputList) {
        try {
            for (Account account : inputList) {
                account.setPassword(cryptionController.cryptIt(account.getPassword()));
                databaseController.insertAccount(account);
            }
        } catch (SQLException insert) {
            logger.error("Error while inserting account");
            viewServicesManager.alert(Alert.AlertType.ERROR,
                    "Error while inserting account",
                    "");
        }
    }

    public void updateAccount(Account account) {
        try {
            databaseController.updateFullAccount(account);
        } catch (SQLException updating) {
            logger.error("Error while updating account");
            updating.printStackTrace();
        }
    }

    public void deleteAccount(ArrayList<Integer> deleteListId) {
        try {
            if (deleteListId != null && deleteListId.size() > 0) {
                for (Integer id : deleteListId) {
                    databaseController.deleteAccount(id);
                }
            }
        } catch (SQLException | ParseException delete) {
            logger.error("Can't delete account");
        }
    }

    public Account getAccountById(int id) {
        Account account = null;
        try {
            account = databaseController.selectAccountById(id);
            StringBuilder uncriptedPass = cryptionController.deCriptIt(account.getPassword());
            account.setPassword(uncriptedPass);
        } catch (SQLException | ParseException e) {
            logger.error("Find Account by id Error");
            e.printStackTrace();
        }
        return account;
    }

    public ArrayList<Account> selectAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            accounts = databaseController.selectAll();
        } catch (SQLException | ParseException selectAll) {
            logger.error("Error while selecting accounts");
        }
        return accounts;
    }

    public String findPath(String fileName) {
        return pathFinder.findPath(fileName);
    }

    public int checkAccess(StringBuilder input) {
        cryptionController.init(new AesCrypt(), input);
        return appDataController.checkAccess(cryptionController.encryptPassPhrase());
    }

    public AppData getAppData() {
        return appDataController.getAppData();
    }

    public ArrayList<PandaAccount> selectPandaAccounts() {
        ArrayList<PandaAccount> output = new ArrayList<>();
        try {
            ArrayList<PandaAccount> pandaAccountsList = databaseController.selectPandas(
                    databaseController.getOwnerName(appDataController.getAppData().getOwner()));
            for (PandaAccount panda : pandaAccountsList) {
                panda.setTableFieldPassword(cryptionController.deCriptIt(
                        new StringBuilder(panda.getTableFieldPassword())).toString());
                output.add(panda);
            }
        } catch (SQLException selectAll) {
            logger.error("Select All Error");
        }
        return output;
    }

    public String generatePassword() {
        try {
            String pass = passwordGenerator.generatePassword(appDataController.getPassTeamplate());
            while (databaseController.isPasswordExist(new StringBuilder(pass))) {
                pass = passwordGenerator.generatePassword(appDataController.getPassTeamplate());
            }
            return pass;
        } catch (SQLException check) {
            logger.error("Can't Check new password");
        }
        return "";
    }

    public ArrayList<String> getOwnerList() {
        Set<String> ownersList = new HashSet<>();
        try {
            ownersList = new HashSet<>(databaseController.selectOwnerList());
        } catch (SQLException ownerList) {
            logger.error("DB owners get error");
            ownerList.printStackTrace();
        }
        return new ArrayList<>(ownersList);
    }

    public boolean isHidePass() {
        return appDataController.getAppData().isHidePass();
    }

    public void setPropertyHidePass(boolean value) {
        appDataController.getAppData().setHidePass(value);
    }

    public void startLink(String link) {
        try {
            Utils.startLink(link);
        } catch (URISyntaxException | IOException syntax) {
            logger.error("Error while trying startLink");
        }
    }

    public void saveCurrentResolution() {
        HashMap<String, Double> resolution = viewServicesManager.getCurrentResolution();
        appDataController.setResolution(resolution);

        try {
            databaseController.updateAppDataResolution(
                    appDataController.getAppData().getScreenWidth(),
                    appDataController.getAppData().getScreenHeight());
            logger.info("Resolution update succesfully");
        } catch (SQLException e) {
            logger.error("Error while updating resolution");
            e.printStackTrace();
        }
    }

    public void restoreResolution() {
        final int width = 900;
        final int height = 600;
        try {
            databaseController.updateAppDataResolution(width, height);
            logger.info("Resolution update succesfully");
        } catch (SQLException e) {
            logger.error("Error while updating resolution");
            e.printStackTrace();
        }
    }

    public void reinitAccessPass(StringBuilder inputNewPass) {
        ArrayList<Account> decryptedAccounts = cryptionController.getDecryptedAccounts(selectAccounts());
        int updatePassStatus = updatePass(inputNewPass);
        if (decryptedAccounts.size() > 0) {
            if (updatePassStatus == 1) {
                clearBase(cryptionController.getEncryptedInput(inputNewPass));
                cryptionController.init(new AesCrypt(), inputNewPass);
                insertAndCryptList(decryptedAccounts);

                logger.info("Password changing and recrypting accounts successfull");
                viewServicesManager.alert(Alert.AlertType.INFORMATION,
                        "Password changing and recrypting accounts successfull",
                        "");
            } else {
                logger.warn("Password changing error");
                viewServicesManager.alert(Alert.AlertType.INFORMATION,
                        "Password changing error",
                        "");
            }
        } else {
            if (updatePassStatus == 1) {
                viewServicesManager.alert(Alert.AlertType.INFORMATION, "Update password successfull", "");
            } else {
                viewServicesManager.alert(Alert.AlertType.WARNING, "Update password error", "");
            }
        }
    }

    private int updatePass(StringBuilder inputNewPass) {
        int result = -1;
        try {
            StringBuilder tempInputPass = cryptionController.getEncryptedInput(inputNewPass);
            databaseController.updatePass(tempInputPass);
            result = appDataController.checkAccess(tempInputPass);
        } catch (SQLException update) {
            logger.warn("Update pass exception");
        }
        return result;
    }

    public void clearBase(StringBuilder input) {
        int result = checkAccess(input);
        if (result == 1) {
            try {
                //TODO backupDatabaseBeforeClear
                databaseController.clear();
                logger.error("Clear base successfull");
            } catch (SQLException clear) {
                logger.error("Clear base exception");
            }
        } else {
            logger.warn("incorrect access. clear base");
        }
    }

}
