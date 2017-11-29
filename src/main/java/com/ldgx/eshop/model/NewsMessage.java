package com.ldgx.eshop.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 图文消息
 * @author Administrator
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)//控制默认情况下是否对字段或Javabean属性进行系列化
public class NewsMessage extends BaseMessage{

	
	@XmlElement(name="ArticleCount")
	private  int ArticleCount;//数量
	
	@XmlElementWrapper(name="Articles")//在外面包一层
	@XmlElement(name="item")
	private List<News> Articles;//集合体	
	
	
	
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
