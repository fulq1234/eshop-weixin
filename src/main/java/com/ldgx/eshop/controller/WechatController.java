package com.ldgx.eshop.controller;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ldgx.eshop.util.SignUtil;

@Controller
@RequestMapping("/weixin")
public class WechatController {

	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
	
	
	/**
	 * 微信连接接口
	 * @param signature:微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	 * @param timestamp:时间戳
	 * @param nonce：随机数
	 * @param echostr：随机字符串
	 * @return
	 */
	/*@ResponseBody
	@GetMapping("/verifyWX")
	//@RequestMapping(value="/connect",method= {RequestMethod.GET})
	//@RequestMapping(value="/connect")
	public String connectionWeixin(String signature,String timestamp,String nonce,String echostr){
        try {
			if(SignUtil.checkSignature(signature, timestamp, nonce)){  
			    return echostr;  
			}else {
				logger.error("微信接入失败");
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("e",e);
		}
        return "";
	}*/
	
	@ResponseBody
	@GetMapping("/verifyWX")
	public String connectionWeixin(String signature,String timestamp,String nonce,String echostr){
		if(signature == null || timestamp == null || nonce == null || echostr == null) {
			logger.error("微信接入错误","微信参数有空值");
		}
		try {
			String jm = SignUtil.checkSignature(signature, timestamp, nonce);
	        if(jm.equals(echostr)) {
	        	return echostr;
	        }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        logger.error("微信接入错误");
        return "";
		
	}
}
