package com.study.encryption;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *  AES加密
 */
public class AesTest {

    /**
     * AES-GCM 认证加密需要用到以下参数：
     *
     * 待加密的明文: data
     * 密钥 : key
     * 初始向量 IV
     * 其他认证数据: additional authenticated data (AAD)
     *
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     */

    public void GCM() throws IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidAlgorithmParameterException {

        String data = "Hello World 2!"; // 待加密的原文
        String key = "12345678abcdefgh"; // key 长度只能是 16、24 或 32 字节
        String iv = "iviviviviviviviv22";
        String aad = "aad22222"; // AAD 长度无限制，可为空

        byte[] ciphertext = encryptGCM(data.getBytes(), key.getBytes(), iv.getBytes(), aad.getBytes());
        System.out.println("GCM 模式加密结果（Base64）：" + Base64.getEncoder().encodeToString(ciphertext));

        byte[] plaintext = decryptGCM(ciphertext, key.getBytes(), iv.getBytes(), aad.getBytes());
        System.out.println("解密结果：" + new String(plaintext));


    }


    public static byte[] encryptGCM(byte[] data, byte[] key, byte[] iv, byte[] aad) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), new GCMParameterSpec(128, iv));
        cipher.updateAAD(aad);
        byte[] result = cipher.doFinal(data);
        return result;
    }

    public static byte[] decryptGCM(byte[] data, byte[] key, byte[] iv, byte[] aad) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new GCMParameterSpec(128, iv));
        cipher.updateAAD(aad);
        byte[] result = cipher.doFinal(data);
        return result;
    }



}
