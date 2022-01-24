package panda.utils.io.previous;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import panda.models.Account;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class XmlReaderV1 {

        private final String filePath;

        public XmlReaderV1(String filePath) {
            this.filePath = filePath;
        }

        public ArrayList<Account> readAccounts() throws Exception {
            NodeList nodeListTemp = getNodeList(filePath);
            ArrayList<Account> accountsList = new ArrayList<>();
            for (int i = 1; i < nodeListTemp.getLength(); i++) {
                accountsList.add(getDirectoryAccountsData(nodeListTemp.item(i)));
            }
            Collections.reverse(accountsList);
            return accountsList;
        }

        private static Account getDirectoryAccountsData(Node node) {
            Account output = new Account();
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                try {
                    output.setId(Integer.parseInt(getTagValue("id", element)));
                } catch (NullPointerException e) {
//                    System.out.println("id2");
                }

                try {
                    output.setName(getTagValue("name", element));
                } catch (NullPointerException e) {
//                    System.out.println("id3");
                }

                try {
                    output.setOwner(getTagValue("owner", element));
                } catch (NullPointerException e) {
//                    System.out.println("id4");
                }

                try {
                    output.setLink(getTagValue("link", element));
                } catch (NullPointerException e) {
//                    System.out.println("id4");
                }

                try {
                    output.setMail(getTagValue("mail", element));
                } catch (NullPointerException e) {
//                    System.out.println("id5");
                }
                try {
                    output.setAccount(getTagValue("account", element));
                } catch (NullPointerException e) {
//                    System.out.println("id5");
                }
                try {
                    output.setPassword(getTagValue("password", element));
                } catch (NullPointerException e) {
//                    System.out.println("id5");
                }
                //TODO discription - > info analogy

            }
            return output;
        }

        private NodeList getNodeList(String filepath) throws Exception {

            File xmlFile = new File(filepath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            return document.getElementsByTagName("directory");
        }

        private static String getTagValue(String tag, Element element) {
            NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
            Node node = nodeList.item(0);
            return node.getNodeValue();
        }

}
