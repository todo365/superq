/**
 * 
 */
package com.zhaoping.service;

import java.util.List;

import com.zhaoping.model.MapPoint;
import com.zhaoping.model.Result;
import com.zhaoping.model.cmodel.RUser;
import com.zhaoping.model.cmodel.Resume;

/**
 * @author hongxiao.shou
 *
 */
public interface ICuserService {

	public abstract RUser getSimpleRegisterUserById(int userid);

	public abstract Result insertRegisterUse(RUser cRegister);

	public abstract Result updateUserResume(Resume user);

	public abstract Result updateRegisterUse(RUser cRegister);

	public abstract RUser login(String tel, String pwd);

	public abstract Result checkTel(String tel);

	public abstract Resume getUserResumeByTel(String tel);

	public abstract Resume getUserResumeByid(int id);
	
	public abstract Result updateUserPoint(int id, MapPoint mapPoint);
	
	public abstract List<Resume> getResumeListByxy(MapPoint mapPoint);
	
	public abstract Result insertResume(Resume resume);
	
	public abstract List<Resume> getResumeListByProvince(int province, MapPoint mapPoint);

}