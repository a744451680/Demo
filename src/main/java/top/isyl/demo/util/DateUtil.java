package top.isyl.demo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间Util
 * @author Ylong
 *
 */
public class DateUtil {

	/**
	 * 时间转String  yyyy-MM-dd
	 * @author Ylong
	 */
	public static String getDateToString(Date date){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	/**
	 * 时间转String  yyyy-MM-dd hh:mm:ss
	 * @author Ylong
	 */
	public static String getDateToStringMilli(Date date){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(date);
	}
	/**
	 * 时间转String  yyyyMMdd
	 * @author Ylong
	 */
	public static String getDateToString8(Date date){
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}


	/**
	 * 字符串转LocalDateTime
	 */
	public static LocalDateTime str2LocalDateTime(String dateTime){
		DateTimeFormatter  format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime, format);
		return  localDateTime;
	}


}
