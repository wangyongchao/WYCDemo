package com.test.entrcy;

import java.util.HashSet;
import java.util.Map;

/**
 * Created by wangyongchao on 16/12/20.
 */

public class TestRSAEncrypt {
    private String publicKey;
    private String privateKey;
    private static HashSet<String> hashSet = new HashSet<>();


    public static void main(String[] args) {

        TestRSAEncrypt encrypt = new TestRSAEncrypt();
        try {
            encrypt.setUp();
            encrypt.testPri2Pub();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setUp() throws Exception {
        Map<String, Object> keyMap = RSACoder.initKey();

        publicKey = RSACoder.getPublicKey(keyMap);
        privateKey = RSACoder.getPrivateKey(keyMap);
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);
    }

    public void testPub2Pri() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String inputStr = "abc";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);

        byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
                privateKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);

    }

    public void testPri2Pub() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String inputStr = "sign";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);

        byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);

        System.err.println("私钥签名——公钥验证签名");
        // 产生签名   这里的encodedData可以与下面的encodedData同时换成new int[]{2,45}
        String sign = RSACoder.sign(encodedData, privateKey); //数字签名只要公钥人拿到签名的sign对比
        //，自己公钥通过同样的byte[]运算得到签名是否一致。是到致代表这个公钥就是对的，就是为现在发私钥人服务的。
        System.err.println("签名:\r" + sign);

        // 验证签名
        boolean status = RSACoder.verify(encodedData, publicKey, sign);
        System.err.println("状态:\r" + status);

    }
}
