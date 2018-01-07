package com.example.designmode.simplefactory.stepsecond;

/**
 * 具体的创建器
 * @author Jason
 * QQ: 1476949583
 * @date 2015年12月4日
 * @version 1.0
 */
public class ConcreteCreator extends Creator{

	@Override
	protected Product factoryMethod() {
		return new ConcreteProduct();
	}

}
