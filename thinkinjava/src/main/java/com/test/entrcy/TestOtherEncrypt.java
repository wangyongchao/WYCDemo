package com.test.entrcy;

import java.security.MessageDigest;


/**
 * Created by wangyongchao on 16/12/20.
 */

public class TestOtherEncrypt {


    public static void main(String[] args) {
        String a = "aabb";
        try {
            System.out.println(Base64.encode(encryptMD5(a.getBytes())));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * MD5加密import static org.junit.Assert.*;
     * <p>
     * import org.junit.Test;
     * <p>
     * /**
     *
     * @author 梁栋
     * @version 1.0
     * @since 1.0
     */


    public static byte[] encryptMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);

        return md5.digest();

    }


}
