/**
 * 
 */
package com.zhaoping.model;

import java.io.Serializable;

/**
 * @author hongxiao.shou
 *
 */
public class Result implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	public int code;
	public String info;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
