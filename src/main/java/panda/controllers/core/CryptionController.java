package panda.controllers.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import panda.utils.cryption.Сipher;

public class CryptionController {

    private static final Logger logger = LoggerFactory.getLogger(CryptionController.class);

    private final Сipher cipher;

    public CryptionController(Сipher cipher){
        this.cipher = cipher;
        logger.info("initialization completed successfully");
    }

    public String cryptIt(String input){
        return cipher.encrypt(input);
    }

    public String deCriptIt(String input){
        return cipher.decrypt(input);
    }

    public boolean checkIt(String inputWord, String cipherWord){
        if(inputWord.length()<cipherWord.length()){
            inputWord = cryptIt(inputWord);
        }
        return inputWord.equals(cipherWord);
    }


}
