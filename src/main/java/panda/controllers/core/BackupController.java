package panda.controllers.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panda.models.Account;
import panda.utils.io.FileIO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BackupController {

    private static final Logger logger = LoggerFactory.getLogger(BackupController.class);

    private FileIO fileIO;

    private String backupFoulderPath;

    public BackupController(String backupFolderPath) {
        this.backupFoulderPath = backupFolderPath;
    }

    public void init(FileIO fileIO) {
        this.fileIO = fileIO;
    }

    public void save(ArrayList<Account> outputAccounts) {
        try {
            fileIO.saveToDirectory(backupFoulderPath + "\\" + getStandartBackupFileName(), outputAccounts);
            logger.info("BackUp save successfull" + fileIO.getVersion());
        } catch (Exception e) {
            logger.error("BackUpCreateException" + fileIO.getVersion(), e);
        }
    }

    public ArrayList<Account> load(String filePath) {
        try {
            ArrayList<Account> readedAccounts = fileIO.readFromDirectory(filePath);
            logger.info("BackUp load successfull" + fileIO.getVersion());
            return readedAccounts;
        } catch (Exception e) {
            logger.error("BackUpCreateException" + fileIO.getVersion(), e);
        }
        return null;
    }

    public void saveToDirectory(String directoryPath, ArrayList<Account> outputAccounts) {
        try {
            fileIO.saveToDirectory(directoryPath, outputAccounts);
            logger.info("BackUp save successfull" + fileIO.getVersion());
        } catch (Exception e) {
            logger.error("BackUpCreateException" + fileIO.getVersion(), e);
        }
    }

    private String getStandartBackupFileName() {
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String date = DateFor.format(System.currentTimeMillis());
        return "\\PandaBackUp" + fileIO.getVersion() + date + ".xml";
    }
}
