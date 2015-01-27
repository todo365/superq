/**
 * 
 */
package com.zhaoping.model;

import java.io.Serializable;

import com.zhaoping.model.constant.DeliverState;
import com.zhaoping.model.constant.JobType;

/**
 * @author hongxiao.shou 职位投递表
 *
 */
public class Deliver implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id = -1;

	private int userId = -1;

	private String userTel;

	private String userName;

	private String jobName;

	private int companyId;

	private int jobId = -1;

	private DeliverState deliverState;

	private String sendDate;

	// 职位类型
	private JobType jobType;

	private boolean sex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJobType() {
		return jobType.ordinal();
	}

	public void setJobType(int jobType) {
		this.jobType = JobType.values()[jobType];
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getDeliverState() {
		return deliverState.ordinal();
	}

	public void setDeliverState(int deliverState) {
		this.deliverState = DeliverState.values()[deliverState];
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

}
