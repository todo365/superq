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
import com.zhaoping.model.Deliver;
import com.zhaoping.model.Result;
import com.zhaoping.model.constant.DeliverState;

/**
 * @author hongxiao.shou
 *
 */
@Repository
public class DeliverService implements IDeliverService {

	private final String delivertablename = "deliver";
	@Resource
	CMongoQuey mongoQuey;
	@Resource
	ICuserService cuserService;
	@Resource
	IPublishJobService PublishJobService;

	/**
	 * 投递职位
	 */
	@Override
	public Result deliverResume(Deliver deliver) {
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(delivertablename);
		int id = mongoQuey.maxID();
		deliver.setId(id);
		int r = mongoQuey.insert(deliver);
		if (r >= 0) {
			result.setCode(1);
		} else {
			result.setCode(-1);
			result.setInfo("投递失败");
		}
		return result;
	}

	/**
	 * 根据用户id找到投递记录
	 */
	@Override
	public List<Deliver> getDeliverListByUserId(int userId) {
		List<Deliver> list = new ArrayList<Deliver>();
		mongoQuey = mongoQuey.SelectCollection(delivertablename);
		list = mongoQuey.where("userId", userId).select(Deliver.class);
		if (list.size() <= 0) {
			Deliver deliver = new Deliver();
			deliver.setJobType(1);
			deliver.setDeliverState(1);
			list.add(deliver);
		}
		return list;
	}

	/**
	 * 根据企业id找到投递记录报名
	 */
	@Override
	public List<Deliver> getDeliverListByCompanyId(int companyid) {
		mongoQuey = mongoQuey.SelectCollection(delivertablename);
		List<Deliver> list = mongoQuey.where("companyId", companyid).select(
				Deliver.class);
		return list;
	}

	/**
	 * 更新投递状态
	 */
	@Override
	public Result updateDeliver(int id, DeliverState state) {
		mongoQuey = mongoQuey.SelectCollection(delivertablename);
		Result result = new Result();
		int r = mongoQuey.where("id", id).update("deliverState",
				state.getValue());
		if (r >= 0) {
			result.setCode(1);
		} else {
			result.setCode(-1);
			result.setInfo("更新投递失败");
		}
		return result;
	}

	/**
	 * 用公司里面存的职位列表找到投递记录
	 * 
	 * @param ids
	 * @return
	 */
	@Override
	public List<Deliver> getDeliverListByids(List<Integer> ids) {
		mongoQuey = mongoQuey.SelectCollection(delivertablename);
		List<Deliver> list = mongoQuey.where("jobId", Condition.IN, ids)
				.select(Deliver.class);
		return list;
	}

	/**
	 * 邀请参加面试
	 * 
	 * @param id
	 * @param jobid
	 * @return
	 */
	public Result inviteUsert(int id, int jobid) {

		return new Result();
	}

}
