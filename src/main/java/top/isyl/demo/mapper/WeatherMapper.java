package top.isyl.demo.mapper;

import top.isyl.demo.entity.WeatherInfo;


public interface WeatherMapper {
//	用户登录
//	UserInfo login(@Param("username") String username,@Param("password") String password);

	//随机查询一条Qh 
	String selectRandomQh();

	//查询天气码
	WeatherInfo selectCityCode(String cityName);
	
	
}
