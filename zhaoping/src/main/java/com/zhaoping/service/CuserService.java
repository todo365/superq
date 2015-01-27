/**
 * 
 */
package com.zhaoping.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zhaoping.framework.mongodb.CMongoQuey;
import com.zhaoping.framework.mongodb.Condition;
import com.zhaoping.model.City;
import com.zhaoping.model.MapPoint;
import com.zhaoping.model.Province;
import com.zhaoping.model.Result;
import com.zhaoping.model.cmodel.RUser;
import com.zhaoping.model.cmodel.Resume;

/**
 * @author hongxiao.shou
 *
 */
@Repository
public class CuserService implements ICuserService {
	private final String usertablename = "cuser";

	@Resource
	CMongoQuey mongoQuey;

	/*
	 * 返回一个简单用户注册信息
	 */
	@Override
	public RUser getSimpleRegisterUserById(int userid) {
		List<RUser> list1 = null;
		RUser rUser = new RUser();
		rUser.setBornCity(new City());
		rUser.setBornProvince(new Province());
		// rUser.setMapPoint(new MapPoint());
		// rUser.setMapPoint(x, y);
		mongoQuey = mongoQuey.SelectCollection(usertablename);
		list1 = mongoQuey.where("id", userid).select(RUser.class);
		if (list1.size() >= 1) {
			return list1.get(0);
		}
		return rUser;
	}

	@Override
	public RUser login(String tel, String pwd) {
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(usertablename);
		List<RUser> list1 = null;
		list1 = mongoQuey.where("phone", tel).add(Condition.IN, pwd)
				.select(RUser.class);

		if (list1.size() >= 1) {
			return getSimpleRegisterUserById(list1.get(0).getId());
		} else {
			return new RUser();
		}
	}

	@Override
	public Result checkTel(String tel) {
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(usertablename);
		List<RUser> list1 = null;
		list1 = mongoQuey.where("phone", tel).select(RUser.class);

		if (list1.size() >= 1) {
			result.code = 1;
			result.info = "这个手机号已经存在";
		} else {
			result.code = -1;
		}
		return result;
	}

	/*
	 * 插入一个用户注册实体，完善个人信息需要更新
	 */
	@Override
	public Result insertRegisterUse(RUser cRegister) {
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(usertablename);
		int id = mongoQuey.maxID();
		cRegister.setId(id);
		int r = mongoQuey.insert(cRegister);
		if (r >= 0) {
			result.setCode(1);
		} else {
			result.setCode(-1);
			result.setInfo("插入用户失败");
		}
		return result;
	}

	@Override
	public Result insertResume(Resume resume) {
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(usertablename);
		int id = mongoQuey.maxID();
		resume.setId(id);
		int r = mongoQuey.insert(resume);
		if (r >= 0) {
			result.setCode(1);
			result.setInfo("" + id);
		} else {
			result.setCode(-1);
			result.setInfo("插入用户失败");
		}
		return result;
	}

	/**
	 * 更新一个用户注册信息
	 */
	@Override
	public Result updateRegisterUse(RUser cRegister) {
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(usertablename);
		int r = mongoQuey.where("id", cRegister.getId()).update(cRegister);
		if (r != -1) {
			result.setCode(1);
		} else {
			result.setCode(-1);
			result.setInfo("更新用户失败");
		}
		return result;
	}

	/**
	 * 更新一个完整用户信息，主要用来更新用户简历
	 */
	@Override
	public Result updateUserResume(Resume user) {
		Result result = new Result();

		mongoQuey = mongoQuey.SelectCollection(usertablename);
		int r = mongoQuey.where("id", user.getId()).update(user);
		if (r != -1) {
			result.setCode(1);
		} else {
			result.setCode(-1);
			result.setInfo("更新用户失败");
		}
		return result;
	}

	/**
	 * 用手机拿简历
	 */
	@Override
	public Resume getUserResumeByTel(String tel) {
		Result result = checkTel(tel);
		List<Resume> list = null;
		if (result.code == 1) {
			mongoQuey = mongoQuey.SelectCollection(usertablename);
			list = mongoQuey.where("phone", tel).select(Resume.class);
			return list.get(0);
		}
		return new Resume();
	}

	/**
	 * 用id拿简历
	 */
	@Override
	public Resume getUserResumeByid(int id) {

		List<Resume> list = null;
		Resume resume = new Resume();
		resume.setBornCity(new City());

		// resume.setMapPoint(new MapPoint());
		resume.setEducation(1);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		list2.add(2);
		resume.setJobIntention(list2);

		mongoQuey = mongoQuey.SelectCollection(usertablename);
		list = mongoQuey.where("id", id).select(Resume.class);
		if (list.size() > 0)
			return list.get(0);
		else
			return new Resume();
	}

	/**
	 * 更新用户地理位置
	 * 
	 * @param id
	 * @param mapPoint
	 * @return
	 */
	@Override
	public Result updateUserPoint(int id, MapPoint mapPoint) {
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(usertablename);
		List<Resume> resumes = mongoQuey.where("id", id).select(Resume.class);
		int r = -1;
		if (resumes.size() > 0) {
			Resume resume = resumes.get(0);
			resume.setMapPoint(mapPoint);
			r = mongoQuey.where("id", id).update(resume);
		} else {
			r = -1;
		}
		if (r != -1) {
			result.setCode(1);
		} else {
			result.setCode(-1);
			result.setInfo("更新用户地理失败");
		}
		return result;
	}

	@Override
	public List<Resume> getResumeListByxy(MapPoint mapPoint) {
		mongoQuey = mongoQuey.SelectCollection(usertablename);

		// Double[] li = new Double[] { mapPoint.getMapX(), mapPoint.getMapY()
		// };
		Double[] li = null;
		if (mapPoint.getMapX() != null)
			li = new Double[] { mapPoint.getMapX(), mapPoint.getMapY() };
		else {
			li = new Double[] { 116.420098, 39.911458 };
		}
		List<Resume> list = mongoQuey.where("mapPoint", Condition.NEAR, li)
				.select(Resume.class);
		return list;
	}

	/**
	 * 查找老乡 一个省的用户
	 */
	@Override
	public List<Resume> getResumeListByProvince(int province, MapPoint mapPoint) {
		mongoQuey = mongoQuey.SelectCollection(usertablename);
		Double[] li = null;
		if (mapPoint.getMapX() != null)
			li = new Double[] { mapPoint.getMapX(), mapPoint.getMapY() };
		else {
			li = new Double[] { 116.420098, 39.911458 };
		}
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(usertablename);
		List<Resume> resumes = mongoQuey.where("bornProvinceId", province).select(Resume.class);

		return resumes;
	}
}
