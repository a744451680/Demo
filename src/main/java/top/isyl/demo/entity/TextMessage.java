package top.isyl.demo.entity;

import lombok.Data;

@Data
public class TextMessage {

	private String ToUserName; //开发者微信号
	private String FromUserName; //发送方帐号（一个OpenID）
	private String CreateTime; //消息创建时间 （整型）
	private String MsgType; //text
	private String Content; //文本消息内容
	private String MsgId; //消息id，64位整型

	
	
}
