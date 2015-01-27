/**
 * 
 */
package com.zhaoping.framework.mongodb;

/**
 * @author hongxiao.shou
 *
 */
public class MongoElemnt {

	private String mongoName;
	private String addr;
	private int port;
	private Boolean readOnly;
	private Boolean enable;
	
	public String getMongoName() {
		return mongoName;
	}

	public void setMongoName(String mongoName) {
		this.mongoName = mongoName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
