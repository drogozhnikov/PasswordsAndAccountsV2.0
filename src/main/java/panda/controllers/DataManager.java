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
import panda.utils.io.FileIO;
import panda.utils.io.xml.XMLio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
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
            backupController = new BackupController("backups");
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
        account.setPassword(cryptionController.cryptIt(new StringBuilder(account.getPassword()))); // CRYPTION!
        databaseController.insertAccount(account);
    }

    public boolean isAccountExist(Account account) {
        if (databaseController.isPasswordExist(cryptionController.cryptIt(account.getPassword()))) {
            return true;
        }
        return false;
    }

    public void updateAccount(Account account) {
        databaseController.updateFullAccount(account);
    }

    public void deleteAccount(ArrayList<Integer> deleteListId) {
        if (deleteListId != null && deleteListId.size() > 0) {
            for (Integer id : deleteListId) {
                databaseController.deleteAccount(id);
            }
        }
    }

    public Account getAccountById(int id) {
        Account account = null;
        account = databaseController.selectAccountById(id);
        StringBuilder uncriptedPass = cryptionController.deCryptIt(account.getPassword());
        account.setPassword(uncriptedPass);
        return account;
    }

    public ArrayList<Account> selectAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts = databaseController.selectAll();
        return accounts;
    }

    public String findPath(String fileName) {
        return pathFinder.findPath(fileName);
    }

    public boolean checkAccess(StringBuilder cryptedInput) {
        return databaseController.checkPass(cryptedInput);
    }

    public boolean validateAccess(StringBuilder input) {
        StringBuilder temp = new CryptionController().getEncryptedInput(input);
        return databaseController.checkPass(temp);
    }

    public boolean initCheckAccess(StringBuilder input) {
        cryptionController.init(new AesCrypt(), input);
        return databaseController.checkPass(cryptionController.getEncryptedInput(input));
    }

    public AppData getAppData() {
        return appDataController.getAppData();
    }

    public ArrayList<PandaAccount> selectPandaAccounts() {
        ArrayList<PandaAccount> output = new ArrayList<>();
        ArrayList<PandaAccount> pandaAccountsList = databaseController.selectPandas(
                appDataController.getLastSelectedOwner().getName()
        );
        for (PandaAccount panda : pandaAccountsList) {
            panda.setTableFieldPassword(cryptionController.deCryptIt(
                    new StringBuilder(panda.getTableFieldPassword())).toString());
            output.add(panda);
        }
        return output;
    }

    public String generatePassword() {
        String pass = passwordGenerator.generatePassword(appDataController.getPassTeamplate());
        while (databaseController.isPasswordExist(new StringBuilder(pass))) {
            pass = passwordGenerator.generatePassword(appDataController.getPassTeamplate());
        }
        return pass;
    }

    public ArrayList<Owner> getOwnerList() {
        Set<Owner> ownersList = new HashSet<>();
        ownersList = new HashSet<Owner>(databaseController.selectOwnerList());
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
        databaseController.updateAppDataResolution(
                appDataController.getAppData().getScreenWidth(),
                appDataController.getAppData().getScreenHeight());
        logger.info("Resolution update succesfully");
    }

    public void restoreResolution() {
        final int width = 900;
        final int height = 600;
        databaseController.updateAppDataResolution(width, height);
        logger.info("Resolution update succesfully");
    }

    public void reinitAccessPass(StringBuilder inputNewPass, StringBuilder inputOldPass) {
        ArrayList<Account> backupDbAccounts = selectAccounts();
        ArrayList<Account> databaseAccounts = selectAccounts();
        cryptionController.decryptAccounts(databaseAccounts);
        final StringBuilder cryptedNewPass = cryptionController.getEncryptedInput(inputNewPass);
        final StringBuilder cryptedOldPass = cryptionController.getEncryptedInput(inputOldPass);

        if (checkAccess(cryptedOldPass)) {
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
        for (Account account : cryptedAccountsList) {
            databaseController.insertAccount(account);
        }
        //TODO resrote pass
    }

    private boolean updatePass(StringBuilder cryptedNewPass, StringBuilder cryptedOldPass) {
        boolean isSuccess = databaseController.updatePass(cryptedNewPass, cryptedOldPass);
        if (isSuccess) {
            logger.info("updatePass Sucessfull");
            return isSuccess;
        } else {
            return false;
        }
    }

    public void clearBase(StringBuilder unCryptedInput) {
        final StringBuilder cryptedPass = cryptionController.getEncryptedInput(unCryptedInput);
        if (checkAccess(cryptedPass)) {
            //TODO backupDatabaseBeforeClear
            databaseController.clear();
            logger.error("Clear base successfull");
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

    public ArrayList<PandaAccount> search(String value, Owner owner) {
        ArrayList<PandaAccount> foundedList = new ArrayList<>();
        if (owner == null) {
            owner = new Owner();
        }
        foundedList = databaseController.search(value, owner.getName());
        return foundedList;
    }

    public void onExitActions() {
        appDataController.updateAppData();
    }

    public void save(XMLio fileio, String filePath) {
        ArrayList<Integer> temp = viewServicesManager.getIdLastSelectedAccounts();
        if(temp != null && temp.size()>0){
            ArrayList<Account> accountsList = new ArrayList<>();
                for(Integer id : temp){
                    Account account = databaseController.selectAccountById(id);
                    account.setPassword(cryptionController.deCryptIt(account.getPassword()));
                    accountsList.add(account);
                }
            backupController.init(fileio);
            backupController.saveToDirectory(filePath, accountsList);
        }
    }

    public void load(XMLio fileio, String filePath) {
        backupController.init(fileio);
        ArrayList<Account> loadedAccounts = backupController.load(filePath);
        ArrayList<Account> existedPasswords = new ArrayList<>();
        int insertedCount = 0;
        for(Account account: loadedAccounts){
            if(databaseController.isPasswordExist(cryptionController.cryptIt(account.getPassword()))){
                existedPasswords.add(account);
            }else{
                insertAccount(account);
                insertedCount++;
            }
        }
        String resultText = "Input:" + loadedAccounts.size() +
                " Existed:" + existedPasswords.size() +
                " Inserted: " + insertedCount;
        logger.info("Backup readed successfull. "  + resultText);
    }


}
