//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.06.22 at 09:59:57 AM MSK 
//


package panda.models.account;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the panda.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Panda_QNAME = new QName("Panda");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: panda.model
     * 
     */
    public ObjectFactory() {
    }

    @XmlElementDecl(name = "Panda")
            public JAXBElement<Panda> createPanda(Panda value) {
        return new JAXBElement<Panda>(_Panda_QNAME, Panda.class, null, value);
    }

    /**
     * Create an instance of {@link Account }
     * 
     */
    public Account createAccount() {
        return new Account();
    }

    /**
     * Create an instance of {@link Panda }
     * 
     */
    public Panda createPanda() {
        return new Panda();
    }

}
