/**
 * 
 */
package com.zhaoping.model.constant;

/**
 * @author hongxiao.shou
 *
 */
public enum UserTrackState {
	Login(1), Register(2), UpdateResume(3);
	private final int value;

	UserTrackState(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
