package com.example.designmode.simplefactory.stepfirst;

public class ExportExcelOperator extends ExportOperate {

	@Override
	public ExportFileApi newFileApi() {
		return new ExportExcelFile();
	}

}
