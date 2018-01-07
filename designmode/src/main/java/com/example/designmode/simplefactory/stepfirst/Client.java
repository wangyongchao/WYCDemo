package com.example.designmode.simplefactory.stepfirst;

import java.util.ArrayList;
import java.util.List;

public class Client {

	public static void main(String[] args) {
		String data = "我的数据";
		ExportDBOperator op = new ExportDBOperator();
		op.export(data);
		
		ExportExcelOperator op2 = new ExportExcelOperator();
		op2.export(data);
		
		//List Creator 
		//ArrayList ConcreteCreator具体的创建者
		List<String> list = new ArrayList<String>();
		list.iterator();
		
		//Iterator Product 
		//ArrayListIterator ConcreteProduct具体的产品
		
	}

}
