/**
 * 
 */
package com.zhaoping.api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zhaoping.model.City;
import com.zhaoping.model.Province;
import com.zhaoping.model.Result;
import com.zhaoping.model.company.JobLabel;

/**
 * @author hongxiao.shou
 *
 */

@Controller
@RequestMapping(value = "/api/baseapi")
public class BaseApi {
	private static Logger logger = Logger.getLogger(BaseApi.class);

	@RequestMapping(value = "/province/", method = RequestMethod.GET)
	public @ResponseBody List<Province> getProvince(HttpServletRequest request,
			HttpServletResponse response) {
		List<Province> list = new ArrayList<Province>();
		Province province = new Province();
		province.setId(1);
		province.setProvinceName("上海");
		Province province1 = new Province();
		province1.setId(2);
		province1.setProvinceName("北京");
		list.add(province);
		list.add(province1);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return list;
	}

	@RequestMapping(value = "/province/{provinceid}", method = RequestMethod.GET)
	public @ResponseBody List<City> getCityByProvinceiD(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable("provinceid") int provinceid) {
		List<City> list = new ArrayList<City>();
		City city = new City();
		if (provinceid == 1) {
			city.setId(1);
			city.setCityName("北京");
			list.add(city);
		}
		if (provinceid == 2) {
			City city1 = new City();
			city1.setId(1);
			city1.setCityName("普陀区");
			City city2 = new City();
			city2.setId(2);
			city2.setCityName("静安区");
			City city3 = new City();
			city3.setId(3);
			city3.setCityName("杨浦区");
			City city4 = new City();
			city4.setId(4);
			city4.setCityName("黄浦区");
			City city5 = new City();
			city5.setId(5);
			city5.setCityName("南汇区");
			City city6 = new City();
			city6.setId(6);
			city6.setCityName("嘉定区");
			City city7 = new City();
			city7.setId(7);
			city7.setCityName("徐汇区");
			City city8 = new City();
			city8.setId(8);
			city8.setCityName("奉贤区");
			City city9 = new City();
			city9.setId(9);
			city9.setCityName("闸北区");
			City city10 = new City();
			city10.setId(10);
			city10.setCityName("卢湾区");
			City city11 = new City();
			city11.setId(11);
			city11.setCityName("长宁区");
			City city12 = new City();
			city12.setId(12);
			city12.setCityName("闵行区");
			City city13 = new City();
			city13.setId(13);
			city13.setCityName("青浦区");
			City city14 = new City();
			city14.setId(14);
			city14.setCityName("金山区");
			City city15 = new City();
			city15.setId(15);
			city15.setCityName("宝山区");
			City city16 = new City();
			city16.setId(16);
			city16.setCityName("虹口区");
			City city17 = new City();
			city17.setId(17);
			city17.setCityName("浦东新区");
			City city18 = new City();
			city18.setId(18);
			city18.setCityName("崇明岛");
			list.add(city1);
			list.add(city2);
			list.add(city3);
			list.add(city4);
			list.add(city5);
			list.add(city6);
			list.add(city7);
			list.add(city8);
			list.add(city9);
			list.add(city10);
			list.add(city11);
			list.add(city12);
			list.add(city13);
			list.add(city14);
			list.add(city15);
			list.add(city16);
			list.add(city17);
			list.add(city18);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return list;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody Result upLoadFile(@RequestParam("file") MultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result();
		result.code = -1;
		for (int i = 0; i < files.length; i++) {
			System.out.println("fileName---------->"
					+ files[i].getOriginalFilename());
			if (!files[i].isEmpty()) {
				String file = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/upload/");

				try {
					String ds = file + new Date().getTime()
							+ files[i].getOriginalFilename();
					FileOutputStream os = new FileOutputStream(ds);
					try {
						FileInputStream in = (FileInputStream) files[i]
								.getInputStream();
						int b = 0;
						while ((b = in.read()) != -1) {
							os.write(b);
						}
						os.flush();
						os.close();
						in.close();
						result.code = 1;
						result.setInfo(ds);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						result.setInfo("上传失败");
						logger.error(e);
					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					result.setInfo("上传失败");
					logger.error(e);
				}

			}

		}

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		return result;
	}

	@RequestMapping(value = "/jablabels", method = RequestMethod.GET)
	public @ResponseBody List<JobLabel> getJobLabels(
			HttpServletRequest request, HttpServletResponse response) {
		List<JobLabel> jobLabels = new ArrayList<JobLabel>();
		JobLabel j1 = new JobLabel();
		// 五险 公积金，包吃包住 ，长白班，年底双薪，年终奖金
		j1.setId(1);
		j1.setJobLabelName("五险");
		jobLabels.add(j1);
		JobLabel j2 = new JobLabel();
		j2.setId(2);
		j2.setJobLabelName("包吃包住");
		jobLabels.add(j2);
		JobLabel j3 = new JobLabel();
		j3.setId(3);
		j3.setJobLabelName("年底双薪");
		jobLabels.add(j3);
		JobLabel j4 = new JobLabel();
		j4.setId(4);
		j4.setJobLabelName("年终奖金");
		jobLabels.add(j4);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		return jobLabels;
	}
}
