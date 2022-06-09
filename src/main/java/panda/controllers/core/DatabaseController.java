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

    public ArrayList<Account> selectAccounts() throws SQLException, ParseException {
        ArrayList<Account> accounts = database.selectAll();
        Collections.reverse(accounts);
        return accounts;
    }

    public ArrayList<PandaAccount> search(String searchValue, String ownerValue) throws SQLException {
        ArrayList<PandaAccount> founded = database.search(searchValue, ownerValue);
        Collections.reverse(founded);
        return founded;
    }

    public ArrayList<Owner> selectOwnerList() throws SQLException {
        ArrayList<Owner> owners = database.selectOwnerList();
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
        database.insertAccount(account);
    }

    public void updateFullAccount(Account account) throws SQLException, ParseException {
        database.updateFullAccount(account);
        database.cleanOwnersList();
    }

    public void deleteAccount(int id) throws SQLException, ParseException {
        database.deleteAccount(id);
        database.cleanOwnersList();
    }

    //AppData
    public AppData selectAppData() throws SQLException {
        return database.selectAppData();
    }

    public void updateAppData(AppData input) throws SQLException {
        database.updateAppData(input);
    }

    public boolean checkPass(StringBuilder input) throws SQLException {
        int result = database.checkAccessPass(input);
        if(result == 1){
            return true;
        }
        return false;
    }

    public boolean isPasswordExist(StringBuilder input) throws SQLException {
        return database.isPasswordExist(input);
    }

    public boolean updatePass(StringBuilder cryptedNewPass, StringBuilder cryptedOldPass) throws SQLException {
        int result = database.updateExistedAppPass(cryptedNewPass,cryptedOldPass);
        if(result == 1){
            return true;
        }
        return false;
    }

    public String getOwnerName(int id) throws SQLException {
        return database.getOwnerName(id);
    }

    public int getOwnerId(String name) throws SQLException {
        if(name!=null){
            return database.getOwnerIndex(name);
        }
        return 0;
    }

    public void updateAppDataResolution(int width, int height) throws SQLException {
        database.updateAppDataResolution(width,height);
    }

    public void clear() throws SQLException {
        database.clearDataBase();
    }


}
