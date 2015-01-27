/**
 * 
 */
package com.zhaoping.framework.mongodb;

import java.util.Map;

/**
 * @author hongxiao.shou
 * @Description:mongod配值类
 * @Copyright: Copyright(c)2004-2014
 * @Company:联嘉云集团有限公司
 */
public class MongoConfig {

	private String userName;
	private int maxthreadqueue;
	private int maxconnetion;
	private String pwd;
	private String dataBase;
	private String name;
	private Map<String, MongoElemnt> mongoList;
	private String key;
	private int maxwaittime;
	private int connecttimeout;
	private int maxconnectionlifetime;
	private int sockettimeout;
	public int getMaxwaittime() {
		return maxwaittime;
	}

	public void setMaxwaittime(int maxwaittime) {
		this.maxwaittime = maxwaittime;
	}

	public int getConnecttimeout() {
		return connecttimeout;
	}

	public void setConnecttimeout(int connecttimeout) {
		this.connecttimeout = connecttimeout;
	}

	public int getMaxconnectionlifetime() {
		return maxconnectionlifetime;
	}

	public void setMaxconnectionlifetime(int maxconnectionlifetime) {
		this.maxconnectionlifetime = maxconnectionlifetime;
	}

	public int getSockettimeout() {
		return sockettimeout;
	}

	public void setSockettimeout(int sockettimeout) {
		this.sockettimeout = sockettimeout;
	}


	

	public MongoConfig(String key) {
		if (key == null || key.length() <= 0)
			this.key = "log";
		else
			this.key = key;

	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public MongoConfig() {
		this.key = "log";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDataBase() {
		return dataBase;
	}

	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxconnetion() {
		return maxconnetion;
	}

	public void setMaxconnetion(int maxconnetion) {
		this.maxconnetion = maxconnetion;
	}

	public int getMaxthreadqueue() {
		return maxthreadqueue;
	}

	public void setMaxthreadqueue(int maxthreadqueue) {
		this.maxthreadqueue = maxthreadqueue;
	}

	public Map<String, MongoElemnt> getMongoList() {
		return mongoList;
	}

	public void setMongoList(Map<String, MongoElemnt> mongoList) {
		this.mongoList = mongoList;
	}

}