package panda.controllers.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import panda.utils.cryption.Сipher;

import javax.crypto.Cipher;

public class CryptionController {

    private static final Logger logger = LoggerFactory.getLogger(CryptionController.class);

    private Сipher cipher;
    private final String cryptPassPhrase = "HelloWorldMotherFucker";

    public CryptionController(Сipher cipher){
        this.cipher = cipher;
        logger.info("initialization completed successfully");
    }

    public void init(Сipher cipher, String input){
        this.cipher = cipher;
        cipher.init(input);
    }

    public String cryptIt(String input){
        return cipher.encrypt(input);
    }

    public String deCriptIt(String input){
        return cipher.decrypt(input);
    }

    //return special phrase encrypted by inputWord.
    public String getSpecialCheckWord(){
        return cipher.encrypt(cryptPassPhrase);
    }


}
