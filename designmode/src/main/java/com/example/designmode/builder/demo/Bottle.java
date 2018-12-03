package com.example.designmode.builder.demo;

public class Bottle implements Packing {
 
   @Override
   public String pack() {
      return "Bottle";
   }
}