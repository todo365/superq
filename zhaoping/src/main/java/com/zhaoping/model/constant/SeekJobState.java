/**
 * 
 */
package com.zhaoping.model.constant;

/**
 * @author hongxiao.shou
 *
 */
public enum SeekJobState {
	Find(1), Over(2);
	private final int value;

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	SeekJobState(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
