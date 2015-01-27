/**
 * 
 */
package com.zhaoping.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhaoping.bdMapApi.Location;
import com.zhaoping.framework.net.IpAddress;
import com.zhaoping.model.MapPoint;
import com.zhaoping.model.Result;
import com.zhaoping.model.bdapi.LocationAddress;
import com.zhaoping.model.cmodel.Resume;
import com.zhaoping.model.company.Firm;
import com.zhaoping.model.company.JobChance;
import com.zhaoping.service.ICompanyService;
import com.zhaoping.service.ICuserService;
import com.zhaoping.service.IPublishJobService;

/**
 * @author hongxiao.shou
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/api/business")
public class BuserApi {

	@Resource
	protected ICompanyService companyService;
	@Resource
	protected IPublishJobService publishJobService;

	@Resource
	protected ICuserService cuserService;

	/**
	 * 新增一个注册公司
	 * 
	 * @param request
	 * @param response
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "/registercompany", method = RequestMethod.POST)
	public @ResponseBody Result insertCompany(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Firm company) {
		Result result = null;
		result = companyService.checkEmail(company.getMail());
		if (result.code == -1) {
			result = companyService.insertCompany(company);
			if (result.code >= 0) {
				result.info ="2" + "," + company.getMail() + "," + result.getInfo();
				updateCookie(request, response, "email", company.getMail());
				updateCookie(request, response, "companyid", result.getInfo());
			}
		} else {
			result.code = -2;
			result.info = "这个邮箱已经被注册";
		}

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		return result;
	}

	@RequestMapping(value = "/login/{email}/{pwd}", method = RequestMethod.GET)
	public @ResponseBody Result login(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("email") String email,
			@PathVariable("pwd") String pwd) {
		Result result = null;
		result = companyService.checkEmail(email);
		if (result.code >= 1) {
			result = companyService.login(email, pwd);
			if (result.code >= 1) {
				result.info ="2" + "," + email + "," + result.getInfo();
				updateCookie(request, response, "email", email);
				updateCookie(request, response, "companyid", result.getInfo());
			}
		} else {
			result.info = "不存在的邮箱";
		}

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return result;
	}

	@RequestMapping(value = "/getcompany/{companyid}", method = RequestMethod.GET)
	public @ResponseBody Firm getCompany(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("companyid") int companyid) {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return companyService.getupdateCompanyById(companyid);

	}

	@RequestMapping(value = "/updatecompany", method = RequestMethod.POST)
	public @ResponseBody Result updateRegisterUser(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Firm company) {
		Result result = null;

		result = companyService.updateCompany(company);
		if (result.code >= 0) {
			updateCookie(request, response, "email", company.getMail());
			updateCookie(request, response, "companyid", ""+company.getId());
		}

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		return result;
	}

	/**
	 * 以下是发布职位
	 */

	@RequestMapping(value = "/insertjob", method = RequestMethod.POST)
	public @ResponseBody Result insertJobChance(HttpServletRequest request,
			HttpServletResponse response, @RequestBody JobChance jobChance) {
		// jobChance.setMapPoint(mapPoint);

		// 根据ip算出mappoint
		LocationAddress address = Location.getLocation(getIP(request));
		MapPoint mapPoint = new MapPoint();
		String x = address.getContent().getPoint().x;
		String y = address.getContent().getPoint().y;
		if (x != null && x.length() != 0) {
			mapPoint.setMapX(Double.parseDouble(x));
			mapPoint.setMapY(Double.parseDouble(y));
		}
		jobChance.setMapPoint(mapPoint);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String createJobTime = formatter.format(date);
		jobChance.setCreateJobTime(createJobTime);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		return publishJobService.insertPublishJob(jobChance);
	}

	/**
	 * 获得一个职位的详细信息
	 * 
	 * @param request
	 * @param response
	 * @param jobid
	 * @return
	 */
	@RequestMapping(value = "/getjobchance/{jobid}", method = RequestMethod.GET)
	public @ResponseBody JobChance getjobchance(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("jobid") int jobid) {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return publishJobService.getdatePubLishById(jobid);

	}

	/**
	 * 根据公司id拿到公司发的职位
	 * 
	 * @param request
	 * @param response
	 * @param companyid
	 * @return
	 */
	@RequestMapping(value = "/getjobchancelist/{companyid}", method = RequestMethod.GET)
	public @ResponseBody List<JobChance> getJobChancelist(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable("companyid") int companyid) {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return publishJobService.getDeliverListBycompanyId(companyid);

	}

	@RequestMapping(value = "/getnearjobs", method = RequestMethod.GET)
	public @ResponseBody List<JobChance> getResumeListByxy(
			HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		LocationAddress address = Location.getLocation(getIP(request));
		
		MapPoint mapPoint = new MapPoint();
		String x = address.getContent().getPoint().x;
		String y = address.getContent().getPoint().y;
		if (x != null && x.length() != 0) {
			mapPoint.setMapX(Double.parseDouble(x));
			mapPoint.setMapY(Double.parseDouble(y));
		}

		List<JobChance> lists = publishJobService.getDeliverListByxy(mapPoint);
		return lists;
	}

	@RequestMapping(value = "/getnearusers", method = RequestMethod.GET)
	public @ResponseBody List<Resume> getUsersListByxy(
			HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		LocationAddress address = Location.getLocation(getIP(request));
		MapPoint mapPoint = new MapPoint();
		String x = address.getContent().getPoint().x;
		String y = address.getContent().getPoint().y;
		if (x != null && x.length() != 0) {
			mapPoint.setMapX(Double.parseDouble(x));
			mapPoint.setMapY(Double.parseDouble(y));
		}

		List<Resume> lists = cuserService.getResumeListByxy(mapPoint);
		return lists;
	}

	@RequestMapping(value = "/downresume/{companyid}/{userid}", method = RequestMethod.GET)
	public @ResponseBody Result downResume(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("companyid") int companyid,
			@PathVariable("userid") int userid) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		Result result = null;
		Resume resume = null;
		result = companyService.updatePoint(companyid);
		if (result.code >= 1) {
			resume = cuserService.getUserResumeByid(userid);
			if (resume != null)
				result.info = resume.getPhone();
			else {
				result.code = -1;
				result.info = "读取用户失败";
			}
		}
		return result;
	}

	private String getIP(HttpServletRequest request ) {
		return "118.207.87.41";
		//return IpAddress.getRemortIP(request);
	}

	private void updateCookie(HttpServletRequest request,
			HttpServletResponse response, String key, String value) {
		// Cookie cookies[] = request.getCookies();
		Cookie c = null;
		c = new Cookie(key, value);
		c.setPath("/");
		c.setDomain(".xpin.com");
		c.setValue(value);
		c.setMaxAge(1000);
		response.addCookie(c); // 修改后，要更新到浏览器中
	}
}
