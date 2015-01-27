/**
 * 
 */
package com.zhaoping.framework.mongodb;

/**
 * @author hongxiao.shou
 * @Description:操作条件
 * @Copyright: Copyright(c)2004-2014
 * @Company:联嘉云集团有限公司
 */

////"$gt"： 大于    
//       //"$gte"：大于等于    
//       //"$lt"： 小于    
//       //"$lte"：小于等于    
//       //"$in"： 包含   
// $in 
//  $nin  
//  $or  

public enum Condition {
	  GET("$gte"), GT("$gt"),LT("$lt"),LTE("$lte"),IN("$in"),NIN("$nin"),OR("$or"),NEAR("$near"),ELEMMATCH("$elemMatch");
	    
	    private final String value;

	    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
	    Condition(String value) {
	        this.value = value;
	    }
	    
	    public String getValue() {
	        return value;
	    }
	 
}
