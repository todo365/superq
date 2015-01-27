/**
 * 
 */
package com.zhaoping.framework.mongodb;

/**
 * @author hongxiao.shou
 *
 */
public enum SortByDirection {
	ASC(1),Des(-1);
	 private final int value;

	    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
	 SortByDirection(int value) {
	        this.value = value;
	    }
	    
	    public int getValue() {
	        return value;
	    }
}
