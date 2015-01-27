/**
 * 
 */
package com.zhaoping.model.constant;


/**
 * @author hongxiao.shou
 *工资支付方式
 */

// 日 月 小时
public enum SalaryWay {

	Month(1), day(2), Hour(3);
	private final int value;

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	SalaryWay(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
