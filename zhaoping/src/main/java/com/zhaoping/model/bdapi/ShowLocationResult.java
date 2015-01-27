/**
 * 
 */
package com.zhaoping.model.bdapi;

import java.io.Serializable;

/**
 * @author hongxiao.shou
 *
 */
public class ShowLocationResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Locationlocation getLocation() {
		return location;
	}

	public void setLocation(Locationlocation location) {
		this.location = location;
	}

	private Locationlocation location;
}
