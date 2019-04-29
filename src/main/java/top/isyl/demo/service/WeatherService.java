package top.isyl.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.isyl.demo.entity.WeatherInfo;
import top.isyl.demo.mapper.WeatherMapper;
import top.isyl.demo.util.HttpsClientUtil;

import javax.annotation.Resource;

@Service
@Slf4j
public class WeatherService {

	@Resource
	private WeatherMapper weatherMapper;

	/**
	 * 查询当日天气
	 * code ： 城市码
	 */
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
	public String getCityCode(String cityName) {
		log.info("查询城市码：cityName={}",cityName);
		String cityCode = "";
		WeatherInfo weaterInfo = weatherMapper.selectCityCode(cityName);
		log.info("weaterInfo={}",JSON.toJSON(weaterInfo));
		if(weaterInfo!=null){
			cityCode = weaterInfo.getCityId();
		}
		log.info("查询城市码出参：{}",cityCode);
		return cityCode;
	}

}
