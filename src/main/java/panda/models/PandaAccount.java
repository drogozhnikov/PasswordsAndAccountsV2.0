package panda.models;

import java.util.GregorianCalendar;

public class PandaAccount {

    private int accountId;
    private String tableFieldName;              //Column # 0
    private String tableFieldMail;              //Column # 1
    private String tableFieldAccount;           //Column # 2
    private String tableFieldPassword;          //Column # 3

    private String tableFieldPasswordHiden = "***************";          //Column # 3

    private int selectedColumn = 0;

    public PandaAccount(){

    }

    public PandaAccount(int accountId, String tableFieldName, String tableFieldMail, String tableFieldAccount, String tableFieldPassword, String tableFieldDate) {
        this.accountId = accountId;
        this.tableFieldName = tableFieldName;
        this.tableFieldMail = tableFieldMail;
        this.tableFieldAccount = tableFieldAccount;
        this.tableFieldPassword = tableFieldPassword;
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

    public String getTableFieldPasswordHiden() { return tableFieldPasswordHiden; }



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


    @Override
    public String toString() {
        return "PandaAccount{" +
                "accountId=" + accountId +
                ", tableFieldName='" + tableFieldName + '\'' +
                ", tableFieldMail='" + tableFieldMail + '\'' +
                ", tableFieldAccount='" + tableFieldAccount + '\'' +
                ", tableFieldPassword='" + tableFieldPassword + '\'' +
                ", selectedColumn=" + selectedColumn +
                '}';
    }
}
