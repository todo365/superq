/**
 * 
 */
package com.zhaoping.model;

import java.io.Serializable;

/**
 * @author hongxiao.shou
 *
 */
public class Depatment implements Serializable
{
	private String name;
	private String techname;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTechname() {
		return techname;
	}
	public void setTechname(String techname) {
		this.techname = techname;
	}
	
}
