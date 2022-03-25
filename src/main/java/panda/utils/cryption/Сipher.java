package panda.utils.cryption;

import javax.crypto.SecretKey;

public interface Сipher {

    void init(String word);

    String encrypt(String input);
    String decrypt(String input);

    SecretKey getSpicificKey(String input);

}
