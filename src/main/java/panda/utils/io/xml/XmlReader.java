package panda.utils.io.xml;


import panda.models.Account;
import panda.models.xml.Panda;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;

public class XmlReader {

    private final String filePath;

    public XmlReader(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Account> readAccounts() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Panda.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Panda panda = (Panda) unmarshaller.unmarshal(new File(filePath));

//        Marshaller marshaller = jc.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(panda, System.out);

        return new ArrayList<Account>(panda.getAccount());
    }


}
