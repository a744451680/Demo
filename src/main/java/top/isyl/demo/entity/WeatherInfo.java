package top.isyl.demo.entity;

import lombok.Data;

@Data
public class WeatherInfo {

	private String cityId;//城市码
	private String cityName; //城市名
	private String desc; //描述
	private String updTime; //更新时间
	
	
}
