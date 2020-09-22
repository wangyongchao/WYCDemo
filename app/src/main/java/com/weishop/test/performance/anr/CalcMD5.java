package com.weishop.test.performance.anr;

import android.os.Environment;

import com.weishop.test.util.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;


public class CalcMD5 {

    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

    public static void getMD5() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            File file = new File(externalStorageDirectory,
                    "test" + File.separator + "file" + File.separator + "testioblock1.txt");
            String md5 = calcMD5(file);
        }
        long endTime = System.currentTimeMillis();
        LogUtils.d("耗时:" + ((endTime - beginTime)) + "ms");
    }


    private static String calcMD5(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] buf = new byte[8192];
            int len;
            while ((len = fileInputStream.read(buf)) > 0) {
                digest.update(buf, 0, len);
            }
            return toHexString(digest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private static String toHexString(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }


}