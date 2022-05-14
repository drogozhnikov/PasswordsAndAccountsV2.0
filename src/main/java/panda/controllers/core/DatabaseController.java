package panda.controllers.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.models.Account;
import panda.models.AppData;
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

    public ArrayList<Account> selectAll() throws SQLException, ParseException {
        ArrayList<Account> accounts = database.selectAll();
        Collections.reverse(accounts);
        return accounts;
    }

    public ArrayList<PandaAccount> selectPandas(String ownerName) throws SQLException {
        ArrayList<PandaAccount> pandas = database.selectPandas(ownerName);
        Collections.reverse(pandas);
        return pandas;
    }

    public ArrayList<PandaAccount> search(String searchValue, String ownerValue) throws SQLException {
        ArrayList<PandaAccount> founded = database.search(searchValue, ownerValue);
        Collections.reverse(founded);
        return founded;
    }

    public ArrayList<String> selectOwnerList() throws SQLException {
        ArrayList<String> owners = database.selectOwnerList();
        Collections.sort(owners);
        return owners;
    }

    public Account selectAccountById(int id) throws SQLException, ParseException {
        Account account = database.selectAccountById(id);
        return account;
    }

    public PandaAccount selectPandaById(int id) throws SQLException {
        PandaAccount pandaAccount = database.selectPandaById(id);
        return pandaAccount;
    }

    public void insertAccount(Account account) throws SQLException {
        if (!database.isPasswordExist(account.getPassword())) {
            database.insertAccount(account);
        } else {
            //TODO Action when password Exist in DB
        }
    }

    public void updateFullAccount(Account account) throws SQLException {
        database.updateFullAccount(account);
    }

    public void deleteAccount(int id) throws SQLException {
        database.deleteAccount(id);
    }

    //AppData
    public AppData selectAppData() throws SQLException {
        return database.selectAppData();
    }

    public void updateAppData(AppData input) throws SQLException {
        database.updateAppData(input);
    }

    public int checkPass(String input) throws SQLException {
        return database.checkAccessPass(input);
    }

    public int updatePass(String oldPass, String newPass) throws SQLException {
        return database.updateExistedAppPass(oldPass, newPass);
    }


}
