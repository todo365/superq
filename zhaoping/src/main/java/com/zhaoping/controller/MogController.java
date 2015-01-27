/**
 * 
 */
package com.zhaoping.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhaoping.framework.mongodb.CMongoQuey;
import com.zhaoping.framework.mongodb.Condition;
import com.zhaoping.framework.mongodb.UpdateCondition;
import com.zhaoping.model.Classes;
import com.zhaoping.model.Depatment;
import com.zhaoping.model.User;

/**
 * @author hongxiao.shou
 *
 */
//@Controller
//@RequestMapping(value = "/api")
//public class MogController {
//
//	@Resource
//	CMongoQuey mongoQuey;
//
//	@RequestMapping(value = "/c", method = RequestMethod.GET)
//	public @ResponseBody User getMong() {
//		User user = new User();
//		Classes classes = new Classes();
//		Classes classes1 = new Classes();
//		Depatment depatment = new Depatment();
//		Depatment depatment2 = new Depatment();
//		classes.setGrade("四年级");
//		classes.setLevel(4);
//		depatment.setName("一科");
//		depatment.setTechname("技术一");
//		depatment2.setName("二科");
//		depatment2.setTechname("技术二");
//		
//		classes.setDepatment(depatment);
//		classes.setDepatment(depatment2);
//		
//	
//		classes1.setGrade("五年级");
//		classes1.setLevel(5);
//		classes1.setDepatment(depatment);
//		classes1.setDepatment(depatment2);
//		
//		user.setNickname("nickname1");
//		user.setUsername("username2");
//		
//		
//		List<Classes> list1 = new ArrayList<Classes>();
//		list1.add(classes);
//		list1.add(classes1);
//		user.setClasses(list1);
//		
//		
//		User user1 = new User();
//		Classes classes3 = new Classes();
//		Classes classes11 = new Classes();
//		Depatment depatment1 = new Depatment();
//
//		classes3.setGrade("六年级");
//		classes3.setLevel(6);
//		depatment1.setName("一科");
//		depatment1.setTechname("技术一");
//		Depatment depatment21 = new Depatment();
//		depatment21.setName("二科");
//		depatment21.setTechname("技术二");
//		classes3.setDepatment(depatment1);
//		classes3.setDepatment(depatment21);
//	
//		classes11.setGrade("七年级");
//		classes11.setLevel(7);
//		classes11.setDepatment(depatment1);
//		classes11.setDepatment(depatment21);
//		user1.setNickname("nickname11");
//		user1.setUsername("username21");
//
//		List<Classes> list = new ArrayList<Classes>();
//		list.add(classes3);
//		list.add(classes11);
//		user1.setClasses(list);
//
//		mongoQuey = mongoQuey.SelectCollection("log");
//		mongoQuey.insert(user);
//		mongoQuey.insert(user1);
//		return user;
//	}
//
//	@RequestMapping(value = "/u", method = RequestMethod.GET)
//	public @ResponseBody List<User> getmos() {
//		List<Classes> list1 = new ArrayList<Classes>();
//		List<User> l1 = new ArrayList<User>();
//
//		Classes cod = new Classes();
//		cod.setGrade("二年级");
//		cod.setLevel(2);
//		Depatment depatment = new Depatment();
//		depatment.setName("一科");
//		depatment.setTechname("技术一");
//		cod.setDepatment(depatment);
//		Depatment depatment2 = new Depatment();
//		depatment2.setName("一科");
//		depatment2.setTechname("技术一");
//		cod.setDepatment(depatment2);
//
//		Classes classes = new Classes();
//		classes.setGrade("五年级");
//		classes.setLevel(5);
//		list1.add(classes);
//		User user = new User();
//		user.setClasses(list1);
//
//		mongoQuey = mongoQuey.SelectCollection("log");
//		l1 = mongoQuey.where("classes", Condition.ELEMMATCH, cod).select(
//				User.class);
//		User user2 = l1.get(0);
//		l1 = mongoQuey.where("username", "username").select(User.class);
//		User user3 = l1.get(0);
//		// mongoQuey.where("classes",Condition.ELEMMATCH,cod).update(user);
//		// mongoQuey.where("classes",Condition.ELEMMATCH,cod).update(classes);
//		return l1;
//	}
//}
