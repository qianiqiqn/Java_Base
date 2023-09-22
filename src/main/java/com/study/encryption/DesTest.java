package com.study.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DesTest {


    public void DESExample () throws  Exception {
        String plainText = "Hello, World!";
        String key = "asdfghjk"; // 密钥长度必须是8个字符

        byte[] encryptedBytes = encrypt(plainText, key);
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decrypt(encryptedBytes, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    public static byte[] encrypt(String plainText, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(plainText.getBytes());
    }

    public static String decrypt(byte[] encryptedBytes, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

}
