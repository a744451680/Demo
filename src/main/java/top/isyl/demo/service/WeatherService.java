package top.isyl.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public interface WeatherService {


	/**
	 * 查询当日天气
	 * code ： 城市码
	 */
	String getWeather(String code) ;

	/**
	 * 查询城市码
	 */
	String getCityCode(String cityName);

}
