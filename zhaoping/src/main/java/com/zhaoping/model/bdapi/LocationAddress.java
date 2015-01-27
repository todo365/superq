/**
 * 
 */
package com.zhaoping.model.bdapi;

import java.io.Serializable;

/**
 * @author hongxiao.shou
 *
 */
public class LocationAddress implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String address;
	public int status;


	public LocationContent content;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	

	public LocationContent getContent() {
		return content;
	}

	public void setContent(LocationContent content) {
		this.content = content;
	}

}
