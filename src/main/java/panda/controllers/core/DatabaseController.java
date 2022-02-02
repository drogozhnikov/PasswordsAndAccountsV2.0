package panda.controllers.core;

import panda.models.Account;
import panda.models.AppData;
import panda.models.PandaAccount;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DatabaseController {

    private Connection connection;

    public DatabaseController(String baseLocation) throws SQLException {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + baseLocation);
    }

    public ArrayList<Account> selectAll() throws SQLException, ParseException {
        try (Statement statement = this.connection.createStatement()) {
            ArrayList<Account> output = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("select a.id, a.name as a_name, o.name as o_name, link, mail, account, password, info, update_date"
                    + " from accounts a join owners o on a.owner = o.id;");
            while (resultSet.next()) {
                output.add(new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("a_name"),
                        resultSet.getString("o_name"),
                        resultSet.getString("link"),
                        resultSet.getString("mail"),
                        resultSet.getString("account"),
                        resultSet.getString("password"),
                        resultSet.getString("info"),
                        translateDate(resultSet.getString("update_date"))
                ));
            }
            return output;
        }
    }

    public ArrayList<PandaAccount> selectPandas(String ownerName) throws SQLException {
        String query = "select a.id, a.name, mail, account, password, update_date from accounts a";
        if (ownerName != null && !ownerName.equals("") && !ownerName.toLowerCase().equals("all")) {
            query = query + " join owners o on a.owner = o.id where o.name = '" + ownerName + "'";
        }

        try (Statement statement = this.connection.createStatement()) {
            ArrayList<PandaAccount> output = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                output.add(new PandaAccount(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("mail"),
                        resultSet.getString("account"),
                        resultSet.getString("password"),
                        resultSet.getString("update_date")
                ));
            }
            return output;
        }
    }

    public ArrayList<String> selectOwnerList() throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            ArrayList<String> dataList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("select name from owners");
            while (resultSet.next()) {
                dataList.add(resultSet.getString("owner_name"));
            }
            return dataList;
        }
    }

    public ArrayList<PandaAccount> search(String searchValue, String ownerValue) throws SQLException {
        String query = "select distinct a.id, a.name as a_name, account, mail, password, info, update_date from accounts a join owners o on a.owner = o.id where ";
        if (!ownerValue.toLowerCase().equals("all")) {
            query = query + "o.name = ('" + ownerValue + "') and ";
        }
        query = query +
                "(a.name like ('%" + searchValue + "%') and " +
                "account like ('%" + searchValue + "%') or " +
                "mail like ('%" + searchValue + "%') or " +
                "password like ('%" + searchValue + "%') or " +
                "update_date like ('%" + searchValue + "%'))";
        try (Statement statement = this.connection.createStatement()) {
            ArrayList<PandaAccount> output = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                output.add(new PandaAccount(
                        resultSet.getInt("id"),
                        resultSet.getString("a_name"),
                        resultSet.getString("mail"),
                        resultSet.getString("account"),
                        resultSet.getString("password"),
                        resultSet.getString("update_date")
                ));
            }
            return output;
        }
    }

    public void insertAccount(Account account) throws SQLException {

        int ownerIndex = getOnwerIndex(account.getOwner());
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO accounts('name','owner','link','mail','account','password','info')" +
                        "VALUES(?,?,?,?,?,?,?,?)")) {
            statement.setObject(1, account.getName());
            statement.setObject(2, ownerIndex);
            statement.setObject(3, account.getLink());
            statement.setObject(4, account.getMail());
            statement.setObject(5, account.getAccount());
            statement.setObject(6, account.getPassword());
            statement.execute();
        }
    }

    public void updateFullAccount(Account account) throws SQLException {
        int ownerIndex = getOnwerIndex(account.getOwner());
        try (PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE accounts SET " +
                        "name = ?, " +
                        "owner = ? ," +
                        "link = ? ," +
                        "mail = ? ," +
                        "account = ? ," +
                        "password = ? ," +
                        " WHERE id = ?")) {
            statement.setObject(1, account.getName());
            statement.setObject(2, ownerIndex);
            statement.setObject(3, account.getLink());
            statement.setObject(4, account.getMail());
            statement.setObject(5, account.getAccount());
            statement.setObject(6, account.getPassword());
            statement.setObject(9, account.getId());
            statement.executeUpdate();
        }
    }

    public void deleteAccount(PandaAccount pandaAccount) throws SQLException {
//            ArrayList<PandaAccount> temp = selectPandas(account.getOwner());
//        if (temp == null || temp.size() == 1) {
//            try (PreparedStatement statement = this.connection.prepareStatement(
//                    "DELETE FROM owners WHERE owner_name = ?")) {
//                statement.setObject(1, account.getOwner());
//                statement.execute();
//            }
//        }
//        try (PreparedStatement statement = this.connection.prepareStatement(
//                "DELETE FROM accounts WHERE id = ?")) {
//            statement.setObject(1, account.getId());
//            statement.execute();
//        }
//        try (PreparedStatement statement = this.connection.prepareStatement(
//                "Vacuum;")) {
//            statement.execute();
//        }
    }

    public boolean isPasswordExist(String inputPassword) throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select count(*) from accounts where password = '" + inputPassword + "'");
            if (resultSet.getInt("count(*)") != 0) {
                return true;
            }
            return false;
        }
    }

    public int getOnwerIndex(String ownerName) throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select count(*) from owners where name = '" + ownerName + "'");
            if (resultSet.getInt("count(*)") == 0) {
                try (PreparedStatement insertStatement = this.connection.prepareStatement(
                        "Insert into owners(name) values (?)")) {
                    insertStatement.setObject(1, ownerName);
                    insertStatement.execute();
                }
                return statement.executeQuery("select last_insert_rowid()").getInt("last_insert_rowid()");
            } else {
                return statement.executeQuery("select id from owners where name = '" + ownerName + "'").getInt("id");
            }
        }
    }

    protected AppData selectAppData() {
        AppData output = new AppData();

        return output;
    }

    private GregorianCalendar translateDate(String input) throws ParseException {
        GregorianCalendar output = new GregorianCalendar();
        output.setTimeZone(TimeZone.getTimeZone("GMT+02:00"));
        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(input);
        output.setTime(date);
        return output;
    }

}
