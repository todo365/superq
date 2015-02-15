package com.zhaoping.model;

import java.io.Serializable;

/**
 * Created by shouhongxiao on 2015/2/15.
 */
public class ResultList implements Serializable {

	private static final long serialVersionUID = 1L;
	private int cityid;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	private long count;

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
}
