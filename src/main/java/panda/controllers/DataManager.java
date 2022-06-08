package panda.controllers;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.controllers.core.*;
import panda.models.Account;
import panda.models.AppData;
import panda.models.Owner;
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
            StringBuilder uncriptedPass = cryptionController.deCryptIt(account.getPassword());
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

    public boolean checkAccess(StringBuilder cryptedInput) {
        try {
            return databaseController.checkPass(cryptedInput);
        } catch (SQLException acess) {
            logger.error("DataBase check pass exception");
        }
        return false;
    }

    public boolean initCheckAccess(StringBuilder input) {
        try {
            cryptionController.init(new AesCrypt(), input);
            return databaseController.checkPass(cryptionController.getEncryptedInput(input));
        } catch (SQLException acess) {
            logger.error("DataBase check pass exception");
        }
        return false;
    }

    public AppData getAppData() {
        return appDataController.getAppData();
    }

    public ArrayList<PandaAccount> selectPandaAccounts() {
        ArrayList<PandaAccount> output = new ArrayList<>();
        try {
            ArrayList<PandaAccount> pandaAccountsList = databaseController.selectPandas(
                    appDataController.getLastSelectedOwner().getName()
            );
            for (PandaAccount panda : pandaAccountsList) {
                panda.setTableFieldPassword(cryptionController.deCryptIt(
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

    public ArrayList<Owner> getOwnerList() {
        Set<Owner> ownersList = new HashSet<>();
        try {
            ownersList = new HashSet<Owner>(databaseController.selectOwnerList());
        } catch (SQLException ownerList) {
            logger.error("DB owners get error");
        }
        return new ArrayList<Owner>(ownersList);
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

    public void reinitAccessPass(StringBuilder inputNewPass, StringBuilder inputOldPass) {
        ArrayList<Account> backupDbAccounts = selectAccounts();
        ArrayList<Account> databaseAccounts = selectAccounts();
        cryptionController.decryptAccounts(databaseAccounts);
        final StringBuilder cryptedNewPass = cryptionController.getEncryptedInput(inputNewPass);
        final StringBuilder cryptedOldPass = cryptionController.getEncryptedInput(inputOldPass);

        if (checkAccess(cryptedOldPass)) {
            try {
                if (updatePass(cryptedNewPass, cryptedOldPass)) {
                    clearBase(inputNewPass);
                    initCheckAccess(inputNewPass);
                    if (checkAccess(cryptedNewPass) && backupDbAccounts.size() > 0) {
                        for (Account account : databaseAccounts) {
                            account.setPassword(cryptionController.cryptIt(account.getPassword()));
                            databaseController.insertAccount(account);
                        }
                        viewServicesManager.refresh();
                    }
                }
            } catch (SQLException insert) {
                restore(cryptedOldPass, backupDbAccounts);
                logger.error("Database insert expeption while reencrypting");
            }
        } else {
            logger.warn("Access to change password denied");
            viewServicesManager.alert(Alert.AlertType.WARNING, "Access to change password denied", "");
        }

        if (backupDbAccounts.size() > 0) {
            String backup = backupDbAccounts.get(0).getName();
            String updated = backupDbAccounts.get(0).getName();

            System.out.println("Backup: " + backup + " pass: " + backupDbAccounts.get(0).getPassword());
            System.out.println("Updated: " + updated + " pass: " + selectAccounts().get(0).getPassword());
        }
        System.out.println("Old pass: " + inputOldPass + " Crypted: " + cryptedOldPass + " " + checkAccess(cryptedOldPass));
        System.out.println("New pass: " + inputNewPass + " Crypted: " + cryptedNewPass + " " + checkAccess(cryptedNewPass));

    }

    private void restore(StringBuilder cryptedPass, ArrayList<Account> cryptedAccountsList) {
        try {
            for (Account account : cryptedAccountsList) {
                databaseController.insertAccount(account);
            }
            //TODO resrote pass
        } catch (SQLException insert) {
            logger.error("Restore db buffer exception");
        }
    }

    private boolean updatePass(StringBuilder cryptedNewPass, StringBuilder cryptedOldPass) {
        try {
            logger.info("updatePass Sucessfull");
            return databaseController.updatePass(cryptedNewPass, cryptedOldPass);
        } catch (SQLException passUpdate) {
            logger.error("Password update Error");
        }
        return false;
    }

    public void clearBase(StringBuilder unCryptedInput) {
        final StringBuilder cryptedPass = cryptionController.getEncryptedInput(unCryptedInput);
        if (checkAccess(cryptedPass)) {
            try {
                //TODO backupDatabaseBeforeClear
                databaseController.clear();
                logger.error("Clear base successfull");
            } catch (SQLException clear) {
                logger.error("Clear base exception");
            }
        } else {
            logger.warn("incorrect access. Base cannot be cleared");
        }
    }

    public void setLastSelectedOwner(Owner owner) {
        appDataController.setLastSelectedOwner(owner);
    }

    public Owner getSelectedOwner() {
        return appDataController.getAppData().getSelectedOwner();
    }

    public ArrayList<PandaAccount> search(String value, Owner owner){
        ArrayList<PandaAccount> foundedList = new ArrayList<>();
        if(owner==null){
            owner = new Owner();
        }
        try{
            foundedList = databaseController.search(value, owner.getName());
        }catch (SQLException search){
            logger.error("Search Exception");
        }
        return foundedList;
    }

}
