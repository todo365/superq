/**
 *
 */
package com.zhaoping.service;

import com.zhaoping.framework.mongodb.CMongoQuey;
import com.zhaoping.framework.mongodb.Condition;
import com.zhaoping.model.MapPoint;
import com.zhaoping.model.Result;
import com.zhaoping.model.company.JobChance;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hongxiao.shou
 */
@Repository
public class PublishJobService implements IPublishJobService {
	private final String jobstablename = "jobs";

	@Resource
	CMongoQuey mongoQuey;

	/*
	 * 发布一个职位信息
	 */
	@Override
	public Result insertPublishJob(JobChance jobChance) {
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(jobstablename);
		int id = mongoQuey.maxID();
		jobChance.setId(id);
		int r = mongoQuey.insert(jobChance);
		if (r >= 0) {
			// 这里更新地理位置信息

			result.setCode(1);
		} else {
			result.setCode(-1);
			result.setInfo("发布职位失败");
		}
		return result;
	}

	/*
	 * 更新一个职位信息
	 *
	 * @see
	 * com.zhaoping.service.IPublishJob#updatePubLishJob(com.zhaoping.model.
	 * company.JobChance)
	 */
	@Override
	public Result updatePubLishJob(JobChance jobChance) {
		Result result = new Result();
		mongoQuey = mongoQuey.SelectCollection(jobstablename);
		int r = mongoQuey.where("id", jobChance.getId()).update(jobChance);
		if (r != -1) {
			result.setCode(1);
		} else {
			result.setCode(-1);
			result.setInfo("更新职位失败");
		}
		return result;
	}

	/*
	 * 获得一个职位信息
	 */
	@Override
	public JobChance getdatePubLishById(int jobid) {
		List<JobChance> list1 = null;
		JobChance jobChance = new JobChance();
		// jobChance.setCity(new City());
		// jobChance.setProvince(new Province());
		// jobChance.setJobType(1);
		// jobChance.setLowSalary(1);
		List<Integer> labels = new ArrayList<Integer>();
		labels.add(1);
		labels.add(2);
		jobChance.setLabels(labels);
		jobChance.setMapPoint(new MapPoint());
		// JobLabel jobLabel = new JobLabel();
		// jobLabel.setId(1);
		// jobLabel.setJobLabelName("包吃住");
		// jobChance.setLabels(jobLabel);

		mongoQuey = mongoQuey.SelectCollection(jobstablename);
		list1 = mongoQuey.where("id", jobid).select(JobChance.class);
		if (list1.size() >= 1) {
			return list1.get(0);
		}
		return jobChance;
	}

	/**
	 * 已经发布的职位
	 */
	@Override
	public List<JobChance> getDeliverListBycompanyId(int companyId) {
		mongoQuey = mongoQuey.SelectCollection(jobstablename);
		List<JobChance> list = mongoQuey.where("companyId", companyId).select(
				JobChance.class);
		return list;
	}

	/**
	 * 根据一组坐标拿到一组职位机会
	 */
	@Override
	public List<JobChance> getDeliverListByxy(MapPoint mapPoint) {
		mongoQuey = mongoQuey.SelectCollection(jobstablename);
		Double[] li = null;
		if (mapPoint.getMapX() != null)
			li = new Double[]{mapPoint.getMapX(), mapPoint.getMapY()};
		else {
			li = new Double[]{116.420098, 39.911458};
		}
		List<JobChance> list = mongoQuey.where("mapPoint", Condition.NEAR, li)
				.select(JobChance.class);
		return list;
	}

	@Override
	public List<JobChance> getJobByCity(int province, int city) {
		mongoQuey = mongoQuey.SelectCollection(jobstablename);

		List<JobChance> list = mongoQuey.where("province", province)
				.where("city", city).select(JobChance.class);
		return list;
	}

	@Override
	public List<JobChance> getJobByCityByPage(int province, int city, int page,
											  int size) {
		mongoQuey = mongoQuey.SelectCollection(jobstablename);

		List<JobChance> list = mongoQuey.where("province", province)
				.where("city", city).page(page, size).select(JobChance.class);
		return list;
	}

	@Override
	public List<JobChance> getJobByCityAndTypeByPage(int province, int city, int jobType, int page,
													 int size) {
		mongoQuey = mongoQuey.SelectCollection(jobstablename);

		List<JobChance> list = mongoQuey.where("province", province)
				.where("city", city).where("jobType", jobType).page(page, size).select(JobChance.class);
		return list;
	}

	@Override
	public List<JobChance> getJobByCityAndTypeandKeyByPage(int province, int city, String key, int page,
														   int size) {
		mongoQuey = mongoQuey.SelectCollection(jobstablename);

		List<JobChance> list = mongoQuey.where("province", province)
				.where("city", city).where("jobName", Condition.REGEX, "/"+key+".*/i").page(page, size).select(JobChance.class);
		return list;
	}

	@Override
	public long getJobByCityAndTypeandKeyByPageCount(int province, int city, String key) {
		mongoQuey = mongoQuey.SelectCollection(jobstablename);

		long count = mongoQuey.where("province", province)
				.where("city", city).where("jobName", Condition.REGEX, "/"+key+".*/i").Count();
		return count;
	}

	@Override
	public long getJobByCityCout(int province, int city) {
		mongoQuey = mongoQuey.SelectCollection(jobstablename);
		long count = mongoQuey.where("province", province).where("city", city)
				.Count();
		return count;
	}

	/**
	 * 根据公司ids发的职位信息找到这个公司发布的职位信息
	 *
	 * @param ids
	 * @return
	 */
	public List<JobChance> getDeliverListByids(List<Integer> ids) {
		mongoQuey = mongoQuey.SelectCollection(jobstablename);
		List<JobChance> list = mongoQuey.where("jobId", Condition.IN, ids)
				.select(JobChance.class);
		return list;
	}

}
