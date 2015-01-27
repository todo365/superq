/**
 * 
 */
package com.zhaoping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhaoping.model.User;

/**
 * @author hongxiao.shou
 *
 */
//@Controller
//@Scope("request")
//public class DemoLoop {
//	@RequestMapping(value = "/a/demoloop")
//	public ModelAndView ordinary(HttpServletRequest request,
//			HttpServletResponse response, ModelMap modelMap) {
//		// velocity测试
//		List<String> strlist = new ArrayList<String>();
//		for (int i = 0; i < 10; i++) {
//			strlist.add("list字符" + i);
//		}
//		modelMap.put("strlist", strlist);
//		Map<String, String> map = new HashMap<String, String>();
//		for (int i = 0; i < 10; i++) {
//			map.put("zifu" + i, "map字符" + i);
//		}
//		map.put(null, "哦");
//		modelMap.put("map", map);
//		List<User> objlist = new Stack<User>();
//		for (int i = 0; i < 10; i++) {
//			objlist.add(new User("name" + i, "nick" + i));
//		}
//		modelMap.put("uselist", objlist);
//		return new ModelAndView("demoloop", modelMap);
//
//	}
//	@RequestMapping(value = "/velcityhello1", method = RequestMethod.GET)
//	public ModelAndView hello1(ModelMap model) {
//
//		model.addAttribute("name", "JCG Hello World!");
//		return new ModelAndView("demovelcity");
//
//	}
//	// @RequestMapping(value = "/user/{name}/{surname}", method =
//	// RequestMethod.GET)
//	// public @ResponseBody User getUserJson(@PathVariable String name,
//	// @PathVariable String surname) {
//	//
//	// User user = new User("name", "surname");
//	// return user;
//	// }
//
//	@RequestMapping(value = "/apiuser", method = RequestMethod.GET)
//	public ModelAndView getUserJson(ModelMap model) {
//		 
//		User user = new User();
//		user.setNickname("nickname");
//		user.setUsername("user");
//		model.addAttribute("name", "JCG Hello World!");
//		return new ModelAndView("demovelcity");
//	}
//
//	
//	
//}
