/**
 * 
 */
package com.zhaoping.model.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zhaoping.model.Area;
import com.zhaoping.model.City;
import com.zhaoping.model.MapPoint;
import com.zhaoping.model.Province;
import com.zhaoping.model.constant.JobType;
import com.zhaoping.model.constant.SalaryWay;

/**
 * @author hongxiao.shou 职位表
 *
 */
public class JobChance implements Serializable {

	private static final long serialVersionUID = 1L;

	// 这就是jobid
	private int id = -1;

	// 发这个职位的公司
	private int companyId = -1;

	// 职位类型专职(1), 兼职(2), 小时工(3), 推销员(4);
	private int jobType;

	private String jobName;

	// 计薪方式
	private int level;

//	public int lowSalary = 0;
//
//	public int highSalary = 0;

	// 工作标签
	private List<Integer> labels = null;

	public List<Integer> getLabels() {
		return labels;
	}

	public void setLabels(List<Integer> labels) {
		this.labels = labels;
	}

	private int sex;

	private int lowAge = 20;

	private int highAge = 50;

	private String contactMan;

	private String contactTel;

	private String email;

	private String introduction;

	private int province;

	private int city;

	// public Area area;

	private MapPoint mapPoint;

	private String createJobTime;

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCreateJobTime() {
		return createJobTime;
	}

	public void setCreateJobTime(String createJobTime) {
		this.createJobTime = createJobTime;
	}

	public int getJobType() {
		return this.jobType;
	}

	public void setJobType(int jobType) {
		this.jobType = jobType;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level =level;
	}

//	public int getLowSalary() {
//		return lowSalary;
//	}
//
//	public void setLowSalary(int lowSalary) {
//		this.lowSalary = lowSalary;
//	}
//
//	public int getHighSalary() {
//		return highSalary;
//	}
//
//	public void setHighSalary(int highSalary) {
//		this.highSalary = highSalary;
//	}

	

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getLowAge() {
		return lowAge;
	}

	public void setLowAge(int lowAge) {
		this.lowAge = lowAge;
	}

	public int getHighAge() {
		return highAge;
	}

	public void setHighAge(int highAge) {
		this.highAge = highAge;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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

	// public Area getArea() {
	// return area;
	// }
	//
	// public void setArea(Area area) {
	// this.area = area;
	// }

	public MapPoint getMapPoint() {
		return mapPoint;
	}

	public void setMapPoint(MapPoint mapPoint) {
		this.mapPoint = mapPoint;
	}

}
