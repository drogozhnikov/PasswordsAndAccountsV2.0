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
        logger.info("DataBase init successfull");
    }

    public ArrayList<Account> selectAll() throws SQLException, ParseException {
        ArrayList<Account> accounts = database.selectAll();
        Collections.reverse(accounts);
        return accounts;
    }

    ArrayList<PandaAccount> selectPandas(String ownerName) throws SQLException {
        ArrayList<PandaAccount> pandas = database.selectPandas(ownerName);
        Collections.reverse(pandas);
        return pandas;
    }

    ArrayList<PandaAccount> search(String searchValue, String ownerValue) throws SQLException {
        ArrayList<PandaAccount> founded = database.search(searchValue, ownerValue);
        Collections.reverse(founded);
        return founded;
    }

    ArrayList<String> selectOwnerList() throws SQLException {
        ArrayList<String> owners = database.selectOwnerList();
        Collections.sort(owners);
        return owners;
    }

    Account selectAccountById(int id) throws SQLException, ParseException {
        Account account = database.selectAccountById(id);
        return account;
    }

    PandaAccount selectPandaById(int id) throws SQLException {
        PandaAccount pandaAccount = database.selectPandaById(id);
        return pandaAccount;
    }

    void insertAccount(Account account) throws SQLException {
        if(!database.isPasswordExist(account.getPassword())){
            database.insertAccount(account);
        }else{
            //TODO Action when password Exist in DB
        }
    }

    void updateFullAccount(Account account) throws SQLException {
        database.updateFullAccount(account);
    }

    void deleteAccount(int id) throws SQLException {
        database.deleteAccount(id);
    }

//    AppData selectAppData(){
//
//    }

    void updateAppData(){

    }


}
