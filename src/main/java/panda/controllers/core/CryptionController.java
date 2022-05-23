package panda.controllers.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import panda.utils.cryption.Сipher;

import javax.crypto.Cipher;

public class CryptionController {

    private static final Logger logger = LoggerFactory.getLogger(CryptionController.class);

    private Сipher cipher;
    private final StringBuilder cryptPassPhrase = new StringBuilder("HelloWorldMotherFucker");

    public CryptionController(Сipher cipher){
        this.cipher = cipher;
        logger.info("initialization completed successfully");
    }

    public void init(Сipher cipher, StringBuilder input){
        this.cipher = cipher;
        cipher.init(input);
    }

    public StringBuilder cryptIt(StringBuilder input){
        return cipher.encrypt(input);
    }

    public StringBuilder deCriptIt(StringBuilder input){
        return cipher.decrypt(input);
    }

    //return special phrase encrypted by inputWord.
    public StringBuilder getSpecialCheckWord(){
        return cipher.encrypt(cryptPassPhrase);
    }


}
