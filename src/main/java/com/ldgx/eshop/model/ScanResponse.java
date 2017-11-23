package com.ldgx.eshop.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")//xml文件的根元素
public class ScanResponse {
	private Integer id;
	
	private String toUserName;
	
	private String fromUserName;
	
	private Long createTime;
	
	private String msgType;
	
	private String content;
	

	public void setId(Integer id) {
		this.id = id;
	}

	//开发者微信号
	@XmlElement(name="ToUserName")
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	//发送方帐号（一个OpenID）
	@XmlElement(name="FromUserName")
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	//消息创建时间 （整型）
	@XmlElement(name="CreateTime")
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	//text
	@XmlElement(name="MsgType")
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	//文本消息内容
	@XmlElement(name="Content")
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public String getToUserName() {
		return toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public String getContent() {
		return content;
	}

	
	
}
