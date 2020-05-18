package com.example.arithmetic.search;

public class Person {
    private int id;
    private String name;

    public Person(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public String toString() {
        String str = "id" + id;
        return str;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Person)) {
            return false;
        }
        return ((Person) object).id == this.id;
    }
}
