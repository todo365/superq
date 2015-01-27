/**
 * 
 */
package com.zhaoping.model.constant;

/**
 * @author hongxiao.shou
 *
 */

public enum DeliverState {
	Send(1), Accept(2), Refuse(3), Offline(4);
	private final int value;

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	DeliverState(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
