package panda.utils.database.sqlite;

import panda.models.Account;
import panda.models.AppData;
import panda.models.PandaAccount;
import panda.utils.database.Database;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class SqLiteDAO implements Database {

    private Connection connection;

    public SqLiteDAO(String baseFilePath) throws SQLException {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + baseFilePath);
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public Account selectAccountById(int id) throws SQLException, ParseException {
        Account output = null;
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from accounts where id = " + id);
            while (resultSet.next()) {
                output = new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("a_name"),
                        resultSet.getString("o_name"),
                        resultSet.getString("link"),
                        resultSet.getString("mail"),
                        resultSet.getString("account"),
                        resultSet.getString("password"),
                        resultSet.getString("info"),
                        translateDate(resultSet.getString("update_date")));
            }
            return output;
        }
    }

    @Override
    public PandaAccount selectPandaById(int id) throws SQLException {
        PandaAccount output = null;
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from accounts where id = " + id);
            while (resultSet.next()) {
                output = new PandaAccount(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("mail"),
                        resultSet.getString("account"),
                        resultSet.getString("password"),
                        resultSet.getString("update_date"));
            }
            return output;
        }
    }

    @Override
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

    @Override
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

    @Override
    public void deleteAccount(int id) throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM accounts WHERE id = ?")) {
            statement.setObject(1, id);
            statement.execute();
        }
    }

    @Override
    public boolean isPasswordExist(String inputPassword) throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select count(*) from accounts where password = '" + inputPassword + "'");
            if (resultSet.getInt("count(*)") != 0) {
                return true;
            }
            return false;
        }
    }

    @Override
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

    @Override
    public AppData selectAppData() throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from appdata");
            AppData output = null;
            while (resultSet.next()) {
                output = new AppData(
                        resultSet.getString("pass_gen_pattern"),
                        resultSet.getString("selected_theme"),
                        resultSet.getInt("selected_owner"),
                        resultSet.getInt("screen_width"),
                        resultSet.getInt("screen_height")
                       );
            }
           return output;
        }
    }

    @Override
    public void updateAppData(AppData input) throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE appdata SET " +
                        "pass_gen_pattern = ?, " +
                        "selected_theme = ? ," +
                        "selected_owner = ? ," +
                        "screen_width = ? ," +
                        "screen_height = ? ,"
                        )) {
            statement.setObject(1, input.getPassGenPattern());
            statement.setObject(2, input.getTheme());
            statement.setObject(3, input.getOwner());
            statement.setObject(4, input.getScreenWidth());
            statement.setObject(5, input.getScreenHeight());
            statement.executeUpdate();
        }
    }

    // 1-pass updated, 0 pass inserted, -1 - wrong old pass
    @Override
    public int updateExistedAppPass(String oldPass, String newPass) throws SQLException{
        final int passUpdated = 1;
        final int passInsrted = 0;
        final int passMissmatch = -1;

        int oldPassStatus = checkAccessPass(oldPass);
        if(oldPassStatus == 1 || oldPassStatus==0){
            try (PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE appdata SET cipher_Word = ?"
            )) {
                statement.setObject(1, newPass);
            }

            if(oldPassStatus == 1){
                return passUpdated;
            }else{
                return passInsrted;
            }

        }else {
            return passMissmatch;
        }
    }

    //-1 pass not exist. 0 if not matches. 1 if exist
    @Override
    public int checkAccessPass(String input) throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select count(*) from appdata");
            if (resultSet.getInt("count(*)") != 0 && input!="") {
                String query = "select count(*) from appdata where cipher_word = \'" + input + "\'";
                ResultSet resultCount = statement.executeQuery(query);
                if(resultCount.getInt("count(*)") != 0){
                    return 1;
                }
                return -1;
            }else{
                return 0;
            }
        }
    }


    private GregorianCalendar translateDate(String input) throws ParseException {
        GregorianCalendar output = new GregorianCalendar();
        output.setTimeZone(TimeZone.getTimeZone("GMT+02:00"));
        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(input);
        output.setTime(date);
        return output;
    }
}
