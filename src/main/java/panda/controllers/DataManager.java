package panda.controllers;

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

    public void addAccount(Account account) {
        try {
            if (!databaseController.isPasswordExist(cryptionController.cryptIt(account.getPassword()))) {
                account.setPassword(cryptionController.cryptIt(account.getPassword())); // CRYPTION!
                databaseController.insertAccount(account);
            } else {
                viewServicesManager.alert("Warning!", "Same account already exist", "");
            }
        } catch (SQLException inserting) {
            logger.error("Error while inserting new account");
            inserting.printStackTrace();
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
            String uncriptedPass = cryptionController.deCriptIt(account.getPassword());
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

    public int checkAccess(String input) {
        cryptionController.init(new AesCrypt(), input);
        return appDataController.checkAccess(cryptionController.getSpecialCheckWord());
    }

    public AppData getAppData() {
        return appDataController.getAppData();
    }

    public ArrayList<PandaAccount> selectPandaAccounts() {
        ArrayList<PandaAccount> output = new ArrayList<>();
        try {
            ArrayList<PandaAccount> pandaAccountsList = databaseController.selectPandas(databaseController.getOwnerName(appDataController.getAppData().getOwner()));
            for (PandaAccount panda : pandaAccountsList) {
                panda.setTableFieldPassword(cryptionController.deCriptIt(panda.getTableFieldPassword()));
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
            while (databaseController.isPasswordExist(pass)) {
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

    public void reinitAccessPass(String inputNewPass, String inputOldPass) {
        ArrayList<Account> accounts = selectAccounts();
        ArrayList<Account> decrypted = new ArrayList<>();
        if (accounts.size() > 0) {
            for (Account account : accounts) {
                String decryptedPass = cryptionController.deCriptIt(account.getPassword());
                Account temp = account;
                temp.setPassword(decryptedPass);
                decrypted.add(temp);
            }
        }
        try {
            //TODO сука не рабоатет!!!!
            logger.info("inputNewPass=" + inputNewPass + " inputOldPass= " + inputOldPass);
            inputOldPass = cryptionController.cryptIt(cryptionController.getSpecialCheckWord());
            cryptionController.init(new AesCrypt(), inputNewPass);
            inputNewPass = cryptionController.cryptIt(cryptionController.getSpecialCheckWord());
            logger.info("inputNewPass=" + inputNewPass + " inputOldPass" + inputOldPass);
            databaseController.updateExistedAppPass(inputOldPass, inputNewPass);
        } catch (SQLException e) {
            logger.error("Error password replacing");
            viewServicesManager.alert("Error", "Password updating", "");
        }
        int access = checkAccess(inputNewPass);
            if(access==1){
                try{
                    databaseController.clear(inputNewPass);
                    for (Account account : decrypted) {
                        String cryptedPass = cryptionController.cryptIt(account.getPassword());
                        Account temp = account;
                        temp.setPassword(cryptedPass);
                        databaseController.insertAccount(temp);
                    }
                }catch (SQLException databaseUpdate){
                    logger.error("Database accounts update error");
                }
            }else{
                viewServicesManager.alert("Access info", "Access Denied", "Passwords missmatch");
            }
    }

}
