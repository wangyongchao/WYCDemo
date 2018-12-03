package com.example.designmode.builder.definition;

public class Dircector {
    private Builder builder = new ConcreteBuilder();

    public Product getAProduct() {
        builder.setPart();
        return builder.buildProduct();
    }

    public static void main(String[] args) {

    }

}
