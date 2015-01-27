/**
 * 
 */
package com.zhaoping.model.cmodel;

import java.io.Serializable;

import org.bson.types.ObjectId;

import com.zhaoping.model.City;
import com.zhaoping.model.MapPoint;
import com.zhaoping.model.Province;

/**
 * @author hongxiao.shou
 *
 */
public class RUser implements Serializable {

	/**
	 * 用户
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private String phone;

	private String regDate;

	private String pwd;

	private boolean sex;

	private String bornDate;

	private Province bornProvince;

	private City bornCity;

	private String ip;

	private MapPoint mapPoint;
	
	private int bornProvinceId;
	
	public int getBornProvinceId() {
		return bornProvinceId;
	}

	public void setBornProvinceId(int bornProvinceId) {
		this.bornProvinceId = bornProvinceId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 最后mapx ,mapy
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// public MapPoint getMapPoint() {
	// return mapPoint;
	// }
	//
	// public void setMapPoint(MapPoint mapPoint) {
	// this.mapPoint = mapPoint;
	// }
//	public MapPoint getMapPoint() {
//		return mapPoint;
//	}
//
//	public void setMapPoint(Double x, Double y) {
//		this.mapPoint.setMapX(x);
//		this.mapPoint.setMapY(y);
//	}

	public String getName() {
		return name;
	}

	public MapPoint getMapPoint() {
		return mapPoint;
	}

	public void setMapPoint(MapPoint mapPoint) {
		this.mapPoint = mapPoint;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Province getBornProvince() {
		return bornProvince;
	}

	public void setBornProvince(Province bornProvince) {
		this.bornProvince = bornProvince;
	}

	public City getBornCity() {
		return bornCity;
	}

	public void setBornCity(City bornCity) {
		this.bornCity = bornCity;
	}

	public String getBornDate() {
		return bornDate;
	}

	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}

}
