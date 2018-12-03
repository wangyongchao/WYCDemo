package com.example.designmode.builder.definition;

public class ConcreteBuilder extends Builder {
    Product product = new Product();


    @Override
    public void setPart() {

    }

    @Override
    public Product buildProduct() {
        return product;
    }
}
