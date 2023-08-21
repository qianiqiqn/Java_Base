package com.study.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.util.HashMap;
import java.util.Map;

public class PwdUtil {

    public static void main(String[] args) throws Exception {
        System.out.println(encrypt("aB+eTr6%gL#2"));

//        System.out.println(decrypt("fYcnDSWa3/vnpEipvdBzGF9rIKkMtg9EksGpxlD48uKi15hQUC95VL9/eW2kDtDno/GLsecfdJTaZrvFzM63LQ==",
//                "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAICrVzD6Jx0/iGjeRWKZdc4pVkNDsZaqjcUBJzKck6Q05lyfFeQ9xBiMUtHPEt2k19tLAOakQEoMOyrO4lNfbAcCAwEAAQ=="));

    }

    public static Map<String,String> encrypt(String password) throws Exception{
        Map<String,String> resultMap = new HashMap<>();
        String[] keyPair = ConfigTools.genKeyPair(512);
        //私钥
        String privateKey = keyPair[0];
        //公钥
        String publicKey = keyPair[1];
        //用私钥加密后的密文
        password = ConfigTools.encrypt(privateKey, password);

        resultMap.put("password",password);
        resultMap.put("publicKey", publicKey);

        return resultMap;
    }

    public static String decrypt(String password, String publicKey) throws Exception {
        return ConfigTools.decrypt(publicKey, password);
    }
}
