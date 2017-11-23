package com.ldgx.eshop.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * xml转java bean
 * XmlAccessorType:
FIELD:    JAXB 绑定类中的每个非静态、非瞬态字段将会自动绑定到 XML，除非由 XmlTransient 注释。 
NONE:     所有字段或属性都不能绑定到 XML，除非使用一些 JAXB 注释专门对它们进行注释。 
PROPERTY: JAXB 绑定类中的每个获取方法/设置方法对将会自动绑定到 XML，除非由 XmlTransient 注释。 
PUBLIC_MEMBER:每个公共获取方法/设置方法对和每个公共字段将会自动绑定到 XML，除非由 XmlTransient 注释。
 * @author fu
 *
 */
@XmlRootElement(name="xml")//xml文件的根元素
@XmlAccessorType(XmlAccessType.FIELD)//控制默认情况下是否对字段或Javabean属性进行系列化
public class Scan {
	
	private Integer id;
	
	//开发者微信号
	@XmlElement(name="ToUserName")
	private String toUserName;
	
	//发送方帐号（一个OpenID）
	@XmlElement(name="FromUserName")
	private String fromUserName;
	
	//消息创建时间 （整型）
	@XmlElement(name="CreateTime")
	private Long createTime;
	
	//text
	@XmlElement(name="MsgType")
	private String msgType;
	
	//文本消息内容
	@XmlElement(name="Content")
	private String content;
	
	//消息id，64位整型
	@XmlElement(name="MsgId")
	private Integer MsgId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMsgId() {
		return MsgId;
	}

	public void setMsgId(Integer msgId) {
		MsgId = msgId;
	}

	@Override
	public String toString() {
		return "Scan [id=" + id + ", toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime="
				+ createTime + ", msgType=" + msgType + ", content=" + content + ", MsgId=" + MsgId + "]";
	}
	
	
}
