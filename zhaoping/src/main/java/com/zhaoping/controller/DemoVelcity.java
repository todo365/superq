/**
 * 
 */
package com.zhaoping.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhaoping.bdMapApi.Location;
import com.zhaoping.framework.mongodb.MongoQuey;
import com.zhaoping.model.Classes;
import com.zhaoping.model.User;
import com.zhaoping.model.bdapi.LocationAddress;

/**
 * @author hongxiao.shou
 *
 */

@Controller
@Scope("request")
@RequestMapping(value = "/api")
public class DemoVelcity {
	// @RequestMapping(value = "/velcityhello", method = RequestMethod.GET)
	// public ModelAndView hello(ModelMap model) {
	//
	// model.addAttribute("name", "JCG Hello World!");
	// return new ModelAndView("demovelcity");
	//
	// }

	// @RequestMapping(value = "/velcityhello", method = RequestMethod.GET)
	// public @ResponseBody User hello(HttpServletRequest request,
	// HttpServletResponse response) {
	// User user = new User("use1", "nickname");
	// response.setHeader("Access-Control-Allow-Origin", "*");
	// response.setHeader("Access-Control-Allow-Methods", "GET");
	// return user;
	// }
	//
	@RequestMapping(value = "/getu", method = RequestMethod.GET)
	public @ResponseBody List<User> list(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		List<User> users = new ArrayList<User>();
		User u1 = new User();
		u1.setNickname("123456");
		u1.setUsername("李逵");
		User u2 = new User();
		u2.setNickname("123458");
		u2.setUsername("李五");
		users.add(u1);
		users.add(u2);

		return users;
	}

	@RequestMapping(value = "/accc", method = RequestMethod.POST)
	public @ResponseBody User ausers(HttpServletRequest request,
			HttpServletResponse response, @RequestBody User us) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");

		String name = us.getNickname();
		String nString = us.getUsername();

		// response.setHeader("Access-Control-Allow-Headers", "Content-Type");

		// response.setHeader("Allow", "POST");
		//
		// System.out.println(us.getUsername());
		return us;
	}

	@RequestMapping(value = "/a1", method = RequestMethod.GET)
	public @ResponseBody User ausers(HttpServletRequest request,
			HttpServletResponse response) {

		User u1 = new User();
		u1.setNickname("123456");
		u1.setUsername("李逵");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		LocationAddress locaddr = Location.getLocation(getRemortIP(request));

		// response.setHeader("Allow", "POST");
		//
		// System.out.println(us.getUsername());
		return u1;
	}

	private String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("CLIENTIP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// System.out.println(request.getRemoteHost());
		return ip;

	}

	// @RequestMapping(value = "/user/{name}/{grade}", method =
	// RequestMethod.GET)
	// public @ResponseBody User adduser(@PathVariable("name") String name,
	// @PathVariable("grade") String grade) {
	//
	// User user = new User(name, grade);
	// return user;
	//
	// }

	// @RequestMapping(value = "/demok", method = RequestMethod.GET)
	// public @ResponseBody Classes ko() {
	// Classes classes = new Classes("一年级", "一级");
	// return classes;
	// }
	//
	// @RequestMapping(value = "/post111", method = RequestMethod.POST)
	// public @ResponseBody Classes ko11(
	// @RequestParam(value = "inputNumber1", required = true) Integer
	// inputNumber1) {
	// Classes classes = new Classes("一年级", inputNumber1.toString());
	//
	// return classes;
	// }

}
