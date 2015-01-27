/**
 * 
 */
package com.zhaoping.model.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hongxiao.shou 查看已经下载的简历,就是点查手机号加入这个表，并且扣份
 *
 */
public class HistoryBrow  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public List<Integer> userList = new ArrayList<Integer>();
	
	public int companyId;
	
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public List<Integer> getUserList()
	{
		return this.userList;
	}
	
	public void setUserList(int id)
	{
		this.userList.add(id);
	}
	
	
}
