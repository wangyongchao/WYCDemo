package com.example.designmode.builder.definition;

public abstract class Builder {
    //设置产品的不同部分，以获得不同的产品
    public abstract void setPart();

    public abstract Product buildProduct();
}
