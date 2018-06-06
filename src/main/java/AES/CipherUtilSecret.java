package AES;



import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;


        /*Le module de cryptographie (javax.crypto) de Java permet facilement le (dé)chiffrement d'un document ou d'une simple chaîne de caractères :

             - soit en utilisant une même clé pour chiffer/déchiffrer (chiffrement symétrique) : l'algorithme principalement utilisé est AES (Advanced Encryption Standard),
               il nous permet d'utiliser une clé de taille 16, 24 ou 32 caractères (soit 128, 192 ou 256 bits).

             - soit en utilisant des clés différentes (chiffrement asymétrique), une clé publique et une clé privée : l'algorithme utilisé est RSA.*/


        /*Chiffrement symétrique*/

public class CipherUtilSecret {


    public static final String CIPHER_ALGORITHM = "AES";
    public static final String KEY_ALGORITHM = "AES";
    public static final byte[] SECRET_KEY = "pppppppppppppppp".getBytes(); // exactly 16 bytes to not use JCE (Java Cryptography Extension)

    public String decrypt(String encryptedInput) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(SECRET_KEY, KEY_ALGORITHM));
            return new String(cipher.doFinal(Base64.decode(encryptedInput)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String encrypt(String str) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(SECRET_KEY, KEY_ALGORITHM));
            return Base64.encode(cipher.doFinal(str.getBytes()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        CipherUtilSecret cipherUtil = new CipherUtilSecret();
        // Encryption
        String encryptedString = cipherUtil.encrypt("1,2,3 allons dans les bois : " );
        // Before Decryption
        System.out.println("Avant déchiffrement : " + encryptedString);
        String s = cipherUtil.decrypt(encryptedString);
        System.out.println("Après déchiffrement : " + s);
    }
}
