package com.test.project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class MD5Utils {


    public static void main(String[] args) {
		String s="Nice going! You\\'re about to break your last record!";
		System.out.println(getMD5(s));
    }

    private final static String HEX = "0123456789abcdef";

    public static String getMD5(String data) {
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            digester.update(data.getBytes(), 0, data.getBytes().length);
            byte[] digest = digester.digest();
            return toHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
}
