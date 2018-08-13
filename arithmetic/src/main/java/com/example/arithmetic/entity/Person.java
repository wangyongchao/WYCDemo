package com.example.arithmetic.entity;

/**
 * Created by wangyongchao on 2018/8/13.
 */

public class Person {
    private String idCard;
    private String name;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Person) {
            Person person = (Person) o;
            return person.getIdCard() == this.getIdCard();
        }
        return false;
    }
}
