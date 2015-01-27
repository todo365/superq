/**
 * 
 */
package com.zhaoping.service;

import java.util.List;

import com.zhaoping.model.Deliver;
import com.zhaoping.model.Result;
import com.zhaoping.model.constant.DeliverState;

/**
 * @author hongxiao.shou
 *
 */
public interface IDeliverService {
	public abstract Result deliverResume(Deliver deliverTable);

	public abstract List<Deliver> getDeliverListByUserId(int userId);

	public abstract List<Deliver> getDeliverListByCompanyId(int companyid);

	public abstract List<Deliver> getDeliverListByids(List<Integer> ids);
	
	public abstract Result updateDeliver(int id ,DeliverState state);
}