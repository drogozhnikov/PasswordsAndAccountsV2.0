package panda.utils.io;

import panda.models.Account;

import java.util.ArrayList;

public interface FileIO {

    void saveToDirectory(String filePath, ArrayList<Account> inputList) throws Exception;
    ArrayList<Account> readFromDirectory(String filePath) throws Exception;

    //Version must be type: _ioType_version
    String getVersion();
}
