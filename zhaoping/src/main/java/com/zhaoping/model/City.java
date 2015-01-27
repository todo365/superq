/**
 * 
 */
package com.zhaoping.model;

import java.io.Serializable;

/**
 * @author hongxiao.shou 市
 *
 */
public class City implements Serializable {

	private static final long serialVersionUID = 1L;

	public int id=1;

	public String cityName="";

	public int provinceId=-1;

	//public MapPoint mapPoint;

//	public int getCityId() {
//		return id;
//	}
//
//	public void setCityId(int id) {
//		this.id = id;
//	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public MapPoint getMapPoint() {
//		return mapPoint;
//	}
//
//	public void setMapPoint(MapPoint mapPoint) {
//		this.mapPoint = mapPoint;
//	}

}
