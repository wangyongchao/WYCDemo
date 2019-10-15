package com.example.designmode.prototype;

public class Thing implements Cloneable {

    @Override
    protected Thing clone() throws CloneNotSupportedException {
        Thing clone = (Thing) super.clone();
        return clone;
    }

    public static void main(String[] args) {
        try {
            Thing thing = new Thing();
            Thing clone = thing.clone();
            System.out.println(thing + "," + clone);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
