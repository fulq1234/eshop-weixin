package com.ldgx.eshop.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 消息的基本类
 * @author Administrator
 *
 */
@XmlRootElement(name="BaseMessage")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseMessage {

	//接收方帐号（收到的OpenID）
	@XmlElement(name="ToUserName")
	private String ToUserName;
	
	
	//开发者微信号
	@XmlElement(name="FromUserName")
	private String FromUserName;
	
	//消息创建时间 （整型）
	@XmlElement(name="CreateTime")
	private Long CreateTime;
	
	@XmlElement(name="MsgType")
	private String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

}
