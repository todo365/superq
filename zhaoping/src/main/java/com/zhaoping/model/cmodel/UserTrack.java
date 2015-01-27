/**
 * 
 */
package com.zhaoping.model.cmodel;

import java.io.Serializable;

import com.zhaoping.model.MapPoint;
import com.zhaoping.model.constant.UserTrackState;

/**
 * @author hongxiao.shou 用户轨迹
 *
 */
public class UserTrack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int id;
	public MapPoint mapPoint;
	public String ip;
	public UserTrackState userTrackState;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public UserTrackState getUserTrackState() {
		return userTrackState;
	}

	public void setUserTrackState(UserTrackState userTrackState) {
		this.userTrackState = userTrackState;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MapPoint getMapPoint() {
		return mapPoint;
	}

	public void setMapPoint(MapPoint mapPoint) {
		this.mapPoint = mapPoint;
	}

}
