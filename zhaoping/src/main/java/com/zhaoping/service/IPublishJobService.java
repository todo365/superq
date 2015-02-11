/**
 * 
 */
package com.zhaoping.service;

import java.util.List;

import com.zhaoping.model.MapPoint;
import com.zhaoping.model.Result;
import com.zhaoping.model.bdapi.BdMapPoint;
import com.zhaoping.model.company.JobChance;

/**
 * @author hongxiao.shou
 *
 */
public interface IPublishJobService {

	public abstract Result insertPublishJob(JobChance jobChance);

	public abstract Result updatePubLishJob(JobChance jobChance);

	public abstract JobChance getdatePubLishById(int jobid);

	public abstract List<JobChance> getDeliverListBycompanyId(int companyId);

	public abstract List<JobChance> getDeliverListByxy(MapPoint mapPoint);

	public abstract List<JobChance> getJobByCity(int province, int city);
	
	public abstract List<JobChance> getJobByCityByPage(int province, int city,int page,int size);
	public abstract long getJobByCityCout(int province, int city);
	public abstract List<JobChance> getJobByCityAndTypeByPage(int province, int city,int jobType, int page,int size);
}