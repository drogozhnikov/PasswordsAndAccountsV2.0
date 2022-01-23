package panda.utils.cryption;

import javax.crypto.SecretKey;

public interface PandaСipher {

    String encrypt(String input);
    String decrypt(String input);

    SecretKey getSpicificKey(String input);

}
