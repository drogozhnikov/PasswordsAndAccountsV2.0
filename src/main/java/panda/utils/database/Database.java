package panda.utils.database;

import panda.models.Account;
import panda.models.AppData;
import panda.models.Owner;
import panda.models.PandaAccount;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface Database {

    ArrayList<Account> selectAll() throws SQLException, ParseException;

    ArrayList<PandaAccount> selectPandas(String ownerName) throws SQLException;
    ArrayList<PandaAccount> search(String searchValue, String ownerValue) throws SQLException;

    ArrayList<Owner>  selectOwnerList() throws SQLException;

    Account selectAccountById(int id) throws SQLException, ParseException;
    PandaAccount selectPandaById(int id) throws SQLException;

    void insertAccount(Account account) throws SQLException;
    void updateFullAccount(Account account) throws SQLException;
    void deleteAccount(int id) throws SQLException, ParseException;
    boolean isPasswordExist(StringBuilder inputPassword) throws SQLException;

    int getOwnerIndex(String ownerName) throws SQLException;
    String getOwnerName(int id) throws SQLException;

    AppData selectAppData() throws SQLException;
    void updateAppData(AppData input) throws SQLException;

    int updateExistedAppPass(StringBuilder newPass, StringBuilder oldPass)throws SQLException;

    int checkAccessPass(StringBuilder input) throws SQLException;

    void updateAppDataResolution(int width, int height) throws SQLException;

    void clearDataBase() throws SQLException;
    void cleanOwnersList() throws SQLException;

}
