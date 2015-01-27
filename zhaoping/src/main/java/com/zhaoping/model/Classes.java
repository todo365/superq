/**
 * 
 */
package com.zhaoping.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hongxiao.shou
 *
 */
public class Classes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String grade;
	private int level;

	private List<Depatment> depatment = new ArrayList<Depatment>();

	public List<Depatment> getDepatment() {
		return depatment;
	}

	public void setDepatment(Depatment d) {
		depatment.add(d);
	}

	public Classes() {
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
