package com.ldgx.eshop.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * 消息的基本类
 * @author Administrator
 *
 */
public class BaseMessage {

	@XmlElement(name="ToUserName")
	private String ToUserName;
	
	@XmlElement(name="FromUserName")
	private String FromUserName;
	
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
