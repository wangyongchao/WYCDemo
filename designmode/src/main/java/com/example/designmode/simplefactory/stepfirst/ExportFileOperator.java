package com.example.designmode.simplefactory.stepfirst;

public class ExportFileOperator extends ExportOperate{

	@Override
	public ExportFileApi newFileApi() {
		return new ExportTextFile();
	}
	
}
