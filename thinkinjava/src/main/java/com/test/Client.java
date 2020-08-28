package com.test;

import java.util.ArrayList;

public class Client {
    public static final String obj1 = "obj1";
    public static final String obj2 = "obj2";
    public static boolean condition = false;
    public String a;
    public String b;

    public static void main(String[] ars) {

        Client client = new Client();

        ArrayList<String> list = new ArrayList<>();
        list.add(client.a);
        System.out.println(list.contains(client.b));


    }

    public static boolean isValidUUID(String uuid) {
        // UUID校验
        if (uuid == null) {
            System.out.println("uuid is null");
        }
        String regex = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
        if (uuid.matches(regex)) {
            return true;
        }
        return false;
    }

}