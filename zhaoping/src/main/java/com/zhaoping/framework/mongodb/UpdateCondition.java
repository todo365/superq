/**
 * 
 */
package com.zhaoping.framework.mongodb;

/**
 * @author hongxiao.shou
 *$addToSet适用于数组，如果数组中该元素已经存在，该命令就不做任何操作后返回，否则将新元素插入数组
 *$push 如果其操作的键不存在，则创建新的键值，其值的类型为数组类型,如果$push操作的键值已经存在，且其值为数组类型，该修改符将为该数组添加新的数组元素。
 *$pop从数组中删除一个元素，如参数为1，表示从数组的尾部删除一个元素，如果是-1，则从头部删除
 *$pull修改符则是从数据中删除指定的元素
 */
public enum UpdateCondition {
	  PUSH("$push"), POP("$pop"),PULL("$pull"),ADDTOSET("$addToSet"),SET("$set");
	    
	    private final String value;

	    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
	    UpdateCondition(String value) {
	        this.value = value;
	    }
	    
	    public String getValue() {
	        return value;
	    }
}
