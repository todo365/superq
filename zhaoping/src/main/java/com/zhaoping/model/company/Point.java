/**
 * 
 */
package com.zhaoping.model.company;

import java.io.Serializable;

/**
 * @author hongxiao.shou
 *
 */
public class Point implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int companyId;
	public int point;
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

}
