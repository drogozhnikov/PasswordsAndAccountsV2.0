package panda.utils.database;

import panda.models.Account;
import panda.models.AppData;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Database {

    ArrayList<Account> selectAllAccounts() throws SQLException;
    ArrayList<Account> selectOwnersAccounts(String ownerName) throws SQLException;
    ArrayList<Account> selectOwners() throws SQLException;
    ArrayList<Account> searchAccounts(String value, String ownerName) throws SQLException;

    void insertAccount(Account account) throws SQLException;
    void updateAccount(Account account) throws SQLException;
    void deleteAccount(Account account) throws SQLException;

    void clearBase() throws SQLException;

    boolean findPasswords() throws SQLException;

    AppData selectAppData() throws SQLException;
    AppData updateAppData() throws SQLException;



}
