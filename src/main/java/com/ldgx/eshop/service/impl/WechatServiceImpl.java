package com.ldgx.eshop.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ldgx.eshop.enums.MessageEnum;
import com.ldgx.eshop.model.ImageMessage;
import com.ldgx.eshop.model.MusicMessage;
import com.ldgx.eshop.model.NewsMessage;
import com.ldgx.eshop.model.Scan;
import com.ldgx.eshop.model.VoiceMessage;
import com.ldgx.eshop.service.IWechatService;
import com.ldgx.eshop.util.MessageUtil;

@Service
public class WechatServiceImpl implements IWechatService {

	@Value("${weixin.url}")
	private String product_ur;
	/**
	 * 处理信息
	 */
	@Override
	public Object handlerMessage(Scan scan) {
		String fromUserName = scan.getFromUserName();
		String toUserName = scan.getToUserName();
		String content = scan.getContent();
		
		//文本消息
		if(MessageEnum.text.getName().equals(scan.getMsgType())){
			if("1".equals(content)) {
				Scan nscan = new Scan(fromUserName,toUserName,MessageUtil.firstMenu());
				return nscan;
			}else if("2".equals(content)) {
				Scan nscan = new Scan(fromUserName,toUserName,MessageUtil.secondMenu());
				return nscan;
			}else if("3".equals(content)) {//图文消息
				NewsMessage newsm = MessageUtil.initNewsMessage(toUserName, fromUserName);
				return newsm;
			}else if("4".equals(content)) {//图片消息
				ImageMessage imagem = MessageUtil.initImageMessage(toUserName, fromUserName);
				return imagem;
			}else if("5".equals(content)) {//语音消息
				VoiceMessage vmessage = MessageUtil.initVoiceMessage(toUserName, fromUserName);
				return vmessage;
			}else if("6".equals(content)) {//音乐消息
				MusicMessage vmessage = MessageUtil.initMusicMessage(toUserName, fromUserName,product_ur + "/static/images/xiaobaitu.mp3");
				return vmessage;
			}else if("?".equals(content)) {
				Scan nscan = new Scan(fromUserName,toUserName,MessageUtil.subscribeMenu());
				return nscan;
			}
			Scan nscan = new Scan(fromUserName,toUserName,"您发送的消息是:" + scan.getContent());
			return nscan;
		}else if(MessageEnum.event.getName().equals(scan.getMsgType())) {//消息推送
			String eventType = scan.getEvent();
			if(MessageEnum.subscribe.getName().equals(eventType)) {//关注
				
				Scan nscan = new Scan(fromUserName,toUserName,MessageUtil.subscribeMenu());
				return nscan;
			}else if(MessageEnum.CLICK.getName().equals(eventType)) {//菜单点击事件

				Scan nscan = new Scan(fromUserName,toUserName,MessageUtil.subscribeMenu());
				return nscan;
			}else if(MessageEnum.VIEW.getName().equals(eventType)) {//
				String url = scan.getEventKey();
				Scan nscan = new Scan(fromUserName,toUserName,url);
				return nscan;
			}else if(MessageEnum.scancode_push.getName().equals(eventType)) {//扫码关注
				String key = scan.getEventKey();
				Scan nscan = new Scan(fromUserName,toUserName,key);
				return nscan;
			}
		}else if(MessageEnum.location.getName().equals(scan.getMsgType())) {//获取地理位置
			String label = scan.getLabel();
			Scan nscan = new Scan(fromUserName,toUserName,label);
			return nscan;
		}
				
		
		return null;
	}
	
	
	

}
