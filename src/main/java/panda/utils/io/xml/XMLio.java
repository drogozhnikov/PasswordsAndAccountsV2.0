package panda.utils.io.xml;

import panda.models.Account;
import panda.utils.io.FileIO;

import java.util.ArrayList;

public class XMLio implements FileIO {

    private final String xmlVersion = "_xml_1.0_";

    public XMLio() {
    }

    @Override
    public void saveToDirectory(String filePath, ArrayList<Account> inputList) throws Exception {
        XmlWriter writer = new XmlWriter(filePath);
        writer.saveAccounts(inputList);
    }

    @Override
    public ArrayList<Account> readFromDirectory(String filePath) throws Exception {
        XmlReader reader = new XmlReader(filePath);
        return reader.readAccounts();
    }

    @Override
    public String getVersion() {
        return xmlVersion;
    }
}
