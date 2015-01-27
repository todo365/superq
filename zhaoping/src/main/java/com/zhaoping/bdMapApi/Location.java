/**
 * 
 */
package com.zhaoping.bdMapApi;

import com.zhaoping.framework.net.SyncGetRequest;
import com.zhaoping.model.bdapi.LocationAddress;
import com.zhaoping.model.bdapi.ShowLocation;

/**
 * @author hongxiao.shou
 *
 */
public class Location {

	public static LocationAddress getLocation(String ip) {
		LocationAddress location = new LocationAddress();
		SyncGetRequest getRequet = new SyncGetRequest();
		String url = "http://api.map.baidu.com/location/ip?ak=InGpuMmkXYFB4dfLrB0BZij4&coor=bd09ll&ip="
				+ ip;

		location = getRequet.doGet(location.getClass(), url, "", "", true);
		return location;
	}

	public static ShowLocation getLocationByName(String name) {
		ShowLocation location = new ShowLocation();
		SyncGetRequest getRequet = new SyncGetRequest();
		String url = "http://api.map.baidu.com/geocoder/v2/?address=" + name
				+ "&output=json&ak=InGpuMmkXYFB4dfLrB0BZij4";

		location = getRequet.doGet(location.getClass(), url, "", "", true);
		return location;
	}
}
