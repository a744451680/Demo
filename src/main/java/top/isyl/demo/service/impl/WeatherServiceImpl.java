package top.isyl.demo.service.impl;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import top.isyl.demo.entity.WeatherInfo;
import top.isyl.demo.mapper.WeatherMapper;
import top.isyl.demo.service.WeatherService;
import top.isyl.demo.util.HttpRequestUtil;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

	/**
	 * 查询当日天气
	 * code ： 城市码
	 */
	@Override
	public  String getWeather(String code) {
		log.info("查询当日天气：code={}",code);
		String s = ""; // HttpsClientUtil.httpRequest("http://tj.nineton.cn/Heart/index/all?city="+code, "GET", null);
		log.info("调用api查询天气返回json={}",s);
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
			Object day = jsi.get("day").toString().replace("周", "星期");//星期二
			String text = jsi.get("text").toString();// 晴/多云
			/*String[] split = text.split("/");

			if(split!=null && split[0].equals(split[1])){
				text = split[0];
			}else if(split!=null){
				text = text.replace("/", " 转 "); //晴 转 多云
			}*/

			if(text.contains("雨")){
				text = " 🌧️ ";
			}else if (text.contains("雪")){
				text = " ❄ ";
			}else if (text.contains("阴")){
				text = " ☁️ ";
			}else if (text.contains("风")){
				text = " 💨 ";
			}else if (text.contains("多云")){
				text = " ⛅️ ";
			}else if (text.contains("晴")){
				text = " ☀️ ";
			}
//			sb.append("【");
			sb.append(day);
//			sb.append("】");
			sb.append(text);				// 【星期一】晴转多云
			sb.append(low+"°C ~ "+high+"°C\n");									//  气温：  3°C ~ 8°C
		}


		log.info("查询当日天气出参："+sb.toString());
		return sb.toString();
	}

	/**
	 * 查询城市码
	 */
	@Override
	public String getCityCode(String cityName) {
		log.info("查询城市码：cityName={}",cityName);
		String cityCode = "";
		//redis  db
		String url = "https://toy1.weather.com.cn/search?cityname="+cityName;
		String codeResp = HttpRequestUtil.get(url);
		if(StringUtils.isNotEmpty(codeResp)) {
			//去括号
			String substring = codeResp.substring(1, codeResp.length() - 3);
			JSONArray jsonArray = JSON.parseArray(substring);
			if(CollectionUtils.isNotEmpty(jsonArray)){
				JSONObject jsonObject = jsonArray.getJSONObject(0);
				String ref = jsonObject.get("ref").toString();
				String[] split = ref.split("~");
				//城市码
				cityCode = split[0];
			}else {
				log.info("查询城市码:城市名称输入有误");
			}
		}
		log.info("查询城市码出参：{}",cityCode);
		return cityCode;
	}

}
