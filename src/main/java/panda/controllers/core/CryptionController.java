package panda.controllers.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import panda.models.Account;
import panda.utils.cryption.AesCrypt;
import panda.utils.cryption.Сipher;

import javax.crypto.Cipher;
import java.util.ArrayList;

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
    public StringBuilder encryptPassPhrase(){
        return cipher.encrypt(cryptPassPhrase);
    }

    public StringBuilder getEncryptedInput(StringBuilder inputWord){
        Сipher cipher = new AesCrypt();
        cipher.init(inputWord);
        return cipher.encrypt(cryptPassPhrase);
    }

    public ArrayList<Account> getDecryptedAccounts(ArrayList<Account> inputList){
        ArrayList<Account> output = new ArrayList<>();
        for(Account account: inputList){
            account.setPassword(deCriptIt(account.getPassword()));
            output.add(account);
        }
        return output;
    }


}
