package com.example.designmode.prototype;

import java.util.ArrayList;

public class PrototypeClass implements Cloneable {

    private ArrayList<String> list = new ArrayList();

    public Object object = new Object();

    public void addValues(String value) {
        list.add(value);
    }

    public ArrayList getValues() {
        return list;
    }

    public PrototypeClass() {
        System.out.println("Construct");
    }

    @Override
    protected PrototypeClass clone() throws CloneNotSupportedException {
        PrototypeClass prototypeClass = (PrototypeClass) super.clone();
        return prototypeClass;
    }


    public static void main(String[] args) {
        try {
            PrototypeClass prototypeClass = new PrototypeClass();
            prototypeClass.addValues("aaa");
            PrototypeClass clone = prototypeClass.clone();
            clone.addValues("bbbb");
            System.out.println(clone + "," + clone.getValues());
            System.out.println(prototypeClass + "," +prototypeClass.getValues());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
