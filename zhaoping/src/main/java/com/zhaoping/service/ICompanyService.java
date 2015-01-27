/**
 * 
 */
package com.zhaoping.service;

import com.zhaoping.model.Result;
import com.zhaoping.model.company.Firm;

/**
 * @author hongxiao.shou
 *
 */
public interface ICompanyService {

	public abstract Result insertCompany(Firm firm);

	public abstract Result updateCompany(Firm firm);

	public abstract Firm getupdateCompanyById(int firmid);

	public abstract Result checkEmail(String mail);

	public abstract Result login(String mail, String pwd);

	public abstract Result updatePoint(int companyid);
}