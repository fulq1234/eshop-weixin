package com.ldgx.eshop.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.ldgx.eshop.enums.MessageEnum;
import com.ldgx.eshop.model.News;
import com.ldgx.eshop.model.NewsMessage;

public class MessageUtil {
	
	@Value("${weixin.baseUrl}")
	private static String baseUrl;
	/**
	 * 输入1，回复的信息
	 * @return
	 */
	public static String firstMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("1.为倾听学生心声，了解教师教学情况，提高教学质量，11月21日，信科系召开了教学工作师生代表座谈会，征求师生对教学工作的意见建议。系主任牛丽宇、支部副书记王东明出席了会议，各团队、订单班教师代表和辅导员及学生代表共计40余人参加了座谈。座谈会由系办主任岳永红主持。");
		
		return sb.toString();
		
	}
	
	public static String secondMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("2.为保证座谈会反馈的问题和建议更具针对性、代表性，此次座谈的学生代表来自不同年级不同专业，教师代表来自各教学团队。座谈会上，师生代表踊跃发言，在总体肯定目前系部教学工作基础上，着重围绕教风学风、课堂教学等方面结合自身的体会纷献言献策，有针对性地提出许多中肯的意见和建议。老师们反映的焦点问题主要在于课堂纪律和学生出勤方面，学生们谈及较多的是老师的授课方法、课堂氛围以及教学效果，希望老师们多调动课堂气氛，丰富授课内容。计算机专业的学生还建议增加上机练习时间，多练技能。与会的老师对提出的问题和疑惑做了详细记录，并逐一给予解答。");
		
		return sb.toString();
		
	}
	
	/**
	 * 关注后回复的信息
	 * @return
	 */
	public static String subscribeMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注，请按照菜单提示进行操作:\n\n");
		sb.append("1.课程介绍");
		sb.append("2.易众科技介绍");
		sb.append("回复?退出此菜单");
		return sb.toString();
	}
	
	/**
	 * 图文消息内容
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static NewsMessage initNewsMessage(String toUserName,String fromUserName) {
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		News news = new News();
		news.setTitle("劳动关系教育学校介绍");
		news.setDescription("河北劳动关系职业学院[1]  有普通普通高等教育、成人高等教育、中专、工会干部培训、短期技能培训等多个教育层次，设置管理学、法学、工学、理学四个学科类别，30多个专业，在校生5000余人。在长期的教育实践中，积累了丰富的教学管理经验，创立了独树一帜的 “一专二能三结合”人才培养模式，即所学主体专业要“专”，掌握外语和计算机要“能”，专业、能力以及职业技能要“结合”。这一创新的教育模式改变了我国传统的追求书本知识及应试能力培养的模式。学生的综合素质和社会适应性不断提升。从中涌现出大量政府官员、成功企业家、优秀工程师和成千上万不同岗位的先进分子和技能高手。");
		
		news.setPicUrl(baseUrl + "/static/images/xhr.jpg");
		news.setUrl("http://www.hbgy.edu.cn/");//跳转路径
		
		newsList.add(news);
		
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new java.util.Date().getTime());
		newsMessage.setMsgType(MessageEnum.news.getName());//消息类型
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		return newsMessage;
	}
}
