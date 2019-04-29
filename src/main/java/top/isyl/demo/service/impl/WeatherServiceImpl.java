package top.isyl.demo.service.impl;/*package com.isyl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.isyl.bean.WeatherInfo;
import com.isyl.mapper.WeatherMapper;
import com.isyl.service.WeatherService;
import com.isyl.utils.HttpsClientUtil;

@Service
public class WeatherServiceImpl implements WeatherService{

	@Resource
	private WeatherMapper weatherMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

	*//**
	 * 查询当日天气
	 * code ： 城市码
	 *//*
	@Override
	public  String getWeather(String code) {
		logger.info("查询当日天气：code={}",code);
		String s = HttpsClientUtil.httpRequest("http://tj.nineton.cn/Heart/index/all?city="+code, "GET", null);
		logger.info("调用api查询天气返回json={}",s);
		JSONObject obj = JSON.parseObject(s);
		Object status = obj.get("status");
		
		if(  !"OK".equals(status.toString())){
			System.out.println("error");
			return "error";
		}
		JSONArray weather = obj.getJSONArray("weather");
		JSONObject data = weather.getJSONObject(0);
		Object city_name = data.get("city_name"); //  地址
		JSONArray jsonArray = data.getJSONArray("future");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 7; i++) {
			JSONObject jsi = jsonArray.getJSONObject(i);//今天

			Object date = jsi.get("date");//2018-04-03
			String dateMd = date.toString().substring(5);//04-03
			Object high = jsi.get("high");//13
			Object low = jsi.get("low");//4
			Object wind = jsi.get("wind");//风力2级
//			Object day = jsi.get("day");//星期二
			String text = jsi.get("text").toString();
			String[] split = text.split("/"); // 晴
			
			if(split!=null && split[0].equals(split[1])){
				text = split[0];
			}else if(split!=null){
				text = text.replace("/", " 转 "); //晴 转 多云
			}
			

			sb.append("【");
			sb.append(dateMd);
			sb.append("】");
			sb.append(text);
			sb.append("\n                 气温： ");					// 【03-03】晴转多云
			sb.append(low+"°C ~ "+high+"°C\n");		//  气温：  3°C ~ 8°C 
		}
		
		
		logger.info("查询当日天气出参："+sb.toString());
		return sb.toString();
	}

	*//**
	 * 查询城市码
	 *//*
	@Override
	public String getCityCode(String cityName) {
		logger.info("查询城市码：cityName={}",cityName);
		String cityCode = "";
		WeatherInfo weaterInfo = weatherMapper.selectCityCode(cityName);
		logger.info("weaterInfo={}",JSON.toJSON(weaterInfo));
		if(weaterInfo!=null){
			cityCode = weaterInfo.getCityId();
		}
		logger.info("查询城市码出参：{}",cityCode);
		return cityCode;
	}
	
}
*/