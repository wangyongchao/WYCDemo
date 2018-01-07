package com.example.designmode.simplefactory.test;


import com.example.designmode.simplefactory.Api;
import com.example.designmode.simplefactory.Factory;

public class Client {

	public static void main(String[] args) {
		
		Api obj = Factory.create(2);
		
		obj = Factory.create(3);
		
		//obj = new Impl
		
	}

}
