/**
 * 
 */
package com.zhaoping.framework.mongodb;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hongxiao.shou
 *  @author hongxiao.shou
 * @Description:需要映射的字段
 * @Copyright: Copyright(c)2004-2014
 * @Company:联嘉云集团有限公司
 */

@Documented  
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.TYPE)  

public @interface FieldColumn {
	String valueStrings() ;
}
