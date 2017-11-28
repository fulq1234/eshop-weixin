package com.ldgx.eshop.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
public class NewsMessage extends BaseMessage{
	
	@XmlElement(name="ArticleCount")
	private  int ArticleCount;//数量
	
	@XmlElement(name="Articles")
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
