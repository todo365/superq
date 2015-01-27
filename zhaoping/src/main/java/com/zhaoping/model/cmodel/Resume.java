/**
 * 
 */
package com.zhaoping.model.cmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zhaoping.model.City;
import com.zhaoping.model.Province;
import com.zhaoping.model.constant.Education;
import com.zhaoping.model.constant.JobType;
import com.zhaoping.model.constant.SeekJobState;

/**
 * @author hongxiao.shou 简历
 */

public class Resume extends RUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private int province;

	private int jobCity;

	private String profession;

	private int education;

	private String college;

	// 求职类型
	private List<Integer> jobIntention = null;

	// // 教育背景
	// public List<BackgroundEducation> backgroundEducations = new
	// ArrayList<BackgroundEducation>();

	private String experience;

	private String introduction;

	// 目前求职状态
	private int jobState = 0;

	public int getProvince() {
		return province;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getJobCity() {
		return jobCity;
	}

	public void setJobCity(int jobCity) {
		this.jobCity = jobCity;
	}

	public String getCollege() {
		return college;
	}

	public int getEducation() {
		return education;
	}

	public void setEducation(int education) {
		this.education =education;
	}

	public List<Integer> getJobIntention() {
		// List<Integer> lists = new ArrayList<Integer>();
		// for (JobType edu : jobIntention) {
		// lists.add(edu.ordinal());
		// }
		//
		// return lists;
		return jobIntention;
	}

	public void setJobIntention(List<Integer> jobIntention) {
		// List<JobType> lists = new ArrayList<JobType>();
		// for (Integer edu : jobIntention) {
		// lists.add(JobType.values()[edu]);
		// }
		this.jobIntention = jobIntention;
	}

	public int getJobState() {
		return jobState;
	}

	public void setJobState(int jobState) {
		this.jobState =jobState;
	}

	public void setCollege(String college) {
		this.college = college;
	}

}
