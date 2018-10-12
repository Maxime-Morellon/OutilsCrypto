package RSA;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;


        /*Le module de cryptographie (javax.crypto) de Java permet facilement le (dé)chiffrement d'un document ou d'une simple chaîne de caractères :

        - soit en utilisant une même clé pour chiffer/déchiffrer (chiffrement symétrique) : l'algorithme principalement utilisé est AES (Advanced Encryption Standard), il nous permet d'utiliser une clé de taille 16, 24 ou 32 caractères (soit 128, 192 ou 256 bits).

        - soit en utilisant des clés différentes (chiffrement asymétrique), une clé publique et une clé privée : l'algorithme utilisé est RSA.*/

        https://www.javacube.fr/chiffrer-dechiffrer-simplement-avec-aes-en-java/ => à voir

        // RSA
public class Sample {

    public static void main(String [] args) throws Exception {
        // generate public and private keys
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // encrypt the message
        byte [] encrypted = encrypt(privateKey, "This is a secret message");
        System.out.println(new String(encrypted));  // <<encrypted message>>

        // decrypt the message
        byte[] secret = decrypt(pubKey, encrypted);
        System.out.println(new String(secret));     // This is a secret message
    }

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(message.getBytes());
    }

    public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(encrypted);
    }
}
