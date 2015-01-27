/**
 * 
 */
package com.zhaoping.model;

import java.io.Serializable;

/**
 * @author hongxiao.shou 省
 *
 */
public class Province implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	public int id=-1;

	public String provinceName="";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}
