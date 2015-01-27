/**
 * 
 */
package com.zhaoping.model.bdapi;

import java.io.Serializable;

/**
 * @author hongxiao.shou
 *
 */
public class BdMapPoint  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String x;
	public String y;

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

}
