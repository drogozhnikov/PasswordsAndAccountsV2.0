package panda.models;

import java.util.GregorianCalendar;

public class PandaAccount {

    private int accountId;
    private String tableFieldName;              //Column # 0
    private String tableFieldMail;              //Column # 1
    private String tableFieldAccount;           //Column # 2
    private String tableFieldPassword;          //Column # 3
    private GregorianCalendar tableFieldDate;   //Column # 4

    private int selectedColumn = 0;

    public PandaAccount(){

    }

    public PandaAccount(int accountId, String tableFieldName, String tableFieldMail, String tableFieldAccount, String tableFieldPassword, GregorianCalendar tableFieldDate) {
        this.accountId = accountId;
        this.tableFieldName = tableFieldName;
        this.tableFieldMail = tableFieldMail;
        this.tableFieldAccount = tableFieldAccount;
        this.tableFieldPassword = tableFieldPassword;
        this.tableFieldDate = tableFieldDate;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getTableFieldName() {
        return tableFieldName;
    }

    public String getTableFieldMail() {
        return tableFieldMail;
    }

    public String getTableFieldAccount() {
        return tableFieldAccount;
    }

    public String getTableFieldPassword() {
        return tableFieldPassword;
    }

    public GregorianCalendar getTableFieldDate() {
        return tableFieldDate;
    }



    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setTableFieldName(String tableFieldName) {
        this.tableFieldName = tableFieldName;
    }

    public void setTableFieldMail(String tableFieldMail) {
        this.tableFieldMail = tableFieldMail;
    }

    public void setTableFieldAccount(String tableFieldAccount) {
        this.tableFieldAccount = tableFieldAccount;
    }

    public void setTableFieldPassword(String tableFieldPassword) {
        this.tableFieldPassword = tableFieldPassword;
    }

    public void setTableFieldDate(GregorianCalendar tableFieldDate) {
        this.tableFieldDate = tableFieldDate;
    }
}
