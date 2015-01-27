/**
 * 
 */
package com.zhaoping.model.company;

import java.io.Serializable;

/**
 * @author hongxiao.shou五险 公积金，包吃包住 ，长白班，年底双薪，年终奖金
 *
 */

public class JobLabel implements Serializable {

	

	private static final long serialVersionUID = 1L;

	private int id=-1;

	private String jobLabelName;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobLabelName() {
		return jobLabelName;
	}

	public void setJobLabelName(String jobLabelName) {
		this.jobLabelName = jobLabelName;
	}

}
