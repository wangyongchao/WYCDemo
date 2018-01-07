package com.example.designmode.simplefactory.stepfirst;

public class ExportExcelFile implements ExportFileApi {

	@Override
	public void export(String data) {
		System.out.println("Excel表格");
	}

}
