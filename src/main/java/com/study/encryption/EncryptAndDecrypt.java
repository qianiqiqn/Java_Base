package com.study.encryption;

import java.security.*;
import java.util.Base64;

public class EncryptAndDecrypt {


    /**
     * 尝试加解密
     * @throws Exception
     */
    public void encryptAndDecrypt() throws Exception {
        String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgxB2xV0dzlyauCIJaPAf4zzLTCgeIFUylk5Nn3l+aKpdvvoYTiMdM/IuriEZWP0SgPBewcjL5s29Z68RDLLNMkUgPNpnDPMGLgNmEb7Tspdv535qKCBFO/OdYk8zVYhwpWluNAI97xcVeKDv6mcPfLDyIbYJKz4f6ezusT0RBa+fOFY1zbTzw7gvYuxTLYP4NYbKb5oUZqcBaCQo1gXu9H+oqjpx5hvWgn5N+Y/F4Rt5+dQnG76TuhH9aJkaNPqRqL3GXMS2eDf7BJ2qkOb1nu3881C7DE855cd1h8rSrv1/YRynyG4gI/8fRdLVrGhBQqXGlTsJRGVXAOLUrqrK4QIDAQAB";
        String privateKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDEHbFXR3OXJq4Iglo8B/jPMtMKB4gVTKWTk2feX5oql2++hhOIx0z8i6uIRlY/RKA8F7ByMvmzb1nrxEMss0yRSA82mcM8wYuA2YRvtOyl2/nfmooIEU7851iTzNViHClaW40Aj3vFxV4oO/qZw98sPIhtgkrPh/p7O6xPREFr584VjXNtPPDuC9i7FMtg/g1hspvmhRmpwFoJCjWBe70f6iqOnHmG9aCfk35j8XhG3n51CcbvpO6Ef1omRo0+pGovcZcxLZ4N/sEnaqQ5vWe7fzzULsMTznlx3WHytKu/X9hHKfIbiAj/x9F0tWsaEFCpcaVOwlEZVcA4tSuqsrhAgMBAAECggEAAU5PsKW930mC7STBrT7/QldfuzGHI1FWALNlCJrATLCt4HJDfbvR1aurFWcVBpL29Tvv5cRna7RPF9JhvBun+cFAkfqnHjTpsdms0a5yE4x4u/jBfAbeSbuirNEXNNn4LV3AkFoZXeGAAESyP0apm9dzgix7KOgkj0MiGbf/DGxw07UPKUYzC2fy25xPDCCfkt/IkikUKeBR1yKrcCtXNeOKgQnzWSzHlX/7E2vTa1SYo45TkA0YCcBdh6AqjGnmMoBo64NvZE9UjCGdVNWw4M8s6CzinaCfrn2ixm6YIyDKomRcQXutDkESwE4ft6wbn7t1lhOVWtx74lFC9w5c9QKBgQDfcvLLUJbrJ6+WaLB38yT2rh2QuLh11YbjWRFt2xBilYlf41z79YDyv0bcm+vU3uX7K+mlg7DNpRYcasjcAsYaY/Fp/g2rg59e9Ys4ukOqzeWqEBL5GSrNqyrICxm1ZWLxtCudCNo+uJDm4UXJ6omBTurYiZ/foS2v9zqSjQGYvwKBgQCWKDnpYL/R4Qji2mnb11CeJgu/B2LdT3dCu16PhwDEu/jf4CtS/9gkVssD1HdP0ft8ZHjE4FK8u9VZWatSvymkMiJxke6/yMwFulQgHl8wvvpSW62R65mFRj+pJegrHh2WOngKauypYsFUeJgDmrwianwUQT+mIYQzEp30YjfkXwKBgGDz6000hoc6DQHiIs+o/FzcZ4+n1sGqCIEU4G8eIhnkcFreuDXK7ExnmkOWk9klbfxxrUtXvJs+Cj4CR2zO9030F4e56AyHa9s0B1hx2XJ/LCuCFflnZh8fKczqpBkFCM1/M31Lngag5p6HGFToIwmsSFH0RR4vPTeguhVkgVDZAoGAJCqSMETuTrXd9w3WW24T69OMFrnHtThTqE4K65T/uWLxAbMznC0lOTNyeHyvNBmGciKc+AZzxyOSso+ndSm39OViaGpWz/ikM2j6pDXMhagqELYmKzRM6M3PvYT005XQWIPCr/erAu1XVWoqawvU7pEpS5Ofqfr+pXKBewBBKA8CgYEAqgLZSkSj5bruFrbOQ46Mk9BIuWbIMb2CKphIykLHdBJRzZb+DiDBTSjMetdPGY+HJAfHvl8fFXPjUx9kzNKyd7U9U8r6sCOIYQ5aMn8ag93qB/fakwus2yg8ti1/wTUypTRMus6XaQ0jTM3DhJnwPaW25iChQUtNfUb/8wOKvuw=";


        System.out.println("-----------------生成的公钥和私钥------------------------------");
        System.out.println("获取到的公钥：" + publicKeyStr);
        System.out.println("获取到的私钥：" + privateKeyStr);
        // 待加密数据
        String data = "123456";
        // 公钥加密
        System.out.println("-----------------加密和解密------------------------------");
        System.out.println("待加密的数据：" + data);
        String encrypt = RsaUtil.encryptByPublicKey(data, publicKeyStr);
        System.out.println("加密后数据：" + encrypt);
        System.out.println("加密后数据长度：" + encrypt.length());

        String password = "ElIkK7KpVrCzWhusBuBQxw1lAD8z2CNzrgPMjowo9j/NbqxPxgzld2038ML4/l/A4LJJH6SF0/UFZR6MVjVCdIGcVjPq8MrqTFojs2DE7/XlwrMBO7jbbl1RdTpPzWuKBDAwbWGt6WopwyC91CE5PG6eU2q6k6WxtDcGh1H7sSg2vAIFJpMwq30KWGa3PPsIUaWuWs5SPlF3P7wN4CLFtpvqK/eiUV42VnWY4LhtXYKVvuLyWjnFrw9M5YHmoNPrdskY65YjfSOFwfTytrteh0OQhig4BmMf0t6ydcfmSABhxXOWyjnAf9p0sD4YMbWxZjRwlvV77i4kgomDzaOeEw==";
        // 私钥解密
        String decrypt = RsaUtil.decryptByPrivateKey(password, privateKeyStr);
        System.out.println("解密后数据：" + decrypt);

//        String orStr = RsaUtil.decryptByPublicKey("gZBpqUoT+/yqY2VphrZNJSXraxLhaIdp79CYxPB/UgvdE2DqUYEV4WeO/VzSqTZf/AG9J+61YduLYDOXcmp8bM1SChMwrYCUlTp+Dy43AtZmQbcrtIj0NBIBDQ0GqUvPc1HtocfxVvNhsKkUI6H90KukyjCejTqeCSwXkEmVIQ+VpczFCGfothNbT6l8Kn+w9yOHJLJhm7Up9qU2M9uNf8l9Lklyu3X0i+WYVWerSA7TMaVha8jmZWxhMLahwbLkjKHodg90YW7KczNuBjZ2EKrjRjKi+PeWXGBGH6YQi5lpGrl31ipXC0WAwCHCVVfQ2ro+/xvekamvR06Ys+zmUQ==",
//                publicKeyStr);
//        System.out.println("公钥解密：" + orStr);
    }


    /**
     * 生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public void creatKey() throws NoSuchAlgorithmException {
        // 生成密钥对对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        // 初始化密钥对生成器
        keyPairGenerator.initialize(2048);

        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 获取公钥和私钥
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("公钥：" + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("私钥：" + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
    }
}
