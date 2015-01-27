/**
 * 
 */
package com.zhaoping.model;

import java.io.Serializable;

/**
 * @author hongxiao.shou 区
 *
 */
public class Area implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	public int id;
	
	public String areaName;
	
	public int cityId;
	
	public String mapX;
	
	public String mapY;

	public int getAreaId() {
		return id;
	}

	public void setAreaId(int id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getMapX() {
		return mapX;
	}

	public void setMapX(String mapX) {
		this.mapX = mapX;
	}

	public String getMapY() {
		return mapY;
	}

	public void setMapY(String mapY) {
		this.mapY = mapY;
	}
	
	
}
