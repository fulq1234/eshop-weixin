package com.ldgx.eshop.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)//控制默认情况下是否对字段或Javabean属性进行系列化
public class NewsMessage{

	@XmlElement(name="ToUserName")
	private String ToUserName;
	
	@XmlElement(name="FromUserName")
	private String FromUserName;
	
	@XmlElement(name="CreateTime")
	private Long CreateTime;
	
	@XmlElement(name="MsgType")
	private String MsgType;
	
	@XmlElement(name="ArticleCount")
	private  int ArticleCount;//数量
	
	@XmlElementWrapper(name="Articles")//在外面包一层
	@XmlElement(name="item")
	private List<News> Articles;//集合体	
	
	
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

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<News> getArticles() {
		return Articles;
	}

	public void setArticles(List<News> articles) {
		Articles = articles;
	}

	
}
