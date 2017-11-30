package com.ldgx.eshop.controller;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ldgx.eshop.model.Scan;
import com.ldgx.eshop.service.IWechatService;
import com.ldgx.eshop.util.CheckUtil;

/**
 * 微信接口
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/weixin")
public class WechatController {

	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
	
	@Autowired
	private IWechatService wechatService;
	
	
	/**
	 * 微信连接接口
	 * @param signature:微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	 * @param timestamp:时间戳
	 * @param nonce：随机数
	 * @param echostr：随机字符串
	 * @return
	 */	
	
	@ResponseBody
	@GetMapping("/verifyWX")
	public String connectionWeixin(String signature,String timestamp,String nonce,String echostr){
		if(signature == null || timestamp == null || nonce == null || echostr == null) {
			logger.error("微信接入错误","微信参数有空值");
		}
		try {
			if(CheckUtil.checkSignature(signature, timestamp, nonce)){
				return echostr;
			}
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("connection exception",e1);
		}
        logger.error("微信接入错误");
        return "";
		
	}
	
	/**
	 * 消息回复
	 * @param scan
	 * @return
	 */
	@PostMapping(value = "/verifyWX")	
	public @ResponseBody Object weixinCreate(@RequestBody Scan scan) {
	//public @ResponseBody Object weixinCreate(Scan scan) {	
		if(scan == null) {
			logger.error("接收到的字符串不能为空");
			return null;
		}
		logger.info(scan.toString());
		
		Object nScan = wechatService.handlerMessage(scan);		
		return nScan;
	}
	
	
	
	

}
