package panda.utils.io;

import panda.models.account.Account;
import panda.models.account.ObjectFactory;
import panda.models.account.Panda;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class XmlWriter {

    private File file;

    public XmlWriter(String filePath) {
        file = new File(filePath);
    }

    private ObjectFactory factory = new ObjectFactory();

    public void saveAccounts(ArrayList<Account> inputList) throws Exception {
        Panda panda = factory.createPanda();
        panda.getAccount().addAll(inputList);

        String outputXMl = toXmlString(factory.createPanda(panda));
        executeToFile(outputXMl);
    }

    private void executeToFile(String input) throws IOException {
        try (FileWriter fw = new FileWriter(file, false)) {
            fw.write(input);
        }
    }

    public String toXmlString(JAXBElement message) throws JAXBException, UnsupportedEncodingException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(message.getDeclaredType());
        final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final StreamResult res = new StreamResult(byteArrayOutputStream);
        jaxbMarshaller.marshal(message, res);
        return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
    }

}
