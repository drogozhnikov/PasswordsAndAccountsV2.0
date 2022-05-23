package panda.utils.cryption;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AesCrypt implements Сipher {

    private static final Logger logger = LoggerFactory.getLogger(AesCrypt.class);

    private SecretKey key;
    private Cipher cipher;
    private Cipher decrypter;

    public AesCrypt(){}

    public void init(StringBuilder checkWord){
        key = getSpicificKey(checkWord.toString());
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            decrypter = Cipher.getInstance("AES");
            decrypter.init(Cipher.DECRYPT_MODE,key);
        } catch (NoSuchAlgorithmException e) {
            logger.error("AesCrypt: NoSuchAlgorithmException");
        } catch (NoSuchPaddingException e) {
            logger.error("AesCrypt: NoSuchPaddingException");
        } catch (InvalidKeyException e) {
            logger.error("AesCrypt: InvalidKeyException");
        }
    }

    private SecretKey getRandomKey() throws NoSuchAlgorithmException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        SecretKey key = kgen.generateKey();
        return key;
    }

    //input length must be 16 symbols
    public SecretKey getSpicificKey(String input){
        String sixteenChars = prepareToKeyGen(input); // convert input string to 16 characters
        return new SecretKeySpec(sixteenChars.getBytes(), "AES");
    }

    public StringBuilder encrypt(StringBuilder input) {
        String result = "";
        try {
            result = Base64.encodeBase64String(cipher.doFinal(input.toString().getBytes()));
        } catch (IllegalBlockSizeException e) {
            logger.error("AesCrypt: IllegalBlockSizeException");
        } catch (BadPaddingException e) {
            logger.error("AesCrypt: BadPaddingException");
        }
        return new StringBuilder(result);
    }

    public StringBuilder decrypt(StringBuilder input){
        byte[] decrypterBytes = new byte[0];
        try {
            decrypterBytes = decrypter.doFinal(Base64.decodeBase64(input.toString()));
        } catch (IllegalBlockSizeException e) {
            logger.error("AesCrypt: IllegalBlockSizeException");
        } catch (BadPaddingException e) {
            logger.error("AesCrypt: BadPaddingException");
        }
        return new StringBuilder(new String(decrypterBytes));
    }

    private String prepareToKeyGen(String input){
        String output = "";
        if(input != null && input.length() != 0){
            output = input;
        }
        if(output.length() < 16){
            while(output.length()<=16){
                output += input;
            }
        }
        if(output.length() == 16){
            return output;
        }
        if(output.length()>16){
            output = output.substring(0,16);
        }
        return output;
    }
}
