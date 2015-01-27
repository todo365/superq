/**
 * 
 */
package com.zhaoping.model.cmodel;

import java.io.Serializable;

import com.zhaoping.model.constant.Education;

/**
 * @author hongxiao.shou
 *
 */
public class BackgroundEducation implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Education education = Education.H;
	
	public String beginDate;

	public String endDate;
	
	public String schoolName;
	
	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}
