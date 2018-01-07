package com.example.designmode.simplefactory.stepsecond;

/**
 * 创建器
 * @author Jason
 * QQ: 1476949583
 * @date 2015年12月4日
 * @version 1.0
 */
public abstract class Creator {

	/**
	 * 创建Product的工厂方法
	 * @return
	 */
	protected abstract Product factoryMethod();
	
	/**
	 * 示意，会使用产品的某些功能
	 */
	public void someOperation(){
		Product p = factoryMethod();
		//使用产品
		//p.
	}
	
	
}
