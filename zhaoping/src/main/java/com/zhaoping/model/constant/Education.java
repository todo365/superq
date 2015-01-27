/**
 * 
 */
package com.zhaoping.model.constant;

/**
 * @author hongxiao.shou
 *
 */
public enum Education {
	HE(1), HS(2), MS(3), H(4);
	private final int value;

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	Education(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
