package panda.utils.cryption;

import javax.crypto.SecretKey;

public interface Сipher {

    void init(StringBuilder word);

    StringBuilder encrypt(StringBuilder input);
    StringBuilder decrypt(StringBuilder input);

    SecretKey getSpicificKey(String input);

}
