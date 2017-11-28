package com.ldgx.eshop.enums;

/**
 * 消息类型
 * @author Administrator
 *
 */
public enum MessageEnum {
	text("text"),//文本类型
	news("news"),//图文
	image("image"),//图片消息
	voice("voice"),//语音消息
	video("video"),
	link("link"),
	location("location"),
	event("event"),
	subscribe("subscribe"),
	unsubscribe("unsubscribe"),
	CLICK("CLICK"),
	VIEW("VIEW");
	private String name;

	private MessageEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
