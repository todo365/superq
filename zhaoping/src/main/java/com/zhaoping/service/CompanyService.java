/**
 * 
 */
package com.zhaoping.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zhaoping.framework.mongodb.CMongoQuey;
import com.zhaoping.framework.mongodb.Condition;
import com.zhaoping.model.City;
import com.zhaoping.model.Province;
import com.zhaoping.model.Result;
import com.zhaoping.model.cmodel.RUser;
import com.zhaoping.model.company.Firm;

/**
 * @author hongxiao.shou
 *
 */
@Repository
public class CompanyService implements ICompanyService {

	private final String companytablename = "company";
	@Resource
	CMongoQuey mongoQuey;

	/*
	 * 新增一个公司
	 */
	@Override
	public Result insertCompany(Firm firm) {
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(companytablename);
		int id = mongoQuey.maxID();
		firm.setId(id);
		int r = mongoQuey.insert(firm);
		if (r >= 0) {
			result.setCode(1);
			result.setInfo("" + id);
		} else {
			result.setCode(-1);
			result.setInfo("插入企业失败");
		}
		return result;
	}

	/**
	 * 检查重复
	 */
	@Override
	public Result checkEmail(String mail) {
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(companytablename);
		List<RUser> list1 = null;
		list1 = mongoQuey.where("mail", mail).select(Firm.class);

		if (list1.size() >= 1) {
			result.code = 1;
			result.info = "这个邮箱已经存在";
		} else {
			result.code = -1;
		}
		return result;
	}

	@Override
	public Result login(String mail, String pwd) {
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(companytablename);
		List<Firm> list1 = null;
		list1 = mongoQuey.where("mail", mail).where("pwd", pwd)
				.select(Firm.class);

		if (list1.size() >= 1) {
			result.code = 1;
			result.info = "" + list1.get(0).getId();
		} else {
			result.code = -1;
			result.info = "邮箱名或密码出错";
		}
		return result;
	}

	/*
	 * 更新一个企业信息
	 */
	@Override
	public Result updateCompany(Firm firm) {
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(companytablename);
		int r = mongoQuey.where("id", firm.id).update(firm);
		if (r != -1) {
			result.setCode(1);
		} else {
			result.setCode(-1);
			result.setInfo("更新公司失败");
		}
		return result;
	}

	/*
	 * 获得一个企业信息
	 */
	@Override
	public Firm getupdateCompanyById(int firmid) {
		List<Firm> list1 = null;
		Firm firm = new Firm();
		firm.setCity(new City());
		firm.setProvince(new Province());

		mongoQuey = mongoQuey.SelectCollection(companytablename);
		list1 = mongoQuey.where("id", firmid).select(Firm.class);
		if (list1.size() >= 1) {
			return list1.get(0);
		}
		return firm;
	}

	@Override
	public Result updatePoint(int companyid) {
		List<Firm> list1 = null;
		Result result = new Result();
		result.code = -1;
		result.info = "不存在的企业";
		mongoQuey = mongoQuey.SelectCollection(companytablename);
		list1 = mongoQuey.where("id", companyid).select(Firm.class);
		if (list1.size() >= 1) {
			{
				Firm firm = list1.get(0);
				int point = firm.getPoint();
				if (point >= 10) {
					firm.setPoint(point - 10);
					int r = mongoQuey.where("id", companyid).update(firm);
					if (r >= 0) {
						result.setCode(1);
					}
				} else {
					result.setCode(-1);
					result.setInfo("积分不够");
				}
			}
		}
		return result;
	}

	// @Override
	// public Result setCompanyPoint(int id,int pi) {
	// Result result = new Result();
	// mongoQuey = mongoQuey.SelectCollection(companytablename);
	// int r = mongoQuey.where("id", id).update("",)
	// }
	// @Override
	// public Result insertJobId(int companyid,int jobid)
	// {
	// List<Integer> jobs= new ArrayList<Integer>();
	// Result result = new Result();
	// // jobs.add(jobid);
	// // mongoQuey = mongoQuey.SelectCollection(companytablename);
	// // int r =mongoQuey.where("id", companyid).update(UpdateCondition.PUSH,
	// jobs.toArray());
	// // if (r != -1) {
	// // result.setCode(1);
	// // } else {
	// // result.setCode(-1);
	// // result.setInfo("在公司表里发布职位信息失败");
	// // }
	// return result;
	// }

}
