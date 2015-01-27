/**
 * 
 */
package com.zhaoping.model.bdapi;

import java.io.Serializable;

/**
 * @author hongxiao.shou
 *
 */
public class Locationlocation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float lng;
	private float lat;
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	
	
}
