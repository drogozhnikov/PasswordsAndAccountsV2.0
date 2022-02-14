package panda.controllers.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.models.Account;
import panda.utils.io.FileIO;
import panda.utils.io.xml.XMLio;

import java.util.ArrayList;
import java.util.Date;

public class BackupController {

    private static final Logger logger = LoggerFactory.getLogger(BackupController.class);

    private FileIO fileIO;

    private String backupFoulderPath;

    public BackupController(String backupFolderPath, FileIO fileIO){
        this.backupFoulderPath = backupFolderPath;
        this.fileIO = fileIO;
    }

    public void save(ArrayList<Account> outputAccounts){
        try {
            fileIO.saveToDirectory(backupFoulderPath + "\\" + getStandartBackupFileName(), outputAccounts);
        } catch (Exception e) {
            logger.error("BackUpCreateException" + fileIO.getVersion(), e);
        }
    }

    public ArrayList<Account> read(String filePath){
        try {
            return fileIO.readFromDirectory(filePath);
        } catch (Exception e) {
            logger.error("BackUpCreateException" + fileIO.getVersion(), e);
        }
        return null;
    }

    public void saveToDirectory(String directoryPath, ArrayList<Account> outputAccounts){
        try {
            fileIO.saveToDirectory(directoryPath + "\\" + getStandartBackupFileName(), outputAccounts);
        } catch (Exception e) {
            logger.error("BackUpCreateException" + fileIO.getVersion(), e);
        }
    }

    private String getStandartBackupFileName(){
       return "\\PandaBackUp" + fileIO.getVersion() + new Date().toString() + ".xml";
    }
}
