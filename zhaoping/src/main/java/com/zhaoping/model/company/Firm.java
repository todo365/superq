/**
 * 
 */
package com.zhaoping.model.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zhaoping.model.City;
import com.zhaoping.model.Province;

/**
 * @author hongxiao.shou
 *
 */
public class Firm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int id = -1;
	public String name;
	public String contactMan;
	public String contactTel;
	public int province;
	public int city;
	public String address;
	public String introduction;
	public String license;
	// 劳动许可证
	public String allowLicense;
	// 人力资源许可证
	public String humanLicense;

	public String firmLogo;

	public String firmPhoto;

	public String mail;
	
	public int point=50;
	
	public String pwd;
	

//	public List<Integer> jobs = new ArrayList<Integer>();
//
//	public List<Integer> getJobs() {
//		return jobs;
//	}
//
//	public void setJobs(int id) {
//		jobs.add(id);
//	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactMan() {
		return contactMan;
	}

	public void setContactMan(String contactMan) {
		this.contactMan = contactMan;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getAllowLicense() {
		return allowLicense;
	}

	public void setAllowLicense(String allowLicense) {
		this.allowLicense = allowLicense;
	}

	public String getHumanLicense() {
		return humanLicense;
	}

	public void setHumanLicense(String humanLicense) {
		this.humanLicense = humanLicense;
	}

	public String getFirmLogo() {
		return firmLogo;
	}

	public void setFirmLogo(String firmLogo) {
		this.firmLogo = firmLogo;
	}

	public String getFirmPhoto() {
		return firmPhoto;
	}

	public void setFirmPhoto(String firmPhoto) {
		this.firmPhoto = firmPhoto;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	// public String filmProperty

}
