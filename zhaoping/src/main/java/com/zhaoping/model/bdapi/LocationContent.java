/**
 * 
 */
package com.zhaoping.model.bdapi;

/**
 * @author hongxiao.shou
 *
 */
public class LocationContent {
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public AddressDetail getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(AddressDetail address_detail) {
		this.address_detail = address_detail;
	}
	
	public BdMapPoint getPoint() {
		return point;
	}

	public void setPoint(BdMapPoint point) {
		this.point = point;
	}

	public String address;
	public AddressDetail address_detail;
	public BdMapPoint point;

}
