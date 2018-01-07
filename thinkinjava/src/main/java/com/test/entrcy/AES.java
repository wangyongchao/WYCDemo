package com.test.entrcy;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Administrator
 */
public class AES {
    public static final String ALGORITHM = "AES";


    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {

        byte[] raw = Base64.decode(sKey);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return Base64.encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            byte[] raw = Base64.decode(sKey);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64.decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void testDigest(String key) {
        byte[] raw = key.getBytes();
        System.out.println(raw.length);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        byte[] encoded = skeySpec.getEncoded();
        System.out.println("secret=" + Base64.encode(encoded));
    }


    //生成key
    public static void initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;

        if (seed != null) {
            secureRandom = new SecureRandom(Coder.decryptBASE64(seed));
        } else {
            secureRandom = new SecureRandom();
        }

        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
        kg.init(secureRandom);

        SecretKey secretKey = kg.generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        System.out.println("key 长度=" + keyBytes.length);
        System.out.println("key字符串=" + new String(keyBytes));
        System.out.println("key Base64=" + Base64.encode(secretKey.getEncoded()));
    }

    private static Key toKey(byte[] key) throws Exception {
         SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);

        return secretKey;
    }

    public static void main(String[] args) throws Exception {
        String cKey = "6nefZBH0SqGV76njvjoVxg==";
        String src = "adasd";
        String encrypt = Encrypt(src, cKey);
        String decrypt = Decrypt(encrypt, cKey);
        System.out.println("encrypt="+encrypt+",decrypt="+decrypt);


//        /*
//         * 此处使用AES-128-ECB加密模式，key需要为16位。
//         */
//        String cKey = "1234567890123456";
//        int length = cKey.getBytes().length;
//        System.out.println(length);
//        // 需要加密的字串
//        String cSrc = "www.gowhere.so";
//        System.out.println(cSrc);
//        // 加密
//        String enString = AES.Encrypt(cSrc, cKey);
//        System.out.println("加密后的字串是：" + enString);
//
//        // 解密
//        String DeString = AES.Decrypt(enString, cKey);
//        System.out.println("解密后的字串是：" + DeString);
    }
}

//源代码片段来自云代码http://yuncode.net
