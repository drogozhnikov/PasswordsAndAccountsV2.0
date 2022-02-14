package panda.controllers.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;

public class CryptionController {

    private static final Logger logger = LoggerFactory.getLogger(CryptionController.class);

    private final Cipher cipher;

    public CryptionController(Cipher cipher){
        this.cipher = cipher;
    }


}
