/**
 * 
 */
package com.zhaoping.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author hongxiao.shou
 *
 */
public class User implements Serializable{
	public String username;
	public String nickname;
	
	
	//private List<Classes> classes;
	

//	public User(String nickname,String username)
//	{
//		this.nickname=nickname;
//		this.username=username;
//	}

//	public List<Classes> getClasses() {
//		return classes;
//	}


//	public void setClasses(List<Classes> classes) {
//		this.classes = classes;
//	}


	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}