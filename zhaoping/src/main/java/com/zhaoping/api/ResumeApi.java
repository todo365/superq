/**
 * 
 */
package com.zhaoping.api;

import com.zhaoping.model.Deliver;
import com.zhaoping.model.Result;
import com.zhaoping.model.cmodel.RUser;
import com.zhaoping.model.cmodel.Resume;
import com.zhaoping.model.company.JobChance;
import com.zhaoping.model.constant.DeliverState;
import com.zhaoping.service.ICuserService;
import com.zhaoping.service.IDeliverService;
import com.zhaoping.service.IPublishJobService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author hongxiao.shou
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/api/resume")
public class ResumeApi {
	/**
	 * 还有一个下载简历
	 */
	@Resource
	protected ICuserService cuserService;
	@Resource
	IPublishJobService PublishJobService;
	@Resource
	protected IDeliverService deliverService;

	/**
	 * 投递职位
	 * 
	 * @param request
	 * @param response
	 * @param userid
	 * @param jobId
	 * @return
	 */
	@RequestMapping(value = "/deliver/{phone}/{jobId}", method = RequestMethod.GET)
	public @ResponseBody Result deliverResume(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("phone") String phone,
			@PathVariable("jobId") int jobId) {
		Result result = null;
		Deliver deliver = new Deliver();
		Resume resume = cuserService.getUserResumeByTel(phone);
		RUser rUser = cuserService.getSimpleRegisterUserById(resume.getId());
		JobChance jobchance = PublishJobService.getdatePubLishById(jobId);
		int jobtype = jobchance.getJobType();

		String userName = rUser.getName();
		String jobName = jobchance.getJobName();
		String tel = rUser.getPhone();
		int companyid = jobchance.getCompanyId();
		boolean sex = rUser.isSex();

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String time = formatter.format(date);

		deliver.setSex(sex);
		deliver.setJobType(jobtype);
		deliver.setJobName(jobName);
		deliver.setUserName(userName);
		deliver.setUserTel(tel);
		deliver.setCompanyId(companyid);
		deliver.setSendDate(time);
		deliver.setDeliverState(1);
		deliver.setUserId(resume.getId());
		deliver.setJobId(jobId);

		result= deliverService.deliverResume(deliver);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");

		return result;
	}

	/**
	 * 根据用户id找到报名的值位
	 * 
	 * @param request
	 * @param response
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/deliver/{phone}", method = RequestMethod.GET)
	public @ResponseBody List<Deliver> getDeliverList(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable("phone") String phone) {
		Resume resume = cuserService.getUserResumeByTel(phone);
		List<Deliver> list = deliverService.getDeliverListByUserId(resume
				.getId());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return list;
	}

	@RequestMapping(value = "/getdeliverlistbycompanyid/{companyid}", method = RequestMethod.GET)
	public @ResponseBody List<Deliver> getDeliverListByCompanyId(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable("companyid") int companyid) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return deliverService.getDeliverListByCompanyId(companyid);
	}

	@RequestMapping(value = "/interview/{id}", method = RequestMethod.GET)
	public @ResponseBody Result interview(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("id") int id) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return deliverService.updateDeliver(id, DeliverState.Accept);
	}

	@RequestMapping(value = "/refuse/{id}", method = RequestMethod.GET)
	public @ResponseBody Result refuse(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("id") int id) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return deliverService.updateDeliver(id, DeliverState.Refuse);
	}

}
