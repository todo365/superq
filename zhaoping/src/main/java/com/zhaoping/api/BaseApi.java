/**
 * 
 */
package com.zhaoping.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhaoping.model.City;
import com.zhaoping.model.Province;

/**
 * @author hongxiao.shou
 *
 */

@Controller
@RequestMapping(value = "/api/baseapi")
public class BaseApi {

	@RequestMapping(value = "/province/", method = RequestMethod.GET)
	public @ResponseBody List<Province> getProvince(HttpServletRequest request,
			HttpServletResponse response) {
		List<Province> list = new ArrayList<Province>();
		Province province = new Province();
		province.setId(1);
		province.setProvinceName("上海");
		Province province1 = new Province();
		province1.setId(2);
		province1.setProvinceName("北京");
		list.add(province);
		list.add(province1);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return list;
	}

	@RequestMapping(value = "/province/{provinceid}", method = RequestMethod.GET)
	public @ResponseBody City getCityByProvinceiD(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("provinceid") int provinceid) {
		List<City> list = new ArrayList<City>();
		City city = new City();
		if (provinceid == 1) {
			city.setId(1);
			city.setCityName("北京");
		}
		if (provinceid == 2) {
			city.setId(2);
			city.setCityName("上海");
		}

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return city;
	}

}
