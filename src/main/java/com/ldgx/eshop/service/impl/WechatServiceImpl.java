package com.ldgx.eshop.service.impl;


import org.springframework.stereotype.Service;

import com.ldgx.eshop.enums.MessageEnum;
import com.ldgx.eshop.model.NewsMessage;
import com.ldgx.eshop.model.Scan;
import com.ldgx.eshop.service.IWechatService;
import com.ldgx.eshop.util.MessageUtil;

@Service
public class WechatServiceImpl implements IWechatService {

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
			}else if("3".equals(content)) {
				NewsMessage newsm = MessageUtil.initNewsMessage(scan.getToUserName(), scan.getFromUserName());
				return newsm;
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
			}
		}
				
		
		return null;
	}
	
	
	

}
