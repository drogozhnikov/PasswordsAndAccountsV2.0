package panda.controllers.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.models.Account;
import panda.models.AppData;
import panda.models.Owner;
import panda.models.PandaAccount;
import panda.utils.database.Database;
import panda.utils.database.sqlite.SqLiteDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

public class DatabaseController {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseController.class);

    private Database database;

    public DatabaseController(String fileBasePath) throws SQLException {
        database = new SqLiteDAO(fileBasePath);
        logger.info("initialization completed successfully");
    }

    public ArrayList<Account> selectAll() {
        try {
            ArrayList<Account> accounts = database.selectAll();
            Collections.reverse(accounts);
            return accounts;
        } catch (SQLException | ParseException e) {
            logger.error("Select pandaAccounts error");
        }
        return new ArrayList<Account>();
    }

    public ArrayList<PandaAccount> selectPandas(String ownerName) {
        try {
            ArrayList<PandaAccount> pandas = database.selectPandas(ownerName);
            Collections.reverse(pandas);
            return pandas;
        } catch (SQLException e) {
            logger.error("Select pandaAccounts error");
        }
        return new ArrayList<PandaAccount>();
    }

    public ArrayList<Account> selectAccounts() {
        try {
            ArrayList<Account> accounts = database.selectAll();
            Collections.reverse(accounts);
            return accounts;
        } catch (SQLException | ParseException e) {
            logger.error("Select accounts error");
        }
        return new ArrayList<Account>();
    }

    public ArrayList<PandaAccount> search(String searchValue, String ownerValue) {
        try {
            if(ownerValue==null) ownerValue = new Owner().getName();
            ArrayList<PandaAccount> foundedPandas = database.search(searchValue, ownerValue);
            Collections.reverse(foundedPandas);
            return foundedPandas;
        } catch (SQLException e) {
            logger.error("Search error");
        }
        return new ArrayList<PandaAccount>();
    }

    public ArrayList<Owner> selectOwnerList() {
        try {
            ArrayList<Owner> owners = database.selectOwnerList();
            Collections.sort(owners);
            return owners;
        } catch (SQLException e) {
            logger.error("Select owners list error");
        }
        return new ArrayList<Owner>();
    }

    public Account selectAccountById(int id) {
        try {
            return database.selectAccountById(id);
        } catch (SQLException | ParseException e) {
            logger.error("Select account by ID error");
        }
        return new Account();
    }

    public PandaAccount selectPandaById(int id) throws SQLException {
        try {
            return database.selectPandaById(id);
        } catch (SQLException e) {
            logger.error("Select account by ID error");
        }
        return new PandaAccount();
    }

    public void insertAccount(Account account) {
        try {
            database.insertAccount(account);
        } catch (SQLException e) {
            logger.error("Insert account error");
        }
    }

    public void updateFullAccount(Account account) {
        try {
            database.updateFullAccount(account);
            database.cleanOwnersList();
        } catch (SQLException e) {
            logger.error("Update full account error");
        }
    }

    public void deleteAccount(int id) {
        try {
            database.deleteAccount(id);
            database.cleanOwnersList();
        } catch (SQLException | ParseException e) {
            logger.error("Delete account error");
        }
    }

    //AppData
    public AppData selectAppData() {
        try {
            return database.selectAppData();
        } catch (SQLException e) {
            logger.error("select appData error");
        }
        return new AppData();
    }

    public void updateAppData(AppData input) {
        try {
            database.updateAppData(input);
        } catch (SQLException e) {
            logger.error("Update account error");
        }
    }

    public boolean checkPass(StringBuilder input) {
        try {
            return database.checkAccessPass(input) == 1;
        } catch (SQLException e) {
            logger.error("Check access error");
        }
        return true; // imitate like pass already exist;
    }

    public boolean isPasswordExist(StringBuilder input) {
        boolean result = true;
        try {
            result = database.isPasswordExist(input);
        } catch (SQLException e) {
            logger.error("Count pass error");
        }
        return result;
    }

    public boolean updatePass(StringBuilder cryptedNewPass, StringBuilder cryptedOldPass) {
        try {
            return database.updateExistedAppPass(cryptedNewPass, cryptedOldPass) == 1;
        } catch (SQLException e) {
            logger.error("Update pass error");
        }
        return false;
    }

    public void updateAppDataResolution(int width, int height) {
        try {
            database.updateAppDataResolution(width, height);
        } catch (SQLException e) {
            logger.error("Update resolution error");
        }
    }

    public void clear() {
        try {
            database.clearDataBase();
        } catch (SQLException e) {
            logger.error("Clear db error");
        }
    }
}
