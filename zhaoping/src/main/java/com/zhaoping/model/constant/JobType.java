/**
 * 
 */
package com.zhaoping.model.constant;

/**
 * @author hongxiao.shou
 *
 */
public enum JobType {
	专职(1), 兼职(2), 小时工(3), 推销员(4);
	private final int value;

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	JobType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
