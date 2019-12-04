package com.example.designmode.test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by wangyongchao on 2018/8/19.
 */

public class Test {
    public static void main(String[] args) {
        byte a = 123;
        short b = a;
        System.out.println(a + "," + b);
        byte[] bytes = new byte[2];
        bytes[0] = 22;
        bytes[1] = 123;
        short[] shorts = new short[bytes.length];

        System.out.println(bytesToShort(bytes)[0]);
        System.out.println(shortToBytes(bytesToShort(bytes))[1]);
    }

    public static short getShort(byte[] b, int index) {
        return (short) (((b[index + 1] << 8) | b[index + 0] & 0xff));
    }

    public static short[] getShort(byte[] bytes, short[] shorts) {
        for(int i=0;i<bytes.length;i++){
            shorts[i] = bytes[i];
        }
        return shorts;
    }


    public static short[] bytesToShort(byte[] bytes) {
        if(bytes==null){
            return null;
        }
        short[] shorts = new short[bytes.length/2];
        ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
        return shorts;
    }

    public static byte[] shortToBytes(short[] shorts) {
        if(shorts==null){
            return null;
        }
        byte[] bytes = new byte[shorts.length * 2];
        ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(shorts);

        return bytes;
    }

}
