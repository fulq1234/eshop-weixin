package com.ldgx.eshop.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ldgx.eshop.enums.MessageEnum;


/**
 * xml和java bean，互相转换
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
public class Scan extends BaseMessage{
	
	private Integer id;
	
	//文本消息内容
	@XmlElement(name="Content")
	private String content;
	
	//消息id，64位整型
	@XmlElement(name="MsgId")
	private Integer MsgId;
	
	@XmlElement(name="Event")
	private String event;

	@XmlElement(name="EventKey")
	private String EventKey;
	
	@XmlElement(name="Label")
	private String Label;
	
	public Scan() {
		super();
	}

	public Scan(String toUserName, String fromUserName, String content) {
		super();
		super.setMsgType(MessageEnum.text.getName());
		super.setCreateTime(new Date().getTime());
		super.setToUserName(toUserName);
		super.setFromUserName(fromUserName);
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	

	
	
	
}
