package panda.utils.database.sqlite;

import panda.models.Account;
import panda.models.AppData;
import panda.utils.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class SqLiteDAO implements Database {

    private Connection connection;

    public SqLiteDAO(String baseFilePath) throws SQLException {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + baseFilePath);
    }

    @Override
    public ArrayList<Account> selectAllAccounts() throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            ArrayList<Account> dataList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("select id, name, owner_name, link, mail, account," +
                    " password, description, priority from accounts join owners o on accounts.owner = o.owner_name;");
            while (resultSet.next()) {
                dataList.add(new Account());
//                        resultSet.getInt("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("owner_name"),
//                        resultSet.getString("link"),
//                        resultSet.getString("mail"),
//                        resultSet.getString("account"),
//                        resultSet.getString("password"),
//                        resultSet.getString("description"),
//                        Integer.parseInt(resultSet.getString("priority"))));
            }
            Collections.sort(dataList);
            Collections.reverse(dataList);
            return dataList;
        }
    }

    @Override
    public ArrayList<Account> selectOwnersAccounts(String ownerName) {
        return null;
    }

    @Override
    public ArrayList<Account> selectOwners() {
        return null;
    }

    @Override
    public ArrayList<Account> searchAccounts(String value, String ownerName) {
        return null;
    }

    @Override
    public void insertAccount(Account account) {

    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void deleteAccount(Account account) {

    }

    @Override
    public void clearBase() {

    }

    @Override
    public boolean findPasswords() {
        return false;
    }

    @Override
    public AppData selectAppData() {
        return null;
    }

    @Override
    public AppData updateAppData() {
        return null;
    }


}
