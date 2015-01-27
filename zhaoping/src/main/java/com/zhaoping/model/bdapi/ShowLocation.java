/**
 * 
 */
package com.zhaoping.model.bdapi;

import java.io.Serializable;

/**
 * @author hongxiao.shou
 *
 */
public class ShowLocation  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private ShowLocationResult result;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ShowLocationResult getResult() {
		return result;
	}
	public void setResult(ShowLocationResult result) {
		this.result = result;
	}
	public int getPrecise() {
		return precise;
	}
	public void setPrecise(int precise) {
		this.precise = precise;
	}
	public int getConfidence() {
		return confidence;
	}
	public void setConfidence(int confidence) {
		this.confidence = confidence;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	private int precise;
	private int confidence;
	private String level;
	
}
