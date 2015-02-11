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
import com.zhaoping.model.bdapi.ShowLocation;
import com.zhaoping.model.cmodel.RUser;
import com.zhaoping.model.cmodel.Resume;
import com.zhaoping.model.company.JobChance;
import com.zhaoping.service.ICuserService;
import com.zhaoping.service.IPublishJobService;

/**
 * @author hongxiao.shou C端用户请求接口
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/api/user")
public class CuserApi {

	@Resource
	protected ICuserService cuserService;
	@Resource
	protected IPublishJobService publishJobService;

	@RequestMapping(value = "/login/{phone}/{pwd}", method = RequestMethod.GET)
	public @ResponseBody Result login(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("phone") String phone,
			@PathVariable("pwd") String pwd) {
		RUser cRegister = null;
		cRegister = cuserService.login(phone, pwd);

		String s = cRegister.getName();
		Result result = new Result();
		MapPoint mapPoint = new MapPoint();
		// IpAddress.getRemortIP(getIP());

		LocationAddress address = Location.getLocation(getIP(request));

		String x = "";
		String y = "";
		if (address != null && address.getContent() != null) {
			x = address.getContent().getPoint().x;
			y = address.getContent().getPoint().y;
			if (x != null && x.length() != 0) {
				mapPoint.setMapX(Double.parseDouble(x));
				mapPoint.setMapY(Double.parseDouble(y));
				result = cuserService.updateUserPoint(cRegister.getId(),
						mapPoint);
			}

		}

		if (s == null || s.isEmpty()) {
			result.code = -1;
			result.info = "用户手机号或密码输入错误";
		} else {
			result.code = 1;
			updateCookie(request, response, "phone", phone);
			updateCookie(request, response, "id", "" + cRegister.getId());
			result.info = "1" + "," + phone + "," + cRegister.getId();
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return result;
	}

	@RequestMapping(value = "/getsimpleuser/{userid}", method = RequestMethod.GET)
	public @ResponseBody RUser getsimpleUser(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("userid") int userid) {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return cuserService.getSimpleRegisterUserById(userid);

	}

	@RequestMapping(value = "/getnearjobchancelistid/{id}", method = RequestMethod.GET)
	public @ResponseBody List<JobChance> getNearDeliverListById(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") int id) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		Resume resume = cuserService.getUserResumeByid(id);
		MapPoint mapPoint = resume.getMapPoint();

		return publishJobService.getDeliverListByxy(mapPoint);
	}

	@RequestMapping(value = "/getjobbycityandtypebypage/{province}/{city}/{jobtype}/{page}/{size}", method = RequestMethod.GET)
	public @ResponseBody List<JobChance> getJobByCityAndTypeByPage(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable("province") int province,
			@PathVariable("city") int city,
			@PathVariable("jobtype") int jobtype,
			@PathVariable("page") int page, @PathVariable("size") int size) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return publishJobService.getJobByCityAndTypeByPage(province, city,
				jobtype, page, size);
	}
	
	@RequestMapping(value = "/getjobbycitycount/{province}/{city}", method = RequestMethod.GET)
	public @ResponseBody Result getJobByCitycount(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable("province") int province,
			@PathVariable("city") int city) {
		Result result =new Result();
		result.code =1;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		result.setInfo(""+publishJobService.getJobByCityCout(province, city));
		
		return result;
			 
	}

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public @ResponseBody Result insertUser(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Resume resume) {
		Result result = null;
		result = cuserService.checkTel(resume.getPhone());
		if (result.code == 1) {
			result.code = -2;
			result.info = "这个手机号已经存在";
		} else {
			resume.setBornProvinceId(resume.getBornProvince().getId());
			LocationAddress address = Location.getLocation(getIP(request));
			MapPoint mapPoint = new MapPoint();
			String x = address.getContent().getPoint().x;
			String y = address.getContent().getPoint().y;
			if (x != null && x.length() != 0) {
				mapPoint.setMapX(Double.parseDouble(x));
				mapPoint.setMapY(Double.parseDouble(y));
				resume.setMapPoint(mapPoint);
			}
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String time = formatter.format(date);
			resume.setRegDate(time);

			result = cuserService.insertResume(resume);
			if (result.code >= 0) {
				updateCookie(request, response, "phone", resume.getPhone());
				updateCookie(request, response, "id", result.getInfo());
				result.info = "1" + "," + resume.getPhone() + ","
						+ result.getInfo();
			}
		}

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		return result;
	}

	/**
	 * 更新注册信息
	 * 
	 * @param request
	 * @param response
	 * @param cRegister
	 * @return
	 */
	@RequestMapping(value = "/updateregisteruser", method = RequestMethod.POST)
	public @ResponseBody Result updateRegisterUser(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Resume resume) {
		Result result = null;
		LocationAddress address = Location.getLocation(getIP(request));
		MapPoint mapPoint = new MapPoint();
		String x = address.getContent().getPoint().x;
		String y = address.getContent().getPoint().y;
		if (x != null && x.length() != 0) {
			mapPoint.setMapX(Double.parseDouble(x));
			mapPoint.setMapY(Double.parseDouble(y));
			resume.setMapPoint(mapPoint);
		}

		result = cuserService.updateUserResume(resume);
		if (result.code >= 0) {
			updateCookie(request, response, "phone", resume.getPhone());
			updateCookie(request, response, "id", "" + "" + resume.getId());
			result.info = "1" + "," + resume.getPhone() + "," + resume.getId();
		}

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		return result;
	}

	/**
	 * 更新简历
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateresume", method = RequestMethod.POST)
	public @ResponseBody Result updateUser(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Resume user) {
		Result result = null;

		result = cuserService.updateUserResume(user);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		return result;
	}

	/**
	 * 根据用户电话可以取得用户的简历信息，后期需要注意xss
	 * 
	 * @param request
	 * @param response
	 * @param tel
	 * @return
	 */
	@RequestMapping(value = "/getresume/{tel}", method = RequestMethod.GET)
	public @ResponseBody Resume getresume(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("tel") String tel) {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return cuserService.getUserResumeByTel(tel);
	}

	@RequestMapping(value = "/getresumebyid/{id}", method = RequestMethod.GET)
	public @ResponseBody Resume getresumebyid(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("id") int id) {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		Resume resume = cuserService.getUserResumeByid(id);
		return resume;
	}

	@RequestMapping(value = "/updatemapbyid/{id}/{mapx}/{mapy}", method = RequestMethod.GET)
	public @ResponseBody Result updatemapbyid(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("id") int id,
			@PathVariable("mapx") String mapx, @PathVariable("mapy") String mapy) {
		Result result = null;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		MapPoint mapPoint = new MapPoint();
		mapPoint.setMapX(Double.parseDouble(mapx));
		mapPoint.setMapY(Double.parseDouble(mapy));
		result = cuserService.updateUserPoint(id, mapPoint);

		return result;
	}

	@RequestMapping(value = "/getnearusers/{userid}", method = RequestMethod.GET)
	public @ResponseBody List<Resume> getResumeListByxy(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable("userid") int id) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		Resume resume = cuserService.getUserResumeByid(id);

		MapPoint mapPoint = resume.getMapPoint();
		// mapPoint.setMapX(116.0);
		// mapPoint.setMapY(39.0);

		List<Resume> lists = cuserService.getResumeListByxy(mapPoint);
		return lists;
	}

	@RequestMapping(value = "/getfriend/{userid}", method = RequestMethod.GET)
	public @ResponseBody List<Resume> getfriendListByxy(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable("userid") int id) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		Resume resume = cuserService.getUserResumeByid(id);

		MapPoint mapPoint = resume.getMapPoint();
		// mapPoint.setMapX(116.0);
		// mapPoint.setMapY(39.0);

		List<Resume> lists = cuserService.getResumeListByProvince(resume
				.getBornProvince().getId(), mapPoint);
		return lists;
	}

	private String getIP(HttpServletRequest request) {
		return "118.207.87.41";
		// return IpAddress.getRemortIP(request);
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
